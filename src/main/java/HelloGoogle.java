import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class HelloGoogle {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.manage().window().maximize();
            driver.get("https://google.com/ncr");
            driver.findElement(By.name("q")).sendKeys("yacht" + Keys.ENTER);
            driver.findElement(By.xpath("//a[text()='Images']")).click();
            File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(f, new File("src/main/resources/screenshot01.png"));
            WebElement firstResult = wait.until(presenceOfElementLocated(cssSelector("h3")));
            System.out.println(firstResult.getAttribute("textContent"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
