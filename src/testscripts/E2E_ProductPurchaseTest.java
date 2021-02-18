package testscripts;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.CheckoutSummaryPage;
import pages.HomePage;
import pages.MyProfilePage;
import pages.ProductCategoryPage;
import pages.ProductDetailsPage;
import pojo.ProductDetailsPojo;

public class E2E_ProductPurchaseTest extends TestBase {

	@Test
	public void e2eProductPurchase() {
		HomePage homePage = new HomePage();
		System.out.println("STEP: Click on Signin button from homepage");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		
		System.out.println("STEP: Enter email id and password and login");
		MyProfilePage myProfilePage = authenticationPage.doLogin("automation07@gmail.com", "auto_123");
		
		System.out.println("STEP: Validate page header text");
		String actual = myProfilePage.getUserFullName();
		String expected = "Automation Technocredits";
		Assert.assertEquals(actual, expected, "Verification of User Name failed");
		
		System.out.println("STEP: Select section from the list");
		ProductCategoryPage productCategoryPage = myProfilePage.selectSection("Women");
		
		System.out.println("STEP: Navigate to Product Category page");
		List <WebElement> productList = productCategoryPage.getProductList();
		Assert.assertTrue(productList.size()>=1, "No Products to purchase");
		
		System.out.println("STEP: Select 1st available product from the list");
		ProductDetailsPage productDetailsPage = productCategoryPage.selectFirstAvailableProduct(productList);
		
		System.out.println("STEP: Set all required product details");
		ProductDetailsPojo productDetailsPojo = new ProductDetailsPojo();
		productDetailsPage.setQuantity("10");
		productDetailsPage.setSize("M");
		productDetailsPage.setColor("Blue");
		
		System.out.println("STEP: Capture all product details");
		productDetailsPojo = productDetailsPage.captureProductDetails(productDetailsPojo);
		
		System.out.println("STEP: Click on Add to Cart button");
		productDetailsPage.clickOnAddToCart();
		
		System.out.println("STEP: Verify Cart details on Product details page");
		productDetailsPage.verifyCartDetails(productDetailsPojo);
		
		System.out.println("STEP: Click on Proceed to checkout");
		CheckoutSummaryPage checkoutSummaryPage = productDetailsPage.clickOnProceedToCheckout();
		
		System.out.println("STEP: Verify Shopping summary page details");
		checkoutSummaryPage.verifyShoppingSummaryPageDetails(productDetailsPojo);
		
		
	}
	
}
