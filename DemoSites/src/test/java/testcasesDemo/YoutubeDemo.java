package testcasesDemo;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class YoutubeDemo {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		try {
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.navigate().to("https://www.youtube.com/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			// To click on Home page
			WebElement homeButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//yt-formatted-string[text()='Home']")));
			homeButton.click();
			System.out.println("Page Title: " + driver.getTitle());
			String homeURL = driver.getCurrentUrl();
			System.out.println("Home URL: " + homeURL);
			Assert.assertEquals(homeURL, "https://www.youtube.com/");

			// To click on Shorts
			WebElement shortsButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//yt-formatted-string[text()='Shorts']")));
			shortsButton.click();
			Thread.sleep(2000); // Temporary wait for navigation
			String shortsURL = driver.getCurrentUrl();
			System.out.println("Shorts URL: " + shortsURL);
			Assert.assertTrue(shortsURL.startsWith("https://www.youtube.com/shorts/"),
					"Shorts URL does not match expected pattern. Found: " + shortsURL);

			// to click on subscriptions
			WebElement subscriptionsButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//yt-formatted-string[text()='Subscriptions']")));
			subscriptionsButton.click();
			// wait
			wait.until(ExpectedConditions.urlToBe("https://www.youtube.com/feed/subscriptions"));
			String subscriptionsURL = driver.getCurrentUrl();
			System.out.println("Subscriptions URL: " + subscriptionsURL);
			Assert.assertEquals(subscriptionsURL, "https://www.youtube.com/feed/subscriptions");
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			driver.quit();
		}

	}

}
