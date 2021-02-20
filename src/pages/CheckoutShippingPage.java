package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.PredefinedActions;

public class CheckoutShippingPage extends PredefinedActions {

	WebDriverWait wait = new WebDriverWait(driver, 30);
	private static CheckoutShippingPage checkoutShippingPage;
	
	public static CheckoutShippingPage getInstance() {
		if(checkoutShippingPage==null) {
			checkoutShippingPage = new CheckoutShippingPage();
		}
		return checkoutShippingPage;
	}

	public String getShippingPrice() {
		String shippingPrice = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.delivery_option_price")))
				.getText().trim().substring(1);
		return shippingPrice;
	}

	public void selectCheckbox() {
		System.out.println("STEP: Select Checkbox");
		driver.findElement(By.cssSelector(".checkbox input[type='checkbox']")).click();
	}
	
	public CheckoutPaymentPage clickOnProceedToCheckout() {
		driver.findElement(By.cssSelector(".cart_navigation.clearfix span")).click();
		return CheckoutPaymentPage.getInstance();
	}
	
	public String verifyErrorMessage() {
		String errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.fancybox-error"))).getText();
		closeError();
		return errorMessage;
	}
	
	private void closeError() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Close']"))).click();
	}
}
