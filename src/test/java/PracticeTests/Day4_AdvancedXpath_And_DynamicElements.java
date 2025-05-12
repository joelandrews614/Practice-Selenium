package PracticeTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day4_AdvancedXpath_And_DynamicElements {

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
	public void TC01_Xpath_Strategies() {
		
		driver.get("https://the-internet.herokuapp.com/");
		
		//Parent of an Element:		
		System.out.println(driver.findElement(By.xpath("//a[@href='/abtest']/parent::li")).getTagName());
		
		//Sibling of an Element:
		driver.findElement(By.xpath("//h1[@class='heading']/following-sibling::h2"));
		
		//Find the nth of a Dynamic List:
		driver.findElement(By.xpath("(//li)[7]"));
		
		//Dynamic X-path for Web tables:
		driver.findElement(By.xpath("(//tr[td[text()='Conway']]//a[text()='delete'])[1]"));
		
	}
	
}
