package MockInterviewTests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;

public class Day2_Interview extends BaseTest{

	@Test
	public void TC_Login_Using_POM() throws InterruptedException {
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/login.php");
		
		loginPage.login("Joel Andrews Jeyakumar", "Rukshana123");
		
		Thread.sleep(Duration.ofSeconds(4));
	}
	
	@Test
	public void TC_Dynamic_WebElements() {
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/login.php");
		
		driver.findElement(By.xpath("//div[starts-with(@id, 'product')]")).sendKeys("");
		
	}
	
	@Test
	public void TC_Window_Handling() {
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");
		
		String parentHandle = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//*[text() = 'New Window']")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		
		for(String handle : windowHandles) {
			
			if(!handle.equalsIgnoreCase(parentHandle)) {
				driver.switchTo().window(handle);
				Assert.assertEquals(driver.findElement(By.xpath("(//div/h1)[2]")).getText(), "New Window");
				driver.close();
			}
			
		}
		
		driver.switchTo().window(parentHandle);
		Assert.assertEquals(driver.findElement(By.xpath("(//div/h1)[2]")).getText(), "Browser Windows");
	}
	
	@Test
	public void TC_DropDown() throws InterruptedException {
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/select-menu.php");
		
		Select dropdown = new Select(driver.findElement(By.id("inputGroupSelect03")));
		
		dropdown.selectByVisibleText("Other");
		
		List<WebElement> allOptions = dropdown.getOptions();
		
		Thread.sleep(Duration.ofSeconds(4));
		
		for(WebElement ele : allOptions) {
			System.out.println(ele.getText());
		}
		
	}

	@Test
	public void TC_Broken_Images() throws MalformedURLException, IOException {
		
		driver.get("https://practice.expandtesting.com/broken-images");
		
		List<WebElement> allImages = driver.findElements(By.tagName("img"));
		
		for(WebElement img : allImages) {
			
			HttpURLConnection con = (HttpURLConnection) new URL(img.getAttribute("src")).openConnection();
			con.setRequestMethod("GET");
			con.connect();
			
			if(con.getResponseCode() >= 400) {
				System.out.println(img.getAttribute("src") + " is Dead, Status Code: " + con.getResponseCode());
			}else {
				System.out.println(img.getAttribute("src") + " is Alive , Status Code: " + con.getResponseCode());
			}
			
		}
		
	}

	@Test
	public void TC_Alerts() throws InterruptedException {
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/alerts.php");
		
		driver.findElement(By.xpath("(//div/button)[1]")).click();
		
		Alert alert = driver.switchTo().alert();
		
		Thread.sleep(Duration.ofSeconds(3));
		alert.accept();
		Thread.sleep(Duration.ofSeconds(3));	
	
	}
}

// Mock Interview 2: 
// 1. Login Test Using POM - OK
// 2. Capture Screenshot when test fails - NO
// 3. Handle Dynamic Xpath WebElements - OK
// 4. Switch Between Windows - OK
// 5. Dropdown Selections - OK
// 6. Find Broken Images on a Web Page - NO
// 7. Java Program to Count Character Occurrence - NO
// 8. Handling Javascript Alerts - OK
// 9. Data-Driven Test Using Excel or Properties File - NO
// 10. Implicit Wait vs Explicit Wait - OK