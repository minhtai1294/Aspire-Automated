package poms.pageregister;

import components.level2.Button;
import components.level1.DropDownTextSearch;
import components.level2.TextField;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import poms.APageObject;

public class PageRegister extends APageObject {

    Button             btn;
    TextField          tfd;
    DropDownTextSearch drDwTextSearch;

    public PageRegister(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
        btn            = new Button(driver, wait, logger);
        tfd            = new TextField(driver, wait, logger);
        drDwTextSearch = new DropDownTextSearch(driver, wait, logger);
    }

    // Defined locators -------
    By     byRegisterLink      = By.xpath("//a[@class='text-primary text-bold login-step-start__register-link']");
    String strLabelInput       = "//div[contains(text(), '%s')]/following::*[1]//input";
    By     byCheckboxAgreeTerm = By.xpath("//div[@role = 'checkbox'][following-sibling::div[contains(text(), 'I have read and agree with the')][a[contains(text(), 'Terms and Conditions')]][a[contains(text(), 'Privacy Policy')]]]");
    By     byButtonContinue    = By.xpath("//button//span[text() = 'Continue']");

    // radio button
    String strPageRegisterRadiobutton = "//div[contains(@class,'q-radio__label')][text() = '%s']";

    // OTP field
    String strOTPDigitInput = "//div[@class='digit-input aspire-field']//div[contains(@class, 'digit-input__input')][%s]";

    // Business Role
    String strBusinessRoleCard = "//div[contains(@class, 'business-role')]/div[contains(@class, 'aspire-card')]//div[contains(text(), '%s')]";


    //-------- End of defined locators


    public void clickRegisterLink() {
        logger.debug("[PageRegister] click on Register Link");
        btn.withLocator(byRegisterLink).click();
    }

    public void inputFullName(String strFullName) {
        logger.debug("[PageRegister] input user full name: " + strFullName);
        tfd.withLocator(By.xpath(String.format(strLabelInput, "Full Name as per ID"))).input(strFullName);
    }

    public void inputEmailAddress(String strEmail) {
        logger.debug("[PageRegister] input user email address: " + strEmail);
        tfd.withLocator(By.xpath(String.format(strLabelInput, "Email address"))).input(strEmail);
    }

    public void inputMobileNumber(String strMobile) {
        logger.debug("[PageRegister] input user mobile: " + strMobile);
        tfd.withLocator(By.xpath(String.format(strLabelInput, "Mobile number"))).input(strMobile);
    }

    /**
     * Should have added a component called Radio button, this function is temporary only
     *
     * @param strRole
     */
    public void selectRoleInCompany(String strRole) {
        logger.debug("[PageRegister] select user role in company: " + strRole);
        btn.withLocator(By.xpath(String.format(strPageRegisterRadiobutton, strRole))).click();
    }


    public void checkAgreeTerms() {
        logger.debug("[PageRegister] Check on check box agree term and policy");
        btn.withLocator(byCheckboxAgreeTerm).click();
    }

    public void clickContinue() {
        logger.debug("[PageRegister] Click on button Continue");
        btn.withLocator(byButtonContinue).click();
        untilNoSpinner();
        untilNoInnerLoading();
    }


    public void selectWhereDidYouHereAboutUs(String strValueSelect) {
        logger.debug("[PageRegister] Select Where you hear about us value: " + strValueSelect);
        drDwTextSearch.searchItemDropDown("Where did you hear about us?", strValueSelect);
        drDwTextSearch.selectItemDropDown(strValueSelect);
    }


    public void inputOTPNumber(String strOTP) {
        logger.debug("[PageRegister] input OTP number: " + strOTP);

        try {
            for (int i = 0; i < strOTP.length(); i++) {
                String _strDigitToInput = String.valueOf(strOTP.charAt(i));
                By     xPAth            = By.xpath(String.format(strOTPDigitInput, i + 1));
                tfd.withLocator(By.xpath(String.format(strOTPDigitInput, i + 1))).inputAction(_strDigitToInput);
            }
        } catch (IndexOutOfBoundsException e) {
            logger.debug("Number of OTP digits was not match");
            throw e;
        }

    }

    public void selectBusinessRole(String strBusinessRole) {
        logger.debug("[PageRegister] Select business role: " + strBusinessRole);
        btn.withLocator(By.xpath(String.format(strBusinessRoleCard, strBusinessRole))).click();

    }
}
