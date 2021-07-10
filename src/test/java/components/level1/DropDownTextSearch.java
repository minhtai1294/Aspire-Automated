package components.level1;

import components.level2.Button;
import components.level2.TextField;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static tools.Constants.Indent.LVL_ONE;

public class DropDownTextSearch extends DropDown {

    // Defined child components -------
    TextField txFd;
    Button    btn;
    //-------- End of defined locators

    public DropDownTextSearch(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
        txFd = new TextField(driver, wait, logger);
        btn  = new Button(driver, wait, logger);
    }


    // Defined locators -------
    String strLabelInput = "//div[contains(text(), '%s')]/following::*[1]//input";

    //-------- End of defined locators


    public DropDown searchItemDropDown(String strDropDownLabel, String strTextToSearch) {
        logger.debug(LVL_ONE.concat("[DropDownTextSearch] Search drop down " + strDropDownLabel + " for item: " + strTextToSearch));
        txFd.withLocator(By.xpath(String.format(strLabelInput, strDropDownLabel))).input(strTextToSearch);
        txFd.withLocator(By.xpath(String.format(strDropDownItemLabel, strTextToSearch))).untilVisible();
        return this;
    }


}
