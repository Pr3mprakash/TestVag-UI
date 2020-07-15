package Page;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import com.google.common.base.Function;

public class Weatherpage {
	
	WebDriver D ;
	By lblsrch=By.xpath("//*[@id='searchBox']");
	
	By humid = By.xpath("//*[@class='leaflet-popup-content-wrapper']/div/div/span[3]");
	By Deg =By.xpath("//*[@class='leaflet-popup-content-wrapper']/div/div/span[4]");
	By Far = By.xpath("//*[@class='leaflet-popup-content-wrapper']/div/div/span[5]");
	By close= By.xpath("//*[@class='leaflet-popup-close-button']");
	By Spin= By.xpath("//*[@id='loading']");
	
	
    public Weatherpage(WebDriver driver)
    {
    	this.D=driver;
    }
    
    public boolean Srchcity(String city) throws InterruptedException
    {
    	
    	waitTillSpinnerDisable(D,Spin);
    	jsWaitForPageToLoad(10);
    	D.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    	
    	WebDriverWait wait = new WebDriverWait(D, 15);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(lblsrch));
    	
    	
    	D.findElement(lblsrch).clear();
    	D.findElement(lblsrch).sendKeys(city);
    	D.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    	
    	
    	String pathcity="//*[@id='"+city+"']";
    	
    	By pathcitylb =By.xpath(pathcity) ;
    	WebElement citylb=D.findElement(pathcitylb);
    	boolean enabled =citylb.isEnabled();
    	
    	if(enabled)
    	{
    		if(!citylb.isSelected())
    		{
    		citylb.click();
    		return true;
    		}
    		return true;
    	}
    	return false;
    }
    
    /*
    
    public String Tempcel(String City)
    {
    	String Cel="//*[@title='" + City + "']/div/span";
    	By Celby =By.xpath(Cel) ;
    	WebElement WEcel=D.findElement(Celby);
    	return WEcel.getText().replace("?", "");
    }

    public String Tempfar(String City)
    {
    	String far="//*[@title='" + City + "']/div/span[2]";
    	By farBy =By.xpath(far) ;
    	WebElement WEfar=D.findElement(farBy);
    	return WEfar.getText().replace("?", "");
    }
    
    public String humid(String City)
    {
    	String Cit="//*[@title='" + City + "']";
    	By CitBy =By.xpath(Cit) ;
    	WebElement WECit=D.findElement(CitBy);
    	
    	 Actions action = new Actions(D);
    	 action.moveToElement(WECit).click().perform();
    	
    	 return D.findElement(humid).getText().replace("Humidity:", "");
    }
*/
    
    public void Clickcity(String city)
    {
    	String Cit="//*[@title='" + city + "']";
    	By CitBy =By.xpath(Cit) ;
    	WebElement WECit=D.findElement(CitBy);
    	WebDriverWait wait = new WebDriverWait(D, 15);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(CitBy));
    	
    	 Actions action = new Actions(D);
    	 action.moveToElement(WECit).click().perform();
    }
    
    
    public String[] fetch(String city) throws InterruptedException
    {
    	
    	String[] SArray3 = null;
    	SArray3 = new String[3];
    	WebDriverWait wait = new WebDriverWait(D, 15);
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(humid));
    	SArray3[0]=D.findElement(humid).getText();
    	
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(Deg));
    	SArray3[1]=D.findElement(Deg).getText();
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(Far));
    	SArray3[2]=D.findElement(Far).getText();
    	
    	
    	Thread.sleep(50);
    	Actions action = new Actions(D);
    	WebElement close1=D.findElement(close);
    	action.moveToElement(close1).click().perform();
    	
		String pathcity="//*[@id='"+city+"']";
    	
    	By pathcitylb =By.xpath(pathcity) ;
    	WebElement citylb=D.findElement(pathcitylb);
    	citylb.click();
    	
    	Thread.sleep(50);
    	System.out.println();
    	return SArray3;
    	
    }
    
    
    public void jsWaitForPageToLoad(int timeOutInSeconds) {
        JavascriptExecutor js = (JavascriptExecutor) D;
        String jsCommand = "return document.readyState";

        // Validate readyState before doing any waits
        if (js.executeScript(jsCommand).toString().equals("complete")) {
            return;
        }
    
    }
    
    
    public static boolean waitTillSpinnerDisable(WebDriver D, By by)
    {
      FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(D);
      fWait.withTimeout(10, TimeUnit.SECONDS);
      fWait.pollingEvery(250, TimeUnit.MILLISECONDS);
      fWait.ignoring(NoSuchElementException.class);

      Function<WebDriver, Boolean> func = new Function<WebDriver, Boolean>() 
       {
         @Override
         public Boolean apply(WebDriver D) {
        WebElement element = D.findElement(by);
//        System.out.println(element.getCssValue("display"));         
        if(element.getCssValue("display").equalsIgnoreCase("none")){
        return true;
           }
            return false;
         }
       };

       return fWait.until(func);
    }
}
