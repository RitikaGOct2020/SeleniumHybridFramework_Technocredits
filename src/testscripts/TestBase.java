package testscripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import base.PredefinedActions;

public class TestBase {
	
	@BeforeMethod
	public void setUp() {
		PredefinedActions.start();
	}

	@AfterMethod
	public void tearDown() {
		PredefinedActions.close();
	}

}
