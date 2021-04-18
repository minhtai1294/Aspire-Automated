package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextField extends AComponent<TextField>{

    public TextField(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
    }

    public void input(String strInput) {
        untilPresent().untilVisible().untilClickable();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(strInput);
    }
}
