package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class Products extends TestBase {

    public Products(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Header elements
    @FindBy(className = "app_logo")
    private WebElement appLogo;
    
    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;
    
    @FindBy(id = "shopping_cart_container")
    private WebElement cartBadge;
    
    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;
    
    // Sort dropdown
    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;
    
    // Product items
    @FindBy(className = "inventory_item")
    private List<WebElement> productItems;
    
    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;
    
    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;
    
    @FindBy(xpath = "//button[contains(@class,'btn_inventory')]")
    private List<WebElement> addToCartButtons;
    
    // Footer
    @FindBy(className = "footer_copy")
    private WebElement footerText;

    @FindBy(id = "add-to-cart")
    private WebElement addToCart;

    @FindBy(className = "shopping_cart_link")
    private WebElement goToCart;

    // Getter methods
    public String getAppLogoText() {
        waitElementToBeVisible(appLogo);
        return appLogo.getText();
    }
    
    public int getProductCount() {
        return productItems.size();
    }
    
    public String getProductName(int index) {
        return productNames.get(index).getText();
    }
    
    public String getProductPrice(int index) {
        return productPrices.get(index).getText();
    }
    
    public void addProductToCart(int index) {
        addToCartButtons.get(index).click();
    }
    
    public void openCart() {
        cartIcon.click();
    }
    
    public void openMenu() {
        menuButton.click();
    }
    
    public void sortProducts(String sortOption) {
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText(sortOption);
    }
    
    public String getCartItemCount() {
        try {
            return cartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }
    
    public boolean clickProductByName(String productName) {
        for (WebElement product : productNames) {
            if (product.getText().equalsIgnoreCase(productName)) {
                product.click();
                return true;
            }
        }
        return false;
    }

public void addProductToCart(String productName) {
    clickProductByName(productName);
  //  addToCart.click();
    click(addToCart);
    // goToCart.click();
    click(goToCart);



}


}
