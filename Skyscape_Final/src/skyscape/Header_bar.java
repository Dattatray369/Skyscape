package skyscape;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Header_bar extends Base {
	//public static WebDriver w = new FirefoxDriver();
	String id = "testmobiuso33@gmail.com";
	String password = "tester123";
	String Home_Title = "Mobile medical resources for iOS, Android | Skyscape";
	String App_Title =  "Mobile medical resources for iOS, Android | Skyscape";
	String Product_Title =  "Resources | Skyscape";
	String Schools_Groups_Title =  "Schools & Groups | Skyscape";
	String Support_Title =  "Support";
	String login = "Sign In | Skyscape";
	String My_Account_Title =  "My Account | Skyscape";
	String Cart = "Shopping Cart";
	
	
  @Test // It will fail because need to change title of this page
  public void Home_page() {
	w.get("https://www.skyscape.com/index/home.aspx");
	w.findElement(By.cssSelector("#primary > li.menu-item.menu-item-type-post_type.menu-item-object-page.page_item.page-item-192.current_page_item")).click();
	w.getCurrentUrl().equals("https://www.skyscape.com/index/home.aspx");	  
	String HTitle = w.getTitle();
	System.out.println(HTitle);
	Assert.assertEquals(HTitle, Home_Title);
	}
  
  @Test// It will fail because need to change title of this page
  public void App_page() {
	w.findElement(By.cssSelector("#primary > li:nth-child(2) > a")).click();
	w.getCurrentUrl().equals("https://www.skyscape.com/sml/");	  
	String ATitle = w.getTitle();
	System.out.println(ATitle);
	Assert.assertEquals(ATitle, App_Title);
  }  
  
  
  @Test
  public void Product_page() {
	w.findElement(By.cssSelector("#primary > li:nth-child(3) > a")).click();
	w.getCurrentUrl().equals("https://www.skyscape.com/estore/productoverview.aspx");	  
	String PTitle = w.getTitle();
	System.out.println(PTitle);

	Assert.assertEquals(PTitle, Product_Title);
  }  
  
  @Test
   public void School_page() {
	w.findElement(By.cssSelector("#primary > li:nth-child(4) > a")).click();
	w.getCurrentUrl().equals("https://www.skyscape.com/group/grouphome.aspx");	  
	String STitle = w.getTitle();
	System.out.println(STitle);
	Assert.assertEquals(STitle, Schools_Groups_Title);
  }  
  
  @Test
  public void Support() {
	w.findElement(By.cssSelector("#primary > li:nth-child(5) > a")).click();
	w.getCurrentUrl().equals("https://www.skyscape.com/support/supporthome.aspx");	  
	String SuppTitle = w.getTitle();
	System.out.println(SuppTitle);
	Assert.assertEquals(SuppTitle, Support_Title);
 } 
  
  @Test
  public void Cart() {
 	w.findElement(By.linkText("CART")).click();
 	w.getCurrentUrl().equals("https://www.skyscape.com/estore/shoppingcart.aspx");	  
 	String CartTitle = w.getTitle();
 	System.out.println(CartTitle);
 	Assert.assertEquals(CartTitle, Cart);
  }
  
 @Test
 public void My_Account() {
	w.findElement(By.cssSelector("#primary > li:nth-child(6) > a")).click();
	w.getCurrentUrl().equals("https://www.skyscape.com/register/login.aspx?ReturnUrl=%2fsecure%2fmyaccount.aspx");	  
	String AccTitle = w.getTitle();
	System.out.println(AccTitle);
	Assert.assertEquals(AccTitle, login);
 }


 @Test
 public void My_Account_after_login() throws InterruptedException {
	w.findElement(By.cssSelector("#primary > li:nth-child(6) > a")).click();
	w.getCurrentUrl().equals("https://www.skyscape.com/register/login.aspx?ReturnUrl=%2fsecure%2fmyaccount.aspx");	  
	w.findElement(By.name("EmailAddress")).sendKeys(id);
	w.findElement(By.name("Password")).sendKeys(password);
	w.findElement(By.id("LoginButton")).click();
  	w.getPageSource().contains(id);
	String MyTitle = w.getTitle();
	System.out.println(MyTitle);
	Assert.assertEquals(MyTitle, My_Account_Title);
	w.findElement(By.linkText("MY ACCOUNT")).click();
	  Thread.sleep(500);
	  w.findElement(By.linkText("Sign Out")).click();	
 }
 
 /* 
 @BeforeClass
 public void beforeClass1() {
	w.manage().window().maximize();
	w.get("https://www.skyscape.com/index/home.aspx");
  }

 @AfterClass
 public void afterClass1() {
	 w.close();
	 
  }*/
}