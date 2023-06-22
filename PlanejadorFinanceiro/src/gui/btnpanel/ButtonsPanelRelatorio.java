package gui.btnpanel;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ButtonsPanelRelatorio extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonsPanelRelatorio(ActionListener evento, ActionListener exportEvent) {
		this.setName("Relatorio");
		JButton homeButton = new JButton("Home");
		homeButton.setName("Home");
		homeButton.setBounds(100, 11, 84, 23);
		homeButton.addActionListener(evento);
		setLayout(null);
		add(homeButton);

		JButton btnNewButton = new JButton("Gerar Relatório");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton.setBounds(38, 151, 221, 66);
		btnNewButton.addActionListener(exportEvent);
		add(btnNewButton);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.controlShadow);
		separator_1.setBounds(10, 45, 276, 2);
		add(separator_1);
		
		JLabel lblRelatrio = new JLabel("Relatório");
		lblRelatrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatrio.setFont(new Font("UD Digi Kyokasho NK-B", Font.BOLD, 34));
		lblRelatrio.setBounds(9, 71, 277, 43);
		add(lblRelatrio);

	}
}
