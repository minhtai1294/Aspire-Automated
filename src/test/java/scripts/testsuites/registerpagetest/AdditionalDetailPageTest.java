package scripts.testsuites.registerpagetest;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import poms.pageregister.PageAdditionalDetail;
import poms.pageregister.PageRegister;
import scripts.ATestScript;
import tools.DataParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class AdditionalDetailPageTest extends ATestScript {

    @Test(dataProvider = "dataProvider")
    public void testAdditionalDetailsPage(Map<String, Object> testCaseData) {
        // init Page objects/components
        PageRegister         _pRegister         = new PageRegister(driver, wait, logger);
        PageAdditionalDetail _pAdditionalDetail = new PageAdditionalDetail(driver, wait, logger);


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
        String _strBusinessRole        = (String) testCaseData.get("businessRole");
        String _strSolution            = (String) testCaseData.get("solution");
        String _strRegistrationMethod  = (String) testCaseData.get("registrationMethod");
        String _strDateOfBirth         = (String) testCaseData.get("dateOfBirth");
        String _strNationality         = (String) testCaseData.get("nationality");
        String _strGender              = (String) testCaseData.get("gender");
        String _strEmailOPTNumber      = (String) testCaseData.get("emailOTPNumber");

        // precondition steps
        try {

            logger.info("Precondition step {}: click registration link", step.start());
            _pRegister.clickRegisterLink();
            _pRegister.untilPageIsCompletedLoaded();

            logger.info("Precondition step {}: input user name", step.next());
            _pRegister.inputFullName(_strUserID);

            logger.info("Precondition step {}: input email address", step.next());
            _pRegister.inputEmailAddress(_strEmail);

            logger.info("Precondition step {}: input mobile", step.next());
            _pRegister.inputMobileNumber(_strMobile);

            logger.info("Precondition step {}: Input Where did you hear about us", step.next());
            _pRegister.selectWhereDidYouHereAboutUs(_strWhereDidYouHearAbUs);

            logger.info("Precondition step {}: Agree Term and Policy", step.next());
            _pRegister.checkAgreeTerms();

            logger.info("Precondition step {}: Click Continue", step.next());
            _pRegister.clickContinue();

            logger.info("Precondition step {}: Verify next page is loaded", step.next());
            Assert.assertTrue(_pRegister.isTextVisibleOnPage("Enter phone OTP"), "Failed: Enter phone OTP is not loaded");

            logger.info("Precondition step {}: Input phone OTP number", step.next());
            _pRegister.inputOTPNumber(_strMobileOPTNumber);

            logger.info("Precondition step {}: Press Continue", step.next());
            _pRegister.clickContinue();

            logger.info("Precondition step {}: Verify Tell us more about yourself page is loaded", step.next());
            Assert.assertTrue(_pRegister.isTextVisibleOnPage("Tell us more about yourself"), "Failed: Tell us more about yourself page is not loaded");

            logger.info("Precondition step {}: Select business role", step.next());
            _pRegister.selectBusinessRole(_strBusinessRole);

            logger.info("Precondition step {}: Verify Additional details page is loaded", step.next());
            Assert.assertTrue(_pAdditionalDetail.isTextVisibleOnPage("Additional details"), "Failed: Additional details page is not loaded");

            logger.info("Precondition step {}: Select solutions", step.next());
            _pAdditionalDetail.selectApplicableOptions(Arrays.asList(_strSolution));

            logger.info("Precondition step {}: Press Continue", step.next());
            _pRegister.clickContinue();

        } catch (Exception e) {
            logger.error("Skipped in precondition: " + e.getMessage());
            throw new SkipException(e.getMessage());
        }


        // start test steps
        try {
            logger.info("Step {}: Verify Choose a registration method page is loaded", step.start());
            Assert.assertTrue(_pAdditionalDetail.isTextVisibleOnPage("Choose a registration method"), "Failed: Choose a registration method page is not loaded");

            logger.info("Step {}: Select registration method", step.next());
            _pAdditionalDetail.selectRegistrationMethod(_strRegistrationMethod);

            logger.info("Step {}: Verify Personal Information page is loaded", step.next());
            Assert.assertTrue(_pAdditionalDetail.isTextVisibleOnPage("Personal Information"), "Failed: Personal Details page is not loaded");

            logger.info("Step {}: Select date of birth", step.next());
            _pAdditionalDetail.selectDateOfBirth(_strDateOfBirth);

            logger.info("Step {}: Select nationality", step.next());
            _pAdditionalDetail.selectNationality(_strNationality);

            logger.info("Step {}: Select gender", step.next());
            _pAdditionalDetail.selectGender(_strGender);

            logger.info("Step {}: Press Submit", step.next());
            _pAdditionalDetail.submitPersonalDetail();

            logger.info("Step {}: Verify Enter email OTP page is loaded", step.next());
            Assert.assertTrue(_pAdditionalDetail.isTextVisibleOnPage("Enter email OTP"), "Failed: Enter email OTP page is not loaded");

            logger.info("Step {}: Input email OTP", step.next());
            _pAdditionalDetail.inputOTPNumber(_strEmailOPTNumber);

            logger.info("Step {}: Verify Business Information page is loaded", step.next());
            Assert.assertTrue(_pAdditionalDetail.isTextVisibleOnPage("Business Information"), "Failed: Business Information page is not loaded");


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
