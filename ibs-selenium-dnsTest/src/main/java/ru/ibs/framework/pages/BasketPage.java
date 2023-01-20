package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.framework.Product;
import ru.ibs.framework.pages.blocks.MainBottomMenuPage;

import java.util.List;

public class BasketPage extends BasePage {
    MainBottomMenuPage mainBottomMenuPage = new  MainBottomMenuPage();
    ProductCartPage productCartPage = new ProductCartPage();

    @FindBy(xpath = "//div[@class=\"cart-items__content-container\"]")
    private List<WebElement> productList;
    @FindBy(xpath = "//div[@class=\"summary-header__sum\"]//span[@class=\"price__current\"]")
    private WebElement basketPrice;
    @FindBy(xpath = "//div[@class=\"cart-tab-total-amount\"]//span[@class=\"restore-last-removed\"]")
    private WebElement restoreLastRemovedProductButton;
    @FindBy(xpath = "//input[@class=\"count-buttons__input\"]")
    private WebElement productCount;

//    @FindBy(xpath = "//div[@class=\"cart-items__content-container\"]//div[contains(@class, 'base-ui-radio-button')]")
//    private WebElement guaranteeButtonAttribute;
//    @FindBy(xpath = "//div[@class=\"cart-items__content-container\"]//div[@class=\"cart-items__product-code\"]")
//    private WebElement productCode;
//    @FindBy(xpath = "//div[@class=\"cart-items__content-container\"]//span[@class=\"price__current\"]")
//    private WebElement productPrice;
//    @FindBy(xpath = "//div[@class=\"cart-items__content-container\"]//p[@class=\"remove-button__title\"]")
//    private WebElement productDeletingButton;
//    @FindBy(xpath = "//div[@class=\"cart-items__content-container\"]//button[contains(@class, 'button_plus')]")
//    private WebElement addingProductButton;

    public BasketPage checkIfProductGuaranteeButtonChecked(Product product) {
        List<WebElement> guaranteeButtonList = getCurrentProductContentContainer(product)
                .findElements(By.xpath(".//div[contains(@class, 'base-ui-radio-button')]"));

        String expected = product.getGuarantee();

        String actual = guaranteeButtonList.stream()
                .filter(element -> element.getAttribute("class").contains("checked"))
                .findAny()
                .map(WebElement::getText)
                .get();

        Assertions.assertEquals(guaranteeTextHandler(expected),
                guaranteeTextHandler(actual),
                "Гарантия не выбрана");

        return pageManager.getBasketPage();
    }
    public WebElement getCurrentProductContentContainer(Product product) {
        return productList.stream()
                .filter(element -> element.findElement(By.xpath(".//div[@class=\"cart-items__product-code\"]"))
                        .getText().equalsIgnoreCase(product.getArticle()))
                .findAny()
                .get();
    }
    public BasketPage deleteProduct(Product product) {
        productList.stream()
                .filter(element -> element.findElement(By.xpath(".//div[@class=\"cart-items__product-code\"]"))
                        .getText().equalsIgnoreCase(product.getArticle()))
                .findAny()
                .map(element -> element.findElement(By.xpath(".//p[@class=\"remove-button__title\"]")))
                .get().click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignore) {
        }
        return pageManager.getBasketPage();
    }
    public boolean checkPresence(Product product)  {
        try {
            return productList.stream()
                    .anyMatch(element -> element.findElement(By.xpath(".//div[@class=\"cart-items__product-code\"]"))
                            .getText().equalsIgnoreCase(product.getArticle()));
        } catch (StaleElementReferenceException | NoSuchElementException ignore) {
            return false;
        }
    }
    public BasketPage checkProductAbsence(Product product)  {
        Assertions.assertFalse(checkPresence(product), "Товар не удален из корзины");
        return pageManager.getBasketPage();
    }
    public BasketPage checkProductPresence(Product product) {
        Assertions.assertTrue(checkPresence(product), "Товар отсутствует");
        return pageManager.getBasketPage();
    }
    public BasketPage addProduct(Product product, int count) {
        for (int i = 0; i < count; i++) {
            wait.until(ExpectedConditions
                            .elementToBeClickable(getCurrentProductContentContainer(product)
                                    .findElement(By.xpath(".//button[contains(@class, 'button_plus')]"))))
                    .click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignore) {
            }
        }
        return pageManager.getBasketPage();
    }
    public Integer getProductPrice(Product product) {
        return priceValueHandler(getCurrentProductContentContainer(product)
                .findElement(By.xpath(".//span[@class=\"price__current\"]")));
    }
    public BasketPage checkProductPrice(Product product) {
        Assertions.assertEquals(product.getProductPrice(),
                getProductPrice(product),
                "Сумма цены товара и гарантии не равна общей сумме позиции");
        return pageManager.getBasketPage();
    }
    public BasketPage restoreLastRemovedProduct() {
        restoreLastRemovedProductButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignore) {
        }
        return pageManager.getBasketPage();
    }
    public BasketPage basketPriceCompare(int sum) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignore) {
        }
        Assertions.assertEquals(sum, priceValueHandler(basketPrice), "Сумма товаров не равна итоговой сумме корзины");
        return pageManager.getBasketPage();
    }

    public MainBottomMenuPage getMainBottomMenuPage() {
        return mainBottomMenuPage;
    }
    public BasketPage saveCountValue(Product product) {
        product.setCount(Integer.parseInt(getCurrentProductContentContainer(product)
                .findElement(By.xpath(".//input[@class=\"count-buttons__input\"]")).getAttribute("_value")));
        return pageManager.getBasketPage();
    }
}