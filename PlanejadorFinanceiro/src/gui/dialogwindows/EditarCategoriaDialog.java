package gui.dialogwindows;

import java.awt.BorderLayout; 
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.EditaDadosDao;
import dao.PegaDadosDao;
import dao.RemoveDadosDao;
import service.MainService;

import javax.swing.JEditorPane;

public class EditarCategoriaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JComboBox comboBox;
	private final JEditorPane editorPane;
	private String nome;
	/**
	 * Create the dialog.
	 */
	public EditarCategoriaDialog(String tab) {
		nome = tab;
		this.setName(tab);
		setBounds(100, 100, 450, 213);
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
		comboBox.addItem("Selecione");
		comboBox.setSelectedIndex(comboBox.getItemCount()-1);
		comboBox.setBounds(133, 20, 272, 37);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorPane.setText((String)comboBox.getSelectedItem());
				editorPane.setEditable(true);
			}
		});
		contentPanel.add(comboBox);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(73, 126, 105, 37);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					EditaDadosDao.editarCategoria(nome,(String)comboBox.getSelectedItem(),editorPane.getText(), MainService.getSenha() );
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados. Verifique a conexão com a internet", "Erro", JOptionPane.WARNING_MESSAGE);

					e1.printStackTrace();
				}
				dispose();
			}
		});
		contentPanel.add(btnEditar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(268, 126, 105, 37);
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
		
		editorPane = new JEditorPane();
		editorPane.addKeyListener(new KeyAdapter() {
			 public void keyTyped(KeyEvent e) { 
				 JEditorPane txt = (JEditorPane)e.getSource();
				if(txt.getText().length()>48) {
					txt.setText(txt.getText().substring(0, 48));
				};
			}
		});
		editorPane.setBounds(42, 68, 363, 37);
		editorPane.setEditable(false);
		contentPanel.add(editorPane);


	}
}
