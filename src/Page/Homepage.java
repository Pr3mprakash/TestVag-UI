package Page;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By.ById;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



@SuppressWarnings("unused")
public class Homepage 
{

	//System.setProperty("webdriver.chrome.driver","C:\\Users\\Princ3-PC\\Documents\\chromedriver_win32\\chromedriver.exe");
	WebDriver D ;
    String baseUrl = "https://www.ndtv.com/";
    By submenu=By.xpath("//*[@id='h_sub_menu']");
    By Weather = By.xpath("//a[contains(@href,'static/Weather/report')]");
   
    
    public Homepage(WebDriver driver)
    {
    	this.D=driver;
    }
    
    public String getLoginTitle()
    {
    	D.get(baseUrl);
        D.manage().window().maximize();
        D.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        return    D.getTitle();
    }
    
    public String weathernavigate()
    { 		
    	    Actions action = new Actions(D);
    	    WebElement smenu=D.findElement(submenu);
    	    action.moveToElement(smenu).click().perform();
    		WebElement a=D.findElement(Weather);
    		action.moveToElement(a).click().perform();
    		 D.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    		 return    D.getTitle();
    }
}
