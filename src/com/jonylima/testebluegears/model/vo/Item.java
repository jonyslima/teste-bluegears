package com.jonylima.testebluegears.model.vo;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.jonylima.testebluegears.helper.ImagemUtils;
import com.jonylima.testebluegears.view.ItensTableModel;

public class Item implements Comparable<Item> {
	// guarda imagens baixadas para serem reutilizadas
	private static Map<String, Icon> cacheImagens = new HashMap<>();

	private int id;
	private String foto;
	private String nome;
	private String descricao;
	private boolean selecionado;
	private Calendar dtSelecao;
	private Icon icon;

	public Item(String nome, String foto, String descricao) {
		this.nome = nome;
		this.foto = foto;
		this.descricao = descricao;
		this.selecionado = false;
		buscaFoto();
	}

	public Item(int id, String foto, String nome, String descricao, boolean selecionado, Calendar dtSelecao) {
		this(nome, foto, descricao);
		this.id = id;
		this.selecionado = selecionado;
		this.dtSelecao = dtSelecao;
	}

	public int getId() {
		return id;
	}

	public String getFoto() {
		return foto;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;

		if (selecionado) {// quando selecionado atualiza a data de selecao
			this.dtSelecao = Calendar.getInstance();
		}
	}

	public Calendar getDtSelecao() {
		return dtSelecao;
	}

	public void buscaFoto() {
		try {
			if (cacheImagens.containsKey(foto)) {// verifica se a imagem foi
													// baixada anteriormente
				icon = cacheImagens.get(foto);
			} else {
				URL url = new URL(foto);
				icon = ImagemUtils.processaImagem(url, 100, 100);

			}
		} catch (Exception e) {
			icon = new ImageIcon(ItensTableModel.class.getResource("nao_disponivel.jpg"));
		}
		cacheImagens.put(foto, icon);

	}

	public Icon getImage() {
		return icon;
	}

	public String getDtSelecaoFormatada() {
		String dataFormatada = "";
		if (dtSelecao != null) {
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date data = dtSelecao.getTime();
			dataFormatada = fmt.format(data);
		}

		return dataFormatada;

	}

	@Override
	public int compareTo(Item o) {
		return nome.compareToIgnoreCase(o.nome);
	}

}
