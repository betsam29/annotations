package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
	WebDriver driver; 

	@FindBy(how = How.XPATH, using = "//*[@id=\"user_name\"]") WebElement userNameElement;
	}


