package PracticeTests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day5_Miscellaneous_Questions {

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	public static JavascriptExecutor js = null;
	public static Actions act = null;
	public static Logger log = Logger.getLogger(Day5_Miscellaneous_Questions.class);
	
	@BeforeTest
	public void setUp() {
		
		driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		js = (JavascriptExecutor) driver;
		act = new Actions(driver);
		
	}
	
	@Test
	public void TC01_Uploading_A_File() throws InterruptedException {
		
		driver.get("https://the-internet.herokuapp.com/upload");
		
		// Using relative file path:		
		File file = new File("src/test/resources/TestFiles/JoelAndrewsJeyakumar_QA_Automation.pdf");
		driver.findElement(By.name("file")).sendKeys("..\\\\src\\test\\resources\\TestFiles\\JoelAndrewsJeyakumar_QA_Automation.pdf");
		
		Thread.sleep(Duration.ofSeconds(3));
		
		// Using absolute file path:
		driver.navigate().refresh();
		
		Thread.sleep(Duration.ofSeconds(3));
		
		driver.findElement(By.name("file")).sendKeys("C:\\Users\\V R Della\\Desktop\\Automation\\Selenium\\Practice-Selenium\\src\\test\\resources\\TestFiles\\JoelAndrewsJeyakumar_QA_Automation.pdf");
		
		Thread.sleep(Duration.ofSeconds(3));
	}
	
	@Test
	public void TC02_Handling_JavascriptExecuter() throws InterruptedException {
		
		driver.get("https://the-internet.herokuapp.com/");
		
		// Scrolling to the bottom of the page:		
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Thread.sleep(Duration.ofSeconds(3));
		
		// Scrolling to an web element of the page:
		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector("[href='/disappearing_elements']")));
		Thread.sleep(Duration.ofSeconds(3));
	}
	
	@Test
	public void TC03_Handling_Action_Class() throws InterruptedException {
		
		driver.get("https://the-internet.herokuapp.com/hovers");
		
		//	Handling Hover Over Elements:	
		for(int i = 1; i <= 3; i++) {
			act.moveToElement(driver.findElement(By.xpath("(//*[@class='figure'])[" + i + "]"))).perform();
			Thread.sleep(Duration.ofSeconds(2));
		}
		
		//	Right Click on Elements:
		act.moveToElement(driver.findElement(By.xpath("(//*[@class='figure'])[2]"))).contextClick().perform();
		
		// Drag and Drop Elements:
		driver.navigate().to("https://the-internet.herokuapp.com/drag_and_drop");
		Thread.sleep(Duration.ofSeconds(2));
		act.dragAndDrop(driver.findElement(By.id("column-a")), driver.findElement(By.id("column-b"))).perform();
		Thread.sleep(Duration.ofSeconds(2));
		
		// Double Click:
		driver.navigate().to("https://www.rapidtables.com/tools/click-counter.html");
		driver.findElement(By.id("resetbtn")).click();
		Thread.sleep(Duration.ofSeconds(2));
		act.doubleClick(driver.findElement(By.id("addbtn"))).perform();
		Thread.sleep(Duration.ofSeconds(2));
	}

	@Test
	public void TC_04_Handling_Screenshots() throws IOException {
		
		driver.get("https://the-internet.herokuapp.com/");
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		//	Screenshot an whole page:
		File file = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("src/test/resources/Screenshots/12_05_2025_1550.png"));
		
		//	Screenshot an web element:
		File file2 = driver.findElement(By.cssSelector("[href='/disappearing_elements']")).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file2, new File("src/test/resources/Screenshots/12_05_2025_1601.png"));
		
	}
	
	@Test
	public void TC_05_Handling_Cookies() {
		
		driver.get("https://the-internet.herokuapp.com/");
		
		Set<Cookie> cookies = driver.manage().getCookies();
		cookies.add(new Cookie("sessionId", "1234"));
		log.info("Successfully added a new cookie");
		
		for(Cookie cookie : cookies) {
			System.out.print("Cookie Name: " + cookie.getName() + "\nCookie Value: " + cookie.getValue() + "\nDomain: " + cookie.getDomain() + "\n\n");
		}
		log.info("Successfully printed all the cookies");
	}	
}
