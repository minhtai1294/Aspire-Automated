package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    String strDropDownOpenWithLabel = "//div[@class='aspire-label__text'][normalize-space() = '%s']/following::label[contains(@class, 'q-select')]//i";
    String strDropDownCloseWithLabel = "//div[@class='aspire-label__text'][normalize-space() = '%s']/following::label[contains(@class, 'q-field--highlighted')]";
    String strDropDownItemLabel = "//div[contains(@class,'q-item')][text() = '%s']";

    //-------- End of defined locators

    public DropDown openDropDown(String strLabel) {
        btn.withLocator(By.xpath(String.format(strDropDownOpenWithLabel, strLabel))).click();
        return this;
    }

    /**
     * Note: This method will follow one of methods openDropDown/searchItemDropDown
     *
     * @param strTextToSelect
     */
    public DropDown selectItemDropDown(String strTextToSelect) {
        btn.withLocator(By.xpath(String.format(strDropDownItemLabel, strTextToSelect))).click();
        return this;
    }

    public void closeDropDown(String strLabel){
        btn.withLocator(By.xpath(String.format(strDropDownCloseWithLabel, strLabel))).click();
    }

}
