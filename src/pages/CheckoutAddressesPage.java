package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class CheckoutAddressesPage extends PredefinedActions {

	WebDriverWait wait = new WebDriverWait(driver,30);
	
	private static CheckoutAddressesPage checkoutAddressesPage;
	
	public static CheckoutAddressesPage getInstance() {
		if(checkoutAddressesPage==null) {
			checkoutAddressesPage = new CheckoutAddressesPage();
		}
		return checkoutAddressesPage;
	}
	
	public List<String> getDeliveryAddress(){
		List<WebElement> listOfDeliveryAddress =  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='address_delivery']/li[@class!='address_title'][@class!='address_update']")));
		List<String> deliveryAddress = new ArrayList<>();
		 for(WebElement element : listOfDeliveryAddress) {
			 deliveryAddress.add(element.getText());
		 }
	 return deliveryAddress;
	
	}
	
	public List<String> getBillingAddress(){
		 List<WebElement> listOfBillingAddress = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='address_invoice']/li[@class!='address_title'][@class!='address_update']")));
		 List<String> billingAddress = new ArrayList<>();
		 for(WebElement element : listOfBillingAddress) {
			 billingAddress.add(element.getText());
		 }
	 return billingAddress;
	}
	
	public void selectCheckbox() {
		WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#uniform-addressesAreEquals>span")));
		if(checkbox.getAttribute("class").equals("checked"))
			System.out.println("Checkbox is already clicked");
		else
			checkbox.click();
	}
	
	public CheckoutShippingPage clickOnProceedToCheckout() {
		driver.findElement(By.cssSelector(".cart_navigation.clearfix span")).click();
		return CheckoutShippingPage.getInstance();
	}
}
