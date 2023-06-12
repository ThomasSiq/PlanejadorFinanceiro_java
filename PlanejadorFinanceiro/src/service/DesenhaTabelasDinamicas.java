package service;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import enumdata.MesesEnum;
public class DesenhaTabelasDinamicas {
	static JTable table_1;

	public static void desenhaTabelasAnos(Object[][] valores, Object[][] valor, ArrayList<Integer> anosPeriodo,
			Object[][] tabela, JTable table_11, JPanel internPanel,ArrayList<JTable> tablesList) {

		table_1 = table_11;
		valores = valoresTuplas(tabela);

		valor = new Object[valores.length][valores[0].length];
		for (int i = 0; i < valores.length; i++) {
			System.arraycopy(valores[i], 0, valor[i], 0, valores[i].length);
		}

		for (int j = 0; j < anosPeriodo.size(); j++) {
			ArrayList<Object[]> tabelaLocal = new ArrayList();

			Object[][] valoresTotalOb = new Object[1][4];
			double[] valoresTotal = new double[3];
			valoresTotalOb[0][0] = "Total ano";

			for (double val : valoresTotal) {
 				val = 0;
			}

			for (int i = 0; i < valores.length; i++) {

				if ((int) tabela[i][1] == (int) anosPeriodo.get(j)) {
					tabelaLocal.add(valores[i]);
				}
			}

			Object[][] tabelaTabela = new Object[tabelaLocal.size()][5];

			for (Object[] val : tabelaLocal) {
				tabelaTabela[tabelaLocal.indexOf(val)] = val;
				valoresTotal[0] += (double) val[2];
				valoresTotal[1] += (double) val[3];
				valoresTotal[2] += (double) val[4];
			}
			valoresTotalOb[0][1] = valoresTotal[0];
			valoresTotalOb[0][2] = valoresTotal[1];
			valoresTotalOb[0][3] = valoresTotal[2];

			JTable table = new JTable();

			JTextArea txt = new JTextArea();
			txt.setBackground(SystemColor.control);
			txt.setWrapStyleWord(true);
			txt.setLineWrap(true);
			txt.setFont(new Font("Monospaced", Font.PLAIN, 30));
			txt.setText(anosPeriodo.get(j).toString());
			txt.setBackground(Color.white);
			txt.setBorder(new LineBorder(Color.GRAY));
			table.setFont(new Font("Monospaced", Font.PLAIN, 18));

			table.setTableHeader(null);
			table.setModel(new DefaultTableModel(tabelaTabela,
					new String[] { "Categoria", "Despesa", "Mensal [R$]", "Ocasional [R$]", "Total Anual [R$]" }) {
				Class[] columnTypes = new Class[] { String.class, String.class, Double.class, Double.class,
						Double.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			// internPanel.setLayout(new BoxLayout(internPanel, BoxLayout.X_AXIS));

			JTable table_2 = new JTable();
			table_2.setFont(new Font("Monospaced", Font.BOLD, 19));
			table_2.setTableHeader(null);
			table_2.setModel(new DefaultTableModel(valoresTotalOb,
					new String[] { "Categoria", "Mensal [R$]", "Ocasional [R$]", "Total Anual [R$]" }) {
				Class[] columnTypes = new Class[] { String.class, Double.class, Double.class, Double.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});


			table_2.addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					JTable table = (JTable) e.getSource();
					table.getColumnModel().getColumn(0)
							.setPreferredWidth(table_1.getColumnModel().getColumn(0).getWidth()
									+ table_1.getColumnModel().getColumn(1).getWidth());
					table.getColumnModel().getColumn(1)
							.setPreferredWidth(table_1.getColumnModel().getColumn(2).getWidth());
					table.getColumnModel().getColumn(2)
							.setPreferredWidth(table_1.getColumnModel().getColumn(3).getWidth());
					table.getColumnModel().getColumn(3)
							.setPreferredWidth(table_1.getColumnModel().getColumn(4).getWidth());
				}
			});
			table.addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					JTable table = (JTable) e.getSource();
					table.getColumnModel().getColumn(0)
							.setPreferredWidth(table_1.getColumnModel().getColumn(0).getWidth());
					table.getColumnModel().getColumn(1)
							.setPreferredWidth(table_1.getColumnModel().getColumn(1).getWidth());
					table.getColumnModel().getColumn(2)
							.setPreferredWidth(table_1.getColumnModel().getColumn(2).getWidth());
					table.getColumnModel().getColumn(3)
							.setPreferredWidth(table_1.getColumnModel().getColumn(3).getWidth());
					table.getColumnModel().getColumn(4)
							.setPreferredWidth(table_1.getColumnModel().getColumn(4).getWidth());
				}
			});

			JPanel interno = new JPanel();
			interno.setLayout(new BoxLayout(interno, BoxLayout.Y_AXIS));
			interno.setBorder(new EmptyBorder(20, 0, 0, 0));
			table.setFillsViewportHeight(true);
			table.setRowHeight(30);
			table_2.setRowHeight(30);
			interno.setMaximumSize(new Dimension(4000, tabelaTabela.length * 30 + 96));
			interno.setPreferredSize(new Dimension(400, tabelaTabela.length * 30 + 96));
			interno.add(txt);
			interno.add(table);
			interno.add(table_2);

			internPanel.add(interno);

