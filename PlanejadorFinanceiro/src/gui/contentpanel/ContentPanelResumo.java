package gui.contentpanel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import service.DesenhaTabelasDinamicas;
import service.DesenhaTabelasResumo;
import service.MainService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.border.BevelBorder;

public class ContentPanelResumo extends JPanel {
	private JPanel internPanel;
	private JPanel panel;

	private ArrayList<Integer> anos = new ArrayList();
	private ArrayList<Integer> anosPeriodo;
	private ArrayList<Integer[]> mesesPeriodo = new ArrayList();
	private ArrayList<JTable> tablesList = new ArrayList();

	private JRadioButton rdbtnAno;
	private JRadioButton rdbtnNewRadioButton;

	private JComboBox comboBox;
	private JComboBox comboBox_1;

	private ArrayList<Object[][]> tabela = new ArrayList();
	private ArrayList<Object[][]> tabelaBd = new ArrayList();
	private ArrayList<Object[][]> valores = new ArrayList();
	private ArrayList<Object[][]> valor = new ArrayList();

	private JScrollPane scrollPaneEx;
	private JScrollPane scrollPane_1;

	private JTable table_1;

	private ArrayList<String> nomes = new ArrayList();
	private ArrayList<String> todosNomes = new ArrayList();

	public ContentPanelResumo() {
		todosNomes.add("Rendimentos");
		todosNomes.add("Ocasional");
		todosNomes.add("LongoPrazo");
		todosNomes.add("Despesas");
		todosNomes.add("Total Disponivel");
		todosNomes.add("Resultado");

		nomes.add("Rendimentos");
		nomes.add("Despesas");
		table_1 = new JTable();
		internPanel = new JPanel();
		panel = new JPanel();
		scrollPane_1 = new JScrollPane();
		scrollPaneEx = new JScrollPane(internPanel);

		updateTabelaBd();
		updateTabela(); // Pega dados das tabelas
		anosPeriodo = (ArrayList<Integer>) anos.clone();

		internPanel.setBorder(null);
		internPanel.setLayout(new BoxLayout(internPanel, BoxLayout.Y_AXIS));
		internPanel.setBounds(0, 0, 30, 30);

		scrollPaneEx.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneEx.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		rdbtnNewRadioButton = new JRadioButton("Meses");
		rdbtnNewRadioButton.setBounds(10, 4, 101, 30);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNewRadioButton.isSelected()) {
					rdbtnAno.setSelected(false);
					mudaMeses();
				}
			}
		});

		rdbtnAno = new JRadioButton("Anos");
		rdbtnAno.setForeground(Color.BLACK);
		rdbtnAno.setBounds(113, 4, 80, 30);
		rdbtnAno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnAno.setSelected(true);
		rdbtnAno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAno.isSelected()) {
					rdbtnNewRadioButton.setSelected(false);
					mudaAnos();
				}
			}
		});

		comboBox = new JComboBox(anos.toArray(new Integer[anos.size()]));
		comboBox.setBounds(318, 6, 100, 25);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAno.isSelected()) {
					mudaAnos();
				}
				if (rdbtnNewRadioButton.isSelected()) {
					mudaMeses();
				}
			}
		});

		comboBox_1 = new JComboBox(anos.toArray(new Integer[anos.size()]));
		comboBox_1.setBounds(430, 6, 100, 25);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAno.isSelected()) {
					mudaAnos();
				}
				if (rdbtnNewRadioButton.isSelected()) {
					mudaMeses();
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Período:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(246, 6, 101, 25);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(215, 6, 1, 26);

		table_1.getTableHeader().setFont(new Font("Monospaced", Font.PLAIN, 19));
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Tipo", "Mensal [R$]", "Ocasional [R$]", "Total Mensal [R$]" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(300);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_1.getColumnModel().addColumnModelListener(columEvent);

		scrollPane_1.setBounds(0, 40, 755, 31);
		scrollPane_1.setViewportView(table_1);

		panel.setForeground(Color.GRAY);
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(0, 0, 544, 40);
		panel.setLayout(null);
		panel.add(rdbtnNewRadioButton);
		panel.add(rdbtnAno);
		panel.add(comboBox);
		panel.add(comboBox_1);
		panel.add(lblNewLabel);
		panel.add(separator);

		setLayout(null);
		add(scrollPane_1);
		add(scrollPaneEx);
		add(panel);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component component = e.getComponent();

				scrollPaneEx.setBounds(component.getX(), component.getY() + 80, component.getWidth(),
						component.getBounds().height - 80);
				internPanel.setBounds(component.getX(), component.getY(), component.getWidth(),
						component.getBounds().height);
				scrollPane_1.setBounds(component.getX(), component.getY() + 50, component.getWidth() - 15, 50);
				table_1.setBounds(component.getX(), component.getY() + 50, component.getWidth() - 15, 50);
			}
		});

		mudaAnos();
	}

	TableColumnModelListener columEvent = new TableColumnModelListener() {
		public void columnMoved(TableColumnModelEvent e) {

		}

		@Override
		public void columnAdded(TableColumnModelEvent e) {

		}

		@Override
		public void columnRemoved(TableColumnModelEvent e) {

		}

		@Override
		public void columnMarginChanged(ChangeEvent e) {
			TableColumnModel component = (TableColumnModel) e.getSource();
			for (JTable table : tablesList) {
				if (table.getModel().getColumnCount() == 4) {
					table.getColumnModel().getColumn(0).setPreferredWidth(component.getColumn(0).getWidth());
					table.getColumnModel().getColumn(1).setPreferredWidth(component.getColumn(1).getWidth());
					table.getColumnModel().getColumn(2).setPreferredWidth(component.getColumn(2).getWidth());
					table.getColumnModel().getColumn(3).setPreferredWidth(component.getColumn(3).getWidth());

				} else {
					table.getColumnModel().getColumn(0).setPreferredWidth(component.getColumn(0).getWidth());
					table.getColumnModel().getColumn(1).setPreferredWidth(component.getColumn(1).getWidth());
					table.getColumnModel().getColumn(2).setPreferredWidth(component.getColumn(2).getWidth());
					table.getColumnModel().getColumn(3).setPreferredWidth(component.getColumn(3).getWidth());
				}
			}

		}

		@Override
		public void columnSelectionChanged(ListSelectionEvent e) {

		}
	};

	private void updateTabelaBd() {
		tabelaBd.clear();
		int maior = 0;
		for (int i = 0; i < 4; i++) {
			ArrayList<Object> retorno = MainService.getTabela(todosNomes.get(i));
			tabelaBd.add((Object[][]) retorno.get(0));
			achaAno((ArrayList<Integer>) retorno.get(1));
			
			for (int j = 0; j < tabelaBd.get(i).length; j++) {
				tabelaBd.get(i)[j][6] = (int) tabelaBd.get(i)[j][3] * (double) tabelaBd.get(i)[j][6];
			}
			if(tabelaBd.get(i).length>maior) {
				maior = tabelaBd.get(i).length;
			}
		}
		Object[][] total = new Object[anos.size()][9];
		Object[][] resultado = new Object[anos.size()][9];

		for (int i = 0; i < anos.size(); i++) {
			total[i][6] = 0.0;
			total[i][7] = 0.0;
			total[i][8] = 0.0;
			resultado[i][6] = 0.0;
			resultado[i][7] = 0.0;
			resultado[i][8] = 0.0;
			total[i][1] = anos.get(i);
			resultado[i][1] = anos.get(i);
		}
		System.out.println(">>>>>"+maior);

		for (int k = 0; k < anos.size(); k++) {
			for (int i = 0; i < maior; i++) {

				for (int j = 6; j < 9; j++) {
					

						System.out.println(anos.get(k));
						System.out.println(j);

					
					if (tabelaBd.get(0).length > i && anos.get(k) == (int) tabelaBd.get(0)[i][1]) {
	
						total[k][j] = (double) total[k][j] + (double) tabelaBd.get(0)[i][j];

					}

					if (tabelaBd.get(1).length > i && anos.get(k) == (int) tabelaBd.get(1)[i][1]) {

						total[k][j] = (double) total[k][j] - (double) tabelaBd.get(1)[i][j];

					}
					if (tabelaBd.get(2).length > i && anos.get(k) == (int) tabelaBd.get(2)[i][1]) {

						total[k][j] = (double) total[k][j] - (double) tabelaBd.get(2)[i][j];
					}
					if (tabelaBd.get(3).length > i && anos.get(k) == (int) tabelaBd.get(3)[i][1]) {
						if(anos.get(k) == 2023) {
							System.out.println(resultado[k][j]);

						}
						resultado[k][j] = (double) resultado[k][j] - (double) tabelaBd.get(3)[i][j];
					}

				}
			}
			
			System.out.println(">>>>"+resultado[k][7]);
			resultado[k][6] = (double) resultado[k][6] + (double)total[k][6];
			resultado[k][7] = (double) resultado[k][7] + (double)total[k][7];
			resultado[k][8] = (double) resultado[k][8] + (double)total[k][8];
			if(anos.get(k) == 2023) {
				System.out.println(">>>>"+total[k][7]);
				System.out.println(">>>>"+resultado[k][7]);
				System.out.println(">>>>"+anos.get(k));
				System.out.println(">>>>"+k);

			}
		}

		tabelaBd.add(total);
		tabelaBd.add(resultado);

	}

	private void achaAno(ArrayList<Integer> retorno) {
		for (int ano : retorno) {
			boolean igual = false;
			for (int a : anos) {
				if (ano == a) {
					igual = true;
				}
			}
			if (igual == false) {
				anos.add(ano);
			}
		}

		Collections.sort(anos, Collections.reverseOrder());
	}

	private void updateTabela() {
		tabela.clear();
		for (String nome : nomes) {
			System.out.println(nome);
			System.out.println(todosNomes);
			tabela.add(tabelaBd.get(todosNomes.indexOf(nome)));
		}

	}

	private void mudaAnos() {
		anosPeriodo.clear();
		table_1.getColumnModel().getColumn(3).setHeaderValue("Total Anual [R$]");
		table_1.getTableHeader().repaint();
		for (int i = 0; i < anos.size(); i++) {

			if (anos.get(i) >= (int) (comboBox.getSelectedItem())) {
				if (anos.get(i) <= (int) (comboBox_1.getSelectedItem())) {
					anosPeriodo.add(anos.get(i));
					continue;
				}
			}
			if (anos.get(i) <= (int) (comboBox.getSelectedItem())) {
				if (anos.get(i) >= (int) (comboBox_1.getSelectedItem())) {
					anosPeriodo.add(anos.get(i));
				}
			}
		}
		internPanel.removeAll();
		if (tabela.size() > 0) {
			DesenhaTabelasResumo.desenhaTabelasAnos(valores, valor, anosPeriodo, tabela, table_1, internPanel,
					tablesList, nomes);
		}
		internPanel.revalidate();
		internPanel.repaint();

	}

	private void mudaMeses() {
		mesesPeriodo.clear();
		table_1.getColumnModel().getColumn(3).setHeaderValue("Total Mensal [R$]");
		table_1.getTableHeader().repaint();
		for (int i = 0; i < anos.size(); i++) {

			if (anos.get(i) >= (int) (comboBox.getSelectedItem())) {
				if (anos.get(i) <= (int) (comboBox_1.getSelectedItem())) {
					for (int j = 12; j > 0; j--) {
						Integer[] temp = new Integer[2];
						temp[0] = anos.get(i);
						temp[1] = j;
						mesesPeriodo.add(temp);
					}

					continue;
				}
			}
			if (anos.get(i) <= (int) (comboBox.getSelectedItem())) {
				if (anos.get(i) >= (int) (comboBox_1.getSelectedItem())) {
					for (int j = 12; j > 0; j--) {
						Integer[] temp = new Integer[2];
						temp[0] = anos.get(i);
						temp[1] = j;
						mesesPeriodo.add(temp);
					}

				}
			}
		}
		internPanel.removeAll();
		DesenhaTabelasResumo.desenhaTabelasMeses(valores, valor, mesesPeriodo, tabela, table_1, internPanel, tablesList,
				nomes);
		internPanel.revalidate();
		internPanel.repaint();

	}

	public void attTabelas() {
		updateTabelaBd();
		updateTabela();

		if (rdbtnAno.isSelected()) {
			mudaAnos();
		}
		if (rdbtnNewRadioButton.isSelected()) {
			mudaMeses();
		}
	}

	public void setNomes(ArrayList<String> nomes) {
		this.nomes = nomes;
		if (rdbtnAno.isSelected()) {
			updateTabela();
			mudaAnos();
		}
		if (rdbtnNewRadioButton.isSelected()) {
			updateTabela();
			mudaMeses();
		}
	}
}
