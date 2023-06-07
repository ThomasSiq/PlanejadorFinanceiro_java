package enumdata;

public enum Meses {

	JANEIRO(1, "janeiro"), FEVEREIRO(2, "fevereiro"), MARCO(3, "março"), ABRIL(4, "abril"),
	MAIO(5, "maio"), JUNHO(6, "junho"), JULHO(7, "julho"), AGOSTO(8, "agosto"),
	SETEMBRO(9, "setembro"), OUTUBRO(10, "outubro"), NOVEMBRO(11, "novembro"), DEZEMBRO(12, "dezembro");

	private int numero;
	private String mes;

	private Meses(int numero, String mes) {
		this.numero = numero;
		this.mes = mes;

	}

	public int getNumero() {
		return numero;
	}

	public String getMes() {
		return mes;

	}
}
