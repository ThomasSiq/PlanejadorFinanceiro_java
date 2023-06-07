package gui.contentpanel;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.GetDataDao;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;

public class ContentPanelDespesas extends JPanel {
	private JPanel internPanel;
	private JScrollPane scrollPane_1;

	/**
	 * Create the panel.
	 */
	public ContentPanelDespesas() {

		internPanel = new JPanel();

		Object[][] tuplas = tabela("despesas");

		Object[][] valores = valoresTuplas(tuplas);

		Object[][] valor = new Object[valores.length][valores[0].length];
		for (int i = 0; i < valores.length; i++) {
			System.arraycopy(valores[i], 0, valor[i], 0, valores[i].length);
		}

		for (int j = 0; j < 5; j++) {
			JTable table = new JTable();
			table.setModel(new DefaultTableModel(valor,
					new String[] { "Categoria", "Despesa", "Mensal [R$]", "Ocasional [R$]", "Total Anual [R$]" }) {
				Class[] columnTypes = new Class[] { String.class, String.class, Double.class, Double.class,
						Double.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			table.getColumnModel().getColumn(0).setPreferredWidth(104);
			table.getColumnModel().getColumn(1).setPreferredWidth(159);
			table.getColumnModel().getColumn(2).setPreferredWidth(112);
			table.getColumnModel().getColumn(3).setPreferredWidth(127);
			table.getColumnModel().getColumn(4).setPreferredWidth(143);
			internPanel.setLayout(new BoxLayout(internPanel, BoxLayout.Y_AXIS));
			
			JScrollPane scrollPane = new JScrollPane(table);
			
			System.out.println(valor.length);
			scrollPane.setPreferredSize(new Dimension(100, valor.length*18));

			
			internPanel.add(scrollPane);
		}

		setLayout(null);
		internPanel.setBounds(0, 0, 30, 30);
		JScrollPane scrollPaneEx = new JScrollPane(internPanel);
		scrollPaneEx.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		scrollPaneEx.setBounds(0, 0, 600, 300);
		
		
		
		add(scrollPaneEx);

	}

	public void redme(Rectangle rec) {
		setBounds(rec);
	}

	private Object[][] tabela(String nome) {
		ArrayList<ArrayList<Object>> resultado = new ArrayList();
		resultado = GetDataDao.getTable(nome);

		Object tabelaRetorno[][] = new Object[resultado.size()][];
		for (int i = 0; i < resultado.size(); i++) {
			ArrayList<Object> row = resultado.get(i);
			tabelaRetorno[i] = row.toArray(new Object[row.size()]);
		}
		return tabelaRetorno;
	}

	private Object[][] valoresTuplas(Object[][] objeto) {
		if (objeto.length == 0)
			return objeto;

		Object[][] retorno = new Object[objeto.length][objeto[0].length - 2];
		for (int i = 0; i < objeto.length; i++) {
			retorno[i] = Arrays.copyOfRange(objeto[i], 2, objeto[0].length);
		}
		return retorno;
	}

}
