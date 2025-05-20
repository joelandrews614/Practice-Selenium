package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public LoginPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(name = "email") WebElement username_Inp;
	
	@FindBy(id = "password") WebElement password_Inp;
	
	@FindBy(css = "[value='Login']") WebElement login_Btn;
	
	public void login(String username, String password) {
		
		username_Inp.sendKeys(username);
		password_Inp.sendKeys(password);
		login_Btn.click();
		
	}
}
