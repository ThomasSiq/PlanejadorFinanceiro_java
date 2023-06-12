package gui.despesa;

import java.awt.BorderLayout; 
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EditarDadosDao;
import dao.PegaDadosDao;
import dao.RemoveDadosDao;
import javax.swing.JEditorPane;

public class EditarCategoriaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JComboBox comboBox;
	private final JEditorPane editorPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditarCategoriaDialog dialog = new EditarCategoriaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditarCategoriaDialog() {
		setBounds(100, 100, 450, 213);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Object[] lista = PegaDadosDao.getCategoria("Despesas").toArray();
		comboBox = new JComboBox(lista);
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
				
				EditarDadosDao.editarCategoria("Despesas",(String)comboBox.getSelectedItem(),editorPane.getText() );
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
		editorPane.setBounds(42, 68, 363, 37);
		editorPane.setEditable(false);
		contentPanel.add(editorPane);


	}
}
