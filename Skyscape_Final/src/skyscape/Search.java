package skyscape;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Search extends Base {
  //WebDriver w = new FirefoxDriver();
  String id = "testmobiuso33@gmail.com";
  String search_title = "Search Results";
  String product = "5-Minute Pediatric Consult";
  String keyword1 = "davis";	
  String keyword2 = "labs 360";
  String keyword3 = "fever";
  String account = "My Account | Skyscape";

  @Test(priority=0)
  public void Search1_davis() {
	  w.findElement(By.name("q")).sendKeys(keyword1);
	  w.findElement(By.id("search-button")).click();
	  w.getTitle().contains(search_title);
	  w.getPageSource().contains("Search Results");
	  List<WebElement> w1 = w.findElements(By.cssSelector("#ProductArea > table > tbody > tr"));
	  int search1_count = w1.size();
	  w.getPageSource().contains("keyword1");
	  Assert.assertEquals(search1_count, 47);
  }
  
  @Test(priority=0)
  public void Search2_labs360() {
	  w.findElement(By.name("q")).sendKeys(keyword2);
	  w.findElement(By.id("search-button")).click();
	  w.getTitle().contains(search_title);
	  w.getPageSource().contains("Search Results");
	  List<WebElement> w2 = w.findElements(By.cssSelector("#ProductArea > table > tbody > tr"));
	  int search2_count = w2.size();
	  w.getPageSource().contains("keyword2");
	  Assert.assertEquals(search2_count, 5);
  }
    
  
  
  @Test(priority=0)
  public void Search_no_results() {
	  //w.findElement(By.name("q")).sendKeys(keyword3);
	  w.findElement(By.id("search-button")).click();
	  w.getTitle().contains(search_title);
	  w.getPageSource().contains("Your search for [ ] returned no products. Please try a different search term.");
	  }
  
  @Test(priority=1)
  public void search_without_login() throws InterruptedException {
	  w.findElement(By.linkText("MY ACCOUNT")).click();
	  if (w.getTitle().contains(account)){
		  w.findElement(By.linkText("Sign Out")).click();
		   }
	  Thread.sleep(500);
	  w.findElement(By.name("q")).sendKeys(keyword1);
	  w.findElement(By.id("search-button")).click();
	  w.getTitle().contains(search_title);
	  w.getPageSource().contains("Search Results");
	  List<WebElement> w3 = w.findElements(By.cssSelector("#ProductArea > table > tbody > tr"));
	  int search3_count = w3.size();
	  w.getPageSource().contains("keyword1");
	  Assert.assertEquals(search3_count, 47);
  }
  
  
  @BeforeClass
  public void beforeClass3() {
	  w.get("https://www.skyscape.com");
	  //w.manage().window().maximize();
	  w.findElement(By.linkText("MY ACCOUNT")).click();
	  w.findElement(By.name("EmailAddress")).clear();
		w.findElement(By.name("EmailAddress")).sendKeys(id);
		w.findElement(By.name("Password")).clear();
		w.findElement(By.name("Password")).sendKeys("tester123");
		w.findElement(By.id("LoginButton")).click();
	  	w.getPageSource().contains(id);
  }

  @AfterClass
  public void afterClass3() {
	  w.findElement(By.linkText("MY ACCOUNT")).click();
  	  if (w.getTitle().contains(account)) {
			w.getPageSource().contains("Sign Out");
		System.out.println("search executed successfully");
  }
  }
  }
