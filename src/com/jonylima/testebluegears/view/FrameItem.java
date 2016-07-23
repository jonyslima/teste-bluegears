package com.jonylima.testebluegears.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jonylima.testebluegears.model.vo.Item;

public class FrameItem extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tabelaItens;
	private ItensTableModel itensTableModel;
	private List<Item> itens;

	public FrameItem() {
		super("TESTE BLUEGEARS");
		setLocationRelativeTo(null);
		setSize(825, 700);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		itens = new ArrayList<>();
		itensTableModel = new ItensTableModel(itens);
		tabelaItens = new JTable(itensTableModel);
		tabelaItens.setRowHeight(100);
		tabelaItens.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabelaItens.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabelaItens.getColumnModel().getColumn(1).setPreferredWidth(105);
		tabelaItens.getColumnModel().getColumn(2).setPreferredWidth(210);
		tabelaItens.getColumnModel().getColumn(3).setPreferredWidth(290);
		tabelaItens.getColumnModel().getColumn(4).setPreferredWidth(160);

		CelulaRenderer renderer = new CelulaRenderer();
		for (int c = 2; c < tabelaItens.getColumnCount(); c++) {
			if (c != 1)
				tabelaItens.setDefaultRenderer(tabelaItens.getColumnClass(c), renderer);
		}
		JScrollPane scrooll = new JScrollPane(tabelaItens);

		add(scrooll);

	}

	public JTable getTabelaItens() {
		return tabelaItens;
	}

	public List<Item> getItens() {
		return itens;
	}

}
