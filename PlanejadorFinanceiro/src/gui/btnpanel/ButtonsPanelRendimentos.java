package gui.btnpanel;

import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import gui.dialogwindows.AdicionarCategoriaDialog;
import gui.dialogwindows.AdicionarDialog;
import gui.dialogwindows.EditarCategoriaDialog;
import gui.dialogwindows.EditarDialog;
import gui.dialogwindows.ExcluirDialog;
import gui.dialogwindows.RemoveCategoriaDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class ButtonsPanelRendimentos extends JPanel {
	
	

	/**
	 * Create the panel.
	 */
	public ButtonsPanelRendimentos(ActionListener evento, final WindowListener att) {
		setName("Rendimentos");

		JButton homeButton = new JButton("Home");
		homeButton.setName("Home");
		homeButton.setBounds(100, 11, 84, 23);
		homeButton.addActionListener(evento);

		JButton btnNovaCategoria = new JButton("Nova");
		btnNovaCategoria.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnNovaCategoria.setBounds(10, 254, 128, 54);
		btnNovaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarCategoriaDialog diag = new AdicionarCategoriaDialog("Rendimentos");
				diag.setVisible(true);
				Component com = (Component) (e.getSource());
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
		
		JLabel lblRendimentos = new JLabel("Rendimentos");
		lblRendimentos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRendimentos.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 34));
		lblRendimentos.setBounds(0, 80, 277, 43);
		add(lblRendimentos);
		
		JLabel lblNewLabel_1 = new JLabel("Categorias");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(10, 215, 128, 28);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Rendimentos");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1_1.setBounds(148, 215, 128, 28);
		add(lblNewLabel_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.controlShadow);
		separator.setBounds(10, 45, 264, 7);
		add(separator);

	}
}
