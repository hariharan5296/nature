
package org.bas;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.Element;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
		public static void browserLaunch(String url) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);

	}

	public static void getmaxi() {
		driver.manage().window().maximize();
	}

	// WEBDRIVER Lanuch
	public static WebElement locationFind(String locator, String Value) {

		WebElement element = null;

		if (locator.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(Value));

		} else if (locator.equalsIgnoreCase("name")) {
			element = driver.findElement(By.name(Value));

		} else {
			element = driver.findElement(By.xpath(Value));
		}
		return element;
	}

	////////////// sendkeys
	public static void enterText(WebElement element, String value) {
		element.sendKeys(value);
	}

	////////////////// click
	public static void getclick(WebElement element) {
		element.click();
	}
	////// Select
	
	

	public static void selectIndexBy(WebElement element, int index) {
		new Select(element).selectByIndex(index);

	}

	public static void selectVisible(WebElement element, String data) {
		new Select(element).selectByVisibleText(data);
	}

	public static void selectvalue(WebElement element, String data) {
		new Select(element).deselectByValue(data);
	}

	public static void selectOption(WebElement element) {
		new Select(element).getOptions();
	}

	///// Excell
	public static String getexcelData(String path, String sheetname, int rownum, int cellnum) throws IOException {
		String data = null;
		File file = new File(path);
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		int cellType = cell.getCellType();

		if (cellType == 1) {
			data = cell.getStringCellValue();

		} else if (DateUtil.isCellDateFormatted(cell)) {
			Date dateCellValue = cell.getDateCellValue();
			SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
			data = format.format(dateCellValue);

		} else {

			double numericCellValue = cell.getNumericCellValue();
			long l = (long) numericCellValue;
			data = String.valueOf(l);

		}
		return data;
	}
	
	
	
	
	

	public static void getQuit() {
		driver.quit();
	}

}
