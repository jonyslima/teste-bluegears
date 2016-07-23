package com.jonylima.testebluegears.model.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.jonylima.testebluegears.model.vo.Item;

public class ItemDao {
	private Connection connection;

	public ItemDao(Connection connection) {
		this.connection = connection;
	}

	public void salvar(List<Item> itens) throws Exception {
		String sql = "INSERT INTO itens (foto,nome,descricao,selecionado,dt_selecao) VALUES (?,?,?,?,?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			for (Item item : itens) {
				pstm.setString(1, item.getFoto());
				pstm.setString(2, item.getNome());
				pstm.setString(3, item.getDescricao());
				pstm.setBoolean(4, item.isSelecionado());

				if (item.getDtSelecao() == null) {
					pstm.setDate(5, null);
				} else {
					pstm.setDate(5, new Date(item.getDtSelecao().getTimeInMillis()));
				}
				pstm.executeUpdate();
				pstm.clearParameters();
			}

		} catch (Exception e) {
			throw new Exception("Erro ao inserir no banco de dados");
		}

	}

	public void atualizar(Item item) throws Exception {
		String sql = "UPDATE itens SET foto=?,nome=?,descricao=?,selecionado=?,dt_selecao=? where id=?";

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setString(1, item.getFoto());
			pstm.setString(2, item.getNome());
			pstm.setString(3, item.getDescricao());
			pstm.setBoolean(4, item.isSelecionado());

			if (item.getDtSelecao() == null) {
				pstm.setDate(5, null);
			} else {
				pstm.setDate(5, new Date(item.getDtSelecao().getTimeInMillis()));
			}

			pstm.setInt(6, item.getId());
			pstm.executeUpdate();
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar item");
		}
	}

	public Item buscarPorId(int id) throws Exception {
		String sql = "SELECT * FROM itens where id=?";
		Item item = null;

		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				item = criaItem(rs);
			}
		} catch (Exception e) {
			throw new Exception("Erro ao buscar item");
		}
		return item;
	}

	public List<Item> buscarTodos() throws Exception {
		String sql = "SELECT * FROM itens";
		List<Item> itens = null;

		try (Statement stm = connection.createStatement()) {
			ResultSet rs = stm.executeQuery(sql);

			itens = new ArrayList<>();
			while (rs.next()) {
				itens.add(criaItem(rs));
			}

		} catch (Exception e) {
			throw new Exception("Erro ao buscar itens");
		}

		return itens;

	}

	private static Item criaItem(ResultSet rs) throws SQLException {

		Calendar dtSelecao = null;
		Date date = rs.getDate("dt_selecao");

		if (date != null) {
			dtSelecao = Calendar.getInstance();
			dtSelecao.setTime(date);
		}

		return new Item(rs.getInt("id"), rs.getString("foto"), rs.getString("nome"), rs.getString("descricao"),
				rs.getBoolean("selecionado"), dtSelecao);
	}

	public List<Item> buscaItensExternos() throws Exception {
		URL url = null;
		List<Item> itens = null;

		try {
			url = new URL("http://bpixel.com.br/teste/itens.json");

			URLConnection urlConnection = url.openConnection();
			try (InputStream in = urlConnection.getInputStream()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(in,StandardCharsets.UTF_8));

				StringBuffer strJson = new StringBuffer();
				while (br.ready()) {
					strJson.append(br.readLine());
				}
				itens = obtemItensJson(strJson.toString());
			}
		} catch (MalformedURLException e) {
			throw new Exception("Houve um erro interno no sistema,não foi possível buscar o recurso externo");
		} catch (IOException e) {
			throw new Exception("Não foi possivel obter dados de http://bpixel.com.br/teste/itens.json");
		}

		return itens;
	}

	@SuppressWarnings("unchecked")
	private List<Item> obtemItensJson(String strJson) {
		Gson gson = new Gson();
		Map<Object, Object> map = gson.fromJson(strJson.toString(), Map.class);
		List<Map<String, String>> itensMap = (List<Map<String, String>>) map.get("itens");
		List<Item> itens = new ArrayList<>();

		for (Map<String, String> mapItem : itensMap) {
			itens.add(new Item(mapItem.get("nome"), mapItem.get("foto"), mapItem.get("descricao")));
		}

		return itens;
	}

}
