package SBRM.CG;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadCPLRulesFromXLFile {

	public ReadCPLRulesFromXLFile() {
	}

	public static List<CPLRules> getRulesFromXLFile(String filePath) {
		List<CPLRules> rulesList = new ArrayList<CPLRules>();
		try {
			// String filePath = "constraints.xlsx";
			File myFile = new File(filePath);
			FileInputStream fis;
			fis = new FileInputStream(myFile);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			// Iterator<Row> rowIterator1 = mySheet.iterator();
			for (Iterator rowIterator = mySheet.iterator(); rowIterator.hasNext();) {
				Row row = (Row) rowIterator.next();
				CPLRules rule = new CPLRules();
				if (!row.getCell(0).getStringCellValue().toLowerCase().contains("expression")){
				rule.setExpression(row.getCell(0).getStringCellValue());
				rule.setStatus(row.getCell(1).getStringCellValue());
				rule.setViolation((int) row.getCell(2).getNumericCellValue());
				rule.setSupport((int) row.getCell(3).getNumericCellValue());
				rule.setConfidence(row.getCell(4).getNumericCellValue());
				rule.setIsnormal(row.getCell(5).getBooleanCellValue());
//				rule.printRule();
				rulesList.add(rule);
				}
			}
			return rulesList;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rulesList;

	}

}
