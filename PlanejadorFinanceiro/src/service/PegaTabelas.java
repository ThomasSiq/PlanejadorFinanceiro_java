package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import dao.PegaDadosDao;

public class PegaTabelas {

	private ArrayList<Integer> anos = new ArrayList<Integer>();
	private ArrayList<Object[]> categorias = new ArrayList<Object[]>();
	private ArrayList<Object[][]> tabelaBd = new ArrayList<Object[][]>();
	private ArrayList<Object[][]> tabelaResultados = new ArrayList<Object[][]>();
	private ArrayList<Object[][]> tabelaTotal = new ArrayList<Object[][]>();
	private final ArrayList<String> todosNomes = new ArrayList<String>(
			Arrays.asList("Rendimentos", "Ocasional", "LongoPrazo", "Despesas", "Total Disponivel", "Resultado"));

	public void updateTabelaBd() throws SQLException {

		tabelaBd.clear();
		categorias.clear();
		categorias.add(PegaDadosDao.getCategoria("Rendimentos", MainService.getSenha()).toArray());
		categorias.add(PegaDadosDao.getCategoria("Despesas", MainService.getSenha()).toArray());
		int maior = 0;
		anos.clear();
		for (int i = 0; i < 4; i++) {
			ArrayList<Object> retorno = MainService.getTabela(todosNomes.get(i));
			tabelaBd.add((Object[][]) retorno.get(0));
			achaAno((ArrayList<Integer>) retorno.get(1));

			if (tabelaBd.get(i).length > maior) {
				maior = tabelaBd.get(i).length;
			}
		}
		tabelaResultados.clear();
		;
		tabelaTotal.clear();
		for (int ano : anos) {
			Object[][] total = new Object[12][9];
			Object[][] resultado = new Object[12][9];
			for (int i = 11; i >= 0; i--) {

				total[i][6] = 0.0;
				total[i][7] = 0.0;
				total[i][8] = 0.0;
				resultado[i][6] = 0.0;
				resultado[i][7] = 0.0;
				resultado[i][8] = 0.0;
				total[i][1] = ano;
				total[i][2] = (i + 1);
				resultado[i][1] = ano;
				resultado[i][2] = (i + 1);

				for (Object[][] tab : tabelaBd) {
					for (int j = 0; j < tab.length; j++) {
						if ((int) tab[j][1] == ano) {
							if ((int) tab[j][2] == 0) {
								tab[j][2] = 1;
							}
							if ((int) tab[j][2] + ((int) tab[j][3]) > (i + 1) && (int) tab[j][2] <= (i + 1)) {

								switch (tabelaBd.indexOf(tab)) {
								case 0:
									total[i][6] = (double) total[i][6] + (double) tab[j][6];
									total[i][7] = (double) total[i][7] + (double) tab[j][7];
									total[i][8] = (double) total[i][8] + (double) tab[j][6] + (double) tab[j][7];
									break;
								case 1:
									total[i][6] = (double) total[i][6] - (double) tab[j][6];
									total[i][7] = (double) total[i][7] - (double) tab[j][7];
									total[i][8] = (double) total[i][8] - ((double) tab[j][6] + (double) tab[j][7]);
									break;
								case 2:
									total[i][6] = (double) total[i][6] - (double) tab[j][6];
									total[i][7] = (double) total[i][7] - (double) tab[j][7];
									total[i][8] = (double) total[i][8] - ((double) tab[j][6] + (double) tab[j][7]);
									break;
								case 3:
									resultado[i][6] = (double) resultado[i][6] - (double) tab[j][6];
									resultado[i][7] = (double) resultado[i][7] - (double) tab[j][7];
									resultado[i][8] = (double) resultado[i][8]
											- ((double) tab[j][6] + (double) tab[j][7]);
									break;
								}
							}

						}
					}

				}

				resultado[i][6] = (double) resultado[i][6] + (double) total[i][6];
				resultado[i][7] = (double) resultado[i][7] + (double) total[i][7];
				resultado[i][8] = (double) resultado[i][8] + (double) total[i][8];

			}
			tabelaResultados.add(resultado);
			tabelaTotal.add(total);
		}

	}

