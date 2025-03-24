package TutorialsPoint;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {

	public static WebDriver driver = null;
	
	@BeforeTest
	public void setUp() {
		
		driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@Test
	public void Exercise1_Take_Screenshot(){
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
		takeScreenshot(driver, "screenshot.jpg");
		
	}
	
	//Helper/Utility Methods:	
	public static void takeScreenshot(WebDriver driver, String filename) {
		
		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File(filename));
			System.out.println("File is created!!!");
		}catch(Exception e) {
			System.out.println("Exception: " + e);
		}
		
	}
	
}
