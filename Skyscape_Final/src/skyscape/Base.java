package skyscape;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Base {
	  public static WebDriver w = new FirefoxDriver(); 
	
  @BeforeSuite
  public void setup() {
	  w.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	  w.manage().window().maximize();
  }

  @AfterSuite
   public void tearDown() throws IOException{
          try{
        	  w.close();
              }catch (Exception e){
              System.out.println("exception caught while closing driver"
+e);
          }finally{
        	  w.quit();
  }

}
}

