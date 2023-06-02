package gui.btnpanel;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;

public class ButtonsPanelHome extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonsPanelHome(ActionListener evento) {
		
		JButton homeButton = new JButton("Home");
		homeButton.setBounds(96, 11, 87, 23);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnRendimento = new JButton("Rendimentos");
		btnRendimento.setName("Rendimentos");
		btnRendimento.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnRendimento.setBounds(10, 45, 257, 54);
		btnRendimento.addActionListener(evento);
		
		JButton btnDespesas = new JButton("Despesas");
		btnDespesas.setName("Despesas");
		btnDespesas.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnDespesas.setBounds(10, 110, 257, 54);
		btnDespesas.addActionListener(evento);
		
		JButton btnLongoPrazo = new JButton("Investimentos de \r\nlongo prazo");
		btnLongoPrazo.setName("LongoPrazo");
		btnLongoPrazo.setToolTipText("");
		btnLongoPrazo.addActionListener(evento);
		btnLongoPrazo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		btnLongoPrazo.setBounds(10, 266, 257, 81);
		
		JButton btnResumo = new JButton("Resumo");
		btnResumo.setName("Resumo");
		btnResumo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnResumo.setBounds(10, 358, 257, 54);
		btnResumo.addActionListener(evento);
		
		JButton btnRelatorio = new JButton("Relatório");
		btnRelatorio.setName("Relatorio");
		btnRelatorio.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnRelatorio.setBounds(10, 423, 257, 54);
		btnRelatorio.addActionListener(evento);
		
		JButton btnOcasionais = new JButton("Fundo para despesas ocasionais");
		btnOcasionais.setToolTipText("");
		btnOcasionais.setName("Ocasionais");
		btnOcasionais.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		btnOcasionais.setBounds(10, 174, 257, 81);
		btnOcasionais.addActionListener(evento);
		
		setLayout(null);
		add(homeButton);
		add(btnRendimento);
		add(btnDespesas);
		add(btnLongoPrazo);
		add(btnResumo);
		add(btnRelatorio);
		add(btnOcasionais);
		
		
	}
}
