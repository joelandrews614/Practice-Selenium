package MockInterviewTests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;

public class Day1_Interview extends BaseTest{

	@Test
	public void TC_01_Handling_Windows() {
		
		driver.get("https://www.google.com");
	
	}
	
	@Test
	public void TC_02_DynamicWebelements() {
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");

		driver.findElement(By.xpath("//*[starts-with(@id, 'name')]")).sendKeys("Joel Andrews");
		
	}
	
	@Test
	public void TC_03_SwitchingIframes() {
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/frames.php");
		
		driver.switchTo().frame(1);
		
		WebElement ele = driver.findElement(By.xpath("(//div/h1)[2]"));
		Assert.assertEquals(ele.getText(), "New Tab");
		
		driver.switchTo().defaultContent();
		Assert.assertEquals(driver.findElement(By.xpath("(//div/h1)[2]")).getText(), "Frames");
	}
	
	@Test
	public void TC_04_IframeOrNot() {
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
		
		WebElement ele = driver.findElement(By.xpath("//div/h1"));
		
		if(ele.getTagName().equalsIgnoreCase("h1")) {
			System.out.println("It is a h1");
		}else {
			System.out.println("It is not a h1");
		}
		
	}
	
	@Test
	public void TC_05_Actions() throws InterruptedException {
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
		
		WebElement ele = driver.findElement(By.id("name"));
		
		Actions act = new Actions(driver);		
		
		act.keyDown(Keys.LEFT_SHIFT).sendKeys(ele, "joel andrews").perform();
		
		
		Thread.sleep(Duration.ofSeconds(3));
	}
	
	@Test
	public void TC_06_GetAllTheLinks() throws MalformedURLException, IOException {
		
		driver.get("https://www.tutorialspoint.com/selenium/practice/frames.php");
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		for(WebElement link : links) {
			
			System.out.println("href: " + link.getAttribute("href"));
			
			HttpURLConnection con = (HttpURLConnection) new URL(link.getAttribute("href")).openConnection();
			con.setRequestMethod("HEAD");
			con.connect();
			
			int responseCode = con.getResponseCode();
			if(responseCode >= 400) {
				System.out.println(link.getAttribute("href") + " is Dead");
			}else if(responseCode >= 200 && responseCode < 300){
				System.out.println(link.getAttribute("href") + " is Alive");
			}
			
		}
		
		System.out.println(links.size());
		
	}
	
}
