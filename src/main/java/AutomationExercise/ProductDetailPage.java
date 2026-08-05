package AutomationExercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * ProductDetailPage - Represents the Product Detail page
 * Handles viewing and verifying product information
 */
public class ProductDetailPage extends AbstractComponents {
	
	public WebDriver driver;
	
	public ProductDetailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Product name heading
	@FindBy(xpath="//h2[contains(@class, 'productinfo')]")
	WebElement productNameHeading;
	
	// Product price
	@FindBy(xpath="//span/span/span")
	WebElement productPrice;
	
	// Product category/description
	@FindBy(xpath="//p[contains(text(), 'Category')]")
	WebElement productCategory;
	
	// Product availability
	@FindBy(xpath="//p[contains(text(), 'Availability')]")
	WebElement productAvailability;
	
	// Product condition
	@FindBy(xpath="//p[contains(text(), 'Condition')]")
	WebElement productCondition;
	
	// Product brand
	@FindBy(xpath="//p[contains(text(), 'Brand')]")
	WebElement productBrand;
	
	// Quantity input
	@FindBy(id="quantity")
	WebElement quantityInput;
	
	// Add to cart button
	@FindBy(xpath="//button[@class='btn btn-default cart']")
	WebElement addToCartBtn;
	
	/**
	 * Verifies that Product Detail page is displayed
	 * @return true if product name heading is visible
	 */
	public boolean verifyProductDetailPageDisplayed() {
		waitForElementToAppear(productNameHeading);
		return productNameHeading.isDisplayed();
	}
	
	/**
	 * Gets the product name
	 * @return product name text
	 */
	public String getProductName() {
		waitForElementToAppear(productNameHeading);
		return productNameHeading.getText();
	}
	
	/**
	 * Gets the product price
	 * @return product price text
	 */
	public String getProductPrice() {
		waitForElementToAppear(productPrice);
		return productPrice.getText();
	}
	
	/**
	 * Gets the product category
	 * @return category text
	 */
	public String getProductCategory() {
		waitForElementToAppear(productCategory);
		String categoryText = productCategory.getText();
		return categoryText.replace("Category: ", "").trim();
	}
	
	/**
	 * Gets the product availability
	 * @return availability text
	 */
	public String getProductAvailability() {
		waitForElementToAppear(productAvailability);
		String availabilityText = productAvailability.getText();
		return availabilityText.replace("Availability: ", "").trim();
	}
	
	/**
	 * Gets the product condition
	 * @return condition text
	 */
	public String getProductCondition() {
		waitForElementToAppear(productCondition);
		String conditionText = productCondition.getText();
		return conditionText.replace("Condition: ", "").trim();
	}
	
	/**
	 * Gets the product brand
	 * @return brand text
	 */
	public String getProductBrand() {
		waitForElementToAppear(productBrand);
		String brandText = productBrand.getText();
		return brandText.replace("Brand: ", "").trim();
	}
	
	/**
	 * Verifies all product details are visible
	 * @return true if all details are displayed
	 */
	public boolean verifyAllProductDetailsVisible() {
		try {
			waitForElementToAppear(productNameHeading);
			waitForElementToAppear(productPrice);
			waitForElementToAppear(productCategory);
			waitForElementToAppear(productAvailability);
			waitForElementToAppear(productCondition);
			waitForElementToAppear(productBrand);
			
			return productNameHeading.isDisplayed() &&
				   productPrice.isDisplayed() &&
				   productCategory.isDisplayed() &&
				   productAvailability.isDisplayed() &&
				   productCondition.isDisplayed() &&
				   productBrand.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Sets the quantity for the product
	 * @param quantity - quantity to set
	 */
	public void setProductQuantity(int quantity) {
		waitForElementToBeClickable(quantityInput);
		quantityInput.clear();
		quantityInput.sendKeys(String.valueOf(quantity));
	}
	
	/**
	 * Clicks Add to Cart button
	 */
	public void clickAddToCart() {
		waitForElementToBeClickable(addToCartBtn);
		addToCartBtn.click();
	}
	
	/**
	 * Gets all product details as a formatted string
	 * @return product details
	 */
	public String getProductDetailsAsString() {
		StringBuilder details = new StringBuilder();
		details.append("Product Name: ").append(getProductName()).append("\n");
		details.append("Price: ").append(getProductPrice()).append("\n");
		details.append("Category: ").append(getProductCategory()).append("\n");
		details.append("Availability: ").append(getProductAvailability()).append("\n");
		details.append("Condition: ").append(getProductCondition()).append("\n");
		details.append("Brand: ").append(getProductBrand());
		return details.toString();
	}
	
}
