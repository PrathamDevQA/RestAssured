package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProviders {

	public List<String> getData(String testData) throws IOException {
		List<String> datas = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/Book1.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		int noOfSheets = workBook.getNumberOfSheets();
		for (int i = 0; i < noOfSheets; i++) {
			if (workBook.getSheetName(i).contentEquals("Sheet1")) {
				XSSFSheet sheet = workBook.getSheetAt(i);

				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();

				Iterator<Cell> cells = firstRow.iterator();

				int k = 0;
				int column = 0;

				while (cells.hasNext()) {
					Cell cellValue = cells.next();
					if (cellValue.getStringCellValue().equalsIgnoreCase("Testcases")) {
						column = k;
					}
					k++;
				}

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testData)) {
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {
								datas.add(c.getStringCellValue());
							} else {
								datas.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}

					}
				}

			}
		}
		return datas;

	}

}
