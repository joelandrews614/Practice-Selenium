package PracticeTests;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day3_Handling_WebElements {

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
	public void TC01_Handling_Dropdowns() throws InterruptedException {
		
		driver.get("https://the-internet.herokuapp.com/dropdown");
		
		Select dropdown = new Select(driver.findElement(By.cssSelector("[id='dropdown']")));
		
		dropdown.selectByVisibleText("Option 2");
		
		dropdown.selectByValue("1");
		
		dropdown.selectByIndex(2);
		
		Thread.sleep(Duration.ofSeconds(2));
		
	}
	
	@Test
	public void TC02_Handling_Checkboxes() throws InterruptedException {
		
		driver.get("https://the-internet.herokuapp.com/checkboxes");
		
		WebElement checkbox1 = driver.findElement(By.xpath("(//*[@type='checkbox'])[1]"));
		WebElement checkbox2 = driver.findElement(By.xpath("(//*[@type='checkbox'])[2]"));
		
		if(!checkbox1.isSelected() && checkbox2.isSelected()) {
			checkbox1.click();
			checkbox2.click();
			System.out.println("Checkbox was not selected or now selected, so selecting and unselecting it now");
		}	
	}
	
	@Test
	public void TC03_Handling_Javascript_Alerts() {
		
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
		
		//Normal Alert
		driver.findElement(By.cssSelector("[onclick='jsAlert()']")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("[id='result']")).getText(), "You successfully clicked an alert");
		
		//Confirmation Alert
		driver.findElement(By.cssSelector("[onclick='jsConfirm()']")).click();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("[id='result']")).getText(), "You clicked: Ok");
		driver.findElement(By.cssSelector("[onclick='jsConfirm()']")).click();
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.cssSelector("[id='result']")).getText(), "You clicked: Cancel");
		
		//Prompt Alert
		driver.findElement(By.cssSelector("[onclick='jsPrompt()']")).click();
		alert.sendKeys("Joel Andrews Jeyakumar");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("[id='result']")).getText(), "You entered: Joel Andrews Jeyakumar");
		
	}
	
	@Test
	public void TC04_Handling_Authentication() {
		
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		
	}
	
	@Test
	public void TC05_Handling_Mutiple_Windows_And_Tabs() {
		
		driver.get("https://the-internet.herokuapp.com/windows");
		
		String parentHandle = driver.getWindowHandle();
		
		driver.findElement(By.cssSelector("div[class='example'] a")).click();
		
		Set<String> handles = driver.getWindowHandles();
		
		for(String handle: handles) {
			if(!handle.equalsIgnoreCase(parentHandle)) {
				driver.switchTo().window(handle);
				System.out.println(driver.getTitle());
				Assert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "New Window");
			}
		}
		
		driver.switchTo().window(parentHandle);
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(), "Opening a new window");
		
	}
	
	@Test
	public void TC06_Handling_Iframes() {
		
		driver.get("https://the-internet.herokuapp.com/nested_frames");

		driver.switchTo().frame("frame-top");
		driver.switchTo().frame("frame-left");
		
		Assert.assertEquals(driver.findElement(By.xpath("(//body)[1]")).getText(), "LEFT");
		
		driver.switchTo().parentFrame();
		driver.switchTo().parentFrame();
		driver.switchTo().frame("frame-bottom");
		Assert.assertEquals(driver.findElement(By.xpath("(//body)[1]")).getText(), "BOTTOM");
		
	}
	
	@Test
	public void TC07_Handling_Non_Select_Elements() throws InterruptedException {
			
		driver.get("https://demoqa.com/select-menu");
			
		driver.findElement(By.cssSelector("[id='selectOne']")).click();
			
		driver.findElement(By.xpath("//span[contains(text(),'Accordian')]")).click();
				
		Thread.sleep(Duration.ofSeconds(3));
	
	}
	
}