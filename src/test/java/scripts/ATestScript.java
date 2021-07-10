package scripts;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import tool.Constants;
import tool.DriverFactory;

public class ATestScript {

    WebDriver     driver;
    WebDriverWait wait;
    Logger        logger = LogManager.getLogger(getClass());


    @BeforeMethod
    public void ATestScriptInit() throws Exception {
        initTest();
    }


    public void initTest() throws Exception {

        // get driver from driver factory
        DriverFactory _driverFactory = new DriverFactory();
        driver = _driverFactory.getWebDriver("Chrome");

        // init wait and logger
        wait   = new WebDriverWait(driver, _driverFactory.getWait());
        System.out.println(getClass());
        logger = LogManager.getLogger(getClass());

        // open browser with url
        driver.manage().deleteAllCookies();
        String _urlAspire = "https://feature-qa-automation.customer-frontend.staging.aspireapp.com/sg/";
        driver.get(_urlAspire);


    }

}
