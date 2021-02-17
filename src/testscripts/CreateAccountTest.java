package testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyProfilePage;
import pojo.CreateAccountDetailsPojo;
import util.ExcelOperation;

public class CreateAccountTest extends TestBase{
	
	@Test
	public void createAccountPageTest() {
		HomePage homePage = new HomePage();
		
		System.out.println("STEP: Click on Signin button from homepage");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		
		System.out.println("STEP: Verify Authentication page header");
		boolean visibility = authenticationPage.isAuthenticationHeaderVisible();
		Assert.assertTrue(visibility, "Authentication page header text verification failed");
		
		System.out.println("STEP: Enter email address for the create user name");
		authenticationPage.enterEmailAddressInCreateAccount("automation07@gmail.com");
		
		System.out.println("STEP: Navigate to Create Account page");	 
		CreateAccountPage createAccountPage =  authenticationPage.clickOnCreateAccount();
		
		System.out.println("STEP: Verify Create Account Page header");
		visibility = createAccountPage.verifyCreateAccountPageHeader();
		Assert.assertTrue(visibility, "Create Account Page Header text verification failed");
		
		CreateAccountDetailsPojo createAccountDetailsPojo =  new CreateAccountDetailsPojo();
		createAccountDetailsPojo.setMale(true);
		createAccountDetailsPojo.setFirstName("Automation");
		createAccountDetailsPojo.setLastName("Technocredits");
		createAccountDetailsPojo.setPassword("Automation_123");
		createAccountDetailsPojo.setDays("12");
		createAccountDetailsPojo.setMonth("January");
		createAccountDetailsPojo.setYear("1997");
		createAccountDetailsPojo.setCompany("PTC");
		createAccountDetailsPojo.setAddress1("650 Grassmere park");
		createAccountDetailsPojo.setCity("Nashville");
		createAccountDetailsPojo.setState("Tennessee");
		createAccountDetailsPojo.setZipcode("37211");
		createAccountDetailsPojo.setAdditionalInfo("NA");
		createAccountDetailsPojo.setHomePhone("8905714840");
		createAccountDetailsPojo.setMobilePhone("8905714840");
		createAccountDetailsPojo.setAliasAddress("");
		
		System.out.println("STEP: Enter all details");
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);
		
