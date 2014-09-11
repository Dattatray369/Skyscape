package skyscape;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class Shopping_cart extends Base {
	//WebDriver w = new FirefoxDriver();
	String id = "testmobiuso33@gmail.com";
	String product = "5-Minute Pediatric Consult";
	float productcost = (float) 99.95;
	float costafterupdate  = (float) 199.90;
	String quantity = "2";
	
	//WebElement desktop = w.findElement(By.className("icon-rx"));
		
	
@Test
  public void Checkout() throws InterruptedException {
	  w.findElement(By.linkText("PRODUCTS")).click();
	  w.findElement(By.linkText("Physicians")).click();
	  w.findElement(By.linkText(product)).click();
	  w.findElement(By.id("AddToCart")).click();
	  Thread.sleep(2000);
	  w.getPageSource().contains(product);
	  Thread.sleep(2000);
	  w.findElement(By.id("CheckoutLink")).click();
	  w.getPageSource().contains("BILLING & SHIPPING");
	  String id_1 = w.findElement(By.name("BillEmailAddress")).getAttribute("value");
	  Assert.assertEquals(id_1, id);
	  System.out.println("checkout-Pass");
	  w.navigate().back();
	  w.getPageSource().contains("BILLING & SHIPPING");
	  }
  
 
  @Test
  public void Remove_resource_from_cart() throws InterruptedException {
	  
	  w.findElement(By.linkText("PRODUCTS")).click();
	  Thread.sleep(500);
	  w.findElement(By.linkText("Physicians")).click();
	  Thread.sleep(500);
	  w.findElement(By.linkText(product)).click();
	  Thread.sleep(500);
	  w.findElement(By.id("AddToCart")).click();
	  Thread.sleep(2000);
	  w.getPageSource().contains(product);
	  Thread.sleep(800);
	  w.findElement(By.cssSelector("#CartItems__ctl0_RemoveItem")).click();
	  Thread.sleep(800);
	  Assert.assertNotEquals(w.getPageSource().contains("5-Minute Pediatric Consult"), product);
	 System.out.println("Remove_resource_from_cart_pass"); 
  }
  
 @Test
  public void Change_Platform() throws InterruptedException {
	  
	 String ExpSelectedoption3 = "Windows desktop PC";
	 
	  w.findElement(By.linkText("PRODUCTS")).click();
	  Thread.sleep(500);
	  w.findElement(By.linkText("Physicians")).click();
	  Thread.sleep(2000);
	  w.findElement(By.linkText(product)).click();
	  Thread.sleep(200);
	  w.findElement(By.id("AddToCart")).click();
	  Thread.sleep(2000);
	  w.getPageSource().contains(product);
	  Thread.sleep(2000);
	  w.findElement(By.cssSelector("#CartSection > table.mycart.pad_left > tbody > tr:nth-child(2) > td.item-info > span.removebutton > a.APEdocument.APEinternal.box_link")).click();
	  Thread.sleep(800);
	  WebElement frameID = w.findElement(By.cssSelector("#cboxLoadedContent > iframe"));
	  Thread.sleep(800);
	  w.switchTo().frame(frameID);
	  Thread.sleep(800);
	  Select d1 = new Select(w.findElement(By.name("AvailablePlatforms")));
	  d1.selectByVisibleText(ExpSelectedoption3);
	  Thread.sleep(800);
	  w.findElement(By.cssSelector("#Change")).click();
	  Thread.sleep(800);
	  w.switchTo().defaultContent();
	  Thread.sleep(800);
	  w.getPageSource().contains(product);
	  w.findElement(By.cssSelector("#CartSection > table.mycart.pad_left > tbody > tr:nth-child(2) > td.item-info > span.removebutton > a.APEdocument.APEinternal.box_link")).click();
	  Thread.sleep(800);
	  w.switchTo().frame(frameID);
	  Thread.sleep(800);
	  String SelectedOption3 = new Select(w.findElement(By.name("AvailablePlatforms"))).getFirstSelectedOption().getText();
	  Assert.assertEquals(ExpSelectedoption3, SelectedOption3);
	  System.out.println("Change_Platform_Testcase Passed");
	  }
   
 @Test
 public void Update_Cart () throws InterruptedException {
	
	  
	  w.findElement(By.linkText("PRODUCTS")).click();
	  Thread.sleep(500);
	  w.findElement(By.linkText("Physicians")).click();
	  Thread.sleep(500);
	  w.findElement(By.linkText(product)).click();
	  Thread.sleep(500);
	  w.findElement(By.id("AddToCart")).click();
	  Thread.sleep(2000);
	  w.getPageSource().contains(product);
	  Thread.sleep(800);
	  float beforeupdatecost = Float.parseFloat(w.findElement(By.cssSelector("#CartSection > table.mycart.pad_left > tbody > tr:nth-child(4) > td:nth-child(3)")).getText());
	  Assert.assertEquals(beforeupdatecost, productcost);
	  w.findElement(By.name("CartItems:_ctl0:Quantity")).sendKeys(quantity);
	  w.findElement(By.id("UpdateCart")).click();
	  float afterupdatecost = Float.parseFloat(w.findElement(By.cssSelector("#CartSection > table.mycart.pad_left > tbody > tr:nth-child(4) > td:nth-child(3)")).getText());
	  Assert.assertEquals(afterupdatecost, costafterupdate);
	 System.out.println("Remove_resource_from_cart_pass"); 
 }
 
  
  @BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  w.get("https://www.skyscape.com");
	  Thread.sleep(500);
	  w.findElement(By.linkText("MY ACCOUNT")).click();
	  w.findElement(By.name("EmailAddress")).clear();
		w.findElement(By.name("EmailAddress")).sendKeys(id);
		w.findElement(By.name("Password")).clear();
		w.findElement(By.name("Password")).sendKeys("tester123");
		w.findElement(By.id("LoginButton")).click();
	  	w.getPageSource().contains(id);
	  	w.findElement(By.linkText("PRODUCTS")).click();
		w.findElement(By.linkText("Physicians")).click();
		w.findElement(By.linkText(product)).click();  
  }

  @AfterMethod
  public void afterMethod() throws InterruptedException {
	  w.findElement(By.linkText("MY ACCOUNT")).click();
	  Thread.sleep(500);
	  w.findElement(By.linkText("Sign Out")).click();	
  }
  
}
