package gui.btnpanel;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gui.dialogWindows.AdicionarDialog;
import gui.dialogWindows.AdicionarDialogMenor;
import gui.dialogWindows.EditarDialog;
import gui.dialogWindows.EditarDialogMenor;
import gui.dialogWindows.ExcluirDialog;
import gui.dialogWindows.ExcluirDialogMenor;

import javax.swing.JTextArea;
import java.awt.SystemColor;

public class ButtonsPanelOcasional extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonsPanelOcasional(ActionListener evento, final WindowListener att) {
		
		setBounds(10, 11, 277, 528);
		
		JButton homeButton = new JButton("Home");
		homeButton.setName("Home");
		homeButton.setBounds(103, 11, 84, 23);
		homeButton.addActionListener(evento);
		setLayout(null);
		add(homeButton);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnNovo.setBounds(75, 254, 128, 54);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarDialogMenor diag = new AdicionarDialogMenor("Ocasional", att);
				diag.setVisible(true);
			}
		});
		add(btnNovo);
		
		JButton btnEditarCategoria_1 = new JButton("Editar");
		btnEditarCategoria_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnEditarCategoria_1.setBounds(75, 319, 128, 54);
		btnEditarCategoria_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarDialogMenor diag = new EditarDialogMenor("Ocasional", att);
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
				ExcluirDialogMenor diag = new ExcluirDialogMenor("Ocasional", att);
				diag.setVisible(true);
			}
		});
		add(btnExcluirCategoria_1);
		
		JTextArea txtrFundoParaDespesas = new JTextArea();
		txtrFundoParaDespesas.setBackground(SystemColor.control);
		txtrFundoParaDespesas.setWrapStyleWord(true);
		txtrFundoParaDespesas.setLineWrap(true);
		txtrFundoParaDespesas.setFont(new Font("Monospaced", Font.PLAIN, 23));
		txtrFundoParaDespesas.setText("Fundo para despesas ocasionais");
		txtrFundoParaDespesas.setBounds(51, 63, 189, 118);
		add(txtrFundoParaDespesas);
		
	}
}
