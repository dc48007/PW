import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.util.Assert;
import com.companyname.BaseSetUp;
import com.companyname.listerners.ExtentTestNGIReporterListener;
import com.companyname.listerners.TestListener;
import com.companyname.reportmanager.Logger;
@Listeners(ExtentTestNGIReporterListener.class)
public class TestSample extends BaseSetUp {

    @Test
    public void testLogging() {
       org.testng.Assert.assertTrue(true);
    }

    @Test
    public void testReport() {
        org.testng.Assert.assertTrue(true);
    }
    @Test
    public void TestCase3() {
        Logger.getTest().log(Status.PASS, "this is passed");
    }

}
