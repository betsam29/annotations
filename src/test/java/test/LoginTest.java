package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import util.BrowserFactory;

public class LoginTest {
WebDriver driver;
	
	
	@Test
	public void validUserShouldBeAbleToLogin() {
		
		 driver = BrowserFactory.init();
		
		driver.findElement(By.xpath("//*[@id=\"user_name\"]"));
	}
}
