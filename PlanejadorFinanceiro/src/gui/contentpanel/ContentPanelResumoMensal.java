package gui.contentpanel;

import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ContentPanelResumoMensal extends JPanel {
	private JTable table;
	/**
	 * Create the panel.
	 */
	public ContentPanelResumoMensal() {
		
		table = new JTable();
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
			},
			new String[] {
				"Descrição", "Mensal [R$]"
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

		add(new JScrollPane(table));

	}
	
	public void redme(Rectangle rec) {
		setBounds(rec);
	}

}
