package com.stocks;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.base.Base;
import com.opencsv.CSVWriter;

public class GetStockPrices extends Base{
	
	
	public static List<String[]> finalData = new ArrayList<String[]>();
	public static String[] entities = new String[10];
	public static Map<Integer, String[]> data;
	public static void main(String[] args) throws InterruptedException, IOException {
		
		js = (JavascriptExecutor) driver;
		setBrowser("chrome", "https://www.moneycontrol.com/markets/indian-indices/");
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		// table
		WebElement Table = driver.findElement(By.xpath(
				"/html/body/section/div/div/div/div[4]/div/div/div[1]/div/div[2]/div[3]/div[4]/div/div/div[1]/table/tbody"));
		
		// Table rows
		List<WebElement> TableRow = Table.findElements(By.tagName("tr"));

		//for getting the data after every 10 minutes using a infinite loop with sleep method
		while(true) {
			
			for (WebElement tr : TableRow) {
				int i = 0;
				List<WebElement> TableData = tr.findElements(By.tagName("td"));
				for (WebElement td : TableData) {
					entities[i++] = td.getText();
				}
				finalData.add(new String[] {
						entities[0],
						entities[1],
						entities[2],
						entities[3],
						entities[4],
						entities[5],
						entities[6],
						entities[7]
				});

			}

			//writing the generated data to file -> data.csv . Later will update it to excel from csv
			CSVWriter writer = new CSVWriter(new FileWriter("data.csv"));
			writer.writeAll(finalData);
			writer.close();
			
			System.out.println("The stock price for various stocks are add to the Data.csv file successfully");
			
			// for making the program to start again after 10 mins 
			TimeUnit.MINUTES.sleep(10);

		}
		
	}

}
