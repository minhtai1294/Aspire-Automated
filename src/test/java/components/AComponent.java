package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AComponent<T extends AComponent> {

    protected WebDriver     driver;
    protected WebDriverWait wait;
    protected Logger        logger;
    protected By            locator;


    public AComponent(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.driver = driver;
        this.logger = logger;
        this.wait   = wait;
        locator     = By.xpath("");
    }

    public T withLocator(By byLocator) {
        this.locator = byLocator;
        return (T) this;
    }

    public T untilPresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return (T) this;
    }

    public T untilClickable() {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return (T) this;
    }

    public T untilVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return (T) this;
    }

    public T untilInVisible() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        return (T) this;
    }

}
