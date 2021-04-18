package poms;

import components.Button;
import components.TextField;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageLogin extends APageObject {

    Button    btn;
    TextField tfd;

    // xpath
    By     byRegisterLink      = By.xpath("//a[@class='text-primary text-bold login-step-start__register-link']");
    String strLabelInput       = "//div[contains(text(), '%s')]/following::label[1]//input";
    By     byCheckboxAgreeTerm = By.xpath("//div[@role = 'checkbox'][following-sibling::div[contains(text(), 'I have read and agree with the')][a[contains(text(), 'Terms and Conditions')]][a[contains(text(), 'Privacy Policy')]]]");
    By     byButtonContinue    = By.xpath("//button//span[text() = 'Continue']");
    public By byTextPleaseInviteYourDirector = By.xpath("//*[contains(text(), 'Please invite your director to complete registration')]");


    public PageLogin(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
        btn = new Button(driver, wait, logger);
        tfd = new TextField(driver, wait, logger);
    }


    public void clickRegisterLink() {
        btn.withLocator(byRegisterLink).click();
    }

    public void inputFullName(String strFullName) {
        tfd.withLocator(By.xpath(String.format(strLabelInput, "Full Name as per ID"))).input(strFullName);
    }

    public void inputEmailAddress(String strEmail) {
        tfd.withLocator(By.xpath(String.format(strLabelInput, "Email address"))).input(strEmail);
    }

    public void checkAgreeTerms() {
        btn.withLocator(byCheckboxAgreeTerm).click();
    }

    public void clickContinue() {
        btn.withLocator(byButtonContinue).click();
    }


}
