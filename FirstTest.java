package testCases;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTest {
private WebDriver driver;

@BeforeClass

public void setUp() {
WebDriverManager.chromedriver().setup();
System.out.println("2222");
//driver = new ChromeDriver();

System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");	
System.out.println("((((++");
ChromeOptions options=new ChromeOptions();
System.out.println("u9898u9");
options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
WebDriver driver= new ChromeDriver();

driver.manage().window().maximize();
driver.get("http://jupiter.cloud.planittesting.com");
// go to Contact page
driver.findElement(By.linkText("Contact")).click();

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
WebElement submitbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'btn-contact') and contains(text(),'Submit')]")));

submitbtn.click();
driver.findElement(By.xpath("//a[@class='btn-contact btn btn-primary']")).click();

//2) Verify the three error messages appear
assertTrue(driver.findElement(By.id("forename-err")).isDisplayed(), "Forename error missing");
assertTrue(driver.findElement(By.id("email-err")).isDisplayed(), "Email error missing");
assertTrue(driver.findElement(By.id("message-err")).isDisplayed(), "Message error missing");

//3) Fill mandatory fields
driver.findElement(By.id("forename")).sendKeys("Ayaan");
driver.findElement(By.id("email")).sendKeys("ayaan@example.com");
driver.findElement(By.id("message")).sendKeys("This is a test.");

//4) Submit again
driver.findElement(By.linkText("Submit")).click();

//5) Verify errors are gone
assertEquals(driver.findElements(By.id("forename-err")).size(), 0, "Forename error still present");
assertEquals(driver.findElements(By.id("email-err")).size(), 0, "Email error still present");
assertEquals(driver.findElements(By.id("message-err")).size(), 0, "Message error still present");

//(optional) verify success banner
assertTrue(driver.findElement(By.className("alert-success")).isDisplayed(),
"Success message not displayed");

}
@Test
public void testSubmitShowsAndClearsErrors() {
}

@AfterClass
public void tearDown() {
	System.out.println("9999");
if (driver != null) driver.quit();
}
}

