package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.GetTabelasDao;
import dao.InsertTable;
import enumdata.MesesEnum;

public class MainService {

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
				System.out.println(duracao);
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
				InsertTable.cadastrar(tupla);
			}
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static ArrayList<Object> getTabela(String nome) {

		ArrayList<Object> retorno = new ArrayList();
		ArrayList<ArrayList<Object>> resultado = new ArrayList();
		resultado = GetTabelasDao.getTable(nome);

		Object tabelaRetorno[][] = new Object[resultado.size()][];
		for (int i = 0; i < resultado.size(); i++) {
			ArrayList<Object> row = resultado.get(i);
			tabelaRetorno[i] = row.toArray(new Object[row.size()]);
		}

		retorno.add(tabelaRetorno);
		retorno.add(GetTabelasDao.getTableYears(nome));

		return retorno;
	}

}
