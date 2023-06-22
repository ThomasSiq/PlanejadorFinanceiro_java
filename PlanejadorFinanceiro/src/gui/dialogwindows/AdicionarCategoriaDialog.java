package gui.dialogwindows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.InsereDadosDao;
import service.MainService;

public class AdicionarCategoriaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCategoria;
	private String nome;
	/**
	 * Create the dialog.
	 */
	public AdicionarCategoriaDialog(String tab) {
		nome = tab;
		this.setName(tab);
		setMinimumSize(new Dimension(450, 180));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(68, 95, 105, 37);
		contentPanel.add(btnAdicionar);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InsereDadosDao.insertCategoria(nome, textCategoria.getText(), MainService.getSenha());
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados. Verifique a conexão com a internet", "Erro", JOptionPane.WARNING_MESSAGE);
					
				}
				dispose();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(263, 95, 105, 37);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPanel.add(btnCancelar);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCategoria.setBounds(27, 30, 81, 27);
		contentPanel.add(lblCategoria);
		
		textCategoria = new JTextField();
		textCategoria.addKeyListener(new KeyAdapter() {
			 public void keyTyped(KeyEvent e) { 
				JTextField txt = (JTextField)e.getSource();
				if(txt.getText().length()>48) {
					txt.setText(txt.getText().substring(0, 48));
				};
			}
		});
		textCategoria.setColumns(10);
		textCategoria.setBounds(108, 28, 291, 37);
		contentPanel.add(textCategoria);
	}
}
