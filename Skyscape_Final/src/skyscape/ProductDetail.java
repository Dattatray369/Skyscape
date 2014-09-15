package skyscape;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetail extends Base{
  
  String Product1 = "Medical Eponyms";
  String Product1_title = "Medical Eponyms | Skyscape";
  String ExpSelectedoption = "-- Choose a device/smartphone --";
  String selectedOption;
 
  
  @Test
  public void Launch_Product_Detail() throws InterruptedException {  
	 
	  w.findElement(By.linkText(Product1)).click();
	  w.getTitle().contains(Product1_title);
	  Thread.sleep(500);
	  w.getPageSource().contains(Product1);
	  Thread.sleep(500);
	  w.getPageSource().contains("Medical Eponyms");
	  }
  
  
  @Test
  public void Selecting_Platform() throws InterruptedException {
	  
	  w.findElement(By.linkText(Product1)).click();	
	  Select s1 = new Select(w.findElement(By.name("AvailablePlatforms")));
	  s1.selectByVisibleText(ExpSelectedoption);
	  selectedOption = new Select(w.findElement(By.name("AvailablePlatforms"))).getFirstSelectedOption().getText();
	  Assert.assertEquals(ExpSelectedoption, selectedOption);
	  w.findElement(By.id("AddToCart")).click();
	  Thread.sleep(3000);
	  w.getPageSource().contains("Please choose a device/smartphone.");
	  System.out.println("dropdwon_platform-Pass");
	  }
  
  @Test
  public void Add_Product_In_The_Cart() throws InterruptedException {
	  
	  w.findElement(By.linkText(Product1)).click();
	  Thread.sleep(200);
	  String SelectedOption1 = new Select(w.findElement(By.name("AvailablePlatforms"))).getFirstSelectedOption().getText();
	  System.out.println(SelectedOption1);
	  if(!SelectedOption1.equalsIgnoreCase("Please choose a device/smartphone.")){
	  w.findElement(By.id("AddToCart")).click();
	  Thread.sleep(2000);
	  w.getPageSource().contains("SHOPPING CART > BILLING > CONFIRM");
	  w.getPageSource().contains(Product1);
	  }
	  }
  
  
  
  
  @BeforeMethod
  public void beforeMethod() throws InterruptedException
  {
	  w.manage().deleteAllCookies();  
	  w.get("https://www.skyscape.com/index/home.aspx");
	  w.findElement(By.linkText("PRODUCTS")).click();
	  w.findElement(By.linkText("View All Products")).click();
	    
  }
  @AfterMethod
  public void AfterMethod() throws InterruptedException
  {
	  
	  System.out.println("tested");
	    
  }
  
}