			tablesList.add(table_2);
			tablesList.add(table);
		}
	}
	
	public static void desenhaTabelasMeses(Object[][] valores, Object[][] valor, ArrayList<Integer[]> mesesPeriodo,
			Object[][] tabela, JTable table_11, JPanel internPanel,ArrayList<JTable> tablesList) {

		table_1 = table_11;
		valores = valoresTuplas(tabela);

		valor = new Object[valores.length][valores[0].length];
		for (int i = 0; i < valores.length; i++) {
			System.arraycopy(valores[i], 0, valor[i], 0, valores[i].length);
		}

		for (int j = 0; j < mesesPeriodo.size(); j++) {
			ArrayList<Object[]> tabelaLocal = new ArrayList();

			Object[][] valoresTotalOb = new Object[1][4];
			double[] valoresTotal = new double[3];
			valoresTotalOb[0][0] = "Total mês";

			for (double val : valoresTotal) {
 				val = 0;
			}
			for (int i = 0; i < valores.length; i++) {
				if ((int) tabela[i][1] == (int) mesesPeriodo.get(j)[0]) {
					if((int)tabela[i][2] == 0) {
						tabela[i][2] = 1;
					}
					if((int)tabela[i][2]<= (int)mesesPeriodo.get(j)[1]) {
						if (((int) tabela[i][2] + (int) tabela[i][3] - 1) >= (int) mesesPeriodo.get(j)[1]) {
							tabelaLocal.add(valores[i]);
							
						}
					}	
				}
			}
			if(tabelaLocal.isEmpty()) continue;
			Object[][] tabelaTabela = new Object[tabelaLocal.size()][5];

			for (Object[] val : tabelaLocal) {
				val[4] = (double) val[3] + (double)val[2];
				tabelaTabela[tabelaLocal.indexOf(val)] = val;
				
				valoresTotal[0] += (double) val[2];
				valoresTotal[1] += (double) val[3];
				valoresTotal[2] += (double) val[4];
			}
			valoresTotalOb[0][1] = valoresTotal[0];
			valoresTotalOb[0][2] = valoresTotal[1];
			valoresTotalOb[0][3] = valoresTotal[2];

			JTable table = new JTable();
			 
			JTextArea txt = new JTextArea();
			txt.setBackground(SystemColor.control);
			txt.setWrapStyleWord(true);
			txt.setLineWrap(true);
			txt.setFont(new Font("Monospaced", Font.PLAIN, 30));
			txt.setText(mesesPeriodo.get(j)[0].toString()+"/"+ MesesEnum.values()[mesesPeriodo.get(j)[1]-1].getMes());
			txt.setBackground(Color.white);
			txt.setBorder(new LineBorder(Color.GRAY));
			table.setFont(new Font("Monospaced", Font.PLAIN, 18));

			table.setTableHeader(null);
			table.setModel(new DefaultTableModel(tabelaTabela,
					new String[] { "Categoria", "Despesa", "Mensal [R$]", "Ocasional [R$]", "Total Anual [R$]" }) {
				Class[] columnTypes = new Class[] { String.class, String.class, Double.class, Double.class,
						Double.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			// internPanel.setLayout(new BoxLayout(internPanel, BoxLayout.X_AXIS));

			JTable table_2 = new JTable();
			table_2.setFont(new Font("Monospaced", Font.BOLD, 19));
			table_2.setTableHeader(null);
			table_2.setModel(new DefaultTableModel(valoresTotalOb,
					new String[] { "Categoria", "Mensal [R$]", "Ocasional [R$]", "Total Anual [R$]" }) {
				Class[] columnTypes = new Class[] { String.class, Double.class, Double.class, Double.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});


			table_2.addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					JTable table = (JTable) e.getSource();
					table.getColumnModel().getColumn(0)
							.setPreferredWidth(table_1.getColumnModel().getColumn(0).getWidth()
									+ table_1.getColumnModel().getColumn(1).getWidth());
					table.getColumnModel().getColumn(1)
							.setPreferredWidth(table_1.getColumnModel().getColumn(2).getWidth());
					table.getColumnModel().getColumn(2)
							.setPreferredWidth(table_1.getColumnModel().getColumn(3).getWidth());
					table.getColumnModel().getColumn(3)
							.setPreferredWidth(table_1.getColumnModel().getColumn(4).getWidth());
				}
			});
			table.addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					JTable table = (JTable) e.getSource();
					table.getColumnModel().getColumn(0)
							.setPreferredWidth(table_1.getColumnModel().getColumn(0).getWidth());
					table.getColumnModel().getColumn(1)
							.setPreferredWidth(table_1.getColumnModel().getColumn(1).getWidth());
					table.getColumnModel().getColumn(2)
							.setPreferredWidth(table_1.getColumnModel().getColumn(2).getWidth());
					table.getColumnModel().getColumn(3)
							.setPreferredWidth(table_1.getColumnModel().getColumn(3).getWidth());
					table.getColumnModel().getColumn(4)
							.setPreferredWidth(table_1.getColumnModel().getColumn(4).getWidth());
				}
			});

			JPanel interno = new JPanel();
			interno.setLayout(new BoxLayout(interno, BoxLayout.Y_AXIS));
			interno.setBorder(new EmptyBorder(20, 0, 0, 0));
			table.setFillsViewportHeight(true);
			table.setRowHeight(30);
			table_2.setRowHeight(30);
			interno.setMaximumSize(new Dimension(4000, tabelaTabela.length * 30 + 96));
			interno.setPreferredSize(new Dimension(400, tabelaTabela.length * 30 + 96));
			interno.add(txt);
			interno.add(table);
			interno.add(table_2);

			internPanel.add(interno);

			tablesList.add(table_2);
			tablesList.add(table);
		}
	}
	private static Object[][] valoresTuplas(Object[][] tabela) {
		if (tabela.length == 0)
			return tabela;

		Object[][] retorno = new Object[tabela.length][5];
		for (int i = 0; i < tabela.length; i++) {
			retorno[i] = Arrays.copyOfRange(tabela[i], 4, tabela[0].length);
			retorno[i][4] = (int) tabela[i][3] * (double) tabela[i][6] + (double) tabela[i][7];
		}
		return retorno;
	}
}
