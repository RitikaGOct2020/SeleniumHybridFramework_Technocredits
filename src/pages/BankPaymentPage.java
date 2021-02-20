package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.PredefinedActions;

public class BankPaymentPage extends PredefinedActions{

	WebDriverWait wait = new WebDriverWait(driver,30);
	private static BankPaymentPage bankPaymentPage;
	
	public static BankPaymentPage getInstance() {
		if(bankPaymentPage==null) {
			bankPaymentPage = new BankPaymentPage();
		}
		return bankPaymentPage;
	}
	
	public String getPageHeading() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-subheading"))).getText().trim();
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
