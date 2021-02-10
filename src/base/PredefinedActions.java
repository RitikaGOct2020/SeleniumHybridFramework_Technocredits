package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PredefinedActions {

	protected static WebDriver driver;
	
	public static void start() {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		System.out.println("STEP: Launch browser");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("STEP: Navigate to Application");
		driver.get("http://automationpractice.com/index.php");
	}
	
	public static void close() {
		System.out.println("STEP - Close Browser");
		driver.close();
	}
	
}
