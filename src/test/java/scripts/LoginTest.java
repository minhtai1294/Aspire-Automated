package scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LoginTest extends ATestScript {



    @Test (dataProvider = "dataProvider")
    public void TestLogin(HashMap <String, String> testData){
        // init
        System.out.println(testData.get("Full Name as per ID") + " A");
        logger.info("Full Name as per ID");
    }

    @Test (dataProvider = "dataProvider")
    public void TestLogin1(HashMap <String, String> testData){
        // init
        System.out.println(testData.get("Full Name as per ID") + " B");
        logger.info("Full Name as per ID");
    }


    @DataProvider (name = "dataProvider")
    public Object[][] findData(){

        HashMap <String, String> _testData = new HashMap<>();
        _testData.put("Full Name as per ID", "Tai");

        Object [][] testData = new Object[1][1];
        testData[0][0] = _testData;

        return testData;
    }

}
