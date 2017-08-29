package SBRM.RM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import SBRM.CG.CPLRules;

public class WriteRulesToXLFile {

	public static List<CPLRules> ExtractRuleListFromTextFile(String sourcePath) {
		List<CPLRules> rulesList = new ArrayList<CPLRules>();
		try {

			// Selection of subset of text
			String selectedLines = "";
			List<String> lines = Files.readAllLines(Paths.get(sourcePath), Charset.defaultCharset());
			boolean startFlag = false;
			boolean endFlag = true;
			for (String line : lines) {
				if (line.contains("------------------")) {
					startFlag = true;
					endFlag = false;
				}
				if (line.contains("Number of Leaves") || line.contains("Number of Rules") || line.contains("): Failed ")
						|| line.contains("): Connected")) {
					endFlag = true;
				}
				if (startFlag && !endFlag && line.length() > 3 && !line.contains("------------------")) {
					selectedLines += line.replaceAll("AND", " AND ").replaceAll("  ", " ")
							.replace(System.getProperty("line.separator"), "");
				}
			}
//			System.out.println(selectedLines);

			String[] ruleLines = selectedLines.split("\\)");
			for (int i = 0; i < ruleLines.length; i++) {
				CPLRules rule = new CPLRules();
				String[] temp = ruleLines[i].split("\\(");
				if (temp[0] != null) {
					rule.setExpression(temp[0]);
					if (rule.getExpression().contains("Connected")) {
						rule.setStatus("Connected");
						rule.setIsnormal(true);
					} else if (rule.getExpression().contains("Failed")) {
						rule.setStatus("Failed");
						rule.setIsnormal(false);
					} else {
						rule.setStatus("Invalid");
						rule.setIsnormal(false);
					}
					String[] temp4 = rule.getExpression().split(":");
					rule.setExpression(temp4[0]);
				}
				if (temp[1] != null && temp[1].contains("/") == false) {
					rule.setSupport(Double.parseDouble(temp[1]));
					rule.setViolation(0);
					if (rule.getSupport() + rule.getViolation() == 0) {
						rule.setConfidence(0);
						;
					} else {
						rule.setConfidence(1);
					}
				}

				if (temp[1] != null && temp[1].contains("/") == true) {
					String[] temp3 = temp[1].split("/");
					rule.setSupport(Double.parseDouble(temp3[0]));
					rule.setViolation(Double.parseDouble(temp3[1]));
					double conf = (rule.getSupport() - rule.getViolation() - rule.getViolation()) / (rule.getSupport());
					rule.setConfidence(conf);
					rule.setSupport(rule.getSupport() - rule.getViolation());
				}

				rulesList.add(rule);
			}
			return rulesList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rulesList;
	}

	public static void WriteConstraintsToExcel(List<CPLRules> ruleList, String targetFilepath, String sheetName) {
		try {
			
			
			int sheetnumber = 0;
			File file = new File(targetFilepath);
			Workbook wb;
			Sheet sheet;
			if(file.exists()){
				InputStream inp = new FileInputStream(targetFilepath);
				wb = WorkbookFactory.create(inp);
				sheet=wb.createSheet(sheetName);
			}else {
				file = new File(targetFilepath);
				 wb = new XSSFWorkbook();
				 sheet=wb.createSheet(sheetName);
			}
			
		
			
			Row row = sheet.createRow(0);

			Cell cell1 = row.createCell(0);
			cell1.setCellType(1);
			cell1.setCellValue("Expression");

			cell1 = row.createCell(1);
			cell1.setCellType(1);
			cell1.setCellValue("Status");

			cell1 = row.createCell(2);
			cell1.setCellType(1);
			cell1.setCellValue("Violation");

			cell1 = row.createCell(3);
			cell1.setCellType(1);
			cell1.setCellValue("Support");

			cell1 = row.createCell(4);
			cell1.setCellType(1);
			cell1.setCellValue("Confidence");

			cell1 = row.createCell(5);
			cell1.setCellType(1);
			cell1.setCellValue("IsNormalState");

			for (Iterator iterator = ruleList.iterator(); iterator.hasNext();) {
				CPLRules rule = (CPLRules) iterator.next();
//				rule.printRule();
				row = sheet.createRow(sheet.getPhysicalNumberOfRows());

				Cell cell2 = row.createCell(0);
				cell2.setCellType(1);
				cell2.setCellValue(rule.getExpression());

				cell2 = row.createCell(1);
				cell2.setCellType(1);
				cell2.setCellValue(rule.getStatus());

				cell2 = row.createCell(2);
				cell2.setCellType(1);
				cell2.setCellValue(rule.getViolation());

				cell2 = row.createCell(3);
				cell2.setCellType(1);
				cell2.setCellValue(rule.getSupport());

				cell2 = row.createCell(4);
				cell2.setCellType(1);
				cell2.setCellValue(rule.getConfidence());

				cell2 = row.createCell(5);
				cell2.setCellType(1);
				cell2.setCellValue(rule.isIsnormal());
			}

			FileOutputStream fileOut = new FileOutputStream(targetFilepath);
			wb.write(fileOut);
			wb.close();
			fileOut.close();


		} catch (IOException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

	}

	public static void ExtractRules(String sourcePath,String targetFilepath, String sheetName) {
		List<CPLRules> ruleList = ExtractRuleListFromTextFile(sourcePath);
		WriteConstraintsToExcel(ruleList,targetFilepath,sheetName);
	}	
	
	
	public static void main(String[] args) {
		List<CPLRules> ruleList = ExtractRuleListFromTextFile("ExampleFiles/ZeroIteration/C45/Constraints.txt");
		WriteConstraintsToExcel(ruleList,"InitialConstraints.xlsx", "zero");
		for (Iterator iterator = ruleList.iterator(); iterator.hasNext();) {
			CPLRules cplRules = (CPLRules) iterator.next();
//			cplRules.printRule();
		}

	}

}
