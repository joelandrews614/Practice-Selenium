package TutorialsPoint;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeTests {

public static WebDriver driver = null;
public int waitTime = 2000;
	
	@BeforeTest
	public void setUp() {
		
		driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@Test
	public void Exercise1_Take_Screenshot(){
		driver.get("https://www.google.com");
		takeScreenshot(driver, "Screenshots/screenshot1.png");
	}
	
	@Test
	public void Exercise2_File_Uploads() {
		driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
		driver.findElement(By.xpath("//input[@id='picture']")).sendKeys("/Practice-Selenium/testFiles/JoelAndrewsJeyakumar_QA_Automation.pdf");
	}
	
	@Test
	public void Exercise3_Frame_Handling() throws InterruptedException {
		
		driver.get("https://www.hyrtutorials.com/p/frames-practice.html");
		driver.switchTo().frame("frm3");
		driver.switchTo().frame("frm2");
		
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Joel Andrews Jeyakumar");
		
		driver.switchTo().parentFrame();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Joel Andrews Jeyakumar");
	}
	
	//Helper Methods:
	public void takeScreenshot(WebDriver driver, String filename) {
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(filename));
		}catch(Exception e) {
			System.out.println("Exeception: " + e);
		}
	}
	
}
