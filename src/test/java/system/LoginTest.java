package system;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;

public class LoginTest extends AbstractTest {
	@Before
	public void setUp() throws Exception {
		init();
	}

	@Test
	public void shouldAuthentificateWhenCorrectCredentials() throws Exception {
		appManager.login("bender", "password");
		appManager.isLogged();
	}

	@Test
	public void shouldNotAuthentificateWhenIncorrectCredentials() throws Exception {
		appManager.login("noname", "nopassword");
		appManager.isNotLogged();
	}

}

