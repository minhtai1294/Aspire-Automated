package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Button extends AComponent<Button> {

    public Button(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
    }

    public void click() {
        untilPresent().untilVisible().untilClickable();
        driver.findElement(locator).click();
    }



}
