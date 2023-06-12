package gui.despesa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.InsertDadosDao;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarCategoriaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textCategoria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdicionarCategoriaDialog dialog = new AdicionarCategoriaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdicionarCategoriaDialog() {
		setBounds(100, 100, 450, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(68, 95, 105, 37);
		contentPanel.add(btnAdicionar);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertDadosDao.insertCategoria("Despesas", textCategoria.getText());
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
		textCategoria.setColumns(10);
		textCategoria.setBounds(108, 28, 291, 37);
		contentPanel.add(textCategoria);
	}
}
