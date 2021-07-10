package scripts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import poms.pageregister.PageBusinessDetail;
import poms.pageregister.PageAdditionalDetail;
import poms.pageregister.PageIdentityDetail;
import poms.pageregister.PageRegister;
import dataparser.DataParser;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RegisterTest extends ATestScript {


    @Test(dataProvider = "dataProvider")
    public void testRegister(Map<String, Object> testCaseData) {
        // init Page objects/components
        PageRegister         _pRegister         = new PageRegister(driver, wait, logger);
        PageAdditionalDetail _pAdditionalDetail = new PageAdditionalDetail(driver, wait, logger);
        PageBusinessDetail   _pBusinessDetail   = new PageBusinessDetail(driver, wait, logger);
        PageIdentityDetail   _pIdentityDetail   = new PageIdentityDetail(driver, wait, logger);

        // prepare data
        String _uniqueID                    = UUID.randomUUID().toString();
        long   _intRandom                   = ThreadLocalRandom.current().nextLong();
        String _strRandom                   = Long.toString(_intRandom).substring(1, 10);
        String _strUserID                   = ((String) testCaseData.get("userid"));
        String _strEmailDomain              = ((String) testCaseData.get("emailDomain"));
        String _strEmail                    = ((String) testCaseData.get("email")).concat(_uniqueID).concat(_strEmailDomain);
        String _strWhereDidYouHearAbUs      = ((String) testCaseData.get("whereDidYouHearAbUs"));
        String _strMobile                   = _strRandom;
        String _strRoleInCompany            = (String) testCaseData.get("roleInCompany");
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
        String _strRegistrationNumber       = (String) testCaseData.get("registrationNumber");
        String _strIndustry                 = (String) testCaseData.get("industry");
        String _strSubIndustry              = (String) testCaseData.get("subIndustry");
        String _strBusinessActivity         = (String) testCaseData.get("businessActivity");
        String _strBusinessProvide          = (String) testCaseData.get("businessProvide");
        String _strLiveWebSite              = (String) testCaseData.get("liveWebSite");
        String _strNumberOfEmployees        = (String) testCaseData.get("numberOfEmployees");
        String _strAnnualTurnover           = (String) testCaseData.get("annualTurnover");
        String _strShareholdersQualified    = (String) testCaseData.get("shareholdersQualified");
        String _strMoreThan10OrdinaryShares = (String) testCaseData.get("moreThan10OrdinaryShares");
        String _strVerificationSelect       = (String) testCaseData.get("verificationSelect");
        String _strDocumentOption           = (String) testCaseData.get("documentOption");
        String _strFilePath                 = (String) testCaseData.get("filePath");


        // start test steps
        try {
            logger.info("Step 1: click registration link");
            _pRegister.clickRegisterLink();
            _pRegister.untilPageIsCompletedLoaded();

            logger.info("Step 2: input user name");
            _pRegister.inputFullName(_strUserID);

            logger.info("Step 3: input email address");
            _pRegister.inputEmailAddress(_strEmail);

            logger.info("Step 4: input mobile");
            _pRegister.inputMobileNumber(_strMobile);

            logger.info("Step 6: Input Where did you hear about us");
            _pRegister.selectWhereDidYouHereAboutUs(_strWhereDidYouHearAbUs);

            logger.info("Step 7: Agree Term and Policy");
            _pRegister.checkAgreeTerms();

            logger.info("Step 8: Click Continue");
            _pRegister.clickContinue();

            logger.info("Step 9: Verify next page is loaded");
            Assert.assertTrue(_pRegister.isTextVisibleOnPage("Enter phone OTP"), "Failed: Enter phone OTP is not loaded");

            logger.info("Step 10: Input phone OTP number");
            _pRegister.inputOTPNumber(_strMobileOPTNumber);
            _pRegister.untilNoSpinner();

            logger.info("Step 11: Verify successfully verified page is loaded");
            Assert.assertTrue(_pRegister.isTextVisibleOnPage("You have successfully verified your mobile number. You’re on to a great start!"), "Failed: Successfully verified mobile page is not loaded");

            logger.info("Step 12: Press Continue");
            _pRegister.clickContinue();

            logger.info("Step 13: Verify Tell us more about yourself page is loaded");
            Assert.assertTrue(_pRegister.isTextVisibleOnPage("Tell us more about yourself"), "Failed: Tell us more about yourself page is not loaded");

            logger.info("Step 14: Select business role");
            _pRegister.selectBusinessRole(_strBusinessRole);

            logger.info("Step 15: Select solutions");
            _pAdditionalDetail.selectApplicableOptions(Arrays.asList(_strSolution));

            logger.info("Step 16: Press Continue");
            _pRegister.clickContinue();

            logger.info("Step 17: Select registration method");
            _pAdditionalDetail.selectRegistrationMethod(_strRegistrationMethod);

            logger.info("Step 16: Select date of birth");
            _pAdditionalDetail.selectDateOfBirth(_strDateOfBirth);

            logger.info("Step 17: Select nationality");
            _pAdditionalDetail.selectNationality(_strNationality);

            logger.info("Step 18: Select gender");
            _pAdditionalDetail.selectGender(_strGender);

            logger.info("Step 19: Press Submit");
            _pAdditionalDetail.submitPersonalDetail();

            logger.info("Step 20: Input email OTP");
            _pAdditionalDetail.inputOTPNumber(_strEmailOPTNumber);

            logger.info("Step 21: Input Business legal name");
            _pBusinessDetail.inputBusinessLegalName(_strBusinessLegalName);

            logger.info("Step 22: Select Entity Category");
            _pBusinessDetail.selectEntityCategory(_strEntityCategory);

            logger.info("Step 23: Select Entity type");
            _pBusinessDetail.selectEntityType(_strEntityType);

            logger.info("Step 24: Select Business registration number (UEN)");
            _pBusinessDetail.inputBusinessRegistrationNumberUEN(_strRegistrationNumber);

            logger.info("Step 25: Select Industry");
            _pBusinessDetail.selectIndustry(_strIndustry);

            logger.info("Step 26: Select Sub-Industry");
            _pBusinessDetail.selectSubIndustry(_strSubIndustry);

            logger.info("Step 16: Press Continue");
            _pBusinessDetail.clickContinue();

            logger.info("Step 27: Input Business activity");
            _pBusinessDetail.inputBusinessBusinessActivity(_strBusinessActivity);

            logger.info("Step 28: Input example of product/services provided");
            _pBusinessDetail.inputExampleOfProductServicesProvide(_strBusinessProvide);

            logger.info("Step 28: Input Live business website");
            _pBusinessDetail.inputLiveBusinessWebsite(_strLiveWebSite);

            logger.info("Step 29: Select Number of employees");
            _pBusinessDetail.selectNumberOfEmployees(_strNumberOfEmployees);

            logger.info("Step 30: Select Annual turnover");
            _pBusinessDetail.selectAnnualTurnover(_strAnnualTurnover);

            logger.info("Step 31: Submit business detail");
            _pBusinessDetail.submitBusinessDetail();

            logger.info("Step 31: Select Does any of your company directors or shareholders qualify as Politically ...");
            _pBusinessDetail.selectAnyPoliticallyExposedPersonQualified(_strShareholdersQualified);

            logger.info("Step 32: Select Does your company have corporate shareholders with more than 10% ordinary shares?");
            _pBusinessDetail.selectAnyShareholdersWithMoreThan10OrdinaryShares(_strMoreThan10OrdinaryShares);

            logger.info("Step 16: Press Continue");
            _pBusinessDetail.clickContinue();

            logger.info("Step 34: Press Get Started");
            _pIdentityDetail.pressGetStarted();

            logger.info("Step 34: Press Begin Verification");
            _pIdentityDetail.pressBeginVerification();

            logger.info("Step 35: Select document option");
            _pIdentityDetail.selectDocumentationOption(_strDocumentOption);

            logger.info("Step 36: Upload file");
            _pIdentityDetail.pressUploadFile();
            _pIdentityDetail.uploadFile(_strFilePath);

            logger.info("Step 37: Press Confirm");
            _pIdentityDetail.pressConfirm();


        } catch (Exception | AssertionError e) {
            logger.info(e.getMessage());
            throw e;
        }

    }


    @DataProvider(name = "dataProvider")
    public Object[] findData() throws IOException {

        String _testDataFilePath = String.format(DataParser._filePath, getClass().getSimpleName());

        FileInputStream _testData = new FileInputStream(_testDataFilePath);

        DataParser dataParser = new DataParser();

        return dataParser.parseExcel(_testData);
    }

}
