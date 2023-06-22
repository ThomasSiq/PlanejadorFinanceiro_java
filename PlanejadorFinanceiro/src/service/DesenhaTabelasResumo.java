package service;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;

import service.MonCellRenderer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import enumdata.MesesEnum;

public class DesenhaTabelasResumo {
	static JTable table_1;

	public static void desenhaTabelasResumo(ArrayList<Integer> anosPeriodo, ArrayList<Integer[]> mesesPeriodo,
			JTable table_11, JPanel internPanel, ArrayList<JTable> tablesList, ArrayList<String> nomes,
			PegaTabelas tabelasDados, String tipo) {

		table_1 = table_11;

		int periodo = mesesPeriodo.size();
		if (tipo == "Ano") {
			periodo = anosPeriodo.size();
		}

		for (int j = 0; j < periodo; j++) {

			JPanel interno = new JPanel();
			JTextArea txt = new JTextArea();

			txt.setBackground(SystemColor.control);
			txt.setWrapStyleWord(true);
			txt.setLineWrap(true);
			txt.setFont(new Font("Monospaced", Font.PLAIN, 30));

			txt.setBackground(Color.white);
			txt.setBorder(new LineBorder(Color.GRAY));
			interno.add(txt);
			Object[][] valoresTotalOb = null;

			if (tipo == "Ano") {
				txt.setText(anosPeriodo.get(j).toString());
				valoresTotalOb = tabelasDados.geraResultadoAno(nomes, anosPeriodo, j);
			} else {
				txt.setText(mesesPeriodo.get(j)[0].toString() + "/" + mesesPeriodo.get(j)[1].toString());
				valoresTotalOb = tabelasDados.geraResultadoMes(nomes, mesesPeriodo, j);
			}
			int cont = 0;
			for (Object[] val : valoresTotalOb) {

				if ((double) val[1] != 0 || (double) val[2] != 0 || (double) val[3] != 0) {
					break;
				}
				cont++;
			}
			if (cont == valoresTotalOb.length)
				continue;

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

			interno.setLayout(new BoxLayout(interno, BoxLayout.Y_AXIS));
			interno.setBorder(new EmptyBorder(20, 0, 0, 0));
			interno.setMaximumSize(new Dimension(4000, nomes.size() * 30 + 66));
			interno.setPreferredSize(new Dimension(400, nomes.size() * 30 + 66));
			internPanel.add(interno);

		}
	}

	public static void desenhaRelatorio(ArrayList<Integer> anosPeriodo, ArrayList<Integer[]> mesesPeriodo,
			JTable table_11, JPanel internPanel, ArrayList<JTable> tablesList, ArrayList<String> nomes,
			PegaTabelas tabelasDados, String tipo, String agrupar) {

		table_1 = table_11;

		int periodo = mesesPeriodo.size();
		if (tipo == "Ano") {
			periodo = anosPeriodo.size();
		}
		Object[] per = { anosPeriodo, mesesPeriodo, tipo };

		JPanel interno = new JPanel();
		JTextArea txt = new JTextArea();

		txt.setBackground(SystemColor.control);
		txt.setWrapStyleWord(true);
		txt.setLineWrap(true);
		txt.setFont(new Font("Monospaced", Font.PLAIN, 30));

		txt.setBackground(Color.white);
		txt.setBorder(new LineBorder(Color.GRAY));
		interno.add(txt);
		Object[][] valoresTotalOb = null;

		if (tipo == "Ano") {
			txt.setText(anosPeriodo.get(0).toString());
			if(agrupar=="Normal") {
				valoresTotalOb = tabelasDados.geraRelatorioNormal(nomes, per);
			}
			else {
				valoresTotalOb = tabelasDados.geraRelatorioCategoria(nomes, per);
			}
			
		} else {
			txt.setText(mesesPeriodo.get(0)[0].toString() + "/" + mesesPeriodo.get(0)[1].toString());
			if(agrupar=="Normal") {
			valoresTotalOb = tabelasDados.geraRelatorioNormal(nomes, per);
			}
			else {
				valoresTotalOb = tabelasDados.geraRelatorioCategoria(nomes, per);
			}
		}


		JTable table_2 = new JTable();
		table_2.setFont(new Font("Monospaced", Font.PLAIN, 19));
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
				table.getColumnModel().getColumn(0).setPreferredWidth(table_1.getColumnModel().getColumn(0).getWidth());
				table.getColumnModel().getColumn(1).setPreferredWidth(table_1.getColumnModel().getColumn(1).getWidth());
				table.getColumnModel().getColumn(2).setPreferredWidth(table_1.getColumnModel().getColumn(2).getWidth());
				table.getColumnModel().getColumn(3).setPreferredWidth(table_1.getColumnModel().getColumn(3).getWidth());
			}
		});
		table_2.setDefaultRenderer(Object.class, new MonCellRenderer());
	
		table_2.setRowHeight(30);
		interno.add(table_2);
		tablesList.add(table_2);

		interno.setLayout(new BoxLayout(interno, BoxLayout.Y_AXIS));
		interno.setBorder(new EmptyBorder(20, 0, 0, 0));
		interno.setMaximumSize(new Dimension(4000, valoresTotalOb.length * 30 + 66));
		interno.setPreferredSize(new Dimension(400, valoresTotalOb.length * 30 + 66));
		internPanel.add(interno);

	}


}
