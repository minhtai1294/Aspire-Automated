package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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


    /**
     * Also an input action but use Action Object to build, performance can be slower than normal input but can handle better in some case that normal input can't
     * @param strInput
     */
    public void inputAction(String strInput) {
        untilPresent().untilVisible().untilClickable();
        WebElement _inputElement = driver.findElement(locator);
        Actions    builder       = new Actions(driver);
        builder.moveToElement(_inputElement).sendKeys(strInput).build().perform();
    }
}
