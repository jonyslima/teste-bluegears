package com.jonylima.testebluegears.controller;

import java.sql.Connection;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.jonylima.testebluegears.helper.Ordenacao;
import com.jonylima.testebluegears.model.dao.ConnectionFactory;
import com.jonylima.testebluegears.model.dao.ItemDao;
import com.jonylima.testebluegears.model.vo.Item;
import com.jonylima.testebluegears.view.FrameItem;
import com.jonylima.testebluegears.view.ItensTableModel;

public class ItemController implements TableModelListener {
	private FrameItem frameItem;

	public ItemController(FrameItem frameItem) {
		this.frameItem = frameItem;
		this.frameItem.getTabelaItens().getModel().addTableModelListener(this);
		listarItens();
	}

	@Override
	public void tableChanged(TableModelEvent e) {

		// obtem o item modificado na tabela
		Item item = ((ItensTableModel) e.getSource()).getItemRow(e.getFirstRow());
		try (Connection connection = ConnectionFactory.getConnection()) {
			ItemDao itemDao = new ItemDao(connection);
			itemDao.atualizar(item);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	private void listarItens() {
		List<Item> itens = frameItem.getItens();

		try (Connection connection = ConnectionFactory.getConnection()) {
			ItemDao itemDao = new ItemDao(connection);

			// busca os itens em http://bpixel.com.br/teste/itens.json e
			// adiciona ao banco
			itemDao.salvar(itemDao.buscaItensExternos());

			itens.addAll(itemDao.buscarTodos());

			if (!itens.isEmpty()) {
				Ordenacao ordenacao = new Ordenacao(itens);
				ordenacao.heapSort();
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

}
