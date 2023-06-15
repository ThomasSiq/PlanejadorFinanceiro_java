package gui.dialogWindows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import dao.EditarDadosDao;
import dao.PegaDadosDao;
import dao.RemoveDadosDao;
import service.MainService;

public class ExcluirDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JComboBox comboBoxCategoria;
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
	public ExcluirDialog(String tab, WindowListener att) {
		nome = tab;
		setBounds(100, 100, 450, 278);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		updateTabela(tab);

		Object[] lista = PegaDadosDao.getCategoria(nome).toArray();
		comboBoxCategoria = new JComboBox(lista);
		comboBoxCategoria.setBounds(133, 20, 272, 37);

		comboBoxCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaNomes();
			}
		});
		contentPanel.add(comboBoxCategoria);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(66, 185, 105, 37);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveDadosDao.removeTupla(nome,Integer.toString((int)tabela[index.get(comboBoxNome.getSelectedIndex()-1)][0]));
			dispose();
			}
		});
		contentPanel.add(btnExcluir);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(261, 185, 105, 37);
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
		comboBoxAno.setBounds(133, 73, 272, 37);
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
		comboBoxNome.setBounds(133, 121, 272, 37);
		contentPanel.add(comboBoxNome);
		
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
				if (((String) tabela[i][4]).equals((String) comboBoxCategoria.getSelectedItem())) {
					selecionados.add((String) tabela[i][5]);
					index.add(i);
					System.out.println(i);
					System.out.println(tabela[i][0]);
					comboBoxNome.addItem(cont +" - "+ tabela[i][5].toString());
					cont++;
				}
			}
		}

	}
}
