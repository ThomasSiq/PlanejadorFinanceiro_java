package gui;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import service.MainService;
import service.PegaTabelas;

public class MainWindow extends JFrame {

	private JPanel buttonsWindow;
	private JPanel contentPane;
	private JPanel contentWindow;
	private ImageIcon img = new ImageIcon("C:\\Users\\thoma\\OneDrive\\Documentos\\icone.jpg");

	private ArrayList<JPanel> btPanels = new ArrayList<JPanel>();
	private ArrayList<JPanel> ctPanels = new ArrayList<JPanel>();

	public MainWindow(PegaTabelas tabelasDados, ArrayList<JPanel> btP, ArrayList<JPanel> ctP) {
		this.btPanels = btP;
		this.ctPanels = ctP;
		setIconImage(img.getImage());
		contentWindow = new JPanel();
		contentWindow.setLayout(null);
		contentWindow.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		buttonsWindow = new JPanel();
		buttonsWindow.setBorder(null);
		buttonsWindow.setLayout(null);
		buttonsWindow.setBounds(10, 10, 300, 540);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentWindow);
		contentPane.add(buttonsWindow);

		for (JPanel bt : btPanels) {
			bt.setBounds(10, 10, 280, 520);
			buttonsWindow.add(bt);
			bt.setVisible(false);
		}
		for (JPanel ct : ctPanels) {
			contentWindow.add(ct);
			ct.setVisible(false);
		}
		btPanels.get(0).setVisible(true);
		ctPanels.get(6).setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1400, 650));
		setMinimumSize(new Dimension(1400, 600));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		setDinamicBounds();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				setDinamicBounds();
			}
		});

	}

	private void setDinamicBounds() {
		MainService.setDinamicBounds(this);
	}

	public JPanel getContentWindow() {
		return contentWindow;
	}

	public void setContentWindow(JPanel contentWindow) {
		this.contentWindow = contentWindow;
	}

	public ArrayList<JPanel> getCtPanels() {
		return ctPanels;
	}

	public void setCtPanels(ArrayList<JPanel> ctPanels) {
		this.ctPanels = ctPanels;
	}
	
	
}
