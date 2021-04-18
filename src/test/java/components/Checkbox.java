package components;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkbox extends AComponent<Checkbox>{

    public Checkbox(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
    }


}
