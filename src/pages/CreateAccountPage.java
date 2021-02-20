package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.PredefinedActions;
import pojo.CreateAccountDetailsPojo;

public class CreateAccountPage extends PredefinedActions {

	WebDriverWait wait = new WebDriverWait(driver, 30);
	Select s ;
	private static CreateAccountPage createAccountPage;
	
	public static CreateAccountPage getInstance() {
		if(createAccountPage == null) {
			createAccountPage = new CreateAccountPage();
		}
		return createAccountPage;
	}

	public boolean verifyCreateAccountPageHeader() {
		return wait.until(ExpectedConditions.textToBe(By.cssSelector("h1.page-heading"), "CREATE AN ACCOUNT"));
	}
	
	private void selectGender(boolean isMale) {
		System.out.println("STEP - Select title");
		 WebElement titleElement = isMale
				? wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#id_gender1")))
				: wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#id_gender2")));
		wait.until(ExpectedConditions.elementToBeClickable(titleElement));
		titleElement.click();
	}
	private void enterFirstName(String firstName) {
		System.out.println("STEP - Enter First Name");
		if(firstName!=null) {
			driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
		}
	}
	private void enterLastName(String lastName) {
		System.out.println("STEP - Enter Last Name");
		if(lastName != null) {
			driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		}
	}
	private void enterPassword(String password) {
		System.out.println("STEP - Enter Password");
		if(password != null) {
			driver.findElement(By.id("passwd")).sendKeys(password);
		}
	}
	private void selectBirthdate(String day) {
		System.out.println("STEP - Select Birthdate from drop down");
		if(day != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-days"))).click();
			s = new Select(driver.findElement(By.id("days")));
			s.selectByValue(day);
		}
	}
	private void selectMonth(String month) {
		System.out.println("STEP - Select Month from drop down");
		if (month!=null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-months"))).click();
			s = new Select(driver.findElement(By.id("months")));
			s.selectByValue(month);
		}
	}
	private void selectYear(String year) {
		System.out.println("STEP - Select Year from drop down");
		if (year!=null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-years"))).click();
			s = new Select(driver.findElement(By.id("years")));
			s.selectByValue(year);
		}
	}
	private void selectCompany(String company) {
		System.out.println("Step - Select Company Name");
		if(company!=null) {
			driver.findElement(By.id("company")).sendKeys(company);
		}
	}
	private void enterAddress(String address1) {
		System.out.println("STEP - Enter Address");
		if(address1!=null) {
			driver.findElement(By.id("address1")).sendKeys(address1);
		}
	}
	private void enterCity(String city) {
		System.out.println("STEP - Enter City Name");
		if(city!=null) {
			driver.findElement(By.id("city")).sendKeys(city);
		}
	}
	private void enterState(String state) {
		System.out.println("STEP - Select State");
		if(state != null) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-id_state"))).click();
			s = new Select(driver.findElement(By.id("id_state")));
			s.selectByVisibleText(state);
		}
	}
	private void enterZipCode(String zipcode) {
		System.out.println("STEP - Enter Zipcode");
		if(zipcode != null)
		driver.findElement(By.id("postcode")).sendKeys(zipcode);
	}
	private void enterAdditionalInfo(String additionalInfo) {
		System.out.println("STEP - Enter Additional Information");
		if(additionalInfo != null) {
			driver.findElement(By.id("other")).sendKeys(additionalInfo);
		}
	}
	private void enterHomePhone(String homePhone) {
		System.out.println("STEP - Enter HomePhone number");
		if (homePhone != null) {
			driver.findElement(By.id("phone")).sendKeys(homePhone);
		}
	}
	private void enterMobileNumber(String mobileNumber) {
		System.out.println("STEP - Enter Mobile number");
		if (mobileNumber != null) {
			driver.findElement(By.id("phone_mobile")).sendKeys(mobileNumber);
		}
	}
	
	public void enterCreateAccountDetails(CreateAccountDetailsPojo createAccountDetailsPojo) {
		selectGender(createAccountDetailsPojo.isMale());
		enterFirstName(createAccountDetailsPojo.getFirstName());
		enterLastName(createAccountDetailsPojo.getLastName());
		enterPassword(createAccountDetailsPojo.getPassword());
		selectBirthdate(createAccountDetailsPojo.getDays());
		selectMonth(createAccountDetailsPojo.getMonth());
		selectYear(createAccountDetailsPojo.getYear());
		selectCompany(createAccountDetailsPojo.getCompany());
		enterAddress(createAccountDetailsPojo.getAddress1());
		enterCity(createAccountDetailsPojo.getCity());
		enterState(createAccountDetailsPojo.getState());
		enterZipCode(createAccountDetailsPojo.getZipcode());
		enterAdditionalInfo(createAccountDetailsPojo.getAdditionalInfo());
		enterHomePhone(createAccountDetailsPojo.getHomePhone());
		enterMobileNumber(createAccountDetailsPojo.getMobilePhone());
	}

	public MyProfilePage clickOnRegistration() {
		System.out.println("STEP: Click on Registration button after entring all details");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount"))).click();
		//System.out.println("Details Registered in Application");
		return new MyProfilePage();
	}
	
	public List<String> getErrorMessage() {
		
		List<WebElement> listOfErrorElements = driver.findElements(By.cssSelector("ol>li"));
		List<String> listOfErrorText =  new ArrayList<String>();
		String errorHeadingText = driver.findElement(By.cssSelector(".alert.alert-danger>p")).getText();
		listOfErrorText.add(errorHeadingText);
		for (WebElement element : listOfErrorElements) {
			listOfErrorText.add(element.getText());
		}
		
		return listOfErrorText;
	}
}
