package service;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.MainWindow;
import gui.contentpanel.ContentPanel;
import gui.contentpanel.ContentPanelDespesas;
import gui.contentpanel.ContentPanelLongoPrazo;
import gui.contentpanel.ContentPanelOcasional;
import gui.contentpanel.ContentPanelRendimentos;

public class AtualizarTabelasListener implements WindowListener {
	private MainService main;
	
	public AtualizarTabelasListener(MainService main) {
		this.main = main;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		ArrayList<JPanel> panels = main.getCtPanels();
		String nome = ((JDialog) e.getSource()).getName();
		try {

			
			for(JPanel ct : panels) {
				
				if(nome.equals(ct.getName())) {
					((ContentPanel) ct).attTabelas();
				}
				else if((ct.getName()).equals("Relatorio")||(ct.getName()).equals("Resumo")) {
					((ContentPanel) ct).attTabelas();
				}
				
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Erro ao conectar com o banco de dados. Verifique a conexão com a internet", "Erro",
					JOptionPane.WARNING_MESSAGE);

			e1.printStackTrace();
		}
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
