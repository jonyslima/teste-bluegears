package com.jonylima.testebluegears.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CelulaRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component result = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if ((Boolean) table.getModel().getValueAt(row, 0)) {
			result.setBackground(Color.CYAN);
			result.setFont(new Font("arial", Font.BOLD, 12));
		} else {
			result.setBackground(Color.WHITE);
			result.setFont(new Font("arial", Font.PLAIN, 12));
		}
		return result;
	}
}
