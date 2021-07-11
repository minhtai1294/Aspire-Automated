package scripts.testsuites.registerpagetest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import poms.pageregister.PageBusinessDetail;
import poms.pageregister.PageAdditionalDetail;
import poms.pageregister.PageIdentityDetail;
import poms.pageregister.PageRegister;
import tools.DataParser;
import scripts.ATestScript;

import java.io.*;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RegisterPageTest extends ATestScript {


    @Test(dataProvider = "dataProvider")
    public void testFirstRegisterPage(Map<String, Object> testCaseData) {
        // init Page objects/components
        PageRegister _pRegister = new PageRegister(driver, wait, logger);

        // prepare data
        String _uniqueID               = UUID.randomUUID().toString();
        long   _intRandom              = ThreadLocalRandom.current().nextLong();
        String _strRandom              = Long.toString(_intRandom).substring(1, 10);
        String _strUserID              = ((String) testCaseData.get("userid"));
        String _strEmailDomain         = ((String) testCaseData.get("emailDomain"));
        String _strEmail               = ((String) testCaseData.get("email")).concat(_uniqueID).concat(_strEmailDomain);
        String _strWhereDidYouHearAbUs = ((String) testCaseData.get("whereDidYouHearAbUs"));
        String _strMobile              = _strRandom;
        String _strMobileOPTNumber     = (String) testCaseData.get("mobileOTPNumber");

        // start test steps
        try {
            logger.info("Step {}: click registration link", step.start());
            _pRegister.clickRegisterLink();
            _pRegister.untilPageIsCompletedLoaded();

            logger.info("Step {}: input user name", step.next());
            _pRegister.inputFullName(_strUserID);

            logger.info("Step {}: input email address", step.next());
            _pRegister.inputEmailAddress(_strEmail);

            logger.info("Step {}: input mobile", step.next());
            _pRegister.inputMobileNumber(_strMobile);

            logger.info("Step {}: Input Where did you hear about us", step.next());
            _pRegister.selectWhereDidYouHereAboutUs(_strWhereDidYouHearAbUs);

            logger.info("Step {}: Agree Term and Policy", step.next());
            _pRegister.checkAgreeTerms();

            logger.info("Step {}: Click Continue", step.next());
            _pRegister.clickContinue();

            logger.info("Step {}: Verify next page is loaded", step.next());
            Assert.assertTrue(_pRegister.isTextVisibleOnPage("Enter phone OTP"), "Failed: Enter phone OTP is not loaded");

            logger.info("Step {}: Input phone OTP number", step.next());
            _pRegister.inputOTPNumber(_strMobileOPTNumber);

            logger.info("Step {}: Press Continue", step.next());
            _pRegister.clickContinue();

            logger.info("Step {}: Verify Tell us more about yourself page is loaded", step.next());
            Assert.assertTrue(_pRegister.isTextVisibleOnPage("Tell us more about yourself"), "Failed: Tell us more about yourself page is not loaded");


        } catch (Exception | AssertionError e) {
            logger.error(e.getMessage());
            throw e;
        }

    }


    @DataProvider(name = "dataProvider")
    public Object[] findData(Method m) throws IOException {

        String _testDataFilePath = String.format(DataParser._filePath, m.getName());

        FileInputStream _testData = new FileInputStream(_testDataFilePath);

        DataParser dataParser = new DataParser();

        return dataParser.parseExcel(_testData);
    }

}
