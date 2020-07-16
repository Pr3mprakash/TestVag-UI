package Tests;

import java.io.File;
import java.text.DecimalFormat;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Compartor {
	
	
	
	public  String[] UI_citylist() throws InterruptedException,IOException
	{
		String X=System.getProperty("user.dir");
		X=X+"\\"+"Data"+"\\"+"Data.xlsx";
		File file =   new File(X);
//		
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
	

	public  String[] UI_Humidty() throws InterruptedException,IOException
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
	       city[i-1]= row.getCell(2).getStringCellValue();
		}
		
		inputStream.close();
		return city;
	}
	
	public  String[] UI_Temp() throws InterruptedException,IOException
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
	       city[i-1]= row.getCell(3).getStringCellValue();
		}
		
		inputStream.close();
		return city;
		
	}
	
	
	public  String[] API_citylist() throws InterruptedException,IOException
	{
		String X=System.getProperty("user.dir");
		X=X+"\\"+"Data"+"\\"+"Data-API.xls";
		File file =   new File(X);
		
//		File file =   new File("C:\\Users\\Princ3-PC\\Desktop\\TestVagrant\\Data\\Data-API.xls");
		FileInputStream inputStream = new FileInputStream(file);
		@SuppressWarnings("resource")
		HSSFWorkbook  abc = new HSSFWorkbook (inputStream);
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
	

	public  String[] API_Humdlist() throws InterruptedException,IOException
	{
		String X=System.getProperty("user.dir");
		X=X+"\\"+"Data"+"\\"+"Data-API.xls";
		File file =   new File(X);
		
//		File file =   new File("C:\\Users\\Princ3-PC\\Desktop\\TestVagrant\\Data\\Data-API.xls");
		FileInputStream inputStream = new FileInputStream(file);
		@SuppressWarnings("resource")
		HSSFWorkbook  abc = new HSSFWorkbook (inputStream);
		Sheet Sh=  abc.getSheet("Sheet1");
		
		int rowCount = Sh.getLastRowNum()-Sh.getFirstRowNum();
		
		String[] city =new String[rowCount];
		
		for (int i =1; i < rowCount+1; i++) 
		{
	        Row row = Sh.getRow(i);
	       city[i-1]= row.getCell(2).getStringCellValue();
		}
		
		inputStream.close();
		return city;
	}
	
	public  String[] API_Templist() throws InterruptedException,IOException
	{
		
		String X=System.getProperty("user.dir");
		X=X+"\\"+"Data"+"\\"+"Data-API.xls";
		File file =   new File(X);
		
//		File file =   new File("C:\\Users\\Princ3-PC\\Desktop\\TestVagrant\\Data\\Data-API.xls");
		FileInputStream inputStream = new FileInputStream(file);
		@SuppressWarnings("resource")
		HSSFWorkbook  abc = new HSSFWorkbook (inputStream);
		Sheet Sh=  abc.getSheet("Sheet1");
		
		int rowCount = Sh.getLastRowNum()-Sh.getFirstRowNum();
		
		String[] city =new String[rowCount];
		
		for (int i =1; i < rowCount+1; i++) 
		{
	        Row row = Sh.getRow(i);
	       city[i-1]= row.getCell(3).getStringCellValue();
		}
		
		inputStream.close();
		return city;
	}
	
	
	public void comp(String[] UI_City,String[] UI_hum, String[] UI_temp , String[] API_City,String[] API_Humd,String[] API_Temp, int temvariance, int humvariance)
	{
		int max=0;
		float temp1=0;
		float temp2=0;; 
		float tempmax=0;
		float tempmin=0;
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		
		if(UI_City.length>API_City.length)
			max=UI_City.length;
		else 
			max=API_City.length;
			
		for(int i=0;i<max;i++)
		{
		
			if(API_City[i].equals(UI_City[i])) //city
			{
				
				System.out.println(API_City[i] + "--");
				
				// temp
				
				temp1 =  Float.parseFloat(UI_temp[i].replace("Temp in Degrees: ",""));
				temp2 =	  Float.parseFloat(( API_Temp[i]));
				
				temp2 = (float) (temp2-273.15);
				
				
				if(temp1>temp2) {
					tempmax=temp1;
					tempmin=temp2;
				}
				else {
					tempmax=temp2;
					tempmin=temp1;}
				
				
				if((tempmax-tempmin)>temvariance)
				{
					System.out.print("Temp difference doesnt falls under variance ");
					System.out.print("--UI -temperature " + temp1);
					System.out.println("--API -temperature " + temp2);
				}
				else
				{
					System.out.print("Tempdifference falls under variance ");
					System.out.print("--UI -temperature " + temp1);
					System.out.println("--API -temperature " + temp2);
				}
				
				//humdi
					
					
			}
			else
				System.out.println("API-city not matching with UI-City"+ UI_City[i] + API_City[i]);
		}
		
		
	}
	
	public static void main(String arg[])throws InterruptedException,IOException
	{
		int tempvariance = 5;
		int humdvariance =10;
		
		String[] UI_City=null;
		String[] UI_humidty=null;
		String[] UI_temp=null;
		
		String[] API_City=null;
		String[] API_humidty=null;
		String[] API_temp=null;
		
		Compartor c= new Compartor();
		
		UI_City=c.UI_citylist();
		UI_humidty=c.UI_Humidty();
		UI_temp=c.UI_Temp();
		
		API_City=c.API_citylist();
		API_humidty=c.API_Humdlist();
		API_temp=c.API_Templist();
		
		c.comp(UI_City, UI_humidty, UI_temp, API_City, API_humidty, API_temp, tempvariance, humdvariance);
		
		System.out.println("-----------");
		
		for(int i=0;i<UI_City.length;i++)
		{
			System.out.print(UI_City[i]+"--");
			System.out.print(UI_humidty[i]+"--");
			System.out.print(UI_temp[i]);
			
			System.out.println();
		}
		
		for(int i=0;i<API_City.length;i++)
		{
			System.out.print(API_City[i]+"--");
			System.out.print(API_humidty[i]+"--");
			System.out.print(API_temp[i]+"--");
			
			System.out.println();
		}
		
		
	}

}
