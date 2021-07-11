package scripts.testsuites.registerpagetest;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import poms.pageregister.PageAdditionalDetail;
import poms.pageregister.PageBusinessDetail;
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

public class BusinessPageTest extends ATestScript {

    @Test(dataProvider = "dataProvider")
    public void testBusinessDetailPage(Map<String, Object> testCaseData) {
        // init Page objects/components
        PageRegister         _pRegister         = new PageRegister(driver, wait, logger);
        PageAdditionalDetail _pAdditionalDetail = new PageAdditionalDetail(driver, wait, logger);
        PageBusinessDetail   _pBusinessDetail   = new PageBusinessDetail(driver, wait, logger);

        // prepare data
        String _strRandomTime               = new SimpleDateFormat("ddHHmmss").format(Calendar.getInstance().getTime());
        String _uniqueID                    = UUID.randomUUID().toString();
        long   _intRandom                   = ThreadLocalRandom.current().nextLong();
        String _strRandom                   = Long.toString(_intRandom).substring(1, 10);
        String _strUserID                   = ((String) testCaseData.get("userid"));
        String _strEmailDomain              = ((String) testCaseData.get("emailDomain"));
        String _strEmail                    = ((String) testCaseData.get("email")).concat(_uniqueID).concat(_strEmailDomain);
        String _strWhereDidYouHearAbUs      = ((String) testCaseData.get("whereDidYouHearAbUs"));
        String _strMobile                   = _strRandom;
        String _strMobileOPTNumber          = (String) testCaseData.get("mobileOTPNumber");
        String _strBusinessRole             = (String) testCaseData.get("businessRole");
        String _strSolution                 = (String) testCaseData.get("solution");
        String _strRegistrationMethod       = (String) testCaseData.get("registrationMethod");
        String _strDateOfBirth              = (String) testCaseData.get("dateOfBirth");
        String _strNationality              = (String) testCaseData.get("nationality");
        String _strGender                   = (String) testCaseData.get("gender");
        String _strEmailOPTNumber           = (String) testCaseData.get("emailOTPNumber");
        String _strBusinessLegalName        = (String) testCaseData.get("businessLegalName");
        String _strEntityCategory           = (String) testCaseData.get("entityCategory");
        String _strEntityType               = (String) testCaseData.get("entityType");
        String _strRegistrationNumber       = _strRandomTime.concat("T");
        String _strIndustry                 = (String) testCaseData.get("industry");
        String _strSubIndustry              = (String) testCaseData.get("subIndustry");
        String _strBusinessActivity         = (String) testCaseData.get("businessActivity");
        String _strBusinessProvide          = (String) testCaseData.get("businessProvide");
        String _strLiveWebSite              = (String) testCaseData.get("liveWebSite");
        String _strNumberOfEmployees        = (String) testCaseData.get("numberOfEmployees");
        String _strAnnualTurnover           = (String) testCaseData.get("annualTurnover");
        String _strShareholdersQualified    = (String) testCaseData.get("shareholdersQualified");
        String _strMoreThan10OrdinaryShares = (String) testCaseData.get("moreThan10OrdinaryShares");

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

            logger.info("Precondition step {}: Verify Choose a registration method page is loaded", step.next());
            Assert.assertTrue(_pAdditionalDetail.isTextVisibleOnPage("Choose a registration method"), "Failed: Choose a registration method page is not loaded");

            logger.info("Precondition step {}: Select registration method", step.next());
            _pAdditionalDetail.selectRegistrationMethod(_strRegistrationMethod);

            logger.info("Precondition step {}: Verify Personal Information page is loaded", step.next());
            Assert.assertTrue(_pAdditionalDetail.isTextVisibleOnPage("Personal Information"), "Failed: Personal Details page is not loaded");

            logger.info("Precondition step {}: Select date of birth", step.next());
            _pAdditionalDetail.selectDateOfBirth(_strDateOfBirth);

            logger.info("Precondition step {}: Select nationality", step.next());
            _pAdditionalDetail.selectNationality(_strNationality);

            logger.info("Precondition step {}: Select gender", step.next());
            _pAdditionalDetail.selectGender(_strGender);

            logger.info("Precondition step {}: Press Submit", step.next());
            _pAdditionalDetail.submitPersonalDetail();

            logger.info("Precondition step {}: Verify Enter email OTP page is loaded", step.next());
            Assert.assertTrue(_pAdditionalDetail.isTextVisibleOnPage("Enter email OTP"), "Failed: Enter email OTP page is not loaded");

            logger.info("Precondition step {}: Input email OTP", step.next());
            _pAdditionalDetail.inputOTPNumber(_strEmailOPTNumber);

            logger.info("Precondition step {}: Verify Business Information page is loaded", step.next());
            Assert.assertTrue(_pAdditionalDetail.isTextVisibleOnPage("Business Information"), "Failed: Business Information page is not loaded");

        } catch (Exception e) {
            logger.error("Skipped in precondition: " + e.getMessage());
            throw new SkipException(e.getMessage());
        }

        // start test steps
        try {


            logger.info("Step {}: Input Business legal name", step.start());
            _pBusinessDetail.inputBusinessLegalName(_strBusinessLegalName);

            logger.info("Step {}: Select Entity Category", step.next());
            _pBusinessDetail.selectEntityCategory(_strEntityCategory);

            logger.info("Step {}: Select Entity type", step.next());
            _pBusinessDetail.selectEntityType(_strEntityType);

            logger.info("Step {}: Select Business registration number (UEN)", step.next());
            _pBusinessDetail.inputBusinessRegistrationNumberUEN(_strRegistrationNumber);

            logger.info("Step {}: Select Industry", step.next());
            _pBusinessDetail.selectIndustry(_strIndustry);

            logger.info("Step {}: Select Sub-Industry", step.next());
            _pBusinessDetail.selectSubIndustry(_strSubIndustry);

            logger.info("Step {}: Press Continue", step.next());
            _pBusinessDetail.clickContinue();

            logger.info("Step {}: Input Business activity", step.next());
            _pBusinessDetail.inputBusinessBusinessActivity(_strBusinessActivity);

            logger.info("Step {}: Input example of product/services provided", step.next());
            _pBusinessDetail.inputExampleOfProductServicesProvide(_strBusinessProvide);

            logger.info("Step {}: Input Live business website", step.next());
            _pBusinessDetail.inputLiveBusinessWebsite(_strLiveWebSite);

            logger.info("Step {}: Select Number of employees", step.next());
            _pBusinessDetail.selectNumberOfEmployees(_strNumberOfEmployees);

            logger.info("Step {}: Select Annual turnover", step.next());
            _pBusinessDetail.selectAnnualTurnover(_strAnnualTurnover);

            logger.info("Step {}: Submit business detail", step.next());
            _pBusinessDetail.submitBusinessDetail();

            logger.info("Step {}: Select Does any of your company directors or shareholders qualify as Politically ...", step.next());
            _pBusinessDetail.selectAnyPoliticallyExposedPersonQualified(_strShareholdersQualified);

            logger.info("Step {}: Select Does your company have corporate shareholders with more than 10% ordinary shares?", step.next());
            _pBusinessDetail.selectAnyShareholdersWithMoreThan10OrdinaryShares(_strMoreThan10OrdinaryShares);

            logger.info("Step {}: Press Continue", step.next());
            _pBusinessDetail.clickContinue();

            logger.info("Step {}: Verify Complete your verification page is loaded", step.next());
            Assert.assertTrue(_pBusinessDetail.isTextVisibleOnPage("Complete your verification"), "Failed: Complete your verification page is not loaded");


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
