package testscripts;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.BankPaymentPage;
import pages.CheckoutAddressesPage;
import pages.CheckoutPaymentPage;
import pages.CheckoutShippingPage;
import pages.CheckoutSummaryPage;
import pages.ChequePaymentPage;
import pages.HomePage;
import pages.MyProfilePage;
import pages.OrderConfirmationPage;
import pages.OrderHistoryPage;
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
		
		System.out.println("STEP: Click on Proceed to checkout and navigate to Checkout Summary Page");
		CheckoutSummaryPage checkoutSummaryPage = productDetailsPage.clickOnProceedToCheckout();
		
		System.out.println("STEP: Verify Shopping summary page details");
		Assert.assertEquals(checkoutSummaryPage.getProductName(), productDetailsPojo.getProductName(), "Product Name not matching on Shopping Summary page");
		Assert.assertEquals(checkoutSummaryPage.getQuantity(), productDetailsPojo.getQuantity(), "Product quantity not matching on Shopping Summary page");
		Assert.assertEquals(checkoutSummaryPage.getShippingPrice(), productDetailsPojo.getShippingPrice(), "Shipping not matching on Shopping Summary page");
		Assert.assertEquals(checkoutSummaryPage.getTotalPrice(), productDetailsPojo.getTotalPrice(), "Total price is not matching on Shopping Summary page");
		Assert.assertEquals(checkoutSummaryPage.getFinalPrice(), productDetailsPojo.getFinalPrice(), "Total price including shipping is not matching on Shopping Summary page");
		Assert.assertEquals(checkoutSummaryPage.getUnitPrice(), productDetailsPojo.getUnitPrice(), "Product unit price is not matching on Shopping Summary page");
		
		System.out.println("STEP: Click on Proceed to checkout and navigate to Checkout Addresses Page");
		CheckoutAddressesPage checkoutAddressesPage = checkoutSummaryPage.clickOnProceedToCheckout();
		
		System.out.println("STEP: Verify details on Addresses Page");
		List<String> deliveryAddress = checkoutAddressesPage.getDeliveryAddress();
		List<String> billingAddress = checkoutAddressesPage.getBillingAddress();
		Assert.assertEquals(deliveryAddress, billingAddress, "Delivery and Billing Addresses are not same");
		
		System.out.println("STEP: Click on Proceed to checkout and navigate to Checkout Shipping Page");
		CheckoutShippingPage checkoutShippingPage = checkoutAddressesPage.clickOnProceedToCheckout();
		
		System.out.println("STEP: Verify details on Shipping page");
		Assert.assertEquals(checkoutShippingPage.getShippingPrice(), productDetailsPojo.getShippingPrice(), "Shipping price is not matching on Checkout Shipping page");
		checkoutShippingPage.clickOnProceedToCheckout();
		checkoutShippingPage.verifyErrorMessage();
		checkoutShippingPage.selectCheckbox();
		
		System.out.println("STEP: Navigate to Checkout Payment Page");
		CheckoutPaymentPage checkoutPaymentPage = checkoutShippingPage.clickOnProceedToCheckout();
		
		System.out.println("STEP: Verify details on Payment Page");
		Assert.assertEquals(checkoutPaymentPage.getProductName(), productDetailsPojo.getProductName(), "Product Name not matching on Payment page");
		Assert.assertEquals(checkoutPaymentPage.getQuantity(), productDetailsPojo.getQuantity(), "Product quantity not matching on Payment page");
		Assert.assertEquals(checkoutPaymentPage.getUnitPrice(), productDetailsPojo.getUnitPrice(), "Product unit price is not matching on Payment page");
		Assert.assertEquals(checkoutPaymentPage.getTotalPrice(), productDetailsPojo.getTotalPrice(), "Total price is not matching on Payment page");
		Assert.assertEquals(checkoutPaymentPage.getShippingPrice(), productDetailsPojo.getShippingPrice(), "Shipping not matching on Payment page");
		Assert.assertEquals(checkoutPaymentPage.getFinalPrice(), productDetailsPojo.getFinalPrice(), "Total price including shipping is not matching on Payment page");
		
		System.out.println("STEP: Click on Pay by Cheque and navigate to Cheque Payment Page");
		ChequePaymentPage chequePaymentPage = checkoutPaymentPage.clickOnPayByCheque();
		Assert.assertEquals(chequePaymentPage.getPageHeading().toUpperCase(), "CHECK PAYMENT", "Page Heading is not correct");
		
		System.out.println("STEP: Click on Other payment methods link");
		checkoutPaymentPage = chequePaymentPage.clickOnOtherPaymentMethods();
		Assert.assertEquals(checkoutPaymentPage.getPageHeading().toUpperCase(), "PLEASE CHOOSE YOUR PAYMENT METHOD", "Payment page heading is not correct");
		
		System.out.println("STEP: Click on Pay by Bank-Wire");
		BankPaymentPage bankPaymentPage = checkoutPaymentPage.clickOnPayByBankWire();
		Assert.assertEquals(bankPaymentPage.getPageHeading().toUpperCase(), "BANK-WIRE PAYMENT.", "Page Heading is not correct");
		
		System.out.println("STEP: Click on Confirm Order button");
		OrderConfirmationPage orderConfirmationPage = bankPaymentPage.clickOnConfirmOrder();
		
		System.out.println("STEP: Verify details on Order Confirmation Page");
		Assert.assertEquals(orderConfirmationPage.getPageHeading(), "ORDER CONFIRMATION");
		Assert.assertEquals(orderConfirmationPage.getAmountToBePaid(), productDetailsPojo.getFinalPrice(), "Amount to be paid on Order confirmation page is not matching");
		/*String orderReferenceDetails = orderConfirmationPage.getOrderReferenceDetails();
		System.out.println(orderReferenceDetails);*/
		
		System.out.println("STEP: Click on Back to Order button and navigate to Order History page");
		OrderHistoryPage orderHistoryPage = orderConfirmationPage.clickOnBackToOrders();
		Assert.assertEquals(orderHistoryPage.getPageHeading().toUpperCase(), "ORDER HISTORY", "Page Heading is not matching on Order History page");
		//Assert.assertTrue(orderConfirmationPage.getOrderReferenceDetails().contains(orderHistoryPage.getOrderNumber()), "Order number not matching");		
	}
}
