package gui;
import gui.contentpanel.*;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
	private JPanel ctpanelResumoAnual;
	private JPanel ctpanelResumoMensal;
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

	public Rectangle getBtnWindowSizes() {
		return this.buttonsWindow.getBounds();
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
		ctpanelResumoAnual.setVisible(false);
		ctpanelResumoMensal.setVisible(false);
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
			ctpanelResumoAnual.setVisible(true);
			btpanelResumo.setVisible(true);
		} else if (nome == "Relatorio") {
			btpanelRelatorio.setVisible(true);
		} else if (nome == "Ocasionais") {
			ctpanelOcasionais.setVisible(true);
			btpanelOcasionais.setVisible(true);
		}

	}
	
	private void setDinamicBounds() {

		contentWindow.setBounds(320, 10, (this.getBounds().width)-350, this.getBounds().height-60);
		
		
		for(JPanel panel : janelasConteudo) {
			panel.setBounds(0,0,contentWindow.getBounds().width, contentWindow.getBounds().height);
			
		}

	
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 600);
		this.setMinimumSize(new Dimension(900,600));
		
		janelas = new ArrayList();
		janelasConteudo = new ArrayList();
		
		evento = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton comp =(JButton)e.getSource();
				String name = comp.getName();
						
				showPanel(name);
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
		buttonsWindow.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentWindow = new JPanel();
		contentWindow.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		btpanelHome = new ButtonsPanelHome(evento);
		btpanelRendimentos = new ButtonsPanelRendimentos(evento);
		btpanelDespesas = new ButtonsPanelDespesas(evento);	
		btpanelLongoPrazo = new ButtonsPanelLongoPrazo(evento);	
		btpanelResumo = new ButtonsPanelResumo(evento);	
		btpanelRelatorio = new ButtonsPanelRelatorio(evento);	
		btpanelOcasionais = new ButtonsPanelOcasional(evento);
		
		ctpanelRendimentos = new ContentPanelRendimentos();
		ctpanelDespesas = new ContentPanelDespesas();	
		ctpanelLongoPrazo = new ContentPanelLongoPrazo();	
		ctpanelResumoAnual = new ContentPanelResumoAnual();	
		ctpanelResumoMensal = new ContentPanelResumoMensal();
		ctpanelOcasionais = new ContentPanelOcasional();
		
		
		
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
		janelasConteudo.add(ctpanelResumoAnual);
		janelasConteudo.add(ctpanelResumoMensal);
		janelasConteudo.add(ctpanelOcasionais);
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		contentPane.add(contentWindow);
		contentWindow.setLayout(null);
		

		contentPane.add(buttonsWindow);
		
		setDinamicBounds();
		

		buttonsWindow.setBounds(10, 10, 300, 540);
		
		for(JPanel panel : janelas) {
			panel.setBounds(10, 10, 280, 520);
			buttonsWindow.add(panel);
			panel.setVisible(false);
		}
		btpanelHome.setVisible(true);
		
		
		for(JPanel panel : janelasConteudo) {
			contentWindow.add(panel);
			panel.setVisible(false);
		}
		ctpanelResumoAnual.setVisible(true);
		
		
	}
}