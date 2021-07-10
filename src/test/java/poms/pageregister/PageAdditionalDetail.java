package poms.pageregister;

import components.level2.Button;
import components.level2.TextField;
import components.level1.CalendarDate;
import components.level1.DropDown;
import components.level1.DropDownTextSearch;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import poms.APageObject;

import java.util.List;

public class PageAdditionalDetail extends APageObject<PageAdditionalDetail> {

    Button             btn;
    TextField          tfd;
    DropDown           drDw;
    DropDownTextSearch drDwTextSearch;
    CalendarDate       clnDar;

    public PageAdditionalDetail(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
        btn            = new Button(driver, wait, logger);
        tfd            = new TextField(driver, wait, logger);
        drDw           = new DropDown(driver, wait, logger);
        drDwTextSearch = new DropDownTextSearch(driver, wait, logger);
        clnDar         = new CalendarDate(driver, wait, logger);
    }

    // Defined locators -------
    By     bySelectApplicableOption = By.xpath("//div[@placeholder='Select applicable options']");
    String strRegistrationCard      = "//div[@class='auth-form__card']//div[@class='onboarding-step-register-select-method__column-content'][descendant::div[normalize-space() = '%s']]//span[@class='block'][normalize-space() = 'Get Started']";
    By     byButtonSubmit           = By.xpath("//button//span[text() = 'Submit']");
    String strOTPDigitInput         = "//div[@class='digit-input aspire-field']//div[contains(@class, 'digit-input__input')][%s]";

    //-------- End of defined locators

    public PageAdditionalDetail openApplicableOption() {
        logger.debug("[PageAdditionalDetail] Open Applicable Option");
        btn.withLocator(bySelectApplicableOption).click();
        return this;
    }


    public void selectApplicableOptions(List<String> _lisOptions) {
        logger.debug("[PageAdditionalDetail] Select Applicable Option: " + _lisOptions);
        drDw.openDropDown("What solutions are you looking for?");
        for (String _strOption : _lisOptions) {
            drDw.selectItemDropDown(_strOption);
        }
        drDw.closeDropDown("What solutions are you looking for?");
    }


    public void selectCompanyRegistered(String strOption) {
        logger.debug("[PageAdditionalDetail] Select Applicable Option: " + strOption);
        drDwTextSearch.searchItemDropDown("Where is your company registered?", strOption).selectItemDropDown(strOption);
    }

    public void selectRegistrationMethod(String strRegistrationMethod) {
        logger.debug("[PageAdditionalDetail] Select Registration Method: " + strRegistrationMethod);
        btn.withLocator(By.xpath(String.format(strRegistrationCard, strRegistrationMethod))).click();
        untilNoSpinner();
    }

    public void selectDateOfBirth(String strDateOfBirth) {
        logger.debug("[PageAdditionalDetail] Select Date: " + strDateOfBirth);
        String[] _strDate  = strDateOfBirth.split("/");
        String   _strDay   = _strDate[0];
        String   _strMonth = _strDate[1];
        String   _strYear  = _strDate[2];

        clnDar.openCalendarField("Date of birth").selectMonth(_strMonth).selectYear(_strYear).selectDay(_strDay);

    }

    public void selectNationality(String strNationality) {
        drDwTextSearch.searchItemDropDown("Nationality", strNationality).selectItemDropDown(strNationality);
    }

    public void selectGender(String strGender) {
        drDwTextSearch.searchItemDropDown("Gender", strGender).selectItemDropDown(strGender);
    }

    public void submitPersonalDetail() {
        btn.withLocator(byButtonSubmit).click();
        untilNoSpinner();
        untilNoInnerLoading();
    }

    public void inputOTPNumber(String strOTP) {
        logger.debug("[PageRegister] input OTP number: " + strOTP);

        try {
            for (int i = 0; i < strOTP.length(); i++) {
                String _strDigitToInput = String.valueOf(strOTP.charAt(i));
                By     xPAth            = By.xpath(String.format(strOTPDigitInput, i + 1));
                tfd.withLocator(By.xpath(String.format(strOTPDigitInput, i + 1))).actionInput(_strDigitToInput);
            }
            untilNoSpinner();
            untilNoInnerLoading();
        } catch (IndexOutOfBoundsException e) {
            logger.debug("Number of OTP digits was not match");
            throw e;
        }

    }


}
