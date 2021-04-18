package poms;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class APageObject<T extends APageObject> {

    protected WebDriver     driver;
    protected WebDriverWait wait;
    protected Logger        logger;

    public APageObject(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        this.wait   = wait;
    }

    String strTextOnPage = "//*[contains(text(), '%s')]";

    public T untilPageIsCompletedLoaded() {
        return (T) this;
    }

    public boolean isTextVisibleOnPage(String text) {
        untilPageIsCompletedLoaded();
        return driver.findElements(By.xpath(String.format(strTextOnPage, text))).size() > 0;
    }
}
