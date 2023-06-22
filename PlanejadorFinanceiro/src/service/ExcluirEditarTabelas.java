package service;

import java.sql.SQLException;

import dao.EditaDadosDao;
import dao.RemoveDadosDao;

public class ExcluirEditarTabelas {
	public static void EditarTabelas(Object[][] tabela, int ind, String tabelaNome, int codigo, String categoria,
			int ano, String nome, String mensal, String ocasional) throws SQLException {

		while ((int) tabela[ind][2] == 0) {
			ind--;
		}

		do {
			
			EditaDadosDao.editarTupla(tabelaNome, tabela[ind][0].toString(), categoria, tabela[ind][1].toString(), nome, mensal, ocasional, MainService.getSenha());
			ind++;
			if(ind==tabela.length) {
				break;
			}
		} while ((int) tabela[ind][2] == 0);
	}
	
	public static void ExcluirTabelas(Object[][] tabela, int ind, String tabelaNome) throws SQLException  {

		while ((int) tabela[ind][2] == 0) {
			ind--;
		}

		do {
			
			RemoveDadosDao.removeTupla(tabelaNome, tabela[ind][0].toString(), MainService.getSenha());
			ind++;
			if(ind==tabela.length) {
				break;
			}
		} while ((int) tabela[ind][2] == 0);
	}

}
