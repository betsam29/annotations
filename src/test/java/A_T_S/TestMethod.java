package A_T_S;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestMethod {

	WebDriver driver;

	String enviroment;
	String browser;

	String userName;
	String password;

	// Element List
	By USERNAME_FIELD = By.xpath("//*[@id=\"user_name\"]");
	By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]");
	By LOGINSUBMIT_FIELDT = By.xpath("//*[@id=\"login_submit\"]");
	By DASHBOARD_HEADER_FIELDT = By.xpath("/html/body/div[1]/header/nav/div[2]/ul[1]/li[2]/a");

	// Test Data or Mock Data

	String dashboradValidationText = "Codefios QA";
	String alertValidationText = "Please enter your user name";
	String alertValidationText2 = "Please enter your password";

	@BeforeClass
	public void readConfig() {
		// bufferedReader //InputStream //FileReader //Scanner
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			enviroment = prop.getProperty("url");
			userName = prop.getProperty("userName");
			password = prop.getProperty("password");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			System.out.println("Please use a valid browser");
		}

		driver.manage().deleteAllCookies();
		driver.get(enviroment);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority=2)
	public void LoginTest() {
		driver.findElement(USERNAME_FIELD).sendKeys(userName);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(LOGINSUBMIT_FIELDT).click();
	//	Assert.assertEquals(dashboradValidationText, driver.findElement(DASHBOARD_HEADER_FIELDT).getText());

	}
	@Test(priority=1)
	public void testAlertLoginPage() {
		driver.findElement(LOGINSUBMIT_FIELDT).click();
		String actualAlertUserText = driver.switchTo().alert().getText();
		Assert.assertEquals(alertValidationText , actualAlertUserText);
		driver.switchTo().alert().accept();
		driver.findElement(USERNAME_FIELD).sendKeys(userName);
		driver.findElement(LOGINSUBMIT_FIELDT).click();
		String actualAlertUserText2 = driver.switchTo().alert().getText();
		Assert.assertEquals(alertValidationText2 , actualAlertUserText2);
	}

	//@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();

	}
}
