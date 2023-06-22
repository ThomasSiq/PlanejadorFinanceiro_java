package service;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.InsereDadosDao;
import dao.PegaDadosDao;
import enumdata.MesesEnum;
import gui.MainWindow;
import gui.btnpanel.ButtonsPanelDespesas;
import gui.btnpanel.ButtonsPanelHome;
import gui.btnpanel.ButtonsPanelLongoPrazo;
import gui.btnpanel.ButtonsPanelOcasional;
import gui.btnpanel.ButtonsPanelRelatorio;
import gui.btnpanel.ButtonsPanelRendimentos;
import gui.btnpanel.ButtonsPanelResumo;
import gui.contentpanel.ContentPanel;
import gui.contentpanel.ContentPanelDespesas;
import gui.contentpanel.ContentPanelHome;
import gui.contentpanel.ContentPanelLongoPrazo;
import gui.contentpanel.ContentPanelOcasional;
import gui.contentpanel.ContentPanelRelatorio;
import gui.contentpanel.ContentPanelRendimentos;
import gui.contentpanel.ContentPanelResumo;

public class MainService {
	private MainWindow mainWindow;

	public static void main(String[] args) {
		new MainService();
	}

	private ArrayList<JPanel> btPanels = new ArrayList<JPanel>();
	private ArrayList<JPanel> ctPanels = new ArrayList<JPanel>();

	private PegaTabelas tabelasDados;

	private ActionListener evento;
	private ActionListener exportEvent;
	private WindowListener att;

	private static String senha;

