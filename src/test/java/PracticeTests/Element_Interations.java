package PracticeTests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Element_Interations {

	public static WebDriver driver = null;
	
	@BeforeTest
	public void setUp() {
		
		driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.navigate().to("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
		
	}
	
	@Test
	public void TC01_Handling_Textboxes() throws InterruptedException {
		
		driver.findElement(By.id("name")).sendKeys("Joel Andrews Jeyakumar");
		
		driver.findElement(By.cssSelector("textarea[id='picture']")).sendKeys("No. 11, First Floor, Mahalakshmi Complex, North Street, Karumandapam, Trichy - 01");
	
		Thread.sleep(Duration.ofSeconds(3));
		
	}
	
	@Test
	public void TC02_Handling_Buttons() throws InterruptedException {
			
		WebElement ele = driver.findElement(By.xpath("//input[@type='submit']"));
		
		ele.click();
		
		Thread.sleep(Duration.ofSeconds(3));
		
	}
	
	@Test
	public void TC03_Handling_Links() throws InterruptedException {
				
		Assert.assertEquals(driver.findElement(By.partialLinkText("Selenium Tutorial")).getAttribute("href"), "https://www.tutorialspoint.com/selenium/index.htm");
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		for(WebElement link: links) {
			System.out.println(link.getAttribute("href"));
		}
		System.out.println(links.size());
		
	}
	
}
