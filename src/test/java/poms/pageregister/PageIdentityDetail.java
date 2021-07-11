package poms.pageregister;

import components.level2.Button;
import components.level1.DropDownTextSearch;
import components.level1.RadioButton;
import components.level2.TextField;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import poms.APageObject;

public class PageIdentityDetail extends APageObject {

    Button             btn;
    TextField          txFd;
    DropDownTextSearch drDwTextSearch;
    RadioButton        rdButton;

    public PageIdentityDetail(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
        btn            = new Button(driver, wait, logger);
        txFd           = new TextField(driver, wait, logger);
        drDwTextSearch = new DropDownTextSearch(driver, wait, logger);
        rdButton       = new RadioButton(driver, wait, logger);
    }

    // Defined locators -------
    By     byFileUpload              = By.xpath("//span[@class='onfido-sdk-ui-CustomFileInput-container']//input[@type='file']");
    String strCheckBoxSelect         = "//div[contains(@class,'q-checkbox__inner')][following::div[text() = '%s']]";
    By     byButtonGetStarted        = By.xpath("//button//span[text() = 'Get Started']");
    By     byButtonBeginVerification = By.xpath("//button//span[text() = 'Begin Verification']");
    By     byButtonUploadFile        = By.xpath("//button[text() = 'Upload file']");
    By     byButtonConfirm           = By.xpath("//button[text() = 'Confirm']");
    String strDocumentationOption    = "//button[contains(@class,'DocumentSelector-option')][descendant::p[text() = '%s']]";
    By     byAuthSpinner             = By.xpath("//div[@class='auth-form']//div[@class='onfido-sdk-ui-Spinner-loader']");

    //-------- End of defined locators


    public void selectDocumentTypeIdentityVerification(String strValueSelect) {
        btn.withLocator(By.xpath(String.format(strCheckBoxSelect, strValueSelect))).click();
        untilNoSpinner();
        untilNoInnerLoading();
    }

    public void pressUploadFile() {
        btn.withLocator(byButtonUploadFile).click();
    }

    public void pressConfirm() {
        btn.withLocator(byButtonConfirm).click();
        btn.withLocator(byAuthSpinner).untilInVisible();
    }

    public void uploadFile(String strPath) {
        driver.findElement(byFileUpload).sendKeys(strPath);
    }

    public void pressGetStarted() {
        btn.withLocator(byButtonGetStarted).click();
    }

    public void pressBeginVerification() {
        btn.withLocator(byButtonBeginVerification).click();
    }

    public void selectDocumentationOption(String strOption) {
        btn.withLocator(By.xpath(String.format(strDocumentationOption, strOption))).click();
    }


}
