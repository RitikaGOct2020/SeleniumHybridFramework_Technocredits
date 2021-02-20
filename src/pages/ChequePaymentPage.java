package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class ChequePaymentPage extends PredefinedActions {

	WebDriverWait wait = new WebDriverWait(driver,30);
	private static ChequePaymentPage chequePaymentPage;
	public static ChequePaymentPage getInstance() {
		if(chequePaymentPage == null) {
			chequePaymentPage = new ChequePaymentPage();
		}
		return chequePaymentPage;
	}
	
	public String getPageHeading() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-subheading"))).getText();
	}
	
	public OrderConfirmationPage clickOnConfirmOrder() {
		driver.findElement(By.xpath("//p[@id='cart_navigation']//button")).click();
		return OrderConfirmationPage.getInstance();
	}
	
	public CheckoutPaymentPage clickOnOtherPaymentMethods() {
		driver.findElement(By.xpath("//p[@id='cart_navigation']/a")).click();
		return CheckoutPaymentPage.getInstance();
	}
}
