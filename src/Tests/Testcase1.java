package Tests;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Page.Homepage;
import Page.Weatherpage;
import junit.framework.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Testcase1
{
Homepage hm;
Weatherpage wp;
WebDriver driver;

	public void setup()
	{
//    System.setProperty("webdriver.chrome.driver","C:\\Users\\Princ3-PC\\Documents\\chromedriver_win32\\chromedriver.exe");
//	driver = new ChromeDriver();
		
	WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();    
	 hm = new Homepage(driver);
	 wp = new Weatherpage(driver);
	 
	}
	
	public void loginandvalidate() 
	{
    String Logintitle =hm.getLoginTitle().toLowerCase().replace(" ","");
    Assert.assertTrue(Logintitle.contains( "ndtv:latestnews"));
    }
	
	public void navigate()
	{
	   String weather =hm.weathernavigate().toLowerCase().replace(" ","");
	   Assert.assertTrue(weather.contains( "ndtvweather"));
	}
	
	public String[] Fetchdetails(String city) throws InterruptedException
	{
		String arr[] =null;
		try 
		{
			wp.Srchcity(city);
			wp.Clickcity(city);
			arr = wp.fetch(city) ;
		}
		catch (Exception e)
		{
			System.out.println("Exception occurred");
		}
		return arr;
		 
	}
	
//	public void Gettempdetails(String city) throws InterruptedException
//	{
//	 Assert.assertEquals(true,(wp.Srchcity(city)));
//	 System.out.println(wp.Tempcel(city));
//	 System.out.println(wp.Tempfar(city));
//	 System.out.println(wp.humid(city));
//	}
	
	public void close()
	{
		driver.close();
	}
	
	
	public String[] citylist() throws InterruptedException,IOException
	{
		String X=System.getProperty("user.dir");
		X=X+"\\"+"Data"+"\\"+"Data.xlsx";
		
		File file =   new File(X);
//		File file =   new File("C:\\Users\\Princ3-PC\\Desktop\\TestVagrant\\Data\\Data.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		@SuppressWarnings("resource")
		Workbook abc = new XSSFWorkbook(inputStream);
		Sheet Sh=  abc.getSheet("Sheet1");
		
		int rowCount = Sh.getLastRowNum()-Sh.getFirstRowNum();
		
		String[] city =new String[rowCount];
		
		for (int i =1; i < rowCount+1; i++) 
		{
	        Row row = Sh.getRow(i);
	       city[i-1]= row.getCell(1).getStringCellValue();
	    }
		
		inputStream.close();
		return city;
		
	}
	
	public void writedata(String[] details, int row) throws IOException
	{
		String X=System.getProperty("user.dir");
		X=X+"\\"+"Data"+"\\"+"Data.xlsx";
		
		File file =   new File(X);
		
//		File file =   new File("C:\\Users\\Princ3-PC\\Desktop\\TestVagrant\\Data\\Data.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		@SuppressWarnings("resource")
		Workbook abc = new XSSFWorkbook(inputStream);
		Sheet Sh=  abc.getSheet("Sheet1");
		
		
		    Row row1 = Sh.getRow(row+1);
	        Cell cell = row1.createCell(2);
	        cell.setCellValue(details[0]);
	        
	        Cell cell2 = row1.createCell(3);
	        cell2.setCellValue(details[1]);
	        
	        Cell cell3 = row1.createCell(4);
	        cell3.setCellValue(details[2]);
	        
		
		
		inputStream.close();
		 FileOutputStream outputStream = new FileOutputStream(file);
		 abc.write(outputStream);
		 outputStream.close();
		
	}
	
	public static void main(String arg[])throws InterruptedException,IOException
	{
		
		Testcase1 t=new Testcase1();
		String[] details=null;
		String[] city=null;
		
		city=t.citylist();
		
		t.setup();
		t.loginandvalidate();
		t.navigate();
		
		for(int i=0;i<city.length;i++)
		{
			try 
			{
			details=t.Fetchdetails(city[i]);
			t.writedata(details,i);
			
			}
			catch(Exception E)
			{
				System.out.println("main exception");
			}
		
		}
		t.close();
		
		
					
	}	
		
}
