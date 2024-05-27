package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataProviderTwo {

	private String filePath;

	public DataProviderTwo(String filePath) {
		this.filePath = filePath;
	}

	public List<List<String>> readDataFromExcel(String sheetName) throws IOException {
		List<List<String>> data = new ArrayList<>();

		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		if (sheet != null) {
			Iterator<Row> rows = sheet.iterator();

			while (rows.hasNext()) {
				Row row = rows.next();
				List<String> rowData = new ArrayList<>();

				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					rowData.add(getCellValueAsString(cell));
				}

				data.add(rowData);
			}

			workbook.close();
			fis.close();
		}

		return data;
	}

	private String getCellValueAsString(Cell cell) {
		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			} else {
				return Double.toString(cell.getNumericCellValue());
			}
		case BOOLEAN:
			return Boolean.toString(cell.getBooleanCellValue());
		case FORMULA:
			return cell.getCellFormula();
		default:
			return "";
		}
	}

	public static void main(String[] args) {
		String filePath = System.getProperty("user.dir") + "/src/test/resources/Book1.xlsx";
		String sheetName = "Sheet1";

		DataProviderTwo excelDataReader = new DataProviderTwo(filePath);

		try {
			List<List<String>> data = excelDataReader.readDataFromExcel(sheetName);

			for (List<String> rowData : data) {
				System.out.println(rowData);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
