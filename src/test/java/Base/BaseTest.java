package Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver = null;
	public static LoginPage loginPage = null;
	
	@BeforeMethod
	public void setUp() {
	
		driver = WebDriverManager.chromedriver().create();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		
		loginPage = new LoginPage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		
		if(driver != null)
			driver.quit();
	}
	
}
