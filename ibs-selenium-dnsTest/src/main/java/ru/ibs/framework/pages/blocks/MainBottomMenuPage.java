package ru.ibs.framework.pages.blocks;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.pages.BasePage;
import ru.ibs.framework.pages.BasketPage;
import ru.ibs.framework.pages.ProductCartPage;
import ru.ibs.framework.pages.SearchResultsPage;

import java.util.List;

public class MainBottomMenuPage extends BasePage {


    @FindBy(xpath = "//div[@class=\"buttons\"]/*")
    private List<WebElement> headerBottomMenu;
    @FindBy(xpath = "//input[contains(@class, 'presearch__input')]")
    private WebElement searchInputField;
    @FindBy(xpath = "//span[@class=\"presearch__icon-search\"]")
    private WebElement searchButton;
    @FindBy(xpath = "//span[@class=\"buttons__link-price cart-link-counter__price\"]")
    private WebElement basketFinalPrice;
    @FindBy(xpath = "//span[@class=\"buttons__link-icon cart-link-counter__icon\"]")
    private WebElement basketButton;
    @FindBy(xpath = "//div[@id=\"app-cart-modal\"]")
    private WebElement basketWindow;

    public MainBottomMenuPage checkIfPageIsOpened() {
        driverManager.getDriver().getTitle();
        Assertions.assertEquals("DNS – интернет магазин цифровой и бытовой техники по доступным ценам.",
                driverManager.getDriver().getTitle(),
                "Страница не открыта");
        return pageManager.getMainBottomMenuPage();
    }
    public MainBottomMenuPage waitUntilWindowDisappear() {
        waitUntilElementDisappear(basketWindow);
        return pageManager.getMainBottomMenuPage();
    }
    public MainBottomMenuPage fillSearchInputField(String string) {
        waitUntilWindowDisappear();
        fillInputField(searchInputField, string);
        return pageManager.getMainBottomMenuPage();
    }
    public SearchResultsPage clickSearchButton() {
        searchButton.click();
        return pageManager.getSearchResultsPage();
    }
    public BasketPage clickBasketButton() {
        basketButton.click();
        return pageManager.getBasketPage();
    }
    public ProductCartPage basketPriceCompare(int sum) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(sum, priceValueHandler(basketFinalPrice), "Сумма товаров не равна итоговой сумме корзины");
        return pageManager.getProductCartPage();
    }



}
