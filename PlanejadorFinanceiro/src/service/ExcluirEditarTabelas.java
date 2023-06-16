package service;

import dao.EditarDadosDao;
import dao.RemoveDadosDao;

public class ExcluirEditarTabelas {
	public static void EditarTabelas(Object[][] tabela, int ind, String tabelaNome, int codigo, String categoria,
			int ano, String nome, String mensal, String ocasional) {

		while ((int) tabela[ind][2] == 0) {
			ind--;
		}

		do {
			
			EditarDadosDao.editarTupla(tabelaNome, tabela[ind][0].toString(), categoria, tabela[ind][1].toString(), nome, mensal, ocasional);
			ind++;
			if(ind==tabela.length) {
				break;
			}
		} while ((int) tabela[ind][2] == 0);
	}
	
	public static void ExcluirTabelas(Object[][] tabela, int ind, String tabelaNome) {

		while ((int) tabela[ind][2] == 0) {
			ind--;
		}

		do {
			
			RemoveDadosDao.removeTupla(tabelaNome, tabela[ind][0].toString());
			ind++;
			if(ind==tabela.length) {
				break;
			}
		} while ((int) tabela[ind][2] == 0);
	}

}
