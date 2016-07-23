package com.jonylima.testebluegears.view;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import com.jonylima.testebluegears.model.vo.Item;

public class ItensTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private List<Item> itens;

	public static final String nomeColunas[] = { "SELECIONADO", "FOTO", "NOME", "DESCRIÇÃO", "DATA SELEÇÃO" };
	private static final Class<?> tipoColunas[] = { Boolean.class, ImageIcon.class, String.class, String.class,
			String.class };

	public ItensTableModel(List<Item> itens) {
		this.itens = itens;
	}

	public Item getItemRow(int rowIndex) {
		return itens.get(rowIndex);
	}

	@Override
	public int getRowCount() {
		return itens.size();
	}

	@Override
	public int getColumnCount() {
		return nomeColunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object retorno = null;
		Item item = itens.get(rowIndex);
		switch (columnIndex) {
		case 0:
			retorno = item.isSelecionado();
			break;
		case 1:
			retorno = item.getImage();
			break;
		case 2:
			retorno = item.getNome();
			break;
		case 3:
			retorno = item.getDescricao();
			break;
		case 4:
			retorno = item.isSelecionado()?item.getDtSelecaoFormatada():"";
			break;

		}

		return retorno;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		boolean selecionado = (Boolean) aValue;
		itens.get(rowIndex).setSelecionado(selecionado);
		fireTableRowsUpdated(rowIndex, rowIndex);
	}

	@Override
	public String getColumnName(int column) {
		return nomeColunas[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return tipoColunas[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		if (columnIndex > 0) {// só poderá ser editado a coluna "SELECIONADO"
			return false;
		} else {
			return true;
		}
	}
	
	
}
