package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class OrderConfirmationPage extends PredefinedActions{

	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	private static OrderConfirmationPage orderConfirmationPage;
	public static OrderConfirmationPage getInstance() {
		if(orderConfirmationPage == null)
			orderConfirmationPage = new OrderConfirmationPage();
		
		return orderConfirmationPage;
	}
	
	public String getPageHeading() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-heading"))).getText().toUpperCase();
	}
	
	public String getAmountToBePaid() {
		return driver.findElement(By.cssSelector("div.box span.price strong")).getText().substring(1);
	}
	/*public String getOrderReferenceDetails() {
		String s =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='box']//text()[6]"))).getText();
		return s;
	}*/
	
	public OrderHistoryPage clickOnBackToOrders() {
		driver.findElement(By.xpath("//a[@title='Back to orders']")).click();
		return OrderHistoryPage.getInstance();
	}
	
	/*public boolean isSuccessAlertDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.alert.alert-success"))).isDisplayed();
	}*/
	
	
}
