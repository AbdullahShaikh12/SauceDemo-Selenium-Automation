package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.LoginPage;
import pages.CartPage;

public class SauceDemoTest {

    WebDriver driver;
    LoginPage login;
    CartPage cart;

    // 🟢 NEW – Browser launches before EACH test
    @BeforeMethod
    public void setup() {

        System.out.println("Launching Chrome Browser...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        login = new LoginPage(driver);
        cart = new CartPage(driver);
    }

    // 🔵 SAME – Positive Login
    @Test
    public void positiveLoginTest() {

        System.out.println("Executing Positive Login Test...");

        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        String title = login.getProductsTitle();
        Assert.assertEquals(title, "Products");

        System.out.println("Positive Login Test PASSED");
    }

    // 🔵 SAME – Negative Login (Improved Assertion)
    @Test
    public void negativeLoginTest() {

        System.out.println("Executing Negative Login Test...");

        login.enterUsername("wrong_user");
        login.enterPassword("wrong_password");
        login.clickLogin();

        String error = login.getErrorMessage();
        Assert.assertTrue(error.toLowerCase().contains("epic sadface"));

        System.out.println("Negative Login Test PASSED");
    }

    // 🟢 NEW – Add To Cart Feature
    @Test
    public void addToCartTest() {

        System.out.println("Executing Add To Cart Test...");

        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLogin();

        cart.addProductToCart();
        cart.clickCart();

        String itemName = cart.getCartItemName();
        Assert.assertEquals(itemName, "Sauce Labs Backpack");

        System.out.println("Add To Cart Test PASSED");
    }

    // 🟢 NEW – Browser closes after EACH test
    @AfterMethod
    public void tearDown() {

        System.out.println("Closing Browser...\n");
        driver.quit();
    }
}
