import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestEndToEnd {

    @BeforeClass
    public void beforeClass() {
        SeleniumSetup.setup();
    }

    @Test(priority = 40)
    public void endToEndTestScenario() {
        SeleniumSetup.createAnAccount();
        SeleniumSetup.addTwoProductsToCart();
        SeleniumSetup.checkOutInformation();
        Assert.assertEquals(SeleniumSetup.assertConfirmationMessage(), "We'll email you an order confirmation with details and tracking info.");
    }

    @AfterClass
    public void afterClass() {
        SeleniumSetup.end();
    }

}
