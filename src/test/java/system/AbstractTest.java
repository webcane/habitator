package system;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Created by Денис on 22.07.2015.
 */
public class AbstractTest {
	protected AppManager appManager;

	protected void init() {
		appManager = new AppManager(new FirefoxDriver());
	}

	@After
	public void tearDown() throws Exception {
		appManager.end();
	}

}
