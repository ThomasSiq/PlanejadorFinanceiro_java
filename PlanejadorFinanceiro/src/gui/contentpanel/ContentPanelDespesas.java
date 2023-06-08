package gui.contentpanel;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import dao.GetDataDao;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import service.MainService;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;

public class ContentPanelDespesas extends JPanel {
	private JPanel internPanel;
	private JScrollPane scrollPaneEx;
	private ArrayList<Integer> anos;
	private ArrayList<Integer> anosPeriodo;
	private Object[][] tabela;
	private JRadioButton rdbtnAno;
	private JRadioButton rdbtnNewRadioButton;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private Object[][] valores;
	private Object[][] valor;

	/**
	 * Create the panel.
	 */
	public ContentPanelDespesas() {

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component component = e.getComponent();
				
				scrollPaneEx.setBounds(component.getX(), component.getY()+50, component.getWidth(), component.getBounds().height - 50);
				internPanel.setBounds(component.getX(), component.getY(), component.getWidth(), component.getBounds().height);
			}
		});

		internPanel = new JPanel();
		internPanel.setLayout(new BoxLayout(internPanel, BoxLayout.Y_AXIS));
		updateTabela("despesas");
		anosPeriodo = (ArrayList<Integer>)anos.clone();

		desenhaTabelas();

		setLayout(null);
		internPanel.setBounds(0, 0, 30, 30);
		scrollPaneEx = new JScrollPane(internPanel);
		
		scrollPaneEx.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneEx.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		add(scrollPaneEx);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(0, 0, 544, 40);
		add(panel);
		panel.setLayout(null);

		rdbtnNewRadioButton = new JRadioButton("Meses");
		rdbtnNewRadioButton.setBounds(10, 4, 101, 30);
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(rdbtnNewRadioButton);

		rdbtnAno = new JRadioButton("Anos");
		rdbtnAno.setBounds(113, 4, 80, 30);
		rdbtnAno.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(rdbtnAno);

		comboBox = new JComboBox(anos.toArray(new Integer[anos.size()]));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaAnos();
			}
		});
		comboBox.setBounds(318, 6, 100, 25);
		panel.add(comboBox);

		comboBox_1 = new JComboBox(anos.toArray(new Integer[anos.size()]));
		comboBox_1.setBounds(430, 6, 100, 25);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaAnos();
			}
		});
		panel.add(comboBox_1);

		JLabel lblNewLabel = new JLabel("Período:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(246, 6, 101, 25);
		panel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(215, 6, 1, 26);
		panel.add(separator);

	}

	public void redme(Rectangle rec, Component comp) {
		comp.setBounds(rec);
	}

	private void updateTabela(String nome) {
		ArrayList<Object> retorno = MainService.getTabela(nome);
		tabela = (Object[][]) retorno.get(0);
		anos = (ArrayList<Integer>) retorno.get(1);
	}

	private Object[][] valoresTuplas() {
		if (tabela.length == 0)
			return tabela;

		Object[][] retorno = new Object[tabela.length][5];
		for (int i = 0; i < tabela.length; i++) {
			retorno[i] = Arrays.copyOfRange(tabela[i], 4, tabela[0].length);
		}
		return retorno;
	}

	private void desenhaTabelas() {
		
		valores = valoresTuplas();
		valor = new Object[valores.length][valores[0].length];
		for (int i = 0; i < valores.length; i++) {
			System.arraycopy(valores[i], 0, valor[i], 0, valores[i].length);
		}
		
		for (int j = 0; j < anosPeriodo.size(); j++) {
			ArrayList<Object[]> tabelaLocal = new ArrayList();

			for (int i = 0; i < valores.length; i++) {

				if ((int) tabela[i][1] == (int) anosPeriodo.get(j)) {
					tabelaLocal.add(valores[i]);
				}
			}

			Object[][] tabelaTabela = new Object[tabelaLocal.size()][5];

			for (Object[] val : tabelaLocal) {
				tabelaTabela[tabelaLocal.indexOf(val)] = val;
			}

			JTable table = new JTable();
			JTextArea txt = new JTextArea();
			txt.setBackground(SystemColor.control);
			txt.setWrapStyleWord(true);
			txt.setLineWrap(true);
			txt.setFont(new Font("Monospaced", Font.PLAIN, 23));
			txt.setText(anosPeriodo.get(j).toString());
			txt.setBounds(0, 0, 189, 118);
			table.setModel(new DefaultTableModel(tabelaTabela,
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
			//internPanel.setLayout(new BoxLayout(internPanel, BoxLayout.X_AXIS));
			
			JPanel interno = new JPanel();
			interno.setLayout(new BoxLayout(interno, BoxLayout.Y_AXIS));
			interno.setMaximumSize(new Dimension(4000, tabelaTabela.length * 49 + 19));
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			interno.add(txt);
			interno.add(scrollPane);
			scrollPane.setPreferredSize(new Dimension(400, tabelaTabela.length * 18 + 19));

			internPanel.add(interno);
		}
	}

	private void mudaAnos() {
		anosPeriodo.clear();

		for (int i=0; i<anos.size(); i++) {
			
			if (anos.get(i) >= (int)(comboBox.getSelectedItem())) {
				if (anos.get(i) <= (int)(comboBox_1.getSelectedItem())) {
					anosPeriodo.add(anos.get(i));
				}
			}
		}
		repaint();
		internPanel.removeAll();
		desenhaTabelas();
		internPanel.revalidate();
		internPanel.repaint();
		
	}
	private void mudaMeses() {
		anosPeriodo.clear();

		for (int i=0; i<anos.size(); i++) {
			
			if (anos.get(i) >= (int)(comboBox.getSelectedItem())) {
				if (anos.get(i) <= (int)(comboBox_1.getSelectedItem())) {
					anosPeriodo.add(anos.get(i));
				}
			}
		}
		repaint();
		internPanel.removeAll();
		desenhaTabelas();
		internPanel.revalidate();
		internPanel.repaint();
		
	}
}
