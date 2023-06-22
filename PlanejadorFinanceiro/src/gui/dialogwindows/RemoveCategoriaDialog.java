package gui.dialogwindows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PegaDadosDao;
import dao.RemoveDadosDao;
import service.MainService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class RemoveCategoriaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JComboBox comboBox;
	private String nome;

	/**
	 * Create the dialog.
	 */
	public RemoveCategoriaDialog(String tab) {
		nome = tab;
		this.setName(tab);
		setBounds(100, 100, 450, 183);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Object[] lista = null;
		try {
			lista = PegaDadosDao.getCategoria(nome, MainService.getSenha()).toArray();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados. Verifique a conexão com a internet", "Erro", JOptionPane.WARNING_MESSAGE);

			e.printStackTrace();
		}
		comboBox = new JComboBox(lista);
		comboBox.setBounds(133, 20, 272, 37);
		comboBox.addItem("Selecione");
		comboBox.setSelectedIndex(comboBox.getItemCount()-1);
		contentPanel.add(comboBox);

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(73, 95, 105, 37);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RemoveDadosDao.removeCategoria(nome,(String)comboBox.getSelectedItem(), MainService.getSenha());
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados. Verifique a conexão com a internet", "Erro", JOptionPane.WARNING_MESSAGE);

					e1.printStackTrace();
				}
				dispose();
			}
		});
		contentPanel.add(btnRemover);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(268, 95, 105, 37);
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


	}
}
