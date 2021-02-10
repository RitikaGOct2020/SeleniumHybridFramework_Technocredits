package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.PredefinedActions;

public class AuthenticationPage extends PredefinedActions{
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	public void enterEmailAddress(String emailId) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")))
				.sendKeys(emailId);
	}

	public CreateAccountPage clickOnCreateAccount() {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitCreate"))).click();
		return new CreateAccountPage();
	}
	
	public boolean isAuthenticationHeaderVisible() {
		 WebElement authenticationHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.page-heading")));
		 return authenticationHeader.isDisplayed();
	}
}
