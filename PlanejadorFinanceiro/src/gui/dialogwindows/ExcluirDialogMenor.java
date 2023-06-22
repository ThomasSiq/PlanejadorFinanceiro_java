package gui.dialogwindows;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import dao.EditaDadosDao;
import dao.PegaDadosDao;
import dao.RemoveDadosDao;
import service.ExcluirEditarTabelas;
import service.MainService;

public class ExcluirDialogMenor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBoxAno;
	private JComboBox comboBoxNome;
	private Object[][] tabela;
	private ArrayList<String> selecionados = new ArrayList();
	private ArrayList<Integer> index = new ArrayList();
	private ArrayList<Integer> anos;
	private String nome;

	/**
	 * Create the dialog.
	 */
	public ExcluirDialogMenor(String tab, WindowListener att) {
		nome = tab;
		this.setName(tab);
		setBounds(100, 100, 450, 218);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		updateTabela(tab);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(68, 123, 105, 37);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ind = index.get(comboBoxNome.getSelectedIndex() - 1);
				try {
					ExcluirEditarTabelas.ExcluirTabelas(tabela, ind, nome);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados. Verifique a conexão com a internet", "Erro", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
				dispose();
			}
		});
		contentPanel.add(btnExcluir);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(263, 123, 105, 37);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPanel.add(btnCancelar);

		JLabel lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblAno.setBounds(44, 13, 81, 27);
		contentPanel.add(lblAno);

		comboBoxAno = new JComboBox(anos.toArray(new Integer[anos.size()]));
		comboBoxAno.addItem("Selecione");
		comboBoxAno.setSelectedIndex(comboBoxAno.getItemCount()-1);
		comboBoxAno.setBounds(182, 11, 225, 37);
		comboBoxAno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaNomes();
			}
		});
		contentPanel.add(comboBoxAno);

		JLabel lblDespesa = new JLabel(nome);
		lblDespesa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDespesa.setBounds(44, 61, 81, 27);
		contentPanel.add(lblDespesa);

		comboBoxNome = new JComboBox(selecionados.toArray(new String[anos.size()]));
		comboBoxNome.setBounds(182, 59, 225, 37);
		contentPanel.add(comboBoxNome);

		this.addWindowListener(att);

	}

	private void updateTabela(String nome) {
		ArrayList<Object> retorno = null;
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

				selecionados.add((String) tabela[i][5]);
				index.add(i);
				comboBoxNome.addItem(cont + " - " + tabela[i][5].toString());
				cont++;
			}
		}

	}
}
