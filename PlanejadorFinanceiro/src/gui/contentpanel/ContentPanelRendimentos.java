package gui.contentpanel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Rectangle;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;

import gui.MainWindow;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

import dao.GetTabelasDao;

public class ContentPanelRendimentos extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ContentPanelRendimentos() {
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		Object[][] valores = tabela("rendimentos");

		
		table.setModel(new DefaultTableModel(
			valores,
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
	
	private Object[][] tabela(String nome){
		ArrayList<ArrayList<Object>> resultado = new ArrayList();
		resultado = GetTabelasDao.getTable(nome);
		
		Object tabelaRetorno[][]  = new Object[resultado.size()][];
		for (int i = 0; i < resultado.size(); i++) {
		    ArrayList<Object> row = resultado.get(i);
		    tabelaRetorno[i] = row.toArray(new Object[row.size()]);
		}
		return tabelaRetorno;
	}

}
