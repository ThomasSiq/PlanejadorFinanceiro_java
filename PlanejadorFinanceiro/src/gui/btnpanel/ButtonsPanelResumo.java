package gui.btnpanel;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class ButtonsPanelResumo extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonsPanelResumo(ActionListener evento) {
		JButton homeButton = new JButton("Home");
		homeButton.setName("Home");
		homeButton.setBounds(103, 11, 84, 23);
		homeButton.addActionListener(evento);
		
		JButton btnRendimentos = new JButton("Rendimentos");
		btnRendimentos.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnRendimentos.setBounds(10, 190, 257, 39);
		
		
		JButton btnLongoPrazo = new JButton("Investimentos Longo Prazo");
		btnLongoPrazo.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnLongoPrazo.setBounds(10, 240, 257, 39);
		
		JButton btnDespesasOcasionais = new JButton("Fundos P/ despesas ocasionais");
		btnDespesasOcasionais.setToolTipText("");
		btnDespesasOcasionais.addActionListener(evento);
		btnDespesasOcasionais.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnDespesasOcasionais.setBounds(10, 290, 257, 47);
		setLayout(null);
		add(homeButton);
		add(btnRendimentos);
		add(btnLongoPrazo);
		add(btnDespesasOcasionais);
		
		JButton btnDisponivel = new JButton("Total disponivel");
		btnDisponivel.setToolTipText("");
		btnDisponivel.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnDisponivel.setBounds(10, 348, 257, 47);
		add(btnDisponivel);
		
		JButton btnDespesasOrcadas = new JButton("Despesas totais");
		btnDespesasOrcadas.setToolTipText("");
		btnDespesasOrcadas.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnDespesasOrcadas.setBounds(10, 406, 257, 47);
		add(btnDespesasOrcadas);
		
		JButton btnTotal = new JButton("Resultado");
		btnTotal.setToolTipText("");
		btnTotal.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 23));
		btnTotal.setBounds(10, 464, 257, 47);
		add(btnTotal);
		
		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPeriodo.setBounds(21, 102, 121, 23);
		add(lblPeriodo);
		
		JPanel radioPanel = new JPanel();
		radioPanel.setBounds(118, 102, 126, 33);
		add(radioPanel);
		
		JRadioButton rdbtnMes = new JRadioButton("Mês");
		radioPanel.add(rdbtnMes);
		
		JRadioButton rdbtnAno = new JRadioButton("Ano");
		radioPanel.add(rdbtnAno);
		
		JComboBox comboBoxMes = new JComboBox();
		comboBoxMes.setBounds(141, 146, 126, 22);
		add(comboBoxMes);
		
		JComboBox comboBoxAno = new JComboBox();
		comboBoxAno.setBounds(10, 146, 98, 22);
		add(comboBoxAno);
		
		JLabel lblResumo = new JLabel("Resumo");
		lblResumo.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 28));
		lblResumo.setBounds(85, 55, 111, 39);
		add(lblResumo);
		
	}
}
