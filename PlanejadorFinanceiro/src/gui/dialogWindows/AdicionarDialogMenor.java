package gui.dialogWindows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.border.EtchedBorder;

import dao.PegaDadosDao;
import enumdata.MesesEnum;

import service.MainService;

import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import java.awt.Font;

public class AdicionarDialogMenor extends JDialog {

	private JPanel contentPane;
	private JTextField textFieldDespesa;
	private JTextField textFieldAnoMensal;
	private JTextField textFieldValorOcasional;
	private JTextField textFieldValorMensal;
	private JComboBox comboBoxMesOcasional;
	private JComboBox comboBoxMesMensal;
	private JTextField textFieldAnoOcasional;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_3; 
	private JSpinner spinnerDuracao;
	private JRadioButton rdbtnOcasional;
	private JRadioButton rdbtnMensal;
	private String nome;
	/**
	 * Create the frame.
	 */
	public AdicionarDialogMenor(String tab, WindowListener att) {
		nome = tab;
		setBounds(100, 100, 450, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDataDatabase();
			}
		});
		btnAdicionar.setBounds(67, 266, 105, 37);
		contentPane.add(btnAdicionar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(262, 266, 105, 37);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnCancelar);
		
		textFieldDespesa = new JTextField();
		textFieldDespesa.setBounds(87, 55, 337, 20);
		contentPane.add(textFieldDespesa);
		textFieldDespesa.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(nome);
		lblNewLabel.setBounds(10, 58, 67, 17);
		contentPane.add(lblNewLabel);
		Object[] lista = PegaDadosDao.getCategoria(nome).toArray();
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(225, 86, 199, 169);
		contentPane.add(panel);
		panel.setLayout(null);
		
		spinnerDuracao = new JSpinner();
		spinnerDuracao.setBounds(90, 111, 97, 20);
		panel.add(spinnerDuracao);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Duração");
		lblNewLabel_1_1_1.setBounds(19, 114, 46, 14);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mês:");
		lblNewLabel_1_1.setBounds(51, 36, 46, 14);
		panel.add(lblNewLabel_1_1);
		
		comboBoxMesMensal = new JComboBox(MesesEnum.values());
		comboBoxMesMensal.setBounds(90, 32, 97, 22);
		panel.add(comboBoxMesMensal);
		
		textFieldAnoMensal = new JTextField();
		textFieldAnoMensal.setColumns(10);
		textFieldAnoMensal.setBounds(90, 70, 97, 20);
		panel.add(textFieldAnoMensal);
		
		JLabel lblNewLabel_1 = new JLabel("Ano:");
		lblNewLabel_1.setBounds(51, 73, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblInicio = new JLabel("Inicio:");
		lblInicio.setBounds(9, 35, 67, 17);
		panel.add(lblInicio);
		
		JLabel lblNewLabel_2 = new JLabel("Valor Mensal");
		lblNewLabel_2.setBounds(9, 145, 97, 14);
		panel.add(lblNewLabel_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(9, 101, 180, 10);
		panel.add(separator);
		
		rdbtnMensal = new JRadioButton("Mensal");
		rdbtnMensal.setBounds(6, 7, 109, 23);
		panel.add(rdbtnMensal);
		
		textFieldValorMensal = new JTextField();
		textFieldValorMensal.setColumns(10);
		textFieldValorMensal.setBounds(90, 142, 97, 20);
		panel.add(textFieldValorMensal);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(16, 86, 199, 169);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Mês:");
		lblNewLabel_1_1_2.setBounds(51, 36, 46, 14);
		panel_1.add(lblNewLabel_1_1_2);
		
		comboBoxMesOcasional = new JComboBox();
		comboBoxMesOcasional.setModel(new DefaultComboBoxModel<>(MesesEnum.values()));
		comboBoxMesOcasional.setBounds(90, 32, 97, 22);
		panel_1.add(comboBoxMesOcasional);
		
		textFieldAnoOcasional = new JTextField();
		textFieldAnoOcasional.setColumns(10);
		textFieldAnoOcasional.setBounds(90, 70, 97, 20);
		panel_1.add(textFieldAnoOcasional);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ano:");
		lblNewLabel_1_2.setBounds(51, 73, 46, 14);
		panel_1.add(lblNewLabel_1_2);
		
		lblNewLabel_2_1 = new JLabel("Valor:");
		lblNewLabel_2_1.setBounds(51, 122, 97, 14);
		panel_1.add(lblNewLabel_2_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(9, 101, 180, 10);
		panel_1.add(separator_1);
		
		rdbtnOcasional = new JRadioButton("Ocasional");
		rdbtnOcasional.setBounds(6, 7, 133, 23);
		panel_1.add(rdbtnOcasional);
		
		textFieldValorOcasional = new JTextField();
		textFieldValorOcasional.setColumns(10);
		textFieldValorOcasional.setBounds(90, 119, 97, 20);
		panel_1.add(textFieldValorOcasional);
		
		if(nome.equals("Despesas")) {
			lblNewLabel_3 = new JLabel("Adicionar nova despesa");
		}
		else if(nome.equals("Rendimentos")) {
			lblNewLabel_3 = new JLabel("Adicionar novo rendimento");
		}
		else {
			lblNewLabel_3 = new JLabel("Adicionar novo:");
		}

		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_3.setBounds(99, 0, 244, 28);
		contentPane.add(lblNewLabel_3);
		
		this.addWindowListener(att);
	}
	
	
	void addDataDatabase(){
		String tabela  = nome;
		ArrayList<String>campos = new ArrayList();
		ArrayList<String>valores = new ArrayList();
		int mesInicio;
		int duracao = 1;
		int anoInicio;
		
		campos.add("nome");
		
		valores.add("\""+textFieldDespesa.getText()+"\"");
		if(rdbtnOcasional.isSelected()) {
			campos.add("ocasional");
			mesInicio = ((MesesEnum)comboBoxMesOcasional.getSelectedItem()).getNumero();
			anoInicio =  Integer.parseInt((textFieldAnoOcasional.getText()));
			duracao = 1; 
			valores.add(textFieldValorOcasional.getText());
		}
		else {
			campos.add("mensal");
			mesInicio = (((MesesEnum)comboBoxMesMensal.getSelectedItem()).getNumero());
			anoInicio =  Integer.parseInt((textFieldAnoMensal.getText()));
			duracao = ((Integer)spinnerDuracao.getValue()); 
			valores.add(textFieldValorMensal.getText());
		}
		
		MainService.CriaTuplaDespesa(tabela, campos, valores, mesInicio, anoInicio, duracao);
		dispose();
	}
}
