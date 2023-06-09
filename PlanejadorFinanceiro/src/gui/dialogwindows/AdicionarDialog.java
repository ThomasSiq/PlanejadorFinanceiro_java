package gui.dialogwindows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowListener;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.InternationalFormatter;

import dao.PegaDadosDao;
import enumdata.MesesEnum;
import service.EmptyTextError;
import service.MainService;
import service.ValorFormato;

public class AdicionarDialog extends JDialog {

	private JPanel contentPane;
	private JTextField textFieldDespesa;
	private JFormattedTextField textFieldValorOcasional;
	private JComboBox comboBoxMesOcasional;
	private JComboBox comboBoxCategoria;
	private JComboBox comboBoxMesMensal;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_3;
	private JSpinner spinnerDuracao;
	private JRadioButton rdbtnOcasional;
	private JRadioButton rdbtnMensal;
	private String nome;
	private JSpinner spinnerAnoOcasional;
	private JSpinner spinnerAnoMensal;
	private JFormattedTextField textFieldValorMensal;

	/**
	 * Create the frame.
	 */
	public AdicionarDialog(String tab, WindowListener att) {
		nome = tab;
		this.setName(tab);
		setBounds(100, 100, 450, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addDataDatabase();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAdicionar.setBounds(67, 276, 105, 37);
		contentPane.add(btnAdicionar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(262, 276, 105, 37);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnCancelar);

		textFieldDespesa = new JTextField();
		textFieldDespesa.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				JTextField txt = (JTextField) e.getSource();
				if (txt.getText().length() > 48) {
					txt.setText(txt.getText().substring(0, 48));
				}
				;
			}
		});
		textFieldDespesa.setBounds(87, 34, 337, 20);
		contentPane.add(textFieldDespesa);
		textFieldDespesa.setColumns(10);

		JLabel lblNewLabel = new JLabel(nome);
		lblNewLabel.setBounds(10, 37, 67, 17);
		contentPane.add(lblNewLabel);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 65, 67, 17);
		contentPane.add(lblCategoria);
		Object[] lista;
		try {
			lista = PegaDadosDao.getCategoria(nome, MainService.getSenha()).toArray();
			comboBoxCategoria = new JComboBox(lista);
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Erro ao conectar com o banco de dados. Verifique a conexão com a internet", "Erro",
					JOptionPane.WARNING_MESSAGE);
			e1.printStackTrace();
		}

		comboBoxCategoria.setBounds(87, 65, 337, 20);
		contentPane.add(comboBoxCategoria);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(225, 96, 199, 169);
		contentPane.add(panel);
		panel.setLayout(null);

		spinnerDuracao = new JSpinner();
		spinnerDuracao
				.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinnerDuracao.setBounds(90, 111, 97, 20);
		panel.add(spinnerDuracao);

		JLabel lblNewLabel_1_1_1 = new JLabel("Duração");
		lblNewLabel_1_1_1.setBounds(9, 114, 46, 14);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1 = new JLabel("Mês:");
		lblNewLabel_1_1.setBounds(51, 36, 46, 14);
		panel.add(lblNewLabel_1_1);

		comboBoxMesMensal = new JComboBox(MesesEnum.values());
		comboBoxMesMensal.setBounds(90, 32, 97, 22);
		panel.add(comboBoxMesMensal);

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
		rdbtnMensal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnMensal.isSelected()) {
					rdbtnOcasional.setSelected(false);
				}
			}
		});
		panel.add(rdbtnMensal);

		spinnerAnoMensal = new JSpinner();
		spinnerAnoMensal.setModel(new SpinnerNumberModel(2023, 2010, 2100, 1));
		spinnerAnoMensal.setBounds(90, 70, 97, 20);
		panel.add(spinnerAnoMensal);

		textFieldValorMensal = new JFormattedTextField();
		textFieldValorMensal.setFormatterFactory(new ValorFormato());
		textFieldValorMensal.setValue(0.00);
		textFieldValorMensal.setBounds(90, 142, 97, 20);
		panel.add(textFieldValorMensal);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(16, 96, 199, 169);
		contentPane.add(panel_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Mês:");
		lblNewLabel_1_1_2.setBounds(51, 36, 46, 14);
		panel_1.add(lblNewLabel_1_1_2);

		comboBoxMesOcasional = new JComboBox();
		comboBoxMesOcasional.setModel(new DefaultComboBoxModel<>(MesesEnum.values()));
		comboBoxMesOcasional.setBounds(90, 32, 97, 22);
		panel_1.add(comboBoxMesOcasional);

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
		rdbtnOcasional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnOcasional.isSelected()) {
					rdbtnMensal.setSelected(false);
				}
			}
		});
		rdbtnOcasional.setBounds(6, 7, 133, 23);
		panel_1.add(rdbtnOcasional);

		textFieldValorOcasional = new JFormattedTextField();
		textFieldValorOcasional.setFormatterFactory(new ValorFormato());
		textFieldValorOcasional.setValue(0.00);
		textFieldValorOcasional.setColumns(10);
		textFieldValorOcasional.setBounds(90, 119, 97, 20);
		panel_1.add(textFieldValorOcasional);

		spinnerAnoOcasional = new JSpinner();
		spinnerAnoOcasional.setModel(new SpinnerNumberModel(2023, 2010, 2100, 1));
		spinnerAnoOcasional.setBounds(90, 70, 97, 20);
		panel_1.add(spinnerAnoOcasional);

		if (nome.equals("Despesas")) {
			lblNewLabel_3 = new JLabel("Adicionar nova despesa");
		} else if (nome.equals("Rendimentos")) {
			lblNewLabel_3 = new JLabel("Adicionar novo rendimento");
		} else {
			lblNewLabel_3 = new JLabel("Adicionar novo:");
		}

		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_3.setBounds(99, 0, 244, 28);
		contentPane.add(lblNewLabel_3);

		this.addWindowListener(att);
	}

	private void addDataDatabase() throws Exception {
		String tabela = nome;
		ArrayList<String> campos = new ArrayList();
		ArrayList<String> valores = new ArrayList();
		int mesInicio;
		int duracao = 1;
		int anoInicio;

		campos.add("nome");
		campos.add("categoria");
		if (textFieldDespesa.getText().isEmpty()) {
			throw new EmptyTextError("Campo Rendimento não pode ser vazio");
		}
		if (!rdbtnOcasional.isSelected() && !rdbtnMensal.isSelected()) {
			throw new EmptyTextError("Selecione o tipo (Mensal ou Ocasional)");
		}

		valores.add("\"" + textFieldDespesa.getText() + "\"");
		valores.add("\"" + comboBoxCategoria.getSelectedItem().toString() + "\"");
		if (rdbtnOcasional.isSelected()) {
			if ((double) textFieldValorOcasional.getValue() == 0.0) {
				throw new EmptyTextError("Campo Valor não pode ser vazio");
			}
			campos.add("ocasional");
			mesInicio = ((MesesEnum) comboBoxMesOcasional.getSelectedItem()).getNumero();
			anoInicio = (int) spinnerAnoOcasional.getValue();
			duracao = 1;
			valores.add(textFieldValorOcasional.getValue().toString());
		} else {
			if ((double) textFieldValorMensal.getValue() == 0.0) {
				throw new EmptyTextError("Campo Valor não pode ser vazio");
			}
			campos.add("mensal");
			mesInicio = (((MesesEnum) comboBoxMesMensal.getSelectedItem()).getNumero());
			anoInicio = (int) spinnerAnoMensal.getValue();
			duracao = ((Integer) spinnerDuracao.getValue());
			valores.add(textFieldValorMensal.getValue().toString());
		}

		MainService.CriaTuplaDespesa(tabela, campos, valores, mesInicio, anoInicio, duracao);
		dispose();
	}

	

}
