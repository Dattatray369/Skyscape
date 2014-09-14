package skyscape;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Launch extends Base{
  
	 String home_title = "Mobile medical resources for iOS & Android | Skyscape";
	 //test git
	
	@Test(priority=0)
  public void Skyscape_website_launch() {
		w.get("http://www.skyscape.com");
		w.getCurrentUrl().equals("https://www.skyscape.com/index/home.aspx");
		String HTitle = w.getTitle();
		Assert.assertEquals(HTitle, home_title);
		}

	@Test(priority=1)
	  public void Scroll_down() {
		
		//Boolean ww = w.findElements(By.id("logo")).size() > 0 ;
		//System.out.println(ww);
		List<WebElement> elements = w.findElements(By.id("logo"));
		
		
	    // check visibility
	  for (WebElement element : elements) {
	      Assert.assertTrue(element.isDisplayed());
	    }
		// scroll up
	    ((JavascriptExecutor) w).executeScript("scroll(0,250);");
	    elements = w.findElements(By.id("logo"));
	    
	    // check visibility after scroll up
	    List<WebElement> elements1 = w.findElements(By.id("logo"));
	    for (WebElement element : elements1) {
	      Assert.assertTrue(element.isDisplayed());
	    }
//	    Boolean ww1 = w.findElements(By.id("logo")).size() > 0 ;
//	    Assert.assertFalse(ww1);
	    
	    // scroll up
	    ((JavascriptExecutor) w).executeScript("scroll(250,0);");
	    elements = w.findElements(By.id("logo"));
	    
//	     check visibility after scroll up
	    List<WebElement> elements2 = w.findElements(By.id("logo"));
	    for (WebElement element : elements2) {
	      Assert.assertTrue(element.isDisplayed());
	    }
	}

}