package components.level1;

import components.AComponent;
import components.level2.Button;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static tools.Constants.Indent.LVL_ONE;

public class RadioButton extends AComponent<RadioButton> {

    String strRadioParentStatement = "";
    Button btn;

    public RadioButton(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
        btn = new Button(driver, wait, logger);
    }

    // Defined locators -------
    String strLocatorRadioStatement = "//div[contains(text(), '%s')]";
    String strRadioItem             = "//following::div[contains(@class, 'q-radio')][@aria-checked][normalize-space() = '%s']";
    //-------- End of defined locators

    public RadioButton withParentStatement(String strStatement) {
        logger.debug(LVL_ONE.concat("[RadioButton] Radio button statement: " + strStatement));
        strRadioParentStatement = String.format(strLocatorRadioStatement, strStatement);
        return this;
    }

    public void selectRadioButton(String strLabel) {
        logger.debug(LVL_ONE.concat("[RadioButton] Select value: " + strLabel));
        if (!getStatus(strLabel)) {
            btn.withLocator(By.xpath(String.format(strRadioParentStatement.concat(strRadioItem), strLabel))).click();
        }
    }

    public boolean getStatus(String strLabel) {
        logger.debug(LVL_ONE.concat("[RadioButton] Get status of value: " + strLabel));
        By _elGetStatus = By.xpath(String.format(strRadioParentStatement.concat(strRadioItem), strLabel));
        btn.withLocator(_elGetStatus).untilPresent().untilVisible();
        return driver.findElement(_elGetStatus).getAttribute("aria-checked").equalsIgnoreCase("true");
    }


}
