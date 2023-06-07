package service;

import java.util.ArrayList;

public class Tupla{
	String tabela;
	String campos;
	String valores;
	String mesInicio;
	int anoInicio;
	
	public Tupla(String tabela, ArrayList<String> campos, ArrayList<String> valores) {
		this.tabela = tabela;
		for(String campo : campos) {
			if(this.campos.length()!=0) {
				this.campos.concat(", ");
			}
			this.campos.concat(campo);
		}
		for(String valor : valores) {
			if(this.valores.length()!=0) {
				this.valores.concat(", ");
			}
			this.valores.concat(valor);
		}
	}
	
	public String getTabela() {
		return tabela;
	}
	public String getCampos() {
		return campos;
	}
	public String getValores() {
		return valores;
	}
}
