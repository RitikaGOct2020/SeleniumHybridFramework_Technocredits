package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class CheckoutPaymentPage extends PredefinedActions {

	WebDriverWait wait = new WebDriverWait(driver, 30);
	private static CheckoutPaymentPage checkoutPaymentPage;
	
	public static CheckoutPaymentPage getInstance() {
		if(checkoutPaymentPage==null) {
			checkoutPaymentPage = new CheckoutPaymentPage();
		}
		return checkoutPaymentPage;
	}
	
	public String getPageHeading() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("page-heading"))).getText();
	}
	
	public String getProductName() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.product-name>a"))).getText();
	}

	public String getUnitPrice() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.cart_unit>span>span")))
				.getText().substring(1);
	}

	public String getQuantity() {
		return wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("td.cart_quantity.text-center span")))
				.getText().trim();
	}

	public String getTotalPrice() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.cart_total span"))).getText()
				.trim().substring(1);
	}

	public String getShippingPrice() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td#total_shipping"))).getText()
				.substring(1);
	}

	public String getFinalPrice() {
		return wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td#total_price_container>span")))
				.getText().substring(1);
	}
	
	public ChequePaymentPage clickOnPayByCheque() {
		driver.findElement(By.xpath("//a[@class='cheque']")).click();
		return ChequePaymentPage.getInstance();
	}
	
	public BankPaymentPage clickOnPayByBankWire() {
		driver.findElement(By.xpath("//a[@class='bankwire']")).click();
		return BankPaymentPage.getInstance();
	}
}
