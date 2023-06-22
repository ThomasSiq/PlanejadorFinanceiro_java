package gui.dialogwindows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EditaDadosDao;
import dao.PegaDadosDao;
import service.ExcluirEditarTabelas;
import service.MainService;
import service.ValorFormato;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class EditarDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JComboBox comboBoxCategoria;
	private JTextField textFieldNome;
	private JFormattedTextField textFieldValor;
	private JComboBox comboBoxAno;
	private JComboBox comboBoxNome;
	private JComboBox comboBoxNovaCategoria;
	private JSpinner spinnerAno;
	private Object[][] tabela;
	private ArrayList<String> selecionados = new ArrayList();
	private ArrayList<Integer> index = new ArrayList();
	private ArrayList<Integer> anos;
	private String nome;

	/**
	 * Create the dialog.
	 */
	public EditarDialog(String tab, WindowListener att) {
		nome = tab;
		this.setName(tab);
		setBounds(100, 100, 450, 438);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		updateTabela(tab);

		Object[] lista = null;
		try {
			lista = PegaDadosDao.getCategoria(nome, MainService.getSenha()).toArray();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados. Verifique a conexão com a internet", "Erro", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		comboBoxCategoria = new JComboBox(lista);
		comboBoxCategoria.addItem("Selecione");
		comboBoxCategoria.setSelectedIndex(comboBoxCategoria.getItemCount()-1);
		comboBoxCategoria.setBounds(182, 20, 223, 37);

		comboBoxCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaNomes();
			}
		});
		contentPanel.add(comboBoxCategoria);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(69, 351, 105, 37);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double ocasional = 0;
				double mensal = 0;
				if ((double) tabela[index.get(comboBoxNome.getSelectedIndex() - 1)][6] > 0) {
					mensal = Double.parseDouble(textFieldValor.getText());
				} else {
					ocasional = Double.parseDouble(textFieldValor.getValue().toString());
				}

				int ind = index.get(comboBoxNome.getSelectedIndex() - 1);
				try {
					ExcluirEditarTabelas.EditarTabelas(tabela, ind, nome, (int)tabela[ind][0], (String) comboBoxNovaCategoria.getSelectedItem(), (int)spinnerAno.getValue(), textFieldNome.getText(), Double.toString(mensal), Double.toString(ocasional));
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados. Verifique a conexão com a internet", "Erro", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
				dispose();
			}
		});
		contentPanel.add(btnEditar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(264, 351, 105, 37);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPanel.add(btnCancelar);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCategoria.setBounds(42, 22, 81, 27);
		contentPanel.add(lblCategoria);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAno.setBounds(42, 75, 81, 27);
		contentPanel.add(lblAno);

		comboBoxAno = new JComboBox(anos.toArray(new Integer[anos.size()]));
		comboBoxAno.setBounds(182, 73, 223, 37);
		comboBoxAno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaNomes();
			}
		});
		contentPanel.add(comboBoxAno);

		JLabel lblDespesa = new JLabel(nome);
		lblDespesa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDespesa.setBounds(42, 123, 81, 27);
		contentPanel.add(lblDespesa);

		comboBoxNome = new JComboBox(selecionados.toArray(new String[anos.size()]));
		comboBoxNome.setBounds(182, 121, 223, 37);
		comboBoxNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxNome.getSelectedIndex() > 0) {
					spinnerAno.setValue((int) tabela[index.get(comboBoxNome.getSelectedIndex() - 1)][1]);
					comboBoxNovaCategoria.setSelectedIndex(comboBoxCategoria.getSelectedIndex());
					textFieldNome.setText((String) tabela[index.get(comboBoxNome.getSelectedIndex() - 1)][5]);
					textFieldValor
							.setValue((double) tabela[index.get(comboBoxNome.getSelectedIndex() - 1)][6]
									+ (double) tabela[index.get(comboBoxNome.getSelectedIndex() - 1)][7]);
				}
			}
		});
		contentPanel.add(comboBoxNome);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Editar",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 169, 414, 177);
		contentPanel.add(panel);
		panel.setLayout(null);

		comboBoxNovaCategoria = new JComboBox(lista);
		comboBoxNovaCategoria.setBounds(90, 21, 178, 37);
		panel.add(comboBoxNovaCategoria);

		JLabel lblCategoria_1 = new JLabel("Categoria");
		lblCategoria_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCategoria_1.setBounds(10, 23, 81, 27);
		panel.add(lblCategoria_1);

		JLabel lblAno_1 = new JLabel("Ano");
		lblAno_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAno_1.setBounds(282, 23, 38, 27);
		panel.add(lblAno_1);

		JLabel lblDespesa_1 = new JLabel(nome);
		lblDespesa_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDespesa_1.setBounds(10, 82, 81, 27);
		panel.add(lblDespesa_1);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(110, 76, 294, 33);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);

		spinnerAno = new JSpinner();
		spinnerAno.setModel(new SpinnerNumberModel(2023, 2000, 2100, 1));
		spinnerAno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spinnerAno.setBounds(330, 21, 74, 37);
		panel.add(spinnerAno);

		JLabel lblDespesa_1_1 = new JLabel("Valor");
		lblDespesa_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDespesa_1_1.setBounds(10, 131, 81, 27);
		panel.add(lblDespesa_1_1);

		textFieldValor = new JFormattedTextField();
		textFieldValor.setFormatterFactory(new ValorFormato());
		textFieldValor.setColumns(10);
		textFieldValor.setBounds(110, 131, 294, 33);
		panel.add(textFieldValor);

		this.addWindowListener(att);

	}

	private void updateTabela(String nome) {
		ArrayList<Object> retorno = new ArrayList();
		try {
			retorno = MainService.getTabela(nome);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados. Verifique a conexão com a internet", "Erro", JOptionPane.WARNING_MESSAGE);

			e.printStackTrace();
		}
		tabela = (Object[][]) retorno.get(0);
		
		anos = (ArrayList<Integer>) retorno.get(1);
	}

	private void mudaNomes() {
		int cont = 0;
		selecionados.clear();
		index.clear();
		comboBoxNome.removeAllItems();
		comboBoxNome.addItem("Selecione uma Opção");
		for (int i = 0; i < tabela.length; i++) {
			if ((int) tabela[i][1] == (int) comboBoxAno.getSelectedItem()) {
				if (((String) tabela[i][4]).equals((String) comboBoxCategoria.getSelectedItem())) {
					selecionados.add((String) tabela[i][5]);
					index.add(i);
					comboBoxNome.addItem(cont + " - " + tabela[i][5].toString());
					cont++;
				}
			}
		}

	}
}