	public Object[][] geraRelatorioCategoria(ArrayList<String> nomes, Object[] periodo) {
		Object[][] valoresTotalOb = null;
		int ano;
		int mes = 0;
		if (periodo[2] == "Ano") {
			ano = ((ArrayList<Integer>) periodo[0]).get(0);
		} else {
			ano = ((ArrayList<Integer[]>) periodo[1]).get(0)[0];
			mes = ((ArrayList<Integer[]>) periodo[1]).get(0)[1];

		}
		for (String nome : nomes) {
			Object[][] temp = null;

			if (periodo[2] == "Ano") {
				temp = geraRelatorioAno((int) ano, nome);
			} else {
				temp = geraRelatorioMes((int) ano, mes, nome);
			}
			if (nome == "Rendimentos" || nome == "Despesas") {
				ArrayList<Object[]> cat = new ArrayList();
				cat.add(temp[0]);
				Object[] categorias = null;
				if (nome == "Rendimentos") {
					categorias = this.categorias.get(0);
				}
				if (nome == "Despesas") {
					categorias = this.categorias.get(1);
				}

				for (Object categoria : categorias) {
					Object[] l = new Object[4];
					l[0] = categoria;
					l[1] = 0.0;
					l[2] = 0.0;
					l[3] = 0.0;
					for (Object[] line : temp) {
						if (line != null) {
							if (((String) line[0]).contains((String) categoria + " -")) {

								l[1] = (double) l[1] + (double) line[1];
								l[2] = (double) l[2] + (double) line[2];
								l[3] = (double) l[3] + (double) line[3];
							}
						}

					}
					if (((double) l[1] + (double) l[2]) != 0) {
						cat.add(l);
					}
				}
				cat.add(temp[temp.length - 2]);
				cat.add(temp[temp.length - 1]);
				temp = new Object[cat.size()][];
				for (int i = 0; i < cat.size(); i++) {
					temp[i] = cat.get(i);
				}
			}

			if (valoresTotalOb == null) {
				valoresTotalOb = temp;
			} else {
				valoresTotalOb = Arrays.copyOf(valoresTotalOb, valoresTotalOb.length + temp.length);
				System.arraycopy(temp, 0, valoresTotalOb, (valoresTotalOb.length - temp.length), temp.length);
			}

		}
		return valoresTotalOb;
	}

	public Object[][] geraRelatorioNormal(ArrayList<String> nomes, Object[] periodo) {
		Object[][] valoresTotalOb = null;
		int ano;
		int mes = 0;
		if (periodo[2] == "Ano") {
			ano = ((ArrayList<Integer>) periodo[0]).get(0);
		} else {
			ano = ((ArrayList<Integer[]>) periodo[1]).get(0)[0];
			mes = ((ArrayList<Integer[]>) periodo[1]).get(0)[1];

		}
		for (String nome : nomes) {
			Object[][] temp = null;

			if (periodo[2] == "Ano") {
				temp = geraRelatorioAno((int) ano, nome);
			} else {
				temp = geraRelatorioMes((int) ano, mes, nome);
			}

			if (valoresTotalOb == null) {
				valoresTotalOb = temp;
			} else {
				valoresTotalOb = Arrays.copyOf(valoresTotalOb, valoresTotalOb.length + temp.length);
				System.arraycopy(temp, 0, valoresTotalOb, (valoresTotalOb.length - temp.length), temp.length);
			}

		}
		return valoresTotalOb;
	}

