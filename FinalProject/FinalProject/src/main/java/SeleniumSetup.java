import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SeleniumSetup {

    private static WebDriver driver;

    private static WebDriverWait wait;


    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/");
        wait = new WebDriverWait(driver, 60);
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static String randomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static void typeIn(String elementXpath, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))).sendKeys(text);
    }

    public static void clickOn(String elementXpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))).click();
    }

    public static String getText(String elementXpath) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))).getText();
    }

    public static void selectItemsByVisibleText(String elementName, String visibleText) {
        new Select (wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementName)))).selectByVisibleText(visibleText);
    }

    public static void typeInTo(String elementName, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementName))).sendKeys(text);
    }

    public static void checkboxClick(String elementName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementName))).click();
    }


    public static String createAnAccount() {
        clickOn ("/html/body/div[2]/header/div[1]/div/ul/li[3]/a");

        typeIn("//*[@id=\"firstname\"]","Tamara");

        typeIn("//*[@id=\"lastname\"]", "Zabaznoska");

        typeIn("//*[@id=\"email_address\"]", randomEmail());

        typeIn("//*[@id=\"password\"]", "tamara.123");

        typeIn("//*[@id=\"password-confirmation\"]", "tamara.123");

        clickOn("//*[@id=\"form-validate\"]/div/div[1]/button");

        return getText("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div");
    }

    public static String addTwoProductsToCart() {
        clickOn("//*[@id=\"ui-id-4\"]/span[2]");

        clickOn("//*[@id=\"maincontent\"]/div[4]/div[2]/div[2]/div/ul[1]/li[4]/a");

        clickOn("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[2]/div/a/span/span/img");

        clickOn("//*[@id=\"option-label-size-143-item-168\"]");

        clickOn("//*[@id=\"option-label-color-93-item-49\"]");

        clickOn("//*[@id=\"product-addtocart-button\"]");

        clickOn("/html/body/div[2]/div[2]/ul/li[2]/a");

        clickOn("//*[@id=\"maincontent\"]/div[4]/div[2]/div[2]/div/ul[2]/li[2]/a");

        clickOn("//*[@id=\"maincontent\"]/div[3]/div[1]/div[3]/ol/li[3]/div/a/span/span/img");

        clickOn("//*[@id=\"option-label-size-143-item-171\"]");

        clickOn("//*[@id=\"option-label-color-93-item-49\"]");

        clickOn("//*[@id=\"product-addtocart-button\"]");

        clickOn("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div[1]/div/a");

        clickOn("//*[@id=\"maincontent\"]/div[3]/div/div[2]/div[1]/ul/li[1]/button");

        return getText("//*[@id=\"shipping\"]/div[1]");
    }

    public static String checkOutInformation() {
        typeInTo("street[0]", "NNBorce No80");

        typeInTo("city", "Skopje");

        typeInTo("postcode", "1000");

        selectItemsByVisibleText("country_id", "North Macedonia");

        typeInTo("telephone", "+38977637309");

        return getText("//*[@id=\"opc-shipping_method\"]/div/div[1]");
    }

    public static String assertConfirmationMessage() {
        checkboxClick("ko_unique_3");

        clickOn("//*[@id=\"shipping-method-buttons-container\"]/div/button");

        checkboxClick("billing-address-same-as-shipping");

        checkboxClick("billing-address-same-as-shipping");

        clickOn("//*[@id=\"checkout-payment-method-load\"]/div/div/div[2]/div[2]/div[4]/div/button");

        return getText("//*[@id=\"maincontent\"]/div[3]/div/div[2]/p[2]");
    }


    public static void end() {
        driver.quit();
    }

}
