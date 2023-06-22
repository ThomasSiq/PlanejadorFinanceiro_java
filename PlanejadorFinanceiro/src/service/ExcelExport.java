package service;

import java.io.File;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ExcelExport {

	public static void exportTable(Object[] per, PegaTabelas tabelasDados, String path, String agrupar) throws IOException {

		ArrayList<String> nomes = new ArrayList<String>(
				Arrays.asList("Rendimentos", "Ocasional", "LongoPrazo", "Despesas", "Total Disponivel", "Resultado"));
		Object[][] valoresTotalOb = null;

		if(agrupar=="Normal") {
			valoresTotalOb = tabelasDados.geraRelatorioNormal(nomes, per);
		}
		else {
			valoresTotalOb = tabelasDados.geraRelatorioCategoria(nomes, per);
		}
		// workbook object
		XSSFWorkbook workbook = new XSSFWorkbook();

		// spreadsheet object
		XSSFSheet spreadsheet = workbook.createSheet(" Relatório ");

		// creating a row object
		XSSFRow row;

		// This data needs to be written (Object[])
		Map<Integer, Object[]> studentData = new TreeMap<Integer, Object[]>();

		if (per[2] == "Mes") {
			studentData.put(1, new Object[] { "Ano: ", Integer.toString(((ArrayList<Integer[]>) per[1]).get(0)[0]),
					"Mes: ", Integer.toString(((ArrayList<Integer[]>) per[1]).get(0)[1]) });
			studentData.put(2, new Object[] { "Tipo", "Mensal [R$]", "Ocasional [R$]", "Total [R$]" });
		} else {
			studentData.put(1,
					new Object[] { "Ano: ", Integer.toString(((ArrayList<Integer>) per[0]).get(0)), "", "" });
			studentData.put(2, new Object[] { "Tipo", "Mensal [R$]", "Ocasional [R$]", "Total [R$]" });
		}
		for (int i = 0; i < valoresTotalOb.length; i++) {

			if (valoresTotalOb[i] == null ) {
				studentData.put((i + 3), new Object[] { "-----------", "", "", " " });
			}
			else if ( valoresTotalOb[i][1] == null) {
				studentData.put((i + 3),
						new Object[] { valoresTotalOb[i][0], "","","" });
			}else {

				studentData.put((i + 3),
						new Object[] { valoresTotalOb[i][0], Double.toString((double) valoresTotalOb[i][1]),
								Double.toString((double) valoresTotalOb[i][2]),
								Double.toString((double) valoresTotalOb[i][3]) });
			}

		}

		Set<Integer> keyid = studentData.keySet();

		int rowid = 0;

		// writing the data into the sheets...

		for (Integer key : keyid) {

			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = studentData.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}

		// .xlsx is the format for Excel Sheets...
		// writing the workbook into the file...
		FileOutputStream out = new FileOutputStream(
				new File(path+".xlsx"));

		workbook.write(out);
		out.close();
	}
}
