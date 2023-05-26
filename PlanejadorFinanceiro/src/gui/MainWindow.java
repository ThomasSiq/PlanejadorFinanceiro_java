package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
ActionListener evento = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JButton comp =(JButton)e.getSource();
			String name = comp.getName();
					
			showPanel(name);
		}
	};
	
/**iniciando todas as janelas*/
	
	JPanel buttonsWindow = new JPanel();
	JPanel btpanelHome = new buttonsPanelHome(evento);
	JPanel btpanelRendimentos = new buttonsPanelRendimentos(evento);
	JPanel btpanelDespesas = new buttonsPanelDespesas(evento);	
	JPanel btpanelLongoPrazo = new buttonsPanelLongoPrazo(evento);	
	JPanel btpanelResumo = new buttonsPanelResumo(evento);	
	JPanel btpanelRelatorio = new buttonsPanelRelatorio(evento);	
	
	
	public static void main(String[] args) {
		

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void showPanel(String nome) {
		
		btpanelRendimentos.setVisible(false);
		btpanelHome.setVisible(false);
		btpanelDespesas.setVisible(false);	
		btpanelLongoPrazo.setVisible(false);
		btpanelResumo.setVisible(false);
		btpanelRelatorio.setVisible(false);
		
		if(nome == "Home") {
			btpanelHome.setVisible(true);
		}
		else if(nome == "Rendimentos") {
			btpanelRendimentos.setVisible(true);
		}
		else if(nome == "Despesas") {
			btpanelDespesas.setVisible(true);
		}
		else if(nome == "LongoPrazo") {
			btpanelLongoPrazo.setVisible(true);
		}		
		else if(nome == "Resumo") {
			btpanelResumo.setVisible(true);
		}
		else if(nome == "Relatorio") {
			btpanelRelatorio.setVisible(true);
		}
		
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		

		/**iniciando os eventListeners*/
		
		
		/**iniciando todas as janelas*/
		
		
		
		/**iniciando todas as janelas*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 589);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentWindow = new JPanel();
		contentWindow.setBounds(298, 10, 428, 529);
		contentPane.add(contentWindow);
		
		
		
		btpanelHome.setBounds(10, 11, 277, 528);
		contentPane.add(btpanelHome);
		
		
		
		
		
		
		btpanelRendimentos.setBounds(10, 11, 277, 528);
		btpanelDespesas.setBounds(10, 11, 277, 528);
		btpanelLongoPrazo.setBounds(10, 11, 277, 528);
		btpanelResumo.setBounds(10, 11, 277, 528);
		btpanelRelatorio.setBounds(10, 11, 277, 528);
		
		contentPane.add(btpanelDespesas);
		contentPane.add(btpanelRendimentos);
		contentPane.add(btpanelLongoPrazo);
		contentPane.add(btpanelResumo);
		contentPane.add(btpanelRelatorio);
		
		btpanelRendimentos.setVisible(false);
		btpanelDespesas.setVisible(false);
		btpanelLongoPrazo.setVisible(false);
		btpanelResumo.setVisible(false);
		btpanelRelatorio.setVisible(false);
		
		
		//btpanelRendimentos.setVisible(false);
		
		
		
		//contentWindow.add(btpanelRendimentos);
		//btpanelRendimentos.setVisible(false);
		

		
		
	}
}
