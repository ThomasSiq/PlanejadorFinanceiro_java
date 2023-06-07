package gui.btnpanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import gui.dialogWindows.AdicionarDespesasDialog;
public class ButtonsPanelDespesas extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonsPanelDespesas(ActionListener evento) {

		JButton homeButton = new JButton("Home");
		homeButton.setName("Home");
		homeButton.setBounds(103, 11, 84, 23);
		homeButton.addActionListener(evento);

		JButton btnNovaCategoria = new JButton("Nova");
		btnNovaCategoria.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnNovaCategoria.setBounds(10, 254, 128, 54);

		JButton btnEditarCategoria = new JButton("Editar");
		btnEditarCategoria.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnEditarCategoria.setBounds(10, 319, 128, 54);

		JButton btnExcluirCategoria = new JButton("Excluir");
		btnExcluirCategoria.setToolTipText("");
		btnExcluirCategoria.addActionListener(evento);
		btnExcluirCategoria.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnExcluirCategoria.setBounds(10, 384, 128, 54);
		setLayout(null);
		add(homeButton);
		add(btnNovaCategoria);
		add(btnEditarCategoria);
		add(btnExcluirCategoria);

		JTextPane txtpnDespesas = new JTextPane();
		txtpnDespesas.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 30));
		txtpnDespesas.setText("Despesas");
		txtpnDespesas.setBounds(36, 54, 212, 36);
		add(txtpnDespesas);
		

		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnNovo.setBounds(148, 254, 128, 54);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarDespesasDialog diag = new AdicionarDespesasDialog();
				diag.setVisible(true);
			}
		});
		add(btnNovo);

		JButton btnEditarCategoria_1 = new JButton("Editar");
		btnEditarCategoria_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnEditarCategoria_1.setBounds(148, 319, 128, 54);
		add(btnEditarCategoria_1);

		JButton btnExcluirCategoria_1 = new JButton("Excluir");
		btnExcluirCategoria_1.setToolTipText("");
		btnExcluirCategoria_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnExcluirCategoria_1.setBounds(148, 384, 128, 54);
		add(btnExcluirCategoria_1);

		JTextPane txtpnCategorias = new JTextPane();
		txtpnCategorias.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtpnCategorias.setText("  Categorias");
		txtpnCategorias.setBounds(10, 209, 128, 34);
		add(txtpnCategorias);

		JTextPane txtpnDespesas_1 = new JTextPane();
		txtpnDespesas_1.setText("Despesas");
		txtpnDespesas_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtpnDespesas_1.setBounds(148, 209, 128, 34);
		add(txtpnDespesas_1);

	}
}
