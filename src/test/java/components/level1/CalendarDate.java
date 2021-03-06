package components.level1;

import components.AComponent;
import components.level2.Button;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;

import static tools.Constants.Indent.LVL_ONE;

public class CalendarDate extends AComponent<CalendarDate> {

    Button btn;

    public CalendarDate(WebDriver driver, WebDriverWait wait, Logger logger) {
        super(driver, wait, logger);
        btn = new Button(driver, wait, logger);
    }

    // Defined locators -------
    String strCalendarInputField = "//div[@class='aspire-label__text'][normalize-space() = '%s']/following::label[contains(@class, 'q-input')]/div";
    String strCalendarContent    = "//div[@class='q-date__content col relative-position']/div[@class='q-date__view q-date__calendar']";
    String strMonthClickToOpen   = strCalendarContent.concat("//div[@class='relative-position overflow-hidden flex flex-center col']");
    String strMonthSelect        = "//div[@class='q-date__months-item flex flex-center']//span[@class='block'][normalize-space() = '%s']";
    String strYearClickToOpen    = strCalendarContent.concat("//div[@class='relative-position overflow-hidden flex flex-center']");
    String strYearSelect         = "//div[@class='q-date__years-item flex flex-center']//span[@class='block'][normalize-space() = '%s']";
    String strDaySelect          = "//div[@class='q-date__calendar-item q-date__calendar-item--in']//span[@class='block'][normalize-space() = '%s']";
    By     byButtonPrevious      = By.xpath("//div[@class='col-auto'][1]");
    By     byButtonNext          = By.xpath("//div[@class='col-auto'][2]");

    //-------- End of defined locators

    public CalendarDate openCalendarField(String strCalendarLabel) {
        logger.debug(LVL_ONE.concat("[CalendarDate] Open calendar field name: " + strCalendarLabel));
        btn.withLocator(By.xpath(String.format(strCalendarInputField, strCalendarLabel))).click();
        btn.withLocator(By.xpath(strCalendarContent)).untilVisible();
        return this;
    }

    public CalendarDate selectMonth(String strMonth) {
        logger.debug(LVL_ONE.concat("[CalendarDate] Select month: " + strMonth));
        btn.withLocator(By.xpath(strMonthClickToOpen)).click();
        btn.withLocator(By.xpath(String.format(strMonthSelect, strMonth))).click();
        btn.untilInVisible();
        return this;
    }

    public CalendarDate selectYear(String strYear) {
        logger.debug(LVL_ONE.concat("[CalendarDate] Select year: " + strYear));
        btn.withLocator(By.xpath(strYearClickToOpen)).click();
        By      _byYearSelect   = By.xpath(String.format(strYearSelect, strYear));
        Integer _intCurrentYear = Calendar.getInstance().get(Calendar.YEAR);
        while (driver.findElements(_byYearSelect).isEmpty()) {
            if (Integer.valueOf(strYear) > _intCurrentYear) {
                btn.withLocator(byButtonNext).click();
            } else btn.withLocator(byButtonPrevious).click();
        }
        btn.withLocator(By.xpath(String.format(strYearSelect, strYear))).click();
        btn.untilInVisible();
        return this;

    }

    public void selectDay(String strDay) {
        logger.debug(LVL_ONE.concat("[CalendarDate] Select day: " + strDay));
        btn.withLocator(By.xpath(String.format(strDaySelect, strDay))).click();
        btn.untilInVisible();
    }


}