	public Object[][] geraResultadoAno(ArrayList<String> nomes, ArrayList<Integer> anosPeriodo, int j) {
		Object[][] valoresTotalOb = new Object[nomes.size()][4];
		for (int k = 0; k < nomes.size(); k++) {

			valoresTotalOb[k][0] = "Total " + nomes.get(k) + " - ano ";
			if (nomes.get(k) == "LongoPrazo") {
				valoresTotalOb[k][0] = "Total Fundo Longo Prazo - ano ";
			}
			if (nomes.get(k) == "Total Disponivel") {
				valoresTotalOb[k][0] = "Total Disponivel - ano ";
			}

			Object[] temp = geraTabelaAno((int) anosPeriodo.get(j), nomes.get(k));
			if (temp == null) {
				continue;
			}
			valoresTotalOb[k][1] = (double) temp[6];
			valoresTotalOb[k][2] = (double) temp[7];
			valoresTotalOb[k][3] = (double) temp[8];

		}
		return valoresTotalOb;
	}

	public Object[][] geraResultadoMes(ArrayList<String> nomes, ArrayList<Integer[]> mesesPeriodo, int j) {
		Object[][] valoresTotalOb = new Object[nomes.size()][4];
		for (int k = 0; k < nomes.size(); k++) {

			valoresTotalOb[k][0] = "Total " + nomes.get(k) + " - mes ";
			if (nomes.get(k) == "LongoPrazo") {
				valoresTotalOb[k][0] = "Total Fundo Longo Prazo - mes ";
			}
			if (nomes.get(k) == "Total Disponivel") {
				valoresTotalOb[k][0] = "Total Disponivel - mes ";
			}

			Object[] temp = geraTabelaMes((int) mesesPeriodo.get(j)[0], (int) mesesPeriodo.get(j)[1], nomes.get(k));
			if (temp == null) {
				continue;
			}

			valoresTotalOb[k][1] = (double) temp[6];
			valoresTotalOb[k][2] = (double) temp[7];
			valoresTotalOb[k][3] = (double) temp[8];

		}
		return valoresTotalOb;
	}

	public Object[] geraTabelaResultadoMeses(int ano, int mes) {

		for (Object[][] tab : tabelaResultados) {
			for (Object[] line : tab) {
				if ((int) line[1] == ano && (int) line[2] == mes) {

					return line;
				}
			}
		}

		return null;
	}

	public Object[] geraTabelaTotalMeses(int ano, int mes) {
		for (Object[][] tab : tabelaTotal) {
			for (Object[] line : tab) {
				if ((int) line[1] == ano && (int) line[2] == mes) {
					return line;
				}
			}
		}

		return null;
	}

	public Object[][] geraRelatorioAno(int ano, String nome) {
		Object[] ret = null;
		ArrayList<Object[][]> tabela = null;

		if (nome != "Resultado" && nome != "Total Disponivel") {
			int cont = 0;
			ArrayList<Object[]> retorno = new ArrayList();
			for (Object[] line : tabelaBd.get(todosNomes.indexOf(nome))) {

				if ((int) line[1] == ano) {
					ret = new Object[4];
					ret[1] = 0.0;
					ret[2] = 0.0;
					ret[3] = 0.0;
					ret[0] = (String) line[4] + " - " + (String) line[5];
					if (nome != "Despesas" && nome != "Rendimentos")
						ret[0] = (String) line[5];
					ret[1] = (double) line[6] * (int) line[3];
					ret[2] = (double) line[7];
					ret[3] = (double) ret[1] + (double) ret[2];
					retorno.add(ret);
				}
			}

			Object[][] retorno2 = new Object[retorno.size() + 3][];
			Object temp[] = new Object[4];
			temp[0] = "Total " + nome;
			temp[1] = 0.0;
			temp[2] = 0.0;
			temp[3] = 0.0;
			for (int i = 1; i < retorno.size() + 1; i++) {
				retorno2[i] = retorno.get(i - 1);
				temp[1] = (double) temp[1] + (double) retorno2[i][1];
				temp[2] = (double) temp[2] + (double) retorno2[i][2];
				temp[3] = (double) temp[3] + (double) retorno2[i][3];
			}
			Object[] titulo = new Object[4];
			titulo[0] = nome;
			retorno2[0] = titulo;
			retorno2[retorno2.length - 2] = temp;
			return retorno2;
		}

		else if (nome == "Resultado") {
			tabela = tabelaResultados;
		}

		else if (nome == "Total Disponivel") {
			tabela = tabelaTotal;
		}
		Object[][] retorno = new Object[1][4];
		retorno[0][1] = 0.0;
		retorno[0][2] = 0.0;
		retorno[0][3] = 0.0;
		for (Object[][] tab : tabela) {

			for (Object[] line : tab) {

				if ((int) line[1] == ano) {

					retorno[0][0] = nome;
					retorno[0][1] = (double) retorno[0][1] + (double) line[6];
					retorno[0][2] = (double) retorno[0][2] + (double) line[7];
					retorno[0][3] = (double) retorno[0][3] + (double) line[8];
				}
			}

		}
		return retorno;
	}

