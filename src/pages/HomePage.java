package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class HomePage extends PredefinedActions {

	private static HomePage homePage;
	
	public static HomePage getInstance() {
		if(homePage == null) {
			homePage = new HomePage();
		}
		return homePage;
	}
	public AuthenticationPage clickOnSignIn() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".header_user_info>a"))).click();
		System.out.println("Clicked on sign in");
		return AuthenticationPage.getInstance();
	}
}
