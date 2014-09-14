package skyscape;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Registration_Invalid extends Base {
	
	 @Test
	 public void Registration_All_scenarios() throws Exception, IOException {

		FileInputStream f = new FileInputStream("D:/git_dir/Skyscape/Skyscape_Final/Excels/Registration.xls");
		Workbook wb = Workbook.getWorkbook(f);
		Sheet s = wb.getSheet("Sheet1");
		WritableWorkbook copy = Workbook.createWorkbook(new File("status.xls"),wb);
		WritableSheet sheet2 = copy.getSheet(0);
		//WebDriver w = new FirefoxDriver();
		//w.manage().window().maximize();
		w.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		String account = "My Account | Skyscape";
		for (int i = 1; i < s.getRows(); i++) {
			int j = 1;
			String firstname = s.getCell(j, i).getContents();//1
			String lastname = s.getCell(j + 1, i).getContents();//2
			String country = s.getCell(j + 2, i).getContents();//3
			String add = s.getCell(j + 3, i).getContents();//4
			String city = s.getCell(j + 4, i).getContents();
			String state = s.getCell(j + 5, i).getContents();
			String zipcode = s.getCell(j + 6, i).getContents();
			String profession = s.getCell(j + 7, i).getContents();
			String specialty = s.getCell(j + 8, i).getContents();
			String email = s.getCell(j + 9, i).getContents();
			String password = s.getCell(j + 10, i).getContents();
			String confirmpassword = s.getCell(j + 11, i).getContents();
			String expected = s.getCell(j + 12, i).getContents();
			w.get("https://www.skyscape.com/register/login.aspx?ReturnUrl=%2fsecure%2fmyaccount.aspx");
			Thread.sleep(400);
			w.findElement(By.id("NewRegistration")).click();
			Thread.sleep(400);
			WebElement frameID = w.findElement(By.cssSelector("#cboxLoadedContent > iframe"));
			w.switchTo().frame(frameID);
			Thread.sleep(400);
			w.findElement(By.id("SSORegistration_txtFirstName")).sendKeys(firstname);
			w.findElement(By.id("SSORegistration_txtLastName")).sendKeys(lastname);
			if (country.length() != 0) {
				Select d = new Select(w.findElement(By.id("SSORegistration_ddlCountry")));
				d.selectByVisibleText(country);
			}
			if (country.equalsIgnoreCase("United States")){
				w.findElement(By.id("SSORegistration_txtAddress1")).sendKeys(add);
			}
			Thread.sleep(800);
			if (city.length() != 0) {
			w.findElement(By.id("SSORegistration_txtCity")).sendKeys(city);
			}
			if (country.equalsIgnoreCase("United States")){
				if (state.length() != 0) {
				Select d1 = new Select(w.findElement(By.id("SSORegistration_ddlState")));
				d1.selectByVisibleText(state);
				}
			}
			w.findElement(By.id("SSORegistration_txtZip")).sendKeys(zipcode);
			if (profession.length() != 0) {
				Select d2 = new Select(w.findElement(By.id("SSORegistration_ddlOccupation")));
				d2.selectByVisibleText(profession);
			}
			Thread.sleep(400);
			if (profession.equals("Physician")||profession.equals("Medical Student")||profession.equals("Nurse Practitioner")||profession.equals("Physician Assistant")||profession.equals("Licensed Practical Nurse (LPN)")||profession.equals("Registered Nurse (RN)")||profession.equals("Dentist")){
				if (specialty.length() != 0) {
					Select d3 = new Select(w.findElement(By.id("SSORegistration_ddlSpecialty")));
					d3.selectByVisibleText(specialty);
				}
			}
			w.findElement(By.id("SSORegistration_txtEmail")).sendKeys(email);
			w.findElement(By.id("SSORegistration_txtPassword")).sendKeys(password);
			w.findElement(By.id("SSORegistration_txtConfirmPassword")).sendKeys(confirmpassword);
			w.findElement(By.id("SSORegistration_chkTermsOfservice")).click();
			w.findElement(By.id("SSORegistration_btnCreateAccount")).click();
			String actual = w.findElement(By.cssSelector("#SSORegistration_valSummary")).getText();
			System.out.println(actual);
			System.out.println(expected);
			if (w.getPageSource().contains("Please correct the highlighted fields:")) {
				Assert.assertEquals(actual, expected);
				Label label1 = new Label(14, i, "Pass");
				sheet2.addCell(label1);
			} else {
				Label label2 = new Label(14, i, "Fail");
			sheet2.addCell(label2);
		}
			w.switchTo().defaultContent();
			if (w.getTitle().contains(account)) {
				
				w.getPageSource().contains("Sign Out");
				System.out.println("User id created successfully");
				Label label3 = new Label(14, i, "Pass");
				sheet2.addCell(label3);
				w.findElement(By.linkText("Sign Out")).click();
				}
		}
		copy.write();
		copy.close();
		
	}
}