package windowsgroup;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;

public class buttonsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public buttonsPanel() {
		
		JButton homeButton = new JButton("Home");
		homeButton.setBounds(109, 11, 59, 23);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnRendimento = new JButton("Rendimentos");
		btnRendimento.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnRendimento.setBounds(10, 45, 257, 54);
		btnRendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnDespesas = new JButton("Despesas");
		btnDespesas.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnDespesas.setBounds(10, 110, 257, 54);
		
		JButton btnLongoPrazo = new JButton("Investimentos de \r\nlongo prazo");
		btnLongoPrazo.setToolTipText("");
		btnLongoPrazo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLongoPrazo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		btnLongoPrazo.setBounds(10, 175, 257, 81);
		
		JButton btnResumo = new JButton("Resumo");
		btnResumo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnResumo.setBounds(10, 267, 257, 54);
		
		JButton btnRelatorio = new JButton("Relatório");
		btnRelatorio.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnRelatorio.setBounds(10, 332, 257, 54);
		setLayout(null);
		add(homeButton);
		add(btnRendimento);
		add(btnDespesas);
		add(btnLongoPrazo);
		add(btnResumo);
		add(btnRelatorio);
		
	}
}
