package gui.btnpanel;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import gui.dialogwindows.AdicionarDialogMenor;
import gui.dialogwindows.EditarDialogMenor;
import gui.dialogwindows.ExcluirDialogMenor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class ButtonsPanelLongoPrazo extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonsPanelLongoPrazo(ActionListener evento, final WindowListener att) {
		this.setName("LongoPrazo");
		setBounds(10, 11, 277, 528);
		
		JButton homeButton = new JButton("Home");
		homeButton.setName("Home");
		homeButton.setBounds(100, 11, 84, 23);
		homeButton.addActionListener(evento);
		setLayout(null);
		add(homeButton);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnNovo.setBounds(75, 254, 128, 54);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarDialogMenor diag = new AdicionarDialogMenor("LongoPrazo", att);
				diag.setVisible(true);
			}
		});
		add(btnNovo);
		
		JButton btnEditarCategoria_1 = new JButton("Editar");
		btnEditarCategoria_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnEditarCategoria_1.setBounds(75, 319, 128, 54);
		btnEditarCategoria_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarDialogMenor diag = new EditarDialogMenor("LongoPrazo", att);
				diag.setVisible(true);
			}
		});
		add(btnEditarCategoria_1);
		
		JButton btnExcluirCategoria_1 = new JButton("Excluir");
		btnExcluirCategoria_1.setToolTipText("");
		btnExcluirCategoria_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnExcluirCategoria_1.setBounds(75, 388, 128, 54);
		btnExcluirCategoria_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExcluirDialogMenor diag = new ExcluirDialogMenor("LongoPrazo", att);
				diag.setVisible(true);
			}
		});
		add(btnExcluirCategoria_1);
		
		JLabel lblNewLabel = new JLabel("Fundo Para:");
		lblNewLabel.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 78, 277, 30);
		add(lblNewLabel);
		
		JLabel lblDespesasOcasionais = new JLabel("Investimentos de");
		lblDespesasOcasionais.setHorizontalAlignment(SwingConstants.CENTER);
		lblDespesasOcasionais.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 27));
		lblDespesasOcasionais.setBounds(0, 117, 277, 43);
		add(lblDespesasOcasionais);
		
		JLabel lblOcasionais = new JLabel("Longo Prazo");
		lblOcasionais.setHorizontalAlignment(SwingConstants.CENTER);
		lblOcasionais.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 34));
		lblOcasionais.setBounds(0, 161, 277, 40);
		add(lblOcasionais);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 45, 264, 7);
		separator.setForeground(SystemColor.controlShadow);
		add(separator);
		
	}
}