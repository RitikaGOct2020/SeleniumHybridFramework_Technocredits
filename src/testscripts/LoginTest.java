package testscripts;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyProfilePage;
import util.ExcelOperation;

public class LoginTest extends TestBase {

	@Test
	public void validLoginTest() {

		HomePage homePage = new HomePage();
		System.out.println("STEP: Click on Signin button from homepage");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		System.out.println("STEP: Enter email id and password and login");
		MyProfilePage myProfilePage = authenticationPage.doLogin("automation07@gmail.com", "auto_123");
		System.out.println("STEP: Validate page header text");
		String actual = myProfilePage.getUserFullName();
		String expected = "Automation Technocredits";
		Assert.assertEquals(actual, expected, "Verification of User Name failed");
	}

	@Test(dataProvider = "ValidLoginDataProvider")
	public void validLoginTestDataDriven(String emailId, String password, String fName, String lName) {

		HomePage homePage = new HomePage();
		System.out.println("STEP: Click on Signin button from homepage");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		System.out.println("STEP: Enter email id and password and login");
		MyProfilePage myProfilePage = authenticationPage.doLogin(emailId, password);
		System.out.println("STEP: Validate page header text");
		String actual = myProfilePage.getUserFullName();
		String expected = fName + " " + lName;
		Assert.assertEquals(actual, expected, "Verification of User Name failed");
	}

	@DataProvider(name = "ValidLoginDataProvider")
	public String[][] getDataForAuthenticationPageValidLogin() throws IOException {
		return ExcelOperation.getExcelData("data.xlsx", "ValidLogin");
	}
	
	@Test
	public void verifyInvalidLogin() {
		HomePage homePage = new HomePage();
		System.out.println("STEP: Click on Signin button from homepage");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		System.out.println("STEP: Enter email id and password and login");
		authenticationPage.doLogin("autom@gmail.com", "auto123");
		System.out.println("STEP: Verify Error Message");
		String actErrorMsg = authenticationPage.verifyErrorMessage();
		Assert.assertEquals(actErrorMsg, "Authentication failed.", "Error Message not matching");
	}
	
	
	@Test (dataProvider = "InvalidLoginDataProvider")
	public void verifyInvalidLoginDataDriven(String emailId, String password, String expErrorMsg) throws InterruptedException {
		HomePage homePage = new HomePage();
		System.out.println("STEP: Click on Signin button from homepage");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		System.out.println("STEP: Enter email id and password and login");
		authenticationPage.doLogin(emailId, password);
		System.out.println("STEP: Verify Error Message");
		String actErrorMsg = authenticationPage.verifyErrorMessage();
		Thread.sleep(3000);
		Assert.assertEquals(actErrorMsg, expErrorMsg, "Error Message verification Failed");
	}
	
	@DataProvider(name = "InvalidLoginDataProvider")
	public String[][] getDataForAuthenticationPageInvalidLogin() throws IOException {
		return ExcelOperation.getExcelData("data.xlsx", "InvalidLogin");
	}
}
