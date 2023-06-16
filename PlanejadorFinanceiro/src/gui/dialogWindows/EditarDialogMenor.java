package gui.dialogWindows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EditarDadosDao;
import dao.PegaDadosDao;
import service.ExcluirEditarTabelas;
import service.MainService;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class EditarDialogMenor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNome;
	private JTextField textFieldValor;
	private JComboBox comboBoxAno;
	private JComboBox comboBoxNome;
	private JSpinner spinnerAno;
	private Object[][] tabela;
	private ArrayList<String> selecionados = new ArrayList();
	private ArrayList<Integer> index = new ArrayList();
	private ArrayList<Integer> anos;
	private String nome;

	/**
	 * Create the dialog.
	 */
	public EditarDialogMenor(String tab, WindowListener att) {
		nome = tab;
		setBounds(100, 100, 450, 337);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		updateTabela(tab);

		Object[] lista = PegaDadosDao.getCategoria(nome).toArray();

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(69, 244, 105, 37);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double ocasional = 0;
				double mensal = 0;
				if ((double) tabela[index.get(comboBoxNome.getSelectedIndex() - 1)][6] > 0) {
					mensal = Double.parseDouble(textFieldValor.getText());
				} else {
					ocasional = Double.parseDouble(textFieldValor.getText());
				}

				int ind = index.get(comboBoxNome.getSelectedIndex() - 1);
				ExcluirEditarTabelas.EditarTabelas(tabela, ind, nome, (int) tabela[ind][0], "",
						(int) spinnerAno.getValue(), textFieldNome.getText(), Double.toString(mensal),
						Double.toString(ocasional));
				dispose();
			}
		});
		contentPanel.add(btnEditar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(264, 244, 105, 37);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPanel.add(btnCancelar);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAno.setBounds(41, 13, 81, 27);
		contentPanel.add(lblAno);

		comboBoxAno = new JComboBox(anos.toArray(new Integer[anos.size()]));
		comboBoxAno.setBounds(132, 11, 272, 37);
		comboBoxAno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaNomes();
			}
		});
		contentPanel.add(comboBoxAno);

		JLabel lblDespesa = new JLabel(nome);
		lblDespesa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDespesa.setBounds(41, 61, 81, 27);
		contentPanel.add(lblDespesa);

		comboBoxNome = new JComboBox(selecionados.toArray(new String[anos.size()]));
		comboBoxNome.setBounds(132, 59, 272, 37);
		comboBoxNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxNome.getSelectedIndex() > 0) {
					spinnerAno.setValue((int) tabela[index.get(comboBoxNome.getSelectedIndex() - 1)][1]);
					textFieldNome.setText((String) tabela[index.get(comboBoxNome.getSelectedIndex() - 1)][5]);
					textFieldValor
							.setText(Double.toString((double) tabela[index.get(comboBoxNome.getSelectedIndex() - 1)][6]
									+ (double) tabela[index.get(comboBoxNome.getSelectedIndex() - 1)][7]));
				}
			}
		});
		contentPanel.add(comboBoxNome);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Editar",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 107, 414, 129);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblAno_1 = new JLabel("Ano");
		lblAno_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAno_1.setBounds(282, 76, 38, 27);
		panel.add(lblAno_1);

		JLabel lblDespesa_1 = new JLabel(nome);
		lblDespesa_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDespesa_1.setBounds(10, 29, 81, 27);
		panel.add(lblDespesa_1);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(90, 23, 314, 33);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);

		spinnerAno = new JSpinner();
		spinnerAno.setModel(new SpinnerNumberModel(2023, 2000, 2100, 1));
		spinnerAno.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spinnerAno.setBounds(330, 74, 74, 37);
		panel.add(spinnerAno);

		JLabel lblDespesa_1_1 = new JLabel("Valor");
		lblDespesa_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDespesa_1_1.setBounds(10, 78, 81, 27);
		panel.add(lblDespesa_1_1);

		textFieldValor = new JTextField();
		textFieldValor.setColumns(10);
		textFieldValor.setBounds(90, 78, 157, 33);
		panel.add(textFieldValor);

		this.addWindowListener(att);

	}

	private void updateTabela(String nome) {
		ArrayList<Object> retorno = MainService.getTabela(nome);
		tabela = (Object[][]) retorno.get(0);
		System.out.println(tabela[0][0].toString() + "  " + tabela[0][1].toString() + "  " + tabela[0][2].toString()
				+ "  " + tabela[0][3].toString());
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

				selecionados.add((String) tabela[i][5]);
				index.add(i);
				System.out.println(i);
				System.out.println(tabela[i][0]);
				comboBoxNome.addItem(cont + " - " + tabela[i][5].toString());
				cont++;

			}
		}

	}
}
