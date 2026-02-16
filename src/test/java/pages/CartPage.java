package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    By cartIcon = By.className("shopping_cart_link");
    By cartItemName = By.className("inventory_item_name");

    public void addProductToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void clickCart() {
        driver.findElement(cartIcon).click();
    }

    public String getCartItemName() {
        return driver.findElement(cartItemName).getText();
    }
}
