package gui.btnpanel;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class ButtonsPanelLongoPrazo extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonsPanelLongoPrazo(ActionListener evento) {
		
		JButton homeButton = new JButton("Home");
		homeButton.setName("Home");
		homeButton.setBounds(103, 11, 84, 23);
		homeButton.addActionListener(evento);
		setLayout(null);
		add(homeButton);
		
		JTextPane txtpnDespesas = new JTextPane();
		txtpnDespesas.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 30));
		txtpnDespesas.setText("Investimento de longo prazo");
		txtpnDespesas.setBounds(37, 68, 231, 122);
		add(txtpnDespesas);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnNovo.setBounds(94, 254, 128, 54);
		add(btnNovo);
		
		JButton btnEditarCategoria_1 = new JButton("Editar");
		btnEditarCategoria_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnEditarCategoria_1.setBounds(94, 319, 128, 54);
		add(btnEditarCategoria_1);
		
		JButton btnExcluirCategoria_1 = new JButton("Excluir");
		btnExcluirCategoria_1.setToolTipText("");
		btnExcluirCategoria_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnExcluirCategoria_1.setBounds(94, 384, 128, 54);
		add(btnExcluirCategoria_1);
		
	}

}
