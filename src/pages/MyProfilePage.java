package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.PredefinedActions;

public class MyProfilePage extends PredefinedActions{
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	private static MyProfilePage myProfilePage;
	
	public static MyProfilePage getInstance() {
		if(myProfilePage == null) {
			myProfilePage = new MyProfilePage();
		}
		return myProfilePage;
	}
	
	public String getUserFullName() {
		String userFullName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header_user_info>a>span")))
				.getText();
		return userFullName;
	}
	
	public ProductCategoryPage selectSection(String sectionName) {
		List<WebElement> listOfSections = wait
		.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#block_top_menu>ul>li>a")));
		for(WebElement element : listOfSections) {
			if(element.getText().equalsIgnoreCase(sectionName)) {
				element.click();
				System.out.println(sectionName + " section is selected successfully");
				break;
			}
		}	
		return ProductCategoryPage.getInstance();
	}
}
