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

public class PageBusinessDetail extends APageObject {

    Button             btn;
    TextField          txFd;
    DropDownTextSearch drDwTextSearch;
    RadioButton        rdButton;

    public PageBusinessDetail(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
        btn            = new Button(driver, wait, logger);
        txFd           = new TextField(driver, wait, logger);
        drDwTextSearch = new DropDownTextSearch(driver, wait, logger);
        rdButton       = new RadioButton(driver, wait, logger);
    }

    // Defined locators -------
    String strLabelInput    = "//div[contains(text(), '%s')]/following::*[1]//input";
    By     byButtonSubmit   = By.xpath("//button//span[text() = 'Submit']");
    By     byButtonContinue = By.xpath("//button//span[text() = 'Continue']");

    //-------- End of defined locators


    public void inputBusinessLegalName(String strBusinessLegalName) {
        txFd.withLocator(By.xpath(String.format(strLabelInput, "Business legal name"))).input(strBusinessLegalName);
    }

    public void selectEntityCategory(String strEntityCategory) {
        drDwTextSearch.searchItemDropDown("Entity category", strEntityCategory).selectItemDropDown(strEntityCategory);
    }

    public void selectEntityType(String strEntityType) {
        drDwTextSearch.searchItemDropDown("Entity type", strEntityType).selectItemDropDown(strEntityType);
    }

    public void inputBusinessRegistrationNumberUEN(String strRegistrationNumber) {
        txFd.withLocator(By.xpath(String.format(strLabelInput, "Business registration number (UEN)"))).input(strRegistrationNumber);
    }

    public void selectIndustry(String strIndustry) {
        drDwTextSearch.searchItemDropDown("Industry", strIndustry).selectItemDropDown(strIndustry);
    }

    public void selectSubIndustry(String strSubIndustry) {
        drDwTextSearch.searchItemDropDown("Sub-Industry", strSubIndustry).selectItemDropDown(strSubIndustry);
    }

    public void inputBusinessBusinessActivity(String strBusinessActivity) {
        txFd.withLocator(By.xpath(String.format(strLabelInput, "Business activity"))).input(strBusinessActivity);
    }

    public void inputExampleOfProductServicesProvide(String strProductServicesProvide) {
        txFd.withLocator(By.xpath(String.format(strLabelInput, "Tell us a detailed example of product/services you provide"))).input(strProductServicesProvide);
    }

    public void inputLiveBusinessWebsite(String strBusinessWebsite) {
        txFd.withLocator(By.xpath(String.format(strLabelInput, "Live business website"))).input(strBusinessWebsite);
    }

    public void selectNumberOfEmployees(String strNumberOfEmployees) {
        drDwTextSearch.searchItemDropDown("Number of employees", strNumberOfEmployees).selectItemDropDown(strNumberOfEmployees);
    }

    public void selectAnnualTurnover(String strAnnualTurnover) {
        drDwTextSearch.searchItemDropDown("Annual turnover", strAnnualTurnover).selectItemDropDown(strAnnualTurnover);
    }

    public void selectAnyPoliticallyExposedPersonQualified(String strValue) {
        rdButton.withParentStatement("Does any of your company directors or shareholders qualify as Politically Exposed Person (PEP), close family member or close associate of a PEP?")
                .selectRadioButton(strValue);
    }

    public void selectAnyShareholdersWithMoreThan10OrdinaryShares(String strValue) {
        rdButton.withParentStatement("Does your company have corporate shareholders with more than 10%% ordinary shares?")
                .selectRadioButton(strValue);
    }


    public void submitBusinessDetail() {
        btn.withLocator(byButtonSubmit).click();
        untilNoSpinner();
        untilNoInnerLoading();
    }

    public void clickContinue() {
        logger.debug("[PageRegister] Click on button Continue");
        btn.withLocator(byButtonContinue).click();
        untilNoSpinner();
        untilNoInnerLoading();
    }

}
