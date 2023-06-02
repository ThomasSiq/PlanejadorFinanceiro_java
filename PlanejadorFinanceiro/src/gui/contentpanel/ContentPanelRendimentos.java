package gui.contentpanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JTable;

import gui.MainWindow;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class ContentPanelRendimentos extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ContentPanelRendimentos() {
		
		table = new JTable();

		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Categoria", "Rendimento", "Mensal [R$]", "Ocasional [R$]", "Total Anual [R$]"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Double.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(104);
		table.getColumnModel().getColumn(1).setPreferredWidth(159);
		table.getColumnModel().getColumn(2).setPreferredWidth(112);
		table.getColumnModel().getColumn(3).setPreferredWidth(127);
		table.getColumnModel().getColumn(4).setPreferredWidth(143);
		setLayout(new BorderLayout(0, 0));
		

		add(new JScrollPane(table));
		
	}
	
	public void redme(Rectangle rec) {
		setBounds(rec);
	}

}
