import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Tests {

    @BeforeClass
    public void beforeClass() {
        SeleniumSetup.setup();
    }

    @Test (priority = 10)
    public void testForVerifyingLandingOnHomePage() {
        Assert.assertEquals(SeleniumSetup.getCurrentUrl(), "https://magento.softwaretestingboard.com/");
    }

    @Test (priority = 20)
    public void testForVerifyingUserIsAuthenticated() {
        Assert.assertEquals(SeleniumSetup.createAnAccount(), "Thank you for registering with Main Website Store.");
    }

    @Test (priority = 30)
    public void testForVerifyingTwoItemsAreSuccessfullyAddedToCart() {
        Assert.assertEquals(SeleniumSetup.addTwoProductsToCart(), "Shipping Address");
    }

    @AfterClass
    public void afterClass() {
       SeleniumSetup.end();
   }

}
