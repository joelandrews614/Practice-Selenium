package PracticeTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day1_Selenium_Architecture {

	public static WebDriver driver = null;
	public static Wait<WebDriver> wait = null;
	
	@BeforeTest
	public void setUp() {
		driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	}
	
	@Test
	public void TC01_Basic_Browser_Launch() {
		driver.get("https://example.com");
		System.out.println(driver.getTitle());	
	}
	
	@Test
	public void TC02_Locate_and_Print_Element_Text() {
		driver.get("https://www.selenium.dev/");
		System.out.println(driver.findElement(By.linkText("Downloads")).getText());		
	}

	@Test
	public void TC03_Dynamic_Wait() {
		driver.get("https://www.selenium.dev/downloads/");
		
		List<WebElement> elements = driver.findElements(By.cssSelector("[class='col-sm-4 p-3']"));
		
		for(WebElement element : elements) {
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		System.out.println("Count: " + elements.size());
	}
	
	@Test
	public void TC04_Print_All_Links() {
		
		driver.get("https://www.selenium.dev/");
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		for(WebElement link : links) {
			if(link.getText() != " ") {
				System.out.println("Text: " + link.getText() + ", Href: " + link.getAttribute("href"));
			}
		}
		
		System.out.println("Count: " + links.size());
		
	}
	
	@AfterTest
	public void tearDown() {
		if(driver != null)
			driver.close();
	}

}
