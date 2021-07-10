package components.level1;

import components.AComponent;
import components.level2.Button;
import components.level2.TextField;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static tools.Constants.Indent.LVL_ONE;

public class DropDown extends AComponent<DropDown> {

    // Defined child components -------
    TextField txFd;
    Button    btn;
    //-------- End of defined locators

    public DropDown(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
        txFd = new TextField(driver, wait, logger);
        btn  = new Button(driver, wait, logger);
    }


    // Defined locators -------
    String strDropDownOpenWithLabel  = "//div[@class='aspire-label__text'][normalize-space() = '%s']/following::label[contains(@class, 'q-select')]//i";
    String strDropDownCloseWithLabel = "//div[@class='aspire-label__text'][normalize-space() = '%s']/following::label[contains(@class, 'q-field--highlighted')]";
    String strDropDownItemLabel      = "//div[contains(@class,'q-item')][text() = '%s']";

    //-------- End of defined locators

    public DropDown openDropDown(String strLabel) {
        logger.debug(LVL_ONE.concat("[DropDown] Open dropdown: " + strLabel));
        btn.withLocator(By.xpath(String.format(strDropDownOpenWithLabel, strLabel))).click();
        return this;
    }

    /**
     * Note: This method will follow one of methods openDropDown/searchItemDropDown
     *
     * @param strTextToSelect
     */
    public DropDown selectItemDropDown(String strTextToSelect) {
        logger.debug(LVL_ONE.concat("[DropDown] Select item in drop down: " + strTextToSelect));
        btn.withLocator(By.xpath(String.format(strDropDownItemLabel, strTextToSelect))).click();
        return this;
    }

    public void closeDropDown(String strLabel) {
        logger.debug(LVL_ONE.concat("[DropDown] Close drop down: " + strLabel));
        btn.withLocator(By.xpath(String.format(strDropDownCloseWithLabel, strLabel))).click();
    }

}
