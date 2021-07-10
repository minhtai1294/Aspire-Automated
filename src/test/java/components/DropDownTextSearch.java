package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    String strLabelInput        = "//div[contains(text(), '%s')]/following::*[1]//input";


    //-------- End of defined locators


    public DropDown searchItemDropDown(String strDropDownLabel, String strTextToSearch) {
        txFd.withLocator(By.xpath(String.format(strLabelInput, strDropDownLabel))).input(strTextToSearch);
        txFd.withLocator(By.xpath(String.format(strDropDownItemLabel, strTextToSearch))).untilVisible();
        return this;
    }


}
