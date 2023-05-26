package windowsgroup;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
/**iniciando todas as janelas*/
	
	JPanel buttonsWindow;
	JPanel btpanelHome;
	JPanel btpanelRendimentos;
	
	/**iniciando todas as janelas*/
	
	

	
	
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

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		
		
		/**iniciando os eventListeners*/
		ActionListener home = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeButtons(btpanelHome);
			}
		};
		/**iniciando os eventListeners*/
		
		
		/**iniciando todas as janelas*/
		
		JPanel buttonsWindow = new JPanel();
		JPanel btpanelHome = new buttonsPanel();
		JPanel btpanelRendimentos = new buttonsPanelRendimentos(home);
		
		/**iniciando todas as janelas*/
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 589);
		changeButtons(btpanelRendimentos);
		
	}
	
	
	public void changeButtons(JPanel alvo){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentWindow = new JPanel();
		contentWindow.setBounds(298, 10, 428, 529);
		contentPane.add(contentWindow);
		
		buttonsWindow = alvo;
				
		
		buttonsWindow.setBounds(10, 11, 278, 528);
		
		contentPane.add(buttonsWindow);
	}
}
