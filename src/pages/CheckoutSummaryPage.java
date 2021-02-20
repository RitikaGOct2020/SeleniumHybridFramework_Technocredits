package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.PredefinedActions;

public class CheckoutSummaryPage extends PredefinedActions {
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	private static CheckoutSummaryPage checkoutSummaryPage;
	
	public static CheckoutSummaryPage getInstance() {
		if(checkoutSummaryPage==null) {
			checkoutSummaryPage = new CheckoutSummaryPage();
		}
		return checkoutSummaryPage;
	}
	
	public String getProductName() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='cart_description']/p/a"))).getText();
	}
	
	public String getSizeAndColor() {
		return driver.findElement(By.cssSelector("#order-detail-content td.cart_description>small>a")).getText();
	}
	
	public String getUnitPrice() {
		return driver.findElement(By.cssSelector("#order-detail-content td.cart_unit>span>span")).getText().substring(1);
	}

	public String getQuantity() {
		return driver.findElement(By.cssSelector("td.cart_quantity.text-center>input[type='text']")).getAttribute("value");
	}
	
	public String getTotalPrice() {
		return driver.findElement(By.cssSelector("td.cart_total>span")).getText().substring(1);
	}
	
	public String getShippingPrice() {
		return driver.findElement(By.cssSelector("tr.cart_total_delivery #total_shipping")).getText().substring(1);
	}
	
	public String getFinalPrice() {
		return driver.findElement(By.cssSelector("tr.cart_total_price span#total_price")).getText().substring(1);
	}
	
	public CheckoutAddressesPage clickOnProceedToCheckout() {
		driver.findElement(By.cssSelector(".cart_navigation.clearfix span")).click();
		return CheckoutAddressesPage.getInstance();
	}
}

