import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

public class GoogleTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void testsSetUp()  {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com/ncr");
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    }

    @Test(priority = 1)
    public void checkGoogle() throws IOException {
        driver.findElement(By.name("q")).sendKeys("yacht" + Keys.ENTER);
        driver.findElement(By.xpath("//a[text()='Images']")).click();
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f, new File("src/main/resources/screenshot02.png"));
        WebElement firstResult = wait.until(presenceOfElementLocated(cssSelector("h3")));
        String testInPage = firstResult.getAttribute("textContent");
        String text = "ODESSA II, Yacht - Данные судна и текущее положение - IMO 9645671 -  VesselFinder";
        assertTrue(testInPage.contains(text));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
