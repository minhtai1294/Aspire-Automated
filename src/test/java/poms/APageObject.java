package poms;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tools.JavaExecuteScriptHelper;

public class APageObject<T extends APageObject> {

    protected WebDriver     driver;
    protected WebDriverWait wait;
    protected Logger        logger;

    public APageObject(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        this.wait   = wait;
    }

    // Defined locators -------

    String strTextOnPage  = "//*[contains(text(), '%s')]";
    By     bySpinner      = By.xpath("//*[name() = 'svg'][contains(@class,'q-spinner')]");
    By     byInnerLoading = By.xpath("//*[contains(@class,'q-inner-loading')]");


    //-------- End of defined locators


    public T untilPageIsCompletedLoaded() {
        logger.debug("Wait for page loaded completely...");
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript(JavaExecuteScriptHelper.PAGE_READY_STATE).equals("complete"));
        logger.debug("...Page loaded completely");
        return (T) this;
    }

    public boolean isTextVisibleOnPage(String text) {
        logger.debug("Check if this text is visible on page: " + text);
        untilPageIsCompletedLoaded();
        return driver.findElements(By.xpath(String.format(strTextOnPage, text))).size() > 0;
    }

    public void untilNoSpinner() {
        logger.debug("Wait for Spinner to disappear...");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(bySpinner));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(bySpinner));
        } catch (TimeoutException e) {
            logger.debug("No spinner is displayed");
        }
        logger.debug("...Spinner disappeared");
    }

    public void untilNoInnerLoading() {
        logger.debug("Wait for Loading to disappear...");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(byInnerLoading));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(byInnerLoading));
        } catch (TimeoutException e) {
            logger.debug("No loading is displayed");
        }
        logger.debug("...Loading disappeared");
    }
}