	public Object[][] geraRelatorioMes(int ano, int mes, String nome) {
		Object[] ret = null;
		ArrayList<Object[][]> tabela = null;

		if (nome != "Resultado" && nome != "Total Disponivel") {
			int cont = 0;
			ArrayList<Object[]> retorno = new ArrayList();
			for (Object[] line : tabelaBd.get(todosNomes.indexOf(nome))) {
				if ((int) line[2] == 0)

					line[2] = 1;
				if ((int) line[1] == ano && (int) line[2] <= mes && ((int) line[2] + (int) line[3]) > mes) {
					ret = new Object[4];
					ret[1] = 0.0;
					ret[2] = 0.0;
					ret[3] = 0.0;
					ret[0] = (String) line[4] + " - " + (String) line[5];
					ret[1] = (double) line[6];
					ret[2] = (double) line[7];
					ret[3] = (double) ret[1] + (double) ret[2];
					retorno.add(ret);
				}
			}

			Object[][] retorno2 = new Object[retorno.size() + 3][];
			Object temp[] = new Object[4];
			temp[0] = "Total " + nome;
			temp[1] = 0.0;
			temp[2] = 0.0;
			temp[3] = 0.0;
			for (int i = 1; i < retorno.size() + 1; i++) {
				retorno2[i] = retorno.get(i - 1);
				temp[1] = (double) temp[1] + (double) retorno2[i][1];
				temp[2] = (double) temp[2] + (double) retorno2[i][2];
				temp[3] = (double) temp[3] + (double) retorno2[i][3];
			}
			Object[] titulo = new Object[4];
			titulo[0] = nome;
			retorno2[0] = titulo;
			retorno2[retorno2.length - 2] = temp;
			return retorno2;
		}

		else if (nome == "Resultado") {
			tabela = tabelaResultados;
		}

		else if (nome == "Total Disponivel") {
			tabela = tabelaTotal;
		}
		Object[][] retorno = new Object[1][4];

		for (Object[][] tab : tabela) {
			retorno[0][1] = 0.0;
			retorno[0][2] = 0.0;
			retorno[0][3] = 0.0;
			for (Object[] line : tab) {

				if ((int) line[1] == ano && (int) line[2] == mes) {

					retorno[0][0] = nome;
					retorno[0][1] = (double) retorno[0][1] + (double) line[6];
					retorno[0][2] = (double) retorno[0][2] + (double) line[7];
					retorno[0][3] = (double) retorno[0][3] + (double) line[8];
				}
			}

		}
		return retorno;
	}

