package skyscape;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class Shopping extends Base{
	
	
  @Test
  public void Shopping_without_login() {
  
  
  
  }
  @BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  
	  w.get("https://www.skyscape.com");
	  Thread.sleep(500);
	 	w.findElement(By.linkText("PRODUCTS")).click();
		w.findElement(By.linkText("Physicians")).click();
		//w.findElement(By.linkText(product)).click(); 
  }

  @AfterMethod
  public void afterMethod() {
	  
	  
  }

}
