package testNGAssignmentPrograms;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class AssgOne {
	WebDriver driver;


	@Test(priority = 1)
	public void verifyTheLoginCredentialsAreValidOrNot() {
		WebElement userName = driver.findElement(By.xpath("//input[@name='username']")); 	//assigned the location for username input box
		userName.sendKeys("Admin"); //inserted username

		WebElement password = driver.findElement(By.xpath("//input[@name='password']")); 	//assigned the location for password input box
		password.sendKeys("admin123"); //inserted password

		WebElement loginButton = driver.findElement(By.tagName("button")); 	//assigned the location for login button
		loginButton.click(); 	//clicked on login button

		WebElement loggedInUser = driver.findElement(By.className("oxd-userdropdown-name")); 	//assigned the location of logged in users name
		Boolean actualResult = loggedInUser.isDisplayed();
		Assert.assertTrue(actualResult, "User not logged in!"); 	//test case passes since actual result is true
		//Assert.assertFalse(actualResult, "User has logged in!"); //test case fails since actual result is true
	}


	@Test(priority = 2)
	public void verifyTheLoginCredentialsAreInvalidOrNot() {
		WebElement userName = driver.findElement(By.xpath("//input[@name='username']")); 	//assigned the location for username input box
		userName.sendKeys("Admin"); //inserted username

		WebElement password = driver.findElement(By.xpath("//input[@name='password']")); 	//assigned the location for password input box
		password.sendKeys("Admin"); //inserted password

		WebElement loginButton = driver.findElement(By.tagName("button")); 	//assigned the location for login button
		loginButton.click(); 	//clicked on login button

		WebElement invalidCredentialAlert = driver.findElement(By.xpath("//p[contains(@class,'oxd-alert-content-text')]"));
		String expectedResult = "Invalid credentials";
		String actualResult = invalidCredentialAlert.getText();
		Assert.assertEquals(actualResult, expectedResult,"User is logged in!"); 	//verifying the expected result is equal to actual result, if not equal the message will be displayed
	}


	@Test(priority = 3)
	public void verifyTheDashboardPageIsDisplayedAfterLoginOrNot() {
		WebElement userName = driver.findElement(By.xpath("//input[@name='username']")); 	//assigned the location for username input box
		userName.sendKeys("Admin"); //inserted username

		WebElement password = driver.findElement(By.xpath("//input[@name='password']")); 	//assigned the location for password input box
		password.sendKeys("admin123"); //inserted password

		WebElement loginButton = driver.findElement(By.tagName("button")); 	//assigned the location for login button
		loginButton.click(); 	//clicked on login button

		WebElement dashboard = driver.findElement(By.xpath("//h6[contains(@class,'-h6 oxd-topbar-header-')]")); //assigned the location of dashboard
		String expectedResult = "Dashboard"; 	//giving expected result directly
		String actualResult = dashboard.getText(); 	//giving actual result as the location of element
		Assert.assertEquals(actualResult, expectedResult,"User not logged in!"); 	//verifying the expected result is equal to actual result, if not equal the message will be displayed
	}


	@Test(priority = 4)
	public void verifyAdminIsDisplayedInSystemUsersOrNot() throws InterruptedException {
		WebElement userName = driver.findElement(By.xpath("//input[@name='username']")); 	//assigned the location for username input box
		userName.sendKeys("Admin"); //inserted username

		WebElement password = driver.findElement(By.xpath("//input[@name='password']")); 	//assigned the location for password input box
		password.sendKeys("admin123"); //inserted password

		WebElement loginButton = driver.findElement(By.tagName("button")); 	//assigned the location for login button
		loginButton.click(); 	//clicked on login button

		WebElement admin = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[1]"));  //assigned the location of admin option
		admin.click(); //clicked on admin

		WebElement usernNameInput = driver.findElement(By.xpath("(//input[contains(@class,'input oxd-input')])[2]"));  //assigned the location of username input box
		usernNameInput.sendKeys("Admin"); //inserted the value

		//		//WebElement userRole = driver.findElement(By.xpath("(//div[contains(text(),'Select')])[1]"));  //assigned the location for user role dropdown
		//		WebElement userRole = driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]"));
		//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));	//WebDriverWait Instantiated; explicit wait for 10 seconds
		//		userRole = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(text(),'Select')])[1]")));
		//		userRole = wait.until(ExpectedConditions.elementToBeClickable(userRole));
		//		userRole.click(); //clicked on the dropdown
		//		Select select = new Select(userRole); //instantiated select class
		//		select.selectByVisisbleText("Admin"); //selected the required value by visible text method

		WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));  //assigned the location for search button
		searchButton.click(); //clicked on search button

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
