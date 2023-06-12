package gui.despesa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PegaDadosDao;
import dao.RemoveDadosDao;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class RemoveCategoriaDespesaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoveCategoriaDespesaDialog dialog = new RemoveCategoriaDespesaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RemoveCategoriaDespesaDialog() {
		setBounds(100, 100, 450, 183);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Object[] lista = PegaDadosDao.getCategoria("Despesas").toArray();
		comboBox = new JComboBox(lista);
		comboBox.setBounds(133, 20, 272, 37);
		contentPanel.add(comboBox);

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(73, 95, 105, 37);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveDadosDao.removeCategoria("Despesas",(String)comboBox.getSelectedItem());
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
