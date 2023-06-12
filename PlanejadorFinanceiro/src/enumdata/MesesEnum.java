package enumdata;

public enum MesesEnum {

	JANEIRO(1), FEVEREIRO(2), MARCO(3), ABRIL(4),
	MAIO(5), JUNHO(6), JULHO(7), AGOSTO(8),
	SETEMBRO(9), OUTUBRO(10), NOVEMBRO(11), DEZEMBRO(12);

	private int numero;
	private String mes;

	private MesesEnum(int numero) {
		this.numero = numero;
		switch(numero) {
		case 1:
			this.mes = "Janeiro";
		break;
		case 2:
			this.mes =  "Fevereiro";
		break;
		case 3:
			this.mes =  "Março";
		break;
		case 4:
			this.mes =  "Abril";
		break;
		case 5:
			this.mes =  "Maio";
		break;
		case 6:
			this.mes =  "Junho";
		break;
		case 7:
			this.mes =  "Julho";
		break;
		case 8:
			this.mes =  "Agosto";
		break;
		case 9:
			this.mes =  "Setembro";
		break;
		case 10:
			this.mes =  "Outubro";
		break;
		case 11:
			this.mes =  "Novembro";
		break;
		case 12:
			this.mes =  "Dezembro";
		break;
		
		}

	}

	public int getNumero() {
		return numero;
	}

	public String getMes() {
		return mes;

	}
}