	public MainService() {
		
		tabelasDados = new PegaTabelas();

		
		att = new AtualizarTabelasListener(this);
		evento = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton comp = (JButton) e.getSource();
				String name = comp.getName();
				
				showPanel(name);
				if(    name.equals("Relatorio") ||  name.equals("Resumo")  ){
					for( JPanel panel : ctPanels) {
						try {
						((ContentPanel) panel).updateTabela();
						} 
						catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					}
				}
				
			}
		};
		exportEvent = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFileChooser seletor = new JFileChooser();
					int valorRetorno = seletor.showSaveDialog(mainWindow);
					if(valorRetorno == JFileChooser.APPROVE_OPTION) {
						exportRelatorio(seletor.getSelectedFile().getAbsolutePath()) ;
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		
		try {
			ctPanels.add(new ContentPanelRendimentos());
			ctPanels.add(new ContentPanelDespesas());
			ctPanels.add(new ContentPanelLongoPrazo());
			ctPanels.add(new ContentPanelResumo(tabelasDados));
			ctPanels.add(new ContentPanelOcasional());
			ctPanels.add(new ContentPanelRelatorio(tabelasDados));
			ctPanels.add(new ContentPanelHome(this));
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Erro ao conectar com o banco de dados. Verifique a conexão com a internet", "Erro",
					JOptionPane.WARNING_MESSAGE);

			e1.printStackTrace();
		}
		
		
		btPanels.add(new ButtonsPanelHome(evento));
		btPanels.add(new ButtonsPanelRendimentos(evento, att));
		btPanels.add(new ButtonsPanelDespesas(evento, att));
		btPanels.add(new ButtonsPanelLongoPrazo(evento, att));
		btPanels.add(new ButtonsPanelResumo(evento, (ContentPanelResumo) ctPanels.get(3)));
		btPanels.add(new ButtonsPanelRelatorio(evento, exportEvent));
		btPanels.add(new ButtonsPanelOcasional(evento, att));
		
		btPanels.get(0).setEnabled(false);

		//Inicia o programa
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(tabelasDados, btPanels,  ctPanels);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void exportRelatorio(String path) throws IOException {
		Object[] periodo = ((ContentPanelRelatorio) ctPanels.get(5)).getPeriodo();
		String agrupar = ((ContentPanelRelatorio) ctPanels.get(5)).getAgrupar();

		ExcelExport.exportTable(periodo, tabelasDados, path, agrupar);
	}

	public void showPanel(String nome) {
		for (JPanel bt : btPanels) {
			bt.setVisible(false);
			if (nome.equals(bt.getName())) {
				bt.setVisible(true);
			}
		}

		for (JPanel ct : ctPanels) {
			ct.setVisible(false);
			if (nome.equals(ct.getName())) {
				ct.setVisible(true);
			}
		}
	}

	public static void CriaTuplaDespesa(String tabela, ArrayList<String> campos, ArrayList<String> valores,
			int mesInicio, int anoInicio, int duracao) {
		ArrayList<String> tupla = new ArrayList();

		try {
			while (duracao > 0) {
				String camposTupla = "";
				String valoresTupla = "";
				if (!tupla.isEmpty()) {
					tupla.clear();
				}

				tupla.add(tabela);
				for (int j = 0; j < campos.size(); j++) {
					if (j == campos.size() - 1) {
						camposTupla += (campos.get(j));
						valoresTupla += (valores.get(j));
					} else {
						camposTupla += (campos.get(j)) + ",";
						valoresTupla += (valores.get(j) + ",");
					}

				}
				camposTupla += ",ano";
				camposTupla += ",mes";
				camposTupla += ",duracao";
				if (mesInicio == 0) {
					if (duracao > 12) {
						valoresTupla += "," + (Integer.toString(anoInicio));
						valoresTupla += "," + (Integer.toString(mesInicio));
						valoresTupla += "," + (Integer.toString(12));
					} else {
						valoresTupla += "," + (Integer.toString(anoInicio));
						valoresTupla += "," + (Integer.toString(mesInicio));
						valoresTupla += "," + (Integer.toString(duracao));

					}
					duracao -= 12;

				} else if ((duracao + (mesInicio - 1)) > 12) {
					valoresTupla += "," + (Integer.toString(anoInicio));
					valoresTupla += "," + (Integer.toString(mesInicio));
					valoresTupla += "," + (Integer.toString((12 - mesInicio) + 1));
					mesInicio = 0;

				} else {
					valoresTupla += "," + (Integer.toString(anoInicio));
					valoresTupla += "," + (Integer.toString(mesInicio));
					valoresTupla += "," + (Integer.toString(duracao));
					duracao = 0;
				}
				anoInicio += 1;
				tupla.add(camposTupla);
				tupla.add(valoresTupla);
				InsereDadosDao.cadastrar(tupla, senha);
			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void atualizarTabelas() {
		try {
			tabelasDados.updateTabelaBd();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (JPanel panel : ctPanels) {
			try {

				((ContentPanel) panel).updateTabela();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static ArrayList<Object> getTabela(String nome) throws SQLException {

		ArrayList<Object> retorno = new ArrayList();
		ArrayList<ArrayList<Object>> resultado = new ArrayList();
		resultado = PegaDadosDao.getTable(nome, senha);

		Object tabelaRetorno[][] = new Object[resultado.size()][];
		for (int i = 0; i < resultado.size(); i++) {
			ArrayList<Object> row = resultado.get(i);
			tabelaRetorno[i] = row.toArray(new Object[row.size()]);
		}

		retorno.add(tabelaRetorno);
		retorno.add(PegaDadosDao.getTableYears(nome, senha));

		return retorno;
	}

	public static void setDinamicBounds(MainWindow main) {
		main.getContentWindow().setBounds(320, 10, (main.getBounds().width) - 350, main.getBounds().height - 60);

		for (JPanel ct : main.getCtPanels()) {
			ct.setBounds(0, 0, main.getContentWindow().getBounds().width, main.getContentWindow().getBounds().height);

		}

	}

	public static String loginDataBase(String user) throws SQLException {
		return PegaDadosDao.pegaUser(user);
	}

	public static boolean cadastrarDataBase(String user, String senha) throws SQLException {
		return PegaDadosDao.cadastraUser(user, senha);
	}

	public static boolean excluirDataBase(String user, String senha) throws SQLException {
		return PegaDadosDao.deletaUser(user, senha);
	}

	public ArrayList<JPanel> getCtPanels() {
		return ctPanels;
	}

	public ArrayList<JPanel> getBtPanels() {
		return btPanels;
	}

	public static String getSenha() {
		return senha;
	}

	public static void setSenha(String s) {
		senha = s;
	}

}
