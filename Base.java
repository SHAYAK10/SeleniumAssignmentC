package com.base;

import java.io.Writer;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.opencsv.CSVWriter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	public static WebElement Heading, Answer, Views, Votes;
	public static CSVWriter writernew;
	public static JavascriptExecutor js;
	public Writer outputFile;

	// method for initialising the browser with the url
	public static void setBrowser(String browser, String url) {
		if (browser.toLowerCase().equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browser.toLowerCase().equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browser.toLowerCase().equals("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();

		} else if (browser.toLowerCase().equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else {
			System.out.println("Browser not found.\n !!Please configure the Browser name.");
		}

		driver.get(url);
		driver.manage().window().maximize();
	}

}
