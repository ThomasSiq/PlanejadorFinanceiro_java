package gui.btnpanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import gui.dialogWindows.AdicionarCategoriaDialog;
import gui.dialogWindows.AdicionarDialog;
import gui.dialogWindows.EditarCategoriaDialog;
import gui.dialogWindows.EditarDialog;
import gui.dialogWindows.ExcluirDialog;
import gui.dialogWindows.RemoveCategoriaDialog;

public class ButtonsPanelRendimentos extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonsPanelRendimentos(ActionListener evento,final WindowListener att) {

		JButton homeButton = new JButton("Home");
		homeButton.setName("Home");
		homeButton.setBounds(103, 11, 84, 23);
		homeButton.addActionListener(evento);

		JButton btnNovaCategoria = new JButton("Nova");
		btnNovaCategoria.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnNovaCategoria.setBounds(10, 254, 128, 54);
		btnNovaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarCategoriaDialog diag = new AdicionarCategoriaDialog("Rendimentos");
				diag.setVisible(true);
			}
		});

		JButton btnEditarCategoria = new JButton("Editar");
		btnEditarCategoria.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnEditarCategoria.setBounds(10, 319, 128, 54);
		btnEditarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarCategoriaDialog diag = new EditarCategoriaDialog("Rendimentos");
				diag.setVisible(true);
			}
		});

		JButton btnExcluirCategoria = new JButton("Excluir");
		btnExcluirCategoria.setToolTipText("");
		btnExcluirCategoria.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnExcluirCategoria.setBounds(10, 384, 128, 54);
		btnExcluirCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveCategoriaDialog diag = new RemoveCategoriaDialog("Rendimentos");
				diag.setVisible(true);
			}
		});
		
		
		setLayout(null);
		add(homeButton);
		add(btnNovaCategoria);
		add(btnEditarCategoria);
		add(btnExcluirCategoria);

		JTextPane txtpnDespesas = new JTextPane();
		txtpnDespesas.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 30));
		txtpnDespesas.setText("Rendimentos");
		txtpnDespesas.setBounds(36, 54, 212, 36);
		add(txtpnDespesas);
		

		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnNovo.setBounds(148, 254, 128, 54);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarDialog diag = new AdicionarDialog("Rendimentos", att);
				diag.setVisible(true);
			}
		});
		add(btnNovo);

		JButton btnEditarCategoria_1 = new JButton("Editar");
		btnEditarCategoria_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnEditarCategoria_1.setBounds(148, 319, 128, 54);
		btnEditarCategoria_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarDialog diag = new EditarDialog("Rendimentos", att);
				diag.setVisible(true);
			}
		});
		add(btnEditarCategoria_1);

		JButton btnExcluirCategoria_1 = new JButton("Excluir");
		btnExcluirCategoria_1.setToolTipText("");
		btnExcluirCategoria_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnExcluirCategoria_1.setBounds(148, 384, 128, 54);
		btnExcluirCategoria_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExcluirDialog diag = new ExcluirDialog("Rendimentos", att);
				diag.setVisible(true);
			}
		});
	
		add(btnExcluirCategoria_1);

		JTextPane txtpnCategorias = new JTextPane();
		txtpnCategorias.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtpnCategorias.setText("  Categorias");
		txtpnCategorias.setBounds(10, 209, 128, 34);
		add(txtpnCategorias);

		JTextPane txtpnRendimentos = new JTextPane();
		txtpnRendimentos.setText("Rendimentos");
		txtpnRendimentos.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtpnRendimentos.setBounds(148, 209, 128, 34);
		add(txtpnRendimentos);

	}
}
