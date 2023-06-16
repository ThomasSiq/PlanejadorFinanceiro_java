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

public class DesenhaTabelasResumo {
	static JTable table_1;

	public static void desenhaTabelasAnos(ArrayList<Object[][]> valores, ArrayList<Object[][]> valor,
			ArrayList<Integer> anosPeriodo, ArrayList<Object[][]> tabela, JTable table_11, JPanel internPanel,
			ArrayList<JTable> tablesList,  ArrayList<String> nomes) {
		
		System.out.println(nomes);

		table_1 = table_11;
		valores = valoresTuplasMenores(tabela);

		for (Object[][] val : valores) {
			Object[][] newValor = new Object[val.length][val[0].length];
			for (int i = 0; i < val.length; i++) {
				System.arraycopy(val[i], 0, newValor[i], 0, val[i].length);
			}
		}

		for (int j = 0; j < anosPeriodo.size(); j++) {
			

			
			JPanel interno = new JPanel();
			JTextArea txt = new JTextArea();
				txt.setBackground(SystemColor.control);
				txt.setWrapStyleWord(true);
				txt.setLineWrap(true);
				txt.setFont(new Font("Monospaced", Font.PLAIN, 30));
				txt.setText(anosPeriodo.get(j).toString());
				txt.setBackground(Color.white);
				txt.setBorder(new LineBorder(Color.GRAY));
				interno.add(txt);
			
			
			
			for (int k = 0; k < valores.size(); k++) {
				ArrayList<Object[]> tabelaLocal = new ArrayList();
				Object[][] valoresTotalOb = new Object[1][4];
				double[] valoresTotal = new double[3];
				valoresTotalOb[0][0] = "Total ano " + nomes.get(k);
				

				for (double val : valoresTotal) {
					val = 0;
				}

				for (int i = 0; i < valores.get(k).length; i++) {

					if ((int) tabela.get(k)[i][1] == (int) anosPeriodo.get(j)) {
						tabelaLocal.add(valores.get(k)[i]);
					}
				}

				Object[][] tabelaTabela = new Object[tabelaLocal.size()][4];

				for (Object[] val : tabelaLocal) {
					tabelaTabela[tabelaLocal.indexOf(val)] = val;
					valoresTotal[0] += (double) val[1];
					valoresTotal[1] += (double) val[2];
					valoresTotal[2] += (double) val[3];
				}
				valoresTotalOb[0][1] = valoresTotal[0];
				valoresTotalOb[0][2] = valoresTotal[1];
				valoresTotalOb[0][3] = valoresTotal[2];

				// internPanel.setLayout(new BoxLayout(internPanel, BoxLayout.X_AXIS));

				JTable table_2 = new JTable();
				table_2.setFont(new Font("Monospaced", Font.BOLD, 19));
				table_2.setTableHeader(null);
				table_2.setModel(new DefaultTableModel(valoresTotalOb,
						new String[] { "Fundo Ocasional", "Mensal [R$]", "Ocasional [R$]", "Total Anual [R$]" }) {
					Class[] columnTypes = new Class[] { String.class, Double.class, Double.class, Double.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

				table_2.addComponentListener(new ComponentAdapter() {
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
					}
				});

				table_2.setRowHeight(30);
				interno.add(table_2);
				tablesList.add(table_2);
			}

			interno.setLayout(new BoxLayout(interno, BoxLayout.Y_AXIS));
			interno.setBorder(new EmptyBorder(20, 0, 0, 0));
			interno.setMaximumSize(new Dimension(4000, nomes.size() * 30 + 66));
			interno.setPreferredSize(new Dimension(400, nomes.size() * 30 + 66));
			internPanel.add(interno);

		}
	}

	public static void desenhaTabelasMeses(ArrayList<Object[][]> valores, ArrayList<Object[][]> valor,
			ArrayList<Integer[]> mesesPeriodo, ArrayList<Object[][]> tabela, JTable table_11, JPanel internPanel,
			ArrayList<JTable> tablesList,  ArrayList<String> nomes) {

		table_1 = table_11;
		valores = valoresTuplasMenores(tabela);

		for (Object[][] val : valores) {
			Object[][] newValor = new Object[val.length][val[0].length];
			for (int i = 0; i < val.length; i++) {
				System.arraycopy(val[i], 0, newValor[i], 0, val[i].length);
			}
		}

		for (int j = 0; j < mesesPeriodo.size(); j++) {
			
			
			JPanel interno = new JPanel();
			JTextArea txt = new JTextArea();
				txt.setBackground(SystemColor.control);
				txt.setWrapStyleWord(true);
				txt.setLineWrap(true);
				txt.setFont(new Font("Monospaced", Font.PLAIN, 30));
				txt.setText(mesesPeriodo.get(j)[0].toString() + "/"
						+ MesesEnum.values()[mesesPeriodo.get(j)[1] - 1].getMes());
				txt.setBackground(Color.white);
				txt.setBorder(new LineBorder(Color.GRAY));
				interno.add(txt);
			for (int k = 0; k < valores.size(); k++) {
				ArrayList<Object[]> tabelaLocal = new ArrayList();

			Object[][] valoresTotalOb = new Object[1][4];
			double[] valoresTotal = new double[3];
			valoresTotalOb[0][0] = "Total mês " + nomes.get(k);
				

				for (double val : valoresTotal) {
					val = 0;
				}
				for (int i = 0; i < valores.get(k).length; i++) {
					if ((int) tabela.get(k)[i][1] == (int) mesesPeriodo.get(j)[0]) {
						if ((int) tabela.get(k)[i][2] == 0) {
							tabela.get(k)[i][2] = 1;
						}
						if ((int) tabela.get(k)[i][2] <= (int) mesesPeriodo.get(j)[1]) {
							if (((int) tabela.get(k)[i][2] + (int) tabela.get(k)[i][3] - 1) >= (int) mesesPeriodo.get(j)[1]) {
								tabelaLocal.add(valores.get(k)[i]);

							}
						}
					}
				}
				if (tabelaLocal.isEmpty())
					continue;
				Object[][] tabelaTabela = new Object[tabelaLocal.size()][5];

				for (Object[] val : tabelaLocal) {
					val[3] = (double) val[2] + (double) val[1];
					tabelaTabela[tabelaLocal.indexOf(val)] = val;

					valoresTotal[0] += (double) val[1];
					valoresTotal[1] += (double) val[2];
					valoresTotal[2] += (double) val[3];
				}
				valoresTotalOb[0][1] = valoresTotal[0];
				valoresTotalOb[0][2] = valoresTotal[1];
				valoresTotalOb[0][3] = valoresTotal[2];

			
				JTable table_2 = new JTable();
				table_2.setFont(new Font("Monospaced", Font.BOLD, 19));
				table_2.setTableHeader(null);
				table_2.setModel(new DefaultTableModel(valoresTotalOb,
						new String[] { "Fundo Ocasional", "Mensal [R$]", "Ocasional [R$]", "Total Anual [R$]" }) {
					Class[] columnTypes = new Class[] { String.class, Double.class, Double.class, Double.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

				table_2.addComponentListener(new ComponentAdapter() {
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
					}
				});
			
				table_2.setRowHeight(30);
				interno.add(table_2);
				tablesList.add(table_2);
			}
			interno.setMaximumSize(new Dimension(4000, nomes.size() * 30 + 46));
			interno.setPreferredSize(new Dimension(400, nomes.size() * 30 + 46));
			interno.setLayout(new BoxLayout(interno, BoxLayout.Y_AXIS));
			interno.setBorder(new EmptyBorder(20, 0, 0, 0));
			internPanel.add(interno);
		}
	}

	private static ArrayList<Object[][]> valoresTuplasMenores(ArrayList<Object[][]> tab) {
		ArrayList<Object[][]> valores = new ArrayList();
		for (Object[][] tabela : tab) {
			if (tabela.length == 0)
				valores.add(tabela);

			Object[][] retorno = new Object[tabela.length][5];
			for (int i = 0; i < tabela.length; i++) {
				retorno[i] = Arrays.copyOfRange(tabela[i], 5, tabela[0].length);
				retorno[i][3] = (double) tabela[i][6] + (double) tabela[i][7];
			}
			valores.add(retorno);
		}
		return valores;
	}

}