		System.out.println("STEP: Click on Register button");
		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration(); 
		String actual = myProfilePage.getUserFullName();
		String expected = "Automation Technocredits";
		Assert.assertEquals(actual, expected, "Verification of Header text failed");
	}
	
	@Test (dataProvider = "CreateAccountDataProvider")
	public void createAccountPageDataDrivenTest (String email, String gender, String firstName, String lastName, String password,
											String day, String month, String year, String company, String address1, String city,
											String state, String zipcode, String additionalInfo, String homePhone, String mobilePhone, 
											String aliasAdd) {
		HomePage homePage = new HomePage();
		
		System.out.println("STEP: Click on Signin button from homepage");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		
		System.out.println("STEP: Verify Authentication page header");
		boolean visibility = authenticationPage.isAuthenticationHeaderVisible();
		Assert.assertTrue(visibility, "Authentication page header text verification failed");
		
		System.out.println("STEP: Enter email address for the create user name");
		authenticationPage.enterEmailAddressInCreateAccount(email);
		
		System.out.println("STEP: Navigate to Create Account page");	 
		CreateAccountPage createAccountPage =  authenticationPage.clickOnCreateAccount();
		
		System.out.println("STEP: Verify Create Account Page header");
		visibility = createAccountPage.verifyCreateAccountPageHeader();
		Assert.assertTrue(visibility, "Create Account Page Header text verification failed");
		
		CreateAccountDetailsPojo createAccountDetailsPojo =  new CreateAccountDetailsPojo();
		
		boolean mFlag = gender.equalsIgnoreCase("male") ? true : false;
		createAccountDetailsPojo.setMale(mFlag);
		createAccountDetailsPojo.setFirstName(firstName);
		createAccountDetailsPojo.setLastName(lastName);
		createAccountDetailsPojo.setPassword(password);
		createAccountDetailsPojo.setDays(day);
		createAccountDetailsPojo.setMonth(month);
		createAccountDetailsPojo.setYear(year);
		createAccountDetailsPojo.setCompany(company);
		createAccountDetailsPojo.setAddress1(address1);
		createAccountDetailsPojo.setCity(city);
		createAccountDetailsPojo.setState(state);
		createAccountDetailsPojo.setZipcode(zipcode);
		createAccountDetailsPojo.setAdditionalInfo(additionalInfo);
		createAccountDetailsPojo.setHomePhone(homePhone);
		createAccountDetailsPojo.setMobilePhone(mobilePhone);
		createAccountDetailsPojo.setAliasAddress(aliasAdd);
		
		System.out.println("STEP: Enter all details");
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);
		
		System.out.println("STEP: Click on Register button");
		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration(); 
		String actual = myProfilePage.getUserFullName();
		String expected = firstName + " " + lastName;
		Assert.assertEquals(actual, expected, "Verification of Header text failed");
	}
	
	@Test (dataProvider = "CreateAccountDataProviderPojo")
	public void createAccountPageDataDrivenTestPojo (CreateAccountDetailsPojo createAccountDetailsPojo) {
		HomePage homePage = new HomePage();
		
		System.out.println("STEP: Click on Signin button from homepage");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		
		System.out.println("STEP: Verify Authentication page header");
		boolean visibility = authenticationPage.isAuthenticationHeaderVisible();
		Assert.assertTrue(visibility, "Authentication page header text verification failed");
		
		System.out.println("STEP: Enter email address for the create user name");
		authenticationPage.enterEmailAddressInCreateAccount(createAccountDetailsPojo.getEmail());
		
		System.out.println("STEP: Navigate to Create Account page");	 
		CreateAccountPage createAccountPage =  authenticationPage.clickOnCreateAccount();
		
		System.out.println("STEP: Verify Create Account Page header");
		visibility = createAccountPage.verifyCreateAccountPageHeader();
		Assert.assertTrue(visibility, "Create Account Page Header text verification failed");
		
		System.out.println("STEP: Enter all details");
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);
		
		System.out.println("STEP: Click on Register button");
		MyProfilePage myProfilePage = createAccountPage.clickOnRegistration(); 
		String actual = myProfilePage.getUserFullName();
		String expected = createAccountDetailsPojo.getFirstName() + " " + createAccountDetailsPojo.getLastName();
		Assert.assertEquals(actual, expected, "Verification of Header text failed");
	}
	
	@DataProvider(name="CreateAccountDataProvider")
	public String[][] getDataForCreateAccountPage() throws IOException{
		return ExcelOperation.getExcelData("data.xlsx", "CreateAccount");
	}
	
	@DataProvider(name="CreateAccountDataProviderPojo")
	public Object [][] getDataForCreateAccountPagePojo() throws IOException{
		String[][] data =  ExcelOperation.getExcelData("data.xlsx", "CreateAccount");
		Object[][] output = new Object[data.length][1];
		
		CreateAccountDetailsPojo createAccountDetailsPojo =  new CreateAccountDetailsPojo();
		createAccountDetailsPojo.setEmail(data[0][0]);
		boolean mFlag = data[0][1].equalsIgnoreCase("male") ? true : false;
		createAccountDetailsPojo.setMale(mFlag);
		createAccountDetailsPojo.setFirstName(data[0][2]);
		createAccountDetailsPojo.setLastName(data[0][3]);
		createAccountDetailsPojo.setPassword(data[0][4]);
		createAccountDetailsPojo.setDays(data[0][5]);
		createAccountDetailsPojo.setMonth(data[0][6]);
		createAccountDetailsPojo.setYear(data[0][7]);
		createAccountDetailsPojo.setCompany(data[0][8]);
		createAccountDetailsPojo.setAddress1(data[0][9]);
		createAccountDetailsPojo.setCity(data[0][10]);
		createAccountDetailsPojo.setState(data[0][11]);
		createAccountDetailsPojo.setZipcode(data[0][12]);
		createAccountDetailsPojo.setAdditionalInfo(data[0][13]);
		createAccountDetailsPojo.setHomePhone(data[0][14]);
		createAccountDetailsPojo.setMobilePhone(data[0][15]);
		createAccountDetailsPojo.setAliasAddress(data[0][16]);
		
		output[0][0] = createAccountDetailsPojo;
		return output;	
	}
	
	@Test
	public void createAccountUIValidations() {
		HomePage homePage = new HomePage();
		
		System.out.println("STEP: Click on Signin button from homepage");
		AuthenticationPage authenticationPage = homePage.clickOnSignIn();
		
		System.out.println("STEP: Verify Authentication page header");
		boolean visibility = authenticationPage.isAuthenticationHeaderVisible();
		Assert.assertTrue(visibility, "Authentication page header text verification failed");
		
		System.out.println("STEP: Enter email address for the create user name");
		authenticationPage.enterEmailAddressInCreateAccount("automation08@gmail.com");
		
		System.out.println("STEP: Navigate to Create Account page");	 
		CreateAccountPage createAccountPage =  authenticationPage.clickOnCreateAccount();
		
		System.out.println("STEP: Verify Create Account Page header");
		visibility = createAccountPage.verifyCreateAccountPageHeader();
		Assert.assertTrue(visibility, "Create Account Page Header text verification failed");
		
		CreateAccountDetailsPojo createAccountDetailsPojo =  new CreateAccountDetailsPojo();
		createAccountDetailsPojo.setMale(true);
		
		System.out.println("STEP: Enter all details");
		createAccountPage.enterCreateAccountDetails(createAccountDetailsPojo);
		
		System.out.println("STEP: Click on Register button");
		createAccountPage.clickOnRegistration(); 
		
		List<String> actualErrorMessages = new ArrayList<String>();
		actualErrorMessages.add("There are 8 errors");
		actualErrorMessages.add("You must register at least one phone number.");
		actualErrorMessages.add("lastname is required.");
		actualErrorMessages.add("firstname is required.");
		actualErrorMessages.add("passwd is required.");
		actualErrorMessages.add("address1 is required.");
		actualErrorMessages.add("city is required.");
		actualErrorMessages.add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
		actualErrorMessages.add("This country requires you to choose a State.");
		List<String> expectedErrorMessages = createAccountPage.getErrorMessage();
		System.out.println("DEBUG - print actualErrorMessages");
		System.out.println(actualErrorMessages);
		System.out.println("DEBUG - print expectedErrorMessages");
		System.out.println(expectedErrorMessages);
		Assert.assertEquals(actualErrorMessages, expectedErrorMessages);
	}
}
