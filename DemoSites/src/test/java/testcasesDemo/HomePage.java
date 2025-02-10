package testcasesDemo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomePage {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.getTitle();
		driver.navigate().to("https://www.amazon.com/ref=nav_logo");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Samsung Mobiles");
		driver.findElement(By.id("nav-search-submit-button")).click();

		List <WebElement> products = driver.findElements(By.xpath("//div[@role='listitem']"));
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("a h2 span")).getText().contains("Galaxy A35 5G A Series Cell")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".a-button-text")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Wait for the element to be clickable
		WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".nav-cart-icon")));

		// Use JavaScript Executor to click
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", cartIcon);

		// Wait for an element to be visible
		WebElement cartItem = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sc-badge-price-to-pay")));
		System.out.println(cartItem.getText());

		WebElement cartTotal = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("sc-subtotal-amount-buybox")));
		System.out.println(cartTotal.getText());

		String cartItemText = cartItem.getText().replaceAll("\\s+", "").toLowerCase();
		String cartTotalText = cartTotal.getText().replaceAll("\\s+", "").toLowerCase();

		if (cartItemText.contains(cartTotalText) || cartTotalText.contains(cartItemText)) {
		    System.out.println("Cart price matches the subtotal (ignoring spaces).");
		} else {
		    System.out.println("Mismatch: Cart item price = " + cartItem.getText() + ", Subtotal = " + cartTotal.getText());
		}

	}

}
