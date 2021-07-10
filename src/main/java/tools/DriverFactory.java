package tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private String PlatForm;
    private String Browser;
    private long   Wait;

    {
        PlatForm = System.getProperty(Constants.SystemProperties.PLATFORM) != null ? System.getProperty(Constants.SystemProperties.PLATFORM) : "WINDOWS";
        Browser  = System.getProperty(Constants.SystemProperties.BROWSER_TYPE) != null ? System.getProperty(Constants.SystemProperties.PLATFORM) : "Chrome";
        Wait     = 10;
    }

    public static WebDriver getWebDriver(String driverType) throws Exception {
        switch (driverType) {
            case Constants.BrowserType.CHROME:
                return new ChromeDriver();
            case Constants.BrowserType.FIREFOX:
                return new FirefoxDriver();
            case Constants.BrowserType.EDGE:
                return new EdgeDriver();
            default:
                throw new Exception("Input browser does not match any type");
        }
    }


    public String getPlatForm() {
        return PlatForm;
    }

    public void setPlatForm(String platForm) {
        PlatForm = platForm;
    }

    public String getBrowser() {
        return Browser;
    }

    public void setBrowser(String browser) {
        Browser = browser;
    }

    public long getWait() {
        return Wait;
    }

    public void setWait(long wait) {
        Wait = wait;
    }
}
