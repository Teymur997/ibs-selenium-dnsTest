package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.framework.Product;
import ru.ibs.framework.pages.blocks.MainBottomMenuPage;

import java.util.List;

public class ProductCartPage extends BasePage {
    MainBottomMenuPage mainBottomMenuPage = new  MainBottomMenuPage();

    @FindBy(xpath = "//h1")
    private WebElement productName;
    @FindBy(xpath = "//div[@class=\"product-card-top__buy\"]/div/div/div[contains(@class, 'product-buy__price')]")
    private WebElement productPrice;
    @FindBy(xpath = "//div[contains(@class, 'additional-sales-tabs__title')][contains(text(), 'Гарантия')]")
    private WebElement guaranteeButton;
    @FindBy(xpath = "//label[contains(@class, 'ui-radio__item')]")
    private List<WebElement> guaranteeSelectionButton;
    @FindBy(xpath = "//div[@class=\"product-card-top__buy\"]//button[contains(@class, 'buy-btn')]")
    private WebElement buyProductButton;
    @FindBy(xpath = "//div[@class=\"product-buy__sub\"]")
    private WebElement priceCondition;
    @FindBy(xpath = "//div[@class=\"product-card-top__code\"]")
    private WebElement productArticle;

    public ProductCartPage saveProductName(Product product) {
        product.setProductName(productName.getText());
        return pageManager.getProductCartPage();
    }
    public ProductCartPage saveProductPrice(Product product) {
        product.setProductPrice(priceValueHandler(productPrice));
        return pageManager.getProductCartPage();
    }
    public ProductCartPage saveGuaranteePrice(Product product) {
        product.setGuaranteePrice(priceValueHandler(guaranteeSelectionButton.stream()
                .filter(element -> element.findElement(By.xpath("./span[@class=\"ui-radio__content\"]"))
                        .getText()
                        .contains(product.getGuarantee()))
                .map(element -> element.findElement(By.xpath("./span[@class=\"product-warranty__price\"]")))
                .findAny()
                .get()));
        return pageManager.getProductCartPage();
    }
    public ProductCartPage saveArticle(Product product) {
        product.setArticle(articleValueHandler(productArticle));
        return pageManager.getProductCartPage();
    }
    public ProductCartPage clickGuaranteeButton() {
        guaranteeButton.click();
        return pageManager.getProductCartPage();
    }
    public ProductCartPage checkGuaranteeButtonCondition() {
        Assertions.assertTrue(guaranteeButton.getAttribute("class").contains("active"), "Кнопка выбора гарантий не нажата");
        return pageManager.getProductCartPage();
    }
    public ProductCartPage chooseGuaranteeOption(String guaranteeType) {
        guaranteeSelectionButton.stream()
                .map(element -> element.findElement(By.xpath("./span[@class=\"ui-radio__content\"]")))
                .filter(element -> element.getText()
                        .contains(guaranteeType))
                .findAny()
                .get()
                .click();
        return pageManager.getProductCartPage();
    }
    public ProductCartPage checkPriceConditionChanging() {
        Assertions.assertTrue(priceCondition.getText().contains("цена изменена"), "Цена не изменена");
        return pageManager.getProductCartPage();
    }
    public ProductCartPage clickBuyButton() {
        buyProductButton.click();
        wait.until(ExpectedConditions.attributeContains(buyProductButton, "data-redirect", "https"));
        return pageManager.getProductCartPage();
    }
    public MainBottomMenuPage getMainBottomMenuPage() {
        return mainBottomMenuPage;
    }
}
