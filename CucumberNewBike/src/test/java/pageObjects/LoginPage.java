package pageObjects;

import java.util.Set;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	
	//Locators
	
	@FindBy(xpath="//div[@id='forum_login_wrap_lg']")
	private WebElement loginbtn;
	
	@FindBy(xpath="//span[normalize-space()='Google']")
	private WebElement clickOnGoogle;
	
	@FindBy(xpath="//input[@name='identifier']")
	private WebElement emailBox;
	
	@FindBy(xpath = "//span[text()='Next']")
	private WebElement nextButton;
	
	@FindBy(xpath = "//input[@id='identifierId']//ancestor::div[3]//following-sibling::div//span/parent::div")
	private WebElement errorMessage;
	
	
	//Actions
	
	public void loginPage() {
		borderElement(loginbtn);
		loginbtn.click();
	}
	
	public void clickOnGoogle() {
		borderElement(clickOnGoogle);
		clickOnGoogle.click();
	}
	
	private Set<String> windowHandles;
	
	public void navigationToLoginPage() {
		windowHandles=driver.getWindowHandles();
		
		for(String window:windowHandles) {
			driver.switchTo().window(window);
			String tittle=driver.getTitle();
			if(tittle.contains("Google")) {
				break;
			}
		}
	}
	
	public String emailVerification(String email) throws InterruptedException {
		Thread.sleep(5000);
		emailBox.sendKeys(email);
		Thread.sleep(2000);
		nextButton.click();
		String msg=errorMessage.getText();
		return msg;
	}

}
