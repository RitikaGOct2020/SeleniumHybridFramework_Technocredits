package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.PredefinedActions;

public class AuthenticationPage extends PredefinedActions{
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	public void enterEmailAddressInCreateAccount(String emailId) {
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
	
	public MyProfilePage doLogin(String emailID, String password) {
		enterEmailIdInLogin(emailID);
		enterPasswordInLogin(password);
		clickOnSignInButton();
		return new MyProfilePage();
	}

	private void enterEmailIdInLogin(String emailID) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#email"))).sendKeys(emailID);
		System.out.println("Email id entered for login");
	}
	
	private void enterPasswordInLogin(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id = 'passwd']"))).sendKeys(password);
		System.out.println("Password entered for login");
	}

	private void clickOnSignInButton() {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitLogin"))).click();
		System.out.println("Signin button clicked");
	}
	
	public String verifyErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-danger li"))).getText();
	}

}
