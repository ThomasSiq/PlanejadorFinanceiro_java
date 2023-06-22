package gui.contentpanel;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import service.MainService;
import javax.swing.SwingConstants;

public class ContentPanelHome extends ContentPanel {
	private JTextField textUser;
	private JTextField textSenha;
	private MainService main;
	private JLabel lblMensagem;
	private JLabel lblUser;

	/**
	 * Create the panel.
	 */
	public ContentPanelHome(MainService main) {
		this.setName("Home");
		this.main = main;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuário Logado:");
		lblNewLabel.setBounds(265, 90, 244, 41);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		add(lblNewLabel);
		
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLogin.setBounds(501, 251, 143, 41);
		add(lblLogin);
		
	
		
		textUser = new JTextField();
		textUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textUser.setColumns(10);
		textUser.setBounds(528, 314, 237, 35);
		add(textUser);
		
		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblUsuario.setBounds(372, 308, 143, 41);
		add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSenha.setBounds(372, 360, 143, 41);
		add(lblSenha);
		
		textSenha = new JTextField();
		textSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textSenha.setColumns(10);
		textSenha.setBounds(529, 365, 236, 35);
		add(textSenha);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.controlShadow);
		separator.setBounds(218, 146, 737, 2);
		add(separator);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String senha = MainService.loginDataBase(textUser.getText());
					if( senha.equals(textSenha.getText()) ) {
						main.setSenha(textUser.getText()+textSenha.getText());
						lblMensagem.setText("");
						lblUser.setText(textUser.getText());
						(main.getBtPanels().get(0)).setEnabled(true);
						main.atualizarTabelas();
					}
					else {
						lblMensagem.setText("Usuario não cadastrado ou senha incorreta!");
					}
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton.setBounds(372, 419, 157, 41);
		add(btnNewButton);
		
		lblMensagem = new JLabel("");
		lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagem.setBounds(351, 196, 438, 25);
		add(lblMensagem);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnCadastrar.setBounds(651, 419, 114, 41);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(MainService.cadastrarDataBase(textUser.getText(), textSenha.getText())) {
						lblMensagem.setText("Usuario cadastrado com sucesso");
					}
					else {
						lblMensagem.setText("Usuario ja existente");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		add(btnCadastrar);
		
		JButton btnNewButton_1_1 = new JButton("Excluir");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton_1_1.setBounds(539, 419, 105, 41);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(MainService.excluirDataBase(textUser.getText(), textSenha.getText())) {
						lblMensagem.setText("Usuario excluido com sucesso");
					}
					else {
						lblMensagem.setText("Usuario ou senha incorretos");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(btnNewButton_1_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(351, 232, 438, 244);
		add(panel);
		
		lblUser = new JLabel("");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblUser.setBounds(528, 90, 386, 41);
		add(lblUser);

	}
	
}
