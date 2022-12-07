package testNGAssignmentPrograms;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class AssgOneDemo {
	WebDriver driver;


	@Test(priority = 1)
	public void verifySuccessfullLogin() {
		WebElement username=driver.findElement(By.xpath("//input[@name='username']"));
		username.sendKeys("Admin");
		WebElement password=driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("admin123");
		WebElement loginButton=driver.findElement(By.xpath("//button[text()=' Login ']"));
		loginButton.click();
		
		WebElement loggedInUser = driver.findElement(By.className("oxd-userdropdown-name")); 	//assigned the location of logged in users name
		Boolean actualResult = loggedInUser.isDisplayed();
		Assert.assertTrue(actualResult, "User not logged in!");
	}

	
	@Test(priority = 4)
	public void verifyUnSuccessfullLogin() {
		WebElement username=driver.findElement(By.xpath("//input[@name='username']"));
		username.sendKeys("Admin");
		WebElement password=driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("admin");
		WebElement loginButton=driver.findElement(By.xpath("//button[text()=' Login ']"));
		loginButton.click();

		WebElement invalidMsg=driver.findElement(By.xpath("//p[text()='Invalid credentials']"));
		String expectedResult="Invalid credentials";
		String actualResult=invalidMsg.getText();
		Assert.assertEquals(actualResult, expectedResult,"Invalid Message is different");
	}

	
	@Test(priority = 2)
	public void verifyDashboard() {
		WebElement username=driver.findElement(By.xpath("//input[@name='username']"));
		username.sendKeys("Admin");
		WebElement password=driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("admin123");
		WebElement loginButton=driver.findElement(By.xpath("//button[text()=' Login ']"));
		loginButton.click();

		WebElement dashboard=driver.findElement(By.xpath("//h6[text()='Dashboard']"));
		String expectedResult="Dashboard";
		String actualResult=dashboard.getText();
		Assert.assertEquals(actualResult, expectedResult,"dashboard is not visible");
	}


	@Test(priority = 3)
	public void verifySearchFunction() throws InterruptedException {
		WebElement username=driver.findElement(By.xpath("//input[@name='username']"));
		username.sendKeys("Admin");
		WebElement password=driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("admin123");
		WebElement loginButton=driver.findElement(By.xpath("//button[text()=' Login ']"));
		loginButton.click();

		WebElement adminModule=driver.findElement(By.xpath("//*[text()='Admin']"));
		adminModule.click();

		WebElement systemUsername=driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]"));
		systemUsername.sendKeys("Admin");

		WebElement userRole=driver.findElement(By.xpath("(//div[@class='oxd-select-text--after'])[1]"));
		userRole.click();
		Thread.sleep(2000);
		Actions keyDown = new Actions(driver);
		keyDown.sendKeys("Admin").build().perform();
		keyDown.sendKeys(Keys.ARROW_DOWN).sendKeys(userRole,Keys.ENTER).build().perform();

		WebElement searchButton=driver.findElement(By.xpath("//button[@type='submit']"));
		searchButton.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement userNameHeader = driver.findElement(By.xpath("(//div[@role='columnheader'])[2]"));
		js.executeScript("arguments[0].scrollIntoView();",userNameHeader);

		WebElement userNameDisplay = driver.findElement(By.xpath("(//div[text()='Admin'])[2]"));
		String expectedResult = "Admin";
		String actualResult = userNameDisplay.getText();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualResult, expectedResult,"Admin is not present");
		softAssert.assertAll();
	}


	@BeforeMethod
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); 	//launching the url
		driver.manage().window().maximize();	//maximized the window
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	//implicit wait applied
	}

	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
