package testNGAssignmentPrograms;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class AssgTwo {
	WebDriver driver;


	@Test
	public void verifySuccessfulLoginOfUser() {
		WebElement userName = driver.findElement(By.xpath("//input[@name='username']"));
		userName.sendKeys("admin");

		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("admin");

		WebElement signIn = driver.findElement(By.tagName("button"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		signIn = wait.until(ExpectedConditions.elementToBeClickable(signIn));
		signIn.click();

		SoftAssert softAssert = new SoftAssert();
		String expectedResult = "Admin";
		WebElement loggedInUser = driver.findElement(By.xpath("(//*[contains(@class,'nav-link')])[2]"));
		String actualResult = 	loggedInUser.getText();
		softAssert.assertEquals(actualResult, expectedResult, "The login is unsuccessful");
		softAssert.assertAll();
	}


	@Test(dataProvider = "dp")
	public void verifyUnsuccessfulLoginOfuser(String n, String s) {
		WebElement userName = driver.findElement(By.xpath("//input[@name='username']"));
		userName.sendKeys(n);

		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys(s);

		WebElement signIn = driver.findElement(By.tagName("button"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		signIn = wait.until(ExpectedConditions.elementToBeClickable(signIn));
		signIn.click();

		WebElement alert = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
		Boolean actualResult = alert.isDisplayed();
		Assert.assertTrue(actualResult,"User has successfully logged in");
	}


	@BeforeMethod
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://groceryapp.uniqassosiates.com/admin/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}


	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}


	@DataProvider
	public Object[][] dp() {
		return new Object[][] {
			new Object[] { "admin", "nimda" },
			new Object[] { "nimda", "admin" },
			new Object[] {"nimda","nimda"},
		};
	}
}
