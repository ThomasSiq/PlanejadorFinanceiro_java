package gui.btnpanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import gui.contentpanel.ContentPanelResumo;

public class ButtonsPanelResumo extends JPanel {

	private ArrayList<String> nomes = new ArrayList();
	private JCheckBox chckbxTotalDespesas;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxInvestimentoDeLongo;
	private JCheckBox chckbxFundosPDespesas;
	private JCheckBox chckbxTotalDisponivel;
	private JCheckBox chckbxResultado;
	private ContentPanelResumo ctpanelResumo;

	public ButtonsPanelResumo(ActionListener evento, ContentPanelResumo ctpanel) {
		ctpanelResumo = ctpanel;
		nomes.add("Rendimentos");
		nomes.add("Despesas");

		JButton homeButton = new JButton("Home");
		homeButton.setName("Home");
		homeButton.setBounds(103, 11, 84, 23);
		homeButton.addActionListener(evento);
		setLayout(null);
		add(homeButton);

		JLabel lblResumo = new JLabel("Resumo");
		lblResumo.setFont(new Font("UD Digi Kyokasho NK-B", Font.PLAIN, 28));
		lblResumo.setBounds(85, 55, 111, 39);
		add(lblResumo);

		chckbxNewCheckBox = new JCheckBox("Rendimentos");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		chckbxNewCheckBox.setBounds(16, 113, 258, 39);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaNomes(ctpanelResumo);
			}
		});
		add(chckbxNewCheckBox);

		chckbxInvestimentoDeLongo = new JCheckBox("Investimento de Longo Prazo");
		chckbxInvestimentoDeLongo.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxInvestimentoDeLongo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxInvestimentoDeLongo.setBounds(16, 203, 258, 39);
		chckbxInvestimentoDeLongo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaNomes(ctpanelResumo);
			}
		});
		add(chckbxInvestimentoDeLongo);

		chckbxFundosPDespesas = new JCheckBox("Fundos p/ Despesas Ocasionais");
		chckbxFundosPDespesas.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxFundosPDespesas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxFundosPDespesas.setBounds(16, 248, 258, 39);
		chckbxFundosPDespesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaNomes(ctpanelResumo);
			}
		});
		add(chckbxFundosPDespesas);

		chckbxTotalDisponivel = new JCheckBox("Total Disponivel");
		chckbxTotalDisponivel.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxTotalDisponivel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		chckbxTotalDisponivel.setBounds(16, 293, 258, 39);
		chckbxTotalDisponivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaNomes(ctpanelResumo);
			}
		});
		add(chckbxTotalDisponivel);

		chckbxTotalDespesas = new JCheckBox("Total Despesas");
		chckbxTotalDespesas.setSelected(true);
		chckbxTotalDespesas.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxTotalDespesas.setFont(new Font("Tahoma", Font.PLAIN, 17));
		chckbxTotalDespesas.setBounds(16, 158, 258, 39);
		chckbxTotalDespesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaNomes(ctpanelResumo);
			}
		});
		add(chckbxTotalDespesas);

		chckbxResultado = new JCheckBox("Resultado");
		chckbxResultado.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxResultado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		chckbxResultado.setBounds(16, 338, 258, 39);
		chckbxResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mudaNomes(ctpanelResumo);
			}
		});
		add(chckbxResultado);

	}

	private void mudaNomes(ContentPanelResumo ctpanelResumo) {
		nomes.clear();
		if (chckbxNewCheckBox.isSelected()) {
			nomes.add("Rendimentos");
		}

		if (chckbxFundosPDespesas.isSelected()) {
			nomes.add("Ocasional");
		}

		if (chckbxInvestimentoDeLongo.isSelected()) {
			nomes.add("LongoPrazo");
		}
		if (chckbxTotalDespesas.isSelected()) {
			nomes.add("Despesas");
		}

		if (chckbxTotalDisponivel.isSelected()) {
			nomes.add("Total Disponivel");
		}

		if (chckbxResultado.isSelected()) {
			nomes.add("Resultado");
		}
		ctpanelResumo.setNomes(nomes);
	}
}
