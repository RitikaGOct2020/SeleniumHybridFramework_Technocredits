package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.PredefinedActions;
import pojo.ProductDetailsPojo;

public class ProductDetailsPage extends PredefinedActions {

	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	private static ProductDetailsPage productDetailsPage;
	public static ProductDetailsPage getInstance() {
		if(productDetailsPage == null)
			productDetailsPage = new ProductDetailsPage();
		
		return productDetailsPage;
	}

	public ProductDetailsPojo captureProductDetails(ProductDetailsPojo productDetailsPojo) {
		String productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))).getText();
		String description = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id ='short_description_content']//p")))
				.getText();
		String unitPrice = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id ='our_price_display']")))
				.getText().substring(1);
		String quantity = driver.findElement(By.xpath("//input[@id ='quantity_wanted']")).getAttribute("value");
		String size = getSelectedSize();
		String color = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='color_to_pick_list']/li[@class='selected']/a")))
				.getAttribute("title");
		double d = Double.parseDouble(unitPrice) * Integer.parseInt(quantity);
		String totalPrice = String.format("%.2f", d);
		

		productDetailsPojo.setProductName(productName);
		productDetailsPojo.setDescription(description);
		productDetailsPojo.setUnitPrice(unitPrice);
		productDetailsPojo.setQuantity(quantity);
		productDetailsPojo.setSize(size);
		productDetailsPojo.setColor(color);
		productDetailsPojo.setTotalPrice(totalPrice);

		return productDetailsPojo;
	}

	public void setQuantity(String numOfQuantity) {
		WebElement quantityPath = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id ='quantity_wanted']")));
		quantityPath.clear();
		quantityPath.sendKeys(numOfQuantity);
		System.out.println("quantity mentioned successfully");
	}

	public void setSize(String size) {
		Select select = new Select(driver.findElement(By.id("group_1")));
		select.selectByVisibleText(size);
		System.out.println("size selected successfully");
	}

	public void setColor(String color) {
		List<WebElement> colorOptions = driver.findElements(By.xpath("//ul[@id='color_to_pick_list']/li/a"));
		for (WebElement element : colorOptions) {
			if (element.getAttribute("title").equalsIgnoreCase(color)) {
				element.click();
				break;
			}
		}
	}

	private String getSelectedSize() {
		String size = "";
		Select select = new Select(driver.findElement(By.id("group_1")));
		List<WebElement> sizeOptions = select.getOptions();
		for (WebElement element : sizeOptions) {
			if (element.isSelected()) {
				size = element.getAttribute("title");
			}
		}
		return size;
	}

	public void clickOnAddToCart() {
		driver.findElement(By.xpath("//button[@name='Submit']")).click();
	}
	
	public void verifyCartDetails(ProductDetailsPojo productDetailsPojo) {
		
		String productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_title"))).getText();
		String colorAndSize = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_attributes"))).getText();
		String quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_quantity"))).getText();
		String totalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart_product_price"))).getText().substring(1);
		String totalShipping = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ajax_cart_shipping_cost']")))
				.getText().substring(1);
		String totalPriceWithShipping = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ajax_block_cart_total']"))).getText().substring(1);
		productDetailsPojo.setShippingPrice(totalShipping);
		productDetailsPojo.setFinalPrice(totalPriceWithShipping);
		Assert.assertEquals(productName, productDetailsPojo.getProductName(), "Product Name is not matching");
		Assert.assertEquals(colorAndSize, productDetailsPojo.getColor()+", "+productDetailsPojo.getSize(), "Product color and size is not matching");
		Assert.assertEquals(quantity, productDetailsPojo.getQuantity(), "Product quantity is not matching");
		Assert.assertEquals(totalPrice, productDetailsPojo.getTotalPrice(), "Total products price is not matching");
		double d = Double.parseDouble(productDetailsPojo.getTotalPrice()) + Double.parseDouble(productDetailsPojo.getShippingPrice());		
		Assert.assertEquals(totalPriceWithShipping, String.format("%.2f", d), "Total price including shipping is not matching");
	}

	public CheckoutSummaryPage clickOnProceedToCheckout() {
		driver.findElement(By.xpath("//span[contains(text(), 'Proceed to checkout')]")).click();
		return CheckoutSummaryPage.getInstance();
	}
}
