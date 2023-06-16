package gui;

import gui.contentpanel.*;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.btnpanel.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class MainWindow extends JFrame {

	/** iniciando ActionListener */
	private ActionListener evento;
	private WindowListener att;

	/** iniciando todas as janelas vazias */
	private JPanel buttonsWindow;
	private JPanel contentPane;
	private JPanel contentWindow;

	/** iniciando as janelas da buttonWindow */
	private JPanel btpanelHome;
	private JPanel btpanelRendimentos;
	private JPanel btpanelDespesas;
	private JPanel btpanelLongoPrazo;
	private JPanel btpanelResumo;
	private JPanel btpanelRelatorio;
	private JPanel btpanelOcasionais;

	/** iniciando as janelas da contentWindow */
	private JPanel ctpanelRendimentos;
	private JPanel ctpanelDespesas;
	private JPanel ctpanelLongoPrazo;
	private JPanel ctpanelResumo;
	private JPanel ctpanelOcasionais;

	ArrayList<JPanel> janelas;
	ArrayList<JPanel> janelasConteudo;

	/**
	 * Launch the application.
	 */
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
		btpanelOcasionais.setVisible(false);

		ctpanelRendimentos.setVisible(false);
		ctpanelDespesas.setVisible(false);
		ctpanelLongoPrazo.setVisible(false);
		ctpanelResumo.setVisible(false);
		ctpanelOcasionais.setVisible(false);

		if (nome == "Home") {
			btpanelHome.setVisible(true);
		} else if (nome == "Rendimentos") {
			ctpanelRendimentos.setVisible(true);
			btpanelRendimentos.setVisible(true);
		} else if (nome == "Despesas") {
			ctpanelDespesas.setVisible(true);
			btpanelDespesas.setVisible(true);
		} else if (nome == "LongoPrazo") {
			ctpanelLongoPrazo.setVisible(true);
			btpanelLongoPrazo.setVisible(true);
		} else if (nome == "Resumo") {
			ctpanelResumo.setVisible(true);
			btpanelResumo.setVisible(true);
		} else if (nome == "Relatorio") {
			btpanelRelatorio.setVisible(true);
		} else if (nome == "Ocasionais") {
			ctpanelOcasionais.setVisible(true);
			btpanelOcasionais.setVisible(true);
		}

	}

	private void setDinamicBounds() {

		contentWindow.setBounds(320, 10, (this.getBounds().width) - 350, this.getBounds().height - 60);

		for (JPanel panel : janelasConteudo) {
			panel.setBounds(0, 0, contentWindow.getBounds().width, contentWindow.getBounds().height);
			
		}

	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400, 650);
		this.setMinimumSize(new Dimension(900, 600));

		janelas = new ArrayList();
		janelasConteudo = new ArrayList();

		evento = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton comp = (JButton) e.getSource();
				String name = comp.getName();

				showPanel(name);
			}
		};
		att = new WindowListener() {

            public void windowActivated(WindowEvent arg0) {
                // Do nothing
            }
            public void windowClosed(WindowEvent e) {
            	((ContentPanelRendimentos)ctpanelRendimentos).attTabelas();
            	((ContentPanelDespesas)ctpanelDespesas).attTabelas();
            	((ContentPanelOcasional)ctpanelOcasionais).attTabelas();
            	((ContentPanelLongoPrazo)ctpanelLongoPrazo).attTabelas();
            }
            public void windowClosing(WindowEvent arg0) {
                // Do nothing
            }
            public void windowDeactivated(WindowEvent arg0) {
                // Do nothing
            }
            public void windowDeiconified(WindowEvent arg0) {
                // Do nothing
            }
            public void windowIconified(WindowEvent arg0) {
                // Do nothing
            }
            public void windowOpened(WindowEvent arg0) {
                // Do nothing
            }

        };

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				setDinamicBounds();
			}
		});

		contentPane = new JPanel();
		buttonsWindow = new JPanel();
		buttonsWindow.setBorder(null);
		contentWindow = new JPanel();
		contentWindow.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		ctpanelRendimentos = new ContentPanelRendimentos();
		ctpanelDespesas = new ContentPanelDespesas();
		ctpanelLongoPrazo = new ContentPanelLongoPrazo();
		ctpanelResumo = new ContentPanelResumo();
		ctpanelOcasionais = new ContentPanelOcasional();

		btpanelHome = new ButtonsPanelHome(evento);
		btpanelRendimentos = new ButtonsPanelRendimentos(evento, att);
		btpanelDespesas = new ButtonsPanelDespesas(evento, att);
		btpanelLongoPrazo = new ButtonsPanelLongoPrazo(evento, att);
		btpanelResumo = new ButtonsPanelResumo(evento, (ContentPanelResumo)ctpanelResumo);
		btpanelRelatorio = new ButtonsPanelRelatorio(evento);
		btpanelOcasionais = new ButtonsPanelOcasional(evento, att);

		janelas.add(btpanelHome);
		janelas.add(btpanelRendimentos);
		janelas.add(btpanelDespesas);
		janelas.add(btpanelLongoPrazo);
		janelas.add(btpanelResumo);
		janelas.add(btpanelRelatorio);
		janelas.add(btpanelOcasionais);

		/** adicionando as janelas da contentWindow */
		janelasConteudo.add(ctpanelRendimentos);
		janelasConteudo.add(ctpanelDespesas);
		janelasConteudo.add(ctpanelLongoPrazo);
		janelasConteudo.add(ctpanelResumo);
		janelasConteudo.add(ctpanelOcasionais);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(contentWindow);
		contentWindow.setLayout(null);

		contentPane.add(buttonsWindow);
		buttonsWindow.setLayout(null);

		setDinamicBounds();

		buttonsWindow.setBounds(10, 10, 300, 540);

		for (JPanel panel : janelas) {
			panel.setBounds(10, 10, 280, 520);
			buttonsWindow.add(panel);
			panel.setVisible(false);
		}
		btpanelHome.setVisible(true);

		for (JPanel panel : janelasConteudo) {
			contentWindow.add(panel);
			panel.setVisible(false);
		}
		ctpanelResumo.setVisible(true);

	}
}
