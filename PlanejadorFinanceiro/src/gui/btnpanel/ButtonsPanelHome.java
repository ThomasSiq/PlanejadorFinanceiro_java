package gui.btnpanel;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.SystemColor;

public class ButtonsPanelHome extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonsPanelHome(ActionListener evento) {
		this.setName("Home");
		
		JButton homeButton = new JButton("Home");
		homeButton.setBounds(100, 11, 87, 23);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnRendimento = new JButton("Rendimentos");
		btnRendimento.setName("Rendimentos");
		btnRendimento.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnRendimento.setBounds(10, 54, 257, 54);
		btnRendimento.addActionListener(evento);
		
		JButton btnDespesas = new JButton("Despesas");
		btnDespesas.setName("Despesas");
		btnDespesas.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnDespesas.setBounds(10, 119, 257, 54);
		btnDespesas.addActionListener(evento);
		
		JButton btnLongoPrazo = new JButton("Investimentos de \r\nlongo prazo");
		btnLongoPrazo.setName("LongoPrazo");
		btnLongoPrazo.setToolTipText("");
		btnLongoPrazo.addActionListener(evento);
		btnLongoPrazo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		btnLongoPrazo.setBounds(10, 275, 257, 81);
		
		JButton btnResumo = new JButton("Resumo");
		btnResumo.setName("Resumo");
		btnResumo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnResumo.setBounds(10, 367, 257, 54);
		btnResumo.addActionListener(evento);
		
		JButton btnRelatorio = new JButton("Relatório");
		btnRelatorio.setName("Relatorio");
		btnRelatorio.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnRelatorio.setBounds(10, 432, 257, 54);
		btnRelatorio.addActionListener(evento);
		
		JButton btnOcasionais = new JButton("Fundo para despesas ocasionais");
		btnOcasionais.setToolTipText("");
		btnOcasionais.setName("Ocasional");
		btnOcasionais.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		btnOcasionais.setBounds(10, 183, 257, 81);
		btnOcasionais.addActionListener(evento);
		
		setLayout(null);
		add(homeButton);
		add(btnRendimento);
		add(btnDespesas);
		add(btnLongoPrazo);
		add(btnResumo);
		add(btnRelatorio);
		add(btnOcasionais);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.controlShadow);
		separator.setBounds(10, 45, 264, 7);
		add(separator);
		
		
	}
}
