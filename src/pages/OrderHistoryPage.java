package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class OrderHistoryPage extends PredefinedActions {

	WebDriverWait wait = new WebDriverWait(driver,30);
	private static OrderHistoryPage orderHistoryPage;
	public static OrderHistoryPage getInstance() {
		if(orderHistoryPage == null)
			orderHistoryPage = new OrderHistoryPage();
		
		return orderHistoryPage;
	}
	
	public String getPageHeading() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#center_column>h1"))).getText();
	}
	
	public String getOrderNumber() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='first_item ']//a[@class='color-myaccount']"))).getText().trim();
	}
	
	public String getDateOfOrder() {
		return driver.findElement(By.xpath("//tr[@class='first_item ']//td[@class='history_date bold']")).getText().trim();
	}
	
	public String getTotalOrderPrice() {
		return driver.findElement(By.xpath("//tr[@class='first_item ']//td[@class='history_price']/span")).getText().trim().substring(1);
	}
	
	public String getPaymentMethod() {
		return driver.findElement(By.xpath("//tr[@class='first_item ']//td[@class='history_method']")).getText();
	}
	
	public void downloadInvoice() {
		driver.findElement(By.xpath("//tr[@class='first_item ']//td[@class='history_invoice']")).click();
	}
	
	public void clickOnDetailsButton() {
		driver.findElement(By.xpath("//tr[@class='first_item ']//td[@class='history_detail footable-last-column']//span")).click();
	}
	
	public void clickOnReorderButton() {
		driver.findElement(By.xpath("//tr[@class='first_item ']//td[@class='history_detail footable-last-column']/a[@class='link-button']")).click();
	}
	
	public void clickOnBackToAccountButton() {
		driver.findElement(By.xpath("//ul[@class='footer_links clearfix']/li[1]/a")).click();
	}
	
	public void clickOnHomeButton() {
		driver.findElement(By.xpath("//ul[@class='footer_links clearfix']/li[2]/a")).click();
	}
}
