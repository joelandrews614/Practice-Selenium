package PracticeTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day2_Locators_Mastery {

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	
	@BeforeTest
	public void setUp() {
		
		driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}
	
	@Test
	public void TC01_Practice_With_All_Locators() {
		
		driver.get("https://www.saucedemo.com/");
		
		driver.findElement(By.id("user-name")).sendKeys("Using ID");
		driver.findElement(By.name("password")).sendKeys("Using Name");
		
		driver.findElement(By.className("submit-button")).click();
	
		List<WebElement> allInputs = driver.findElements(By.tagName("input"));
		
		for(WebElement input : allInputs) {
			System.out.println(input.getAttribute("class"));
		}	
	}
	
	
	@Test
	public void TC02_Practice_CSS_Selectors() {
		
		driver.get("https://www.saucedemo.com/");
		
		driver.findElement(By.cssSelector("[id='user-name']")).sendKeys("standard_user");
		
		driver.findElement(By.cssSelector("[id='password']")).sendKeys("secret_sauc");
		
		driver.findElement(By.cssSelector("[type='submit']")).click();
	
		if(driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/inventory.html")) {
			System.out.println("Login Successful!!!");
		}else {
			System.out.println("Login Failed!!!");
		}
		
	}
	
	@Test
	public void TC03_Practice_Xpaths() {
		
		driver.get("https://www.saucedemo.com/");
		
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("Found by Xpath");
		
	}
	
	@Test
	public void TC04_Waits() {
		
		driver.get("https://www.saucedemo.com/");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='submit']")));
		
		driver.findElement(By.cssSelector("[type='submit']")).click();
		
	}
	
	@Test
	public void TC05_Dynamic_Elements() throws InterruptedException {
		
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		
		driver.findElement(By.cssSelector("#start button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='finish']")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#finish h4")).getText(), "Hello World!");
		
	}
	
	@Test
	public void TC_Assessment() {
		
		driver.get("https://www.saucedemo.com/");
		
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_description")));
		
		List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item_description"));
		
		System.out.println(inventoryItems.size());
		
	}
	
	
}
