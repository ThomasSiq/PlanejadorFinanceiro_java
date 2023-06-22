package gui.contentpanel;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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

import enumdata.MesesEnum;
import service.DesenhaTabelasDinamicas;
import service.DesenhaTabelasResumo;
import service.MainService;
import service.PegaTabelas;

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
import javax.swing.ComboBoxModel;

public class ContentPanelRelatorio extends ContentPanel {
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
	private JComboBox comboBox_1_1;

	private ArrayList<Object[][]> tabela = new ArrayList();
	private ArrayList<Object[][]> tabelaBd = new ArrayList();
	private ArrayList<Object[][]> valores = new ArrayList();
	private ArrayList<Object[][]> valor = new ArrayList();

	private JScrollPane scrollPaneEx;
	private JScrollPane scrollPane_1;
	private String tipo = "Ano";

	private JTable table_1;
	private String agrupar;

	private ArrayList<String> nomes = new ArrayList();
	private final ArrayList<String> todosNomes = new ArrayList<String>(
			Arrays.asList("Rendimentos", "Ocasional", "LongoPrazo", "Despesas", "Total Disponivel", "Resultado"));

	private PegaTabelas tabelasDados;

	public ContentPanelRelatorio(PegaTabelas tabelasDados) throws SQLException {
		this.setName("Relatorio");
		this.tabelasDados = tabelasDados;
		agrupar = "Normal";
		nomes = todosNomes;
		table_1 = new JTable();
		internPanel = new JPanel();
		panel = new JPanel();
		scrollPane_1 = new JScrollPane();
		scrollPaneEx = new JScrollPane(internPanel);

		// updateTabelaBd();
		tabelaBd = tabelasDados.getTabelaBd();
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
					mudaPeriodo();
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
					mudaPeriodo();
				}
			}
		});

		comboBox = new JComboBox(anos.toArray(new Integer[anos.size()]));
		comboBox.setBounds(270, 6, 86, 25);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaPeriodo();
			}
		});

		comboBox_1 = new JComboBox(new DefaultComboBoxModel<>(MesesEnum.values()));
		comboBox_1.setBounds(406, 6, 124, 25);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNewRadioButton.isSelected()) {
					mudaPeriodo();
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Ano");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(226, 6, 101, 25);

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
		panel.setBounds(0, 0, 755, 40);
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

		JLabel lblMs = new JLabel("Mês");
		lblMs.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMs.setBounds(370, 6, 101, 25);
		panel.add(lblMs);
		
		comboBox_1_1 = new JComboBox();
		comboBox_1_1.addItem("Normal");
		comboBox_1_1.addItem("Categoria");
		comboBox_1_1.setBounds(615, 6, 124, 25);
		comboBox_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agrupar = (String)comboBox_1_1.getSelectedItem();
				mudaPeriodo();
			}
		});
		panel.add(comboBox_1_1);
		
		JLabel lblAgrupar = new JLabel("Agrupar");
		lblAgrupar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAgrupar.setBounds(550, 6, 101, 25);
		panel.add(lblAgrupar);
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

		mudaPeriodo();
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
	@Override
	public void updateTabela() {
		tabela = tabelasDados.updateTabela(nomes);
		
		anos = tabelasDados.getAnos();
		if(comboBox!=null) {
			comboBox.removeAllItems();
		}
		
		for(int ano : anos) {
			comboBox.addItem(ano);
		}
		mudaPeriodo();
	}

	private void mudaPeriodo() {
		if(comboBox == null)return;
		anosPeriodo.clear();
		mesesPeriodo.clear();
		internPanel.removeAll();
		
		if(comboBox.getSelectedItem()==null || comboBox_1.getSelectedItem()==null) {
			internPanel.revalidate();
			internPanel.repaint();
			return;
		}
		if (rdbtnAno.isSelected()) {
			table_1.getColumnModel().getColumn(3).setHeaderValue("Total Anual [R$]");
			tipo = "Ano";
			anosPeriodo.add((int) (comboBox.getSelectedItem()));
		}
		else {
			table_1.getColumnModel().getColumn(3).setHeaderValue("Total Mensal [R$]");
			tipo = "Mes";
			Integer periodo[] = new Integer[2];
			periodo[0] = (int) (comboBox.getSelectedItem());
			periodo[1] = (comboBox_1.getSelectedIndex() + 1);
			mesesPeriodo.add(periodo);
		}

		table_1.getTableHeader().repaint();

		

		internPanel.removeAll();
		if (tabela.size() > 0) {
			DesenhaTabelasResumo.desenhaRelatorio(anosPeriodo, mesesPeriodo, table_1, internPanel, tablesList, nomes,
					tabelasDados, tipo, agrupar);
		}
		internPanel.revalidate();
		internPanel.repaint();
	}
	

	public void attTabelas() throws SQLException {
		tabelasDados.updateTabelaBd();

	}

	public void setNomes(ArrayList<String> nomes) {
		this.nomes = nomes;
			updateTabela();
			mudaPeriodo();
	}
	
	public Object[] getPeriodo() {
		Object[] retorno = {anosPeriodo, mesesPeriodo, tipo};
		return retorno;
	}
	public String getAgrupar() {
		return agrupar;
	}
}
