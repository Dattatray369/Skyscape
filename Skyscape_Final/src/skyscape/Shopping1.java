package skyscape;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class Shopping1 extends Base{
 
	String logintitle = "Sign In | Skyscape";
	String password = "tester123";
	String Product2 = "Medical Eponyms";
	String Product1_title = "Medical Eponyms | Skyscape";
	String ExpSelectedoption4 = "iOS 6.0 or higher";
	String OSverify = "iOS"; 
	
	 @Test
	  public void Shopping_without_login() throws IOException, InterruptedException {
		  Select s1 = new Select(w.findElement(By.name("AvailablePlatforms")));
		  s1.selectByVisibleText(ExpSelectedoption4);
		  w.findElement(By.id("AddToCart")).click();
		  List<WebElement> w2 = w.findElements(By.cssSelector("#ProductArea > table > tbody > tr"));
		  int itemsincart = w2.size();
		  if(itemsincart > 1){
			  w.findElement(By.cssSelector("#CartItems__ctl0_RemoveItem")).click();
		  }else{
			  w.findElement(By.id("CheckoutLink")).click();
			   if(w.getTitle().contains(logintitle)){
				  String email = Registration_Valid.readFromFile();
					String validemail = email + "@gmail.com";
				System.out.println(validemail);
					w.findElement(By.name("EmailAddress")).clear();
					w.findElement(By.name("EmailAddress")).sendKeys(validemail);
					Thread.sleep(400);
					w.findElement(By.name("Password")).clear();
					w.findElement(By.name("Password")).sendKeys(password);
					w.findElement(By.id("LoginButton")).click();
					if(w.getPageSource().contains("Form Entries Incomplete or Invalid")){
						w.findElement(By.name("EmailAddress")).clear();
						w.findElement(By.name("EmailAddress")).sendKeys("testauto2@gmail.com");
						Thread.sleep(400);
						w.findElement(By.name("Password")).clear();
						w.findElement(By.name("Password")).sendKeys("tester123");
						w.findElement(By.id("LoginButton")).click();
					}
					 w.getPageSource().contains("Billing Information");
			  }
		           
		           
		      }

		  }
		  

	  @Test
	  public void Shopping_with_CreditCard() throws BiffException, IOException, RowsExceededException, WriteException{

			FileInputStream f2 = new FileInputStream("D:/git_dir/Skyscape/Skyscape_Final/Excels/Shopping.xls");
			Workbook wb = Workbook.getWorkbook(f2);
			Sheet s2 = wb.getSheet("Sheet1");
			WritableWorkbook copy2 = Workbook.createWorkbook(new File("Shopping.xls"),wb);
			WritableSheet sheet2 = copy2.getSheet(0);
			for (int i = 1; i < s2.getRows(); i++) {
				int j = 1;
				String CardNumber = s2.getCell(j, i).getContents();//1
				String Year = s2.getCell(j + 1, i).getContents();//2
				String CVV = s2.getCell(j + 2, i).getContents();//3
				String Expected = s2.getCell(j + 3, i).getContents();//4
				
		 w.findElement(By.id("BillPhoneNumber")).sendKeys("1234567890");	
		  w.findElement(By.id("CheckoutWithCC")).click();
		  if (CardNumber.length() != 0) {
		  w.findElement(By.id("PaymentAccount")).sendKeys("CardNumber");
		  }
		  if (Year.length() != 0){
		  Select d4 = new Select(w.findElement(By.id("PaymentExpYear")));
			d4.selectByVisibleText(Year);
			}
		  if (CVV.length() != 0){
		  w.findElement(By.id("PaymentAccount")).sendKeys("CVV");
		  }
		   w.findElement(By.id("ContinueLink")).click();
	  
		  if(w.getTitle().contains("Order Confirm")){
			w.getPageSource().contains("Please review and submit your order.");
			w.getPageSource().contains(Product2);
			w.findElement(By.id("PlaceOrderBottomLink")).click();
			w.getPageSource().contains("Thank You! Your order has been placed.");
			w.getPageSource().contains(OSverify);
			Label label1 = new Label(5, i, "Pass");
			sheet2.addCell(label1);
		} else if (w.getTitle().contains("Order Checkout")){
			//add here assert to verify message
			Label label2 = new Label(5, i, "Pass");
		sheet2.addCell(label2);
	}
		 
	}
			 copy2.write();
				copy2.close();
	  }

	  
	  @BeforeTest
	  public void beforeTest() throws InterruptedException {
		  
		  w.get("https://www.skyscape.com/index/home.aspx");
		  w.findElement(By.linkText("PRODUCTS")).click();
		  w.findElement(By.linkText("View All Products")).click();
			w.findElement(By.linkText(Product2)).click();
			Thread.sleep(500);
			  w.getPageSource().contains(Product2);
			  Thread.sleep(500);
			  w.getPageSource().contains("Medical Eponyms");
	  }

	  @AfterTest
	  public void afterMethod() {
		  
		  
	  }

	}