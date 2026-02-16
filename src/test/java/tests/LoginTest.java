package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.LoginPage;

public class LoginTest {

    WebDriver driver;
    LoginPage login;

    @BeforeClass
    public void setup() {
        System.out.println("Launching Chrome Browser and Opening SauceDemo Website...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        login = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void positiveLoginTest() {

        System.out.println("Executing Positive Login Test...");

        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        String actualTitle = login.getProductsTitle();
        Assert.assertEquals(actualTitle, "Products");

        System.out.println("Positive Login Test PASSED");
    }

    @Test(priority = 2)
    public void negativeLoginTest() {

        System.out.println("Executing Negative Login Test...");

        driver.get("https://www.saucedemo.com/");

        login.enterUsername("wrong_user");
        login.enterPassword("wrong_password");
        login.clickLogin();

        String error = login.getErrorMessage();
        Assert.assertTrue(error.contains("Username and password do not match"));

        System.out.println("Negative Login Test PASSED");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Closing Browser...");
        driver.quit();
    }
}
