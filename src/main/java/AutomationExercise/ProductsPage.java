package AutomationExercise;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * ProductsPage - Represents the ALL PRODUCTS page
 * Handles interactions with products list and navigation to product details
 */
public class ProductsPage extends AbstractComponents {
	
	public WebDriver driver;
	
	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// All Products heading
	@FindBy(xpath="//h2[@class='title text-center']")
	WebElement allProductsHeading;
	
	// Product container - each product item
	@FindBy(xpath="//div[@class='product-image-wrapper']")
	List<WebElement> productsList;
	
	// View Product button for each product
	@FindBy(xpath="//a[contains(text(),'View Product')]")
	List<WebElement> viewProductButtons;
	
	// Product name in listing
	@FindBy(xpath="//p[@class='product-name']/a")
	List<WebElement> productNames;
	
	// Product price
	@FindBy(xpath="//span/span")
	List<WebElement> productPrices;
	
	/**
	 * Verifies that ALL PRODUCTS page is displayed
	 * @return true if page heading is visible, false otherwise
	 */
	public boolean verifyAllProductsPageDisplayed() {
		waitForElementToAppear(allProductsHeading);
		return allProductsHeading.isDisplayed();
	}
	
	/**
	 * Gets the ALL PRODUCTS heading text
	 * @return heading text
	 */
	public String getAllProductsHeadingText() {
		waitForElementToAppear(allProductsHeading);
		return allProductsHeading.getText();
	}
	
	/**
	 * Verifies that products list is visible
	 * @return true if products are displayed, false otherwise
	 */
	public boolean verifyProductsListIsVisible() {
		waitForElementToAppear(productsList.get(0));
		return productsList.size() > 0;
	}
	
	/**
	 * Gets the count of products displayed on page
	 * @return number of products
	 */
	public int getProductsCount() {
		return productsList.size();
	}
	
	/**
	 * Gets the name of product at specified index
	 * @param index - index of product
	 * @return product name
	 */
	public String getProductName(int index) {
		if (index >= 0 && index < productNames.size()) {
			return productNames.get(index).getText();
		}
		return null;
	}
	
	/**
	 * Gets the price of product at specified index
	 * @param index - index of product
	 * @return product price
	 */
	public String getProductPrice(int index) {
		if (index >= 0 && index < productPrices.size()) {
			return productPrices.get(index).getText();
		}
		return null;
	}
	
	/**
	 * Clicks 'View Product' button for the first product
	 * @return ProductDetailPage object
	 */
	public ProductDetailPage clickViewProductOfFirstProduct() {
		waitForElementToBeClickable(viewProductButtons.get(0));
		viewProductButtons.get(0).click();
		return new ProductDetailPage(driver);
	}
	
	/**
	 * Clicks 'View Product' button for product at specified index
	 * @param index - index of product
	 * @return ProductDetailPage object
	 */
	public ProductDetailPage clickViewProductAtIndex(int index) {
		if (index >= 0 && index < viewProductButtons.size()) {
			waitForElementToBeClickable(viewProductButtons.get(index));
			viewProductButtons.get(index).click();
			return new ProductDetailPage(driver);
		}
		return null;
	}
	
}
