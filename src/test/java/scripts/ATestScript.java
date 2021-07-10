package scripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import tools.DriverFactory;
import tools.helpers.TestStep;

public class ATestScript {

    protected WebDriver     driver;
    protected WebDriverWait wait;
    protected Logger        logger;
    protected TestStep      step;


    @BeforeMethod
    public void ATestScriptInit() throws Exception {
        initTest();
    }


    public void initTest() throws Exception {

        // init step
        step = new TestStep();

        // get driver from driver factory
        DriverFactory _driverFactory = new DriverFactory();
        driver = _driverFactory.getWebDriver("Chrome");

        // init wait and logger
        wait = new WebDriverWait(driver, _driverFactory.getWait());
        logger = LogManager.getLogger();

        // open browser with url
        driver.manage().deleteAllCookies();
        String _urlAspire = "https://feature-qa-automation.customer-frontend.staging.aspireapp.com/sg/";
        driver.get(_urlAspire);


    }

}
