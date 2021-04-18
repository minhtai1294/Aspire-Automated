package scripts;

import components.Button;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import poms.PageLogin;

import java.util.HashMap;

public class LoginTest extends ATestScript {


    @Test(dataProvider = "dataProvider")
    public void TestLogin(HashMap<String, String> testData) {
        // init Page object
        PageLogin _pLogin = new PageLogin(driver, wait, logger);
        Button _btn = new Button(driver, wait, logger);

        // data preparation
        String _strUserFullName = testData.get("fullNameAsPerID");
        String _strEmailAddress = testData.get("emailAddress");


        //pre-condition block
        try {

        } catch (Exception e) {

        }

        // test steps block
        try {
            logger.info("Step 1: click registration link");
            _pLogin.clickRegisterLink();

            logger.info("Step 2: input user name");
            _pLogin.inputFullName(_strUserFullName);

            logger.info("Step 3: input email address");
            _pLogin.inputEmailAddress(_strEmailAddress);

            logger.info("Step 4: Agree Term and Policy");
            _pLogin.checkAgreeTerms();

            logger.info("Step 5: Click Continue");
            _pLogin.clickContinue();

            logger.info("Step 6: Verify next page is loaded");
            Assert.assertTrue(_pLogin.isTextVisibleOnPage("Please invite your director to complete registration"), "Failed: Invite page is not loaded");

        } catch (Exception | AssertionError e) {
            logger.info(e.getMessage());
            throw e;
        }

        _pLogin.clickRegisterLink();

        System.out.println(testData.get("fullNameAsPerID"));

        logger.info("Full Name as per ID");
    }


    @DataProvider(name = "dataProvider")
    public Object[][] findData() {

        HashMap<String, String> _testData = new HashMap<>();
        _testData.put("fullNameAsPerID", "Tai");
        _testData.put("emailAddress", "minhtai1294@gmail.com");


        Object[][] testData = new Object[1][1];
        testData[0][0] = _testData;

        return testData;
    }

}