	public Object[] geraTabelaAno(int ano, String nome) {
		Object[] ret = new Object[9];
		ArrayList<Object[][]> tabela = null;
		ret[6] = 0.0;
		ret[7] = 0.0;
		ret[8] = 0.0;
		if (nome != "Resultado" && nome != "Total Disponivel") {
			for (Object[] line : tabelaBd.get(todosNomes.indexOf(nome))) {
				if ((int) line[1] == ano) {

					ret[6] = (double) ret[6] + (double) line[6] * (int) line[3];
					ret[7] = (double) ret[7] + (double) line[7];

				}
			}

			ret[8] = (double) ret[6] + (double) ret[7];
			return ret;
		}

		else if (nome == "Resultado") {
			tabela = tabelaResultados;
		}

		else if (nome == "Total Disponivel") {
			tabela = tabelaTotal;
		}
		ret[6] = 0.0;
		ret[7] = 0.0;
		ret[8] = 0.0;
		for (Object[][] tab : tabela) {

			for (Object[] line : tab) {
				if ((int) line[1] == ano) {
					ret[6] = (double) ret[6] + (double) line[6];
					ret[7] = (double) ret[7] + (double) line[7];
					ret[8] = (double) ret[8] + (double) line[8];
				}
			}

		}
		return ret;
	}

	public Object[] geraTabelaMes(int ano, int mes, String nome) {
		ArrayList<Object[][]> tabela = null;
		Object ret[] = new Object[9];
		ret[6] = 0.0;
		ret[7] = 0.0;
		ret[8] = 0.0;
		if (nome != "Resultado" && nome != "Total Disponivel") {
			for (Object[] line : tabelaBd.get(todosNomes.indexOf(nome))) {
				if ((int) line[2] == 0)
					line[2] = 1;
				if ((int) line[1] == ano && (int) line[2] <= mes && ((int) line[2] + (int) line[3]) > (mes)) {

					ret[6] = (double) ret[6] + (double) line[6];
					ret[7] = (double) ret[7] + (double) line[7];

				}
			}

			ret[8] = (double) ret[6] + (double) ret[7];
			return ret;
		}

		else if (nome == "Resultado") {
			tabela = tabelaResultados;
		}

		else if (nome == "Total Disponivel") {
			tabela = tabelaTotal;
		}

		for (Object[][] tab : tabela) {
			for (Object[] line : tab) {
				if ((int) line[1] == ano && (int) line[2] == mes) {
					return line;
				}
			}

		}
		return null;
	}

	private void achaAno(ArrayList<Integer> retorno) {
		for (int ano : retorno) {
			boolean igual = false;
			for (int a : anos) {
				if (ano == a) {
					igual = true;
				}
			}
			if (igual == false) {
				anos.add(ano);
			}
		}

		Collections.sort(anos, Collections.reverseOrder());
	}

	public ArrayList<Object[][]> updateTabela(ArrayList<String> nomes) {
		ArrayList<Object[][]> tabela = new ArrayList();
		if (tabelaBd.isEmpty())
			return null;
		for (String nome : nomes) {
			if (nome != "Total Disponivel" && nome != "Resultado") {
				tabela.add(tabelaBd.get(todosNomes.indexOf(nome)));
			}
		}
		return tabela;

	}

	public static Object[] calculaTabelasMes() {

		return null;
	}

	public ArrayList<Integer> getAnos() {
		return anos;
	}

	public void setAnos(ArrayList<Integer> anos) {
		this.anos = anos;
	}

	public ArrayList<Object[][]> getTabelaBd() {
		return tabelaBd;
	}

	public void setTabelaBd(ArrayList<Object[][]> tabelaBd) {
		this.tabelaBd = tabelaBd;
	}

	public ArrayList<String> getTodosNomes() {
		return todosNomes;
	}

	public ArrayList<Object[][]> getTabelaResutados() {
		return tabelaResultados;
	}

	public ArrayList<Object[][]> getTabelaTotal() {
		return tabelaTotal;
	}

	public void setTabelaResutados(ArrayList<Object[][]> tabelaResutados) {
		this.tabelaResultados = tabelaResutados;
	}

	public void setTabelaTotal(ArrayList<Object[][]> tabelaTotal) {
		this.tabelaTotal = tabelaTotal;
	}

}
