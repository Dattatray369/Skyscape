package skyscape;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Registration_Valid extends Base {

	@Test
	public void main() throws BiffException, IOException, InterruptedException,
			RowsExceededException, WriteException {

		FileInputStream f = new FileInputStream(
				"D:/git_dir/Skyscape/Skyscape_Final/Excels/Registration.xls");
		Workbook wb = Workbook.getWorkbook(f);
		Sheet s = wb.getSheet("Sheet1");
		WritableWorkbook copy = Workbook.createWorkbook(new File(
				"Registrataion.xls"), wb);
		WritableSheet sheet2 = copy.getSheet(0);
		w.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		
		String account = "My Account | Skyscape";
		for (int i = 17; i == 17; i++) {
			int j = 1;
			String firstname = s.getCell(j, 17).getContents();// 1
			String lastname = s.getCell(j + 1, 17).getContents();// 2
			String country = s.getCell(j + 2, 17).getContents();// 3
			String add = s.getCell(j + 3, 17).getContents();// 4
			String city = s.getCell(j + 4, 17).getContents();
			String state = s.getCell(j + 5, 17).getContents();
			String zipcode = s.getCell(j + 6, 17).getContents();
			String profession = s.getCell(j + 7, 17).getContents();
			String specialty = s.getCell(j + 8, 17).getContents();

			String password = s.getCell(j + 10, 17).getContents();
			String confirmpassword = s.getCell(j + 11, 17).getContents();
			String expected = s.getCell(j + 12, 17).getContents();

			String email = readFromFile();
			String validemail = email + "@gmail.com";

				System.out.println(validemail);
			w.get("http://54.83.8.95/register/login.aspx?ReturnUrl=%2fsecure%2fmyaccount.aspx");
			Thread.sleep(400);
			w.findElement(By.id("NewRegistration")).click();
			Thread.sleep(400);
			WebElement frameID = w.findElement(By
					.cssSelector("#cboxLoadedContent > iframe"));
			w.switchTo().frame(frameID);
			Thread.sleep(400);
			w.findElement(By.id("SSORegistration_txtFirstName")).sendKeys(
					firstname);
			w.findElement(By.id("SSORegistration_txtLastName")).sendKeys(
					lastname);
			if (country.length() != 0) {
				Select d = new Select(w.findElement(By
						.id("SSORegistration_ddlCountry")));
				d.selectByVisibleText(country);
			}
			if (country.equalsIgnoreCase("United States")) {
				w.findElement(By.id("SSORegistration_txtAddress1")).sendKeys(
						add);
			}
			Thread.sleep(800);
			if (city.length() != 0) {
				w.findElement(By.id("SSORegistration_txtCity")).sendKeys(city);
			}
			if (country.equalsIgnoreCase("United States")) {
				if (state.length() != 0) {
					Select d1 = new Select(w.findElement(By
							.id("SSORegistration_ddlState")));
					d1.selectByVisibleText(state);
				}
			}
			w.findElement(By.id("SSORegistration_txtZip")).sendKeys(zipcode);
			if (profession.length() != 0) {
				Select d2 = new Select(w.findElement(By
						.id("SSORegistration_ddlOccupation")));
				d2.selectByVisibleText(profession);
			}
			Thread.sleep(400);
			if (profession.equals("Physician")
					|| profession.equals("Medical Student")
					|| profession.equals("Nurse Practitioner")
					|| profession.equals("Physician Assistant")
					|| profession.equals("Licensed Practical Nurse (LPN)")
					|| profession.equals("Registered Nurse (RN)")
					|| profession.equals("Dentist")) {
				if (specialty.length() != 0) {
					Select d3 = new Select(w.findElement(By
							.id("SSORegistration_ddlSpecialty")));
					d3.selectByVisibleText(specialty);
				}
			}
			w.findElement(By.id("SSORegistration_txtEmail")).sendKeys(validemail);
			System.out.println("validemail");

			w.findElement(By.id("SSORegistration_txtPassword")).sendKeys(
					password);
			w.findElement(By.id("SSORegistration_txtConfirmPassword"))
					.sendKeys(confirmpassword);
			w.findElement(By.id("SSORegistration_chkTermsOfservice")).click();
			w.findElement(By.id("SSORegistration_btnCreateAccount")).click();
			Thread.sleep(5000);
			//String actual = w.findElement(By.cssSelector("#SSORegistration_valSummary")).getText();
	
			if (w.getPageSource().contains("Please correct the highlighted fields:")) {
				//String actual = w.findElement(By.cssSelector("#SSORegistration_valSummary")).getText();
				//Assert.assertEquals(actual, expected);
				Label label1 = new Label(14, 17, "Fail");
				sheet2.addCell(label1);
				System.out.println("User id creation is failed");
			}else if(w.getTitle().contains(account)) {
				w.switchTo().defaultContent();
				w.getPageSource().contains("Sign Out");
					String newEmail = getNextAlphaNumeric(email);
					Thread.sleep(2000);
					System.out.println(newEmail+"after creation");
					writeToFile(newEmail);
					Thread.sleep(2000);
					System.out.println("User id created successfully");
					Label label3 = new Label(14, 17, "Pass");
					sheet2.addCell(label3);
					w.findElement(By.linkText("Sign Out")).click();
			}else {
				Label label2 = new Label(14, 17, "Fail");
			sheet2.addCell(label2);
			System.out.println("Failed out");
			
		}							
		}
		copy.write();
		Thread.sleep(2000);
		copy.close();
		Thread.sleep(2000);
	}

	private static void writeToFile(String newEmail) throws IOException {
		//File file = new File("C:/Users/Dattatray/Desktop/Lastcreatedid.txt");
				PrintWriter out = new PrintWriter("D:/git_dir/Skyscape/Skyscape_Final/Excels/Emailidtxt.txt");
				
			//	FileWriter fw = new FileWriter(out.getAbsoluteFile());
		      
				
				//BufferedWriter bw = new BufferedWriter(fw);
		      
				if (newEmail.length() != 0) {

					out.println(newEmail);
					System.out.println(newEmail+"written");
					out.close();
		}
	}

	public static String readFromFile() throws IOException {

		FileInputStream fstream = new FileInputStream("D:/git_dir/Skyscape/Skyscape_Final/Excels/Emailidtxt.txt");
		 BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		StringBuilder email = new StringBuilder();

		String line;
		try {
			while ((line = br.readLine()) != null) {
				email.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return email.toString();
	}

	public static String getNextAlphaNumeric(String oldAlphaNumericString) {

		// number of alphabets in alphanumeric string

		int noOfAlphabets = 8;
		String subStringAlphabet = oldAlphaNumericString.substring(0,
				noOfAlphabets);
		String subStringNoAlpha = oldAlphaNumericString
				.substring(noOfAlphabets);
		String leadingZeros = "";
		String noLeadingZeros = "";
		String newAlphaNumeric = "";
		// To count number of leading zeros

		for (int i = 0; i < subStringNoAlpha.length(); i++) {
			if (subStringNoAlpha.charAt(i) == '0') {
				leadingZeros += subStringNoAlpha.charAt(i);
			}

			else {
				break;
			}
		}
		if (leadingZeros.length() > 0) {
			// number to be added
			int addToAN = 1;
			noLeadingZeros += subStringNoAlpha.substring(leadingZeros.length());
			String noLeadingZeroNewAN = ""
					+ (Long.parseLong(noLeadingZeros) + addToAN);
			if (noLeadingZeroNewAN.length() != noLeadingZeros.length()) {
				// To check whether number of digits changed. Eg : 99 to 100
				int diff = noLeadingZeroNewAN.length()
						- noLeadingZeros.length();
				for (int i = 0; i < leadingZeros.length() - diff; i++) {
					newAlphaNumeric += leadingZeros.charAt(i);
				}
				newAlphaNumeric += "" + noLeadingZeroNewAN;
			} else {
				for (int i = 0; i < leadingZeros.length() - noOfAlphabets; i++) {
					newAlphaNumeric += leadingZeros.charAt(i);
				}
				newAlphaNumeric += "" + noLeadingZeroNewAN;
			}
		}

		else {
			newAlphaNumeric += "" + (Long.parseLong(subStringNoAlpha) + 1);
		}
		return subStringAlphabet + newAlphaNumeric;
	}

}
