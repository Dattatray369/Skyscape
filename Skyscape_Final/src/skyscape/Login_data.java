package skyscape;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Login_data extends Base {

	// public static WebDriver w1 = new FirefoxDriver();

	@Test
	public void Login_All_scenarios() throws BiffException, IOException,
			InterruptedException, RowsExceededException, WriteException {
		FileInputStream f = new FileInputStream("D:/git_dir/Skyscape/Skyscape_Final/Excels/login.xls");
		Workbook wb = Workbook.getWorkbook(f);
		Sheet s = wb.getSheet("Sheet1");

		WritableWorkbook copy = Workbook.createWorkbook(new File("Login.xls"),
				wb);
		WritableSheet sheet2 = copy.getSheet(0);

		for (int i = 1; i < s.getRows(); i++) {
			int j = 0;

			String id = s.getCell(j, i).getContents();
			String password = s.getCell(j + 1, i).getContents();
			String error = s.getCell(j + 2, i).getContents();

			w.get("https://www.skyscape.com/register/login.aspx?ReturnUrl=%2fsecure%2fmyaccount.aspx");
			w.findElement(By.name("EmailAddress")).clear();
			w.findElement(By.name("EmailAddress")).sendKeys(id);
			Thread.sleep(400);
			w.findElement(By.name("Password")).clear();
			w.findElement(By.name("Password")).sendKeys(password);
			w.findElement(By.id("LoginButton")).click();
			
			if (w.getPageSource().contains("Sign Out")) {
				System.out.println(id + " logged in");
				Label label1 = new Label(3, i, "Pass");
				sheet2.addCell(label1);
				Thread.sleep(400);
				w.findElement(By.linkText("Sign Out")).click();
				w.getPageSource().contains("LOGIN TO SKYSCAPE");

			} else if (w.getPageSource().contains("Form Entries Incomplete or Invalid")) {
				System.out.println(id + "Pass");
				String error1 = w.findElement(
						By.cssSelector("#Validationsummary1 > ul >li"))
						.getText();
				System.out.println(id + error1);
				Assert.assertEquals(error, error1);
				Label label2 = new Label(3, i, "Pass");
				sheet2.addCell(label2);
			} else {
				Label label3 = new Label(3, i, "Fail");
				sheet2.addCell(label3);
			}
		}
		copy.write();
		copy.close();
	}
}

/*
 * @BeforeClass public void beforeClass() {
 * 
 * w.manage().window().maximize(); }
 * 
 * @AfterClass public void afterClass() {
 * 
 * 
 * }
 */

