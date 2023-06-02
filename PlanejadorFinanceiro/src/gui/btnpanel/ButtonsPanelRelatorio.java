package gui.btnpanel;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class ButtonsPanelRelatorio extends JPanel {

	/**
	 * Create the panel.
	 */
	public ButtonsPanelRelatorio(ActionListener evento) {
		JButton homeButton = new JButton("Home");
		homeButton.setName("Home");
		homeButton.setBounds(103, 11, 84, 23);
		homeButton.addActionListener(evento);
		setLayout(null);
		add(homeButton);
		
		JLabel lblNewLabel = new JLabel("Relatório");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(93, 78, 94, 34);
		add(lblNewLabel);
		
		JLabel lblPeriodo = new JLabel("Periodo");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPeriodo.setBounds(43, 149, 121, 23);
		add(lblPeriodo);
		
		JPanel radioPanel = new JPanel();
		radioPanel.setBounds(141, 149, 126, 34);
		add(radioPanel);
		
		JRadioButton rdbtnMes = new JRadioButton("Mês");
		radioPanel.add(rdbtnMes);
		
		JRadioButton rdbtnAno = new JRadioButton("Ano");
		radioPanel.add(rdbtnAno);
		
		JComboBox comboBoxAno = new JComboBox();
		comboBoxAno.setBounds(10, 199, 98, 22);
		add(comboBoxAno);
		
		JComboBox comboBoxMes = new JComboBox();
		comboBoxMes.setBounds(141, 199, 126, 22);
		add(comboBoxMes);
		
		JButton btnNewButton = new JButton("Gerar Relatório");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton.setBounds(33, 429, 221, 66);
		add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 416, 276, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 257, 276, 2);
		add(separator_1);
		
		JRadioButton rdbtnPersonalizado = new JRadioButton("Personalizado");
		rdbtnPersonalizado.setBounds(118, 273, 126, 23);
		add(rdbtnPersonalizado);
		
		JLabel lblPeriodo_1 = new JLabel("Periodo");
		lblPeriodo_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPeriodo_1.setBounds(21, 270, 121, 23);
		add(lblPeriodo_1);
		
		JComboBox comboBoxMes_1 = new JComboBox();
		comboBoxMes_1.setBounds(152, 320, 126, 22);
		add(comboBoxMes_1);
		
		JComboBox comboBoxAno_1 = new JComboBox();
		comboBoxAno_1.setBounds(21, 320, 98, 22);
		add(comboBoxAno_1);
		
		JComboBox comboBoxMes_2 = new JComboBox();
		comboBoxMes_2.setBounds(152, 372, 126, 22);
		add(comboBoxMes_2);
		
		JComboBox comboBoxAno_2 = new JComboBox();
		comboBoxAno_2.setBounds(21, 372, 98, 22);
		add(comboBoxAno_2);
		
	}
}
