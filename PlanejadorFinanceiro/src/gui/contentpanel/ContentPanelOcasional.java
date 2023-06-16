package gui.contentpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

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
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import service.DesenhaTabelasDinamicas;
import service.DesenhaTabelasMenores;
import service.MainService;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class ContentPanelOcasional extends JPanel {
	private JPanel internPanel;
	private JPanel panel;
	
	private ArrayList<Integer> anos;
	private ArrayList<Integer> anosPeriodo;
	private ArrayList<Integer[]> mesesPeriodo = new ArrayList();
	private ArrayList<JTable> tablesList = new ArrayList();
	
	private JRadioButton rdbtnAno;
	private JRadioButton rdbtnNewRadioButton;
	
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	
	private Object[][] tabela;
	private Object[][] valores;
	private Object[][] valor;
	
	private JScrollPane scrollPaneEx;
	private JScrollPane scrollPane_1;
	
	private JTable table_1;
	
	private String nome = "Ocasional";
	
	
	public ContentPanelOcasional(){
		
		table_1 = new JTable();
		internPanel = new JPanel();
		panel = new JPanel();
		scrollPane_1 = new JScrollPane();
		scrollPaneEx = new JScrollPane(internPanel);
		
		updateTabela(nome); //Pega dados das tabelas
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
				if(rdbtnNewRadioButton.isSelected()) {
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
				if(rdbtnAno.isSelected()) {
					rdbtnNewRadioButton.setSelected(false);
					mudaAnos();
				}
			}
		});
		
		comboBox = new JComboBox(anos.toArray(new Integer[anos.size()]));
		comboBox.setBounds(318, 6, 100, 25);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAno.isSelected()) {
					mudaAnos();
				}
				if(rdbtnNewRadioButton.isSelected()) {
					mudaMeses();
				}
			}
		});
		
		comboBox_1 = new JComboBox(anos.toArray(new Integer[anos.size()]));
		comboBox_1.setBounds(430, 6, 100, 25);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculaBalanco();
				if(rdbtnAno.isSelected()) {
					mudaAnos();
				}
				if(rdbtnNewRadioButton.isSelected()) {
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
				new String[] { "Fundo Ocasional", "Mensal [R$]", "Ocasional [R$]", "Total Mensal [R$]" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(300);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(100);
		table_1.getColumnModel().addColumnModelListener(columEvent);
		
		scrollPane_1.setBounds(0, 140, 755, 31);
		scrollPane_1.setViewportView(table_1);
		
		panel.setForeground(Color.GRAY);
		panel.setBorder(new LineBorder(Color.GRAY));
		panel.setBounds(0, 0, 544, 87);
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
		
		textBalanco = new JTextPane();
		textBalanco.setBackground(SystemColor.control);
		textBalanco.setEditable(false);
		textBalanco.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textBalanco.setBounds(319, 50, 211, 30);
		panel.add(textBalanco);
		
		JLabel lblNewLabel_1_1 = new JLabel("Balanço ao final do Ano:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_1.setBounds(18, 56, 325, 18);
		panel.add(lblNewLabel_1_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 41, 510, 2);
		panel.add(separator_1);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component component = e.getComponent();

				scrollPaneEx.setBounds(component.getX(), component.getY() + 130, component.getWidth(),
						component.getBounds().height - 130);
				internPanel.setBounds(component.getX(), component.getY(), component.getWidth(),
						component.getBounds().height);
				scrollPane_1.setBounds(component.getX(), component.getY() + 100, component.getWidth() - 15, 50);
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
	private JTextPane textBalanco;

	private void updateTabela(String nome) {
		ArrayList<Object> retorno = MainService.getTabela(nome);
		tabela = (Object[][]) retorno.get(0);
		anos = (ArrayList<Integer>) retorno.get(1);
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
		if(tabela.length != 0) {
			DesenhaTabelasMenores.desenhaTabelasMenoresAnos(valores, valor, anosPeriodo, tabela, table_1, internPanel, tablesList);
		}
		internPanel.revalidate();
		internPanel.repaint();
		calculaBalanco();

	}

	private void mudaMeses() {
		mesesPeriodo.clear();
		table_1.getColumnModel().getColumn(3).setHeaderValue("Total Mensal [R$]");
		table_1.getTableHeader().repaint();
		for (int i = 0; i < anos.size(); i++) {
			

			if (anos.get(i) >= (int) (comboBox.getSelectedItem())) {
				if (anos.get(i) <= (int) (comboBox_1.getSelectedItem())) {
					for(int j =12; j >0; j--) {
						Integer[] temp = new Integer[2];
						temp[0]=anos.get(i);
						temp[1] = j;
						mesesPeriodo.add(temp);
					}
					
					continue;
				}
			}
			if (anos.get(i) <= (int) (comboBox.getSelectedItem())) {
				if (anos.get(i) >= (int) (comboBox_1.getSelectedItem())) {
					for(int j =12; j >0; j--) {
						Integer[] temp = new Integer[2];
						temp[0]=anos.get(i);
						temp[1] = j;
						mesesPeriodo.add(temp);
					}
					
				}
			}
		}
		internPanel.removeAll();
		DesenhaTabelasMenores.desenhaTabelasMenoresMeses(valores, valor, mesesPeriodo, tabela, table_1, internPanel, tablesList);
		internPanel.revalidate();
		internPanel.repaint();
		calculaBalanco();

	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void attTabelas() {
		updateTabela(nome);
		if(rdbtnAno.isSelected()) {
			mudaAnos();
		}
		if(rdbtnNewRadioButton.isSelected()) {
			mudaMeses();
		}
	}
	
	private void calculaBalanco() {
		int anoMaior = (int)comboBox.getSelectedItem();
		double soma = 0;
		if((int)comboBox_1.getSelectedItem()>(int)comboBox.getSelectedItem()) {
			anoMaior = (int)comboBox_1.getSelectedItem();
		}
		for(int i = 0; i<tabela.length; i++) {
			if(anoMaior>=(int)tabela[i][1]) {
				soma += (double)tabela[i][6]*(int)tabela[i][3]+ (double)tabela[i][7];
			}
		}
		
		textBalanco.setText("R$ "+Double.toString(soma));
	}
}
