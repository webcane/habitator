package system;

import org.fest.assertions.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class AppManager {
    private final WebDriver driver;

    public AppManager(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void isLogged() {
        Assertions.assertThat(driver.getTitle()).isEqualTo("Главная");
    }

    public void isNotLogged() {
        Assertions.assertThat(driver.getTitle()).isEqualTo("Вход");
    }

    public void login(String userName, String password) {
        driver.get("http://localhost:8080/Habitator01");
        driver.findElement(By.linkText("вход")).click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys(userName);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    }

    public void end() {
        driver.quit();
    }
}