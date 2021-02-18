package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.PredefinedActions;
import exceptions.ProductNotAvailableException;

public class ProductCategoryPage extends PredefinedActions{

	WebDriverWait wait = new WebDriverWait(driver, 30);
	
	public List<WebElement> getProductList() {
		List<WebElement> listOfProducts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='right-block']/h5/a")));
		return listOfProducts;
	}
	
	public ProductDetailsPage selectFirstAvailableProduct(List<WebElement> productList) {
		try {
			if(productList.size()>0) 
				productList.get(0).click();
			else
				throw new ProductNotAvailableException("Product not available to select");
		} catch (ProductNotAvailableException e) {
			e.getMessage();
		}
		return new ProductDetailsPage();
	}
}
