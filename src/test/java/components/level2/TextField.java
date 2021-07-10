package components.level2;

import components.AComponent;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static tools.Constants.Indent.LVL_TWO;

public class TextField extends AComponent<TextField> {

    public TextField(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
    }

    public void input(String strInput) {
        logger.debug(LVL_TWO.concat("[TextField] Input text: " + strInput));
        untilPresent().untilVisible().untilClickable();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(strInput);
    }


    /**
     * Also an input action but use Action Object to build, performance may be inconsistent than normal input but can handle in some case that normal input can't
     *
     * @param strInput
     */
    public void actionInput(String strInput) {
        logger.debug(LVL_TWO.concat("[TextField] Action Input text: " + strInput));
        untilPresent().untilVisible().untilClickable();
        WebElement _inputElement = driver.findElement(locator);
        Actions    builder       = new Actions(driver);
        builder.moveToElement(_inputElement).sendKeys(strInput).build().perform();
    }
}
