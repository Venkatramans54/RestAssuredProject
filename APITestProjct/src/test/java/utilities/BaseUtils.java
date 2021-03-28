package utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaseUtils {
	
	public String randName() {
		return "John"+RandomStringUtils.randomAlphabetic(2);		
	}
	
	public String randSalary() {
		return RandomStringUtils.randomNumeric(6);
	}
	
	public String randAge() {
		return RandomStringUtils.randomNumeric(2);
	}
	
	public Object[][] parseExcel(String sheetName,int columnCount) {
		String filename=System.getProperty("user.dir")+"\\src\\main\\resources\\DataSheet.xlsx";
		File file=new File(filename);
		XSSFWorkbook wkbook;
		XSSFSheet sheet;
		Object[][] arr=new Object[0][0];
		try {
			FileInputStream excel=new FileInputStream(file);
			wkbook=new XSSFWorkbook(excel);
			sheet=wkbook.getSheet(sheetName);
			int start=sheet.getFirstRowNum();
			int end=sheet.getLastRowNum();
			DataFormatter df=new DataFormatter();
			arr=new Object[end][columnCount];
			for(int i=start+1;i<=end;i++) {
				for(int j=0;j<=2;j++) {
					arr[i-1][j]= df.formatCellValue(sheet.getRow(i).getCell(j));
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return arr;
	}

}
