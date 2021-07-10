package components.level2;

import components.AComponent;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static tools.Constants.Indent.LVL_TWO;

public class Button extends AComponent<Button> {

    public Button(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
    }

    public void click() {
        logger.debug(LVL_TWO.concat("[Button] Click on element: " + locator));
        untilPresent().untilVisible().untilClickable();
        driver.findElement(locator).click();
    }


}
