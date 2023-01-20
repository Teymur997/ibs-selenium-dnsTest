package ru.ibs.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import ru.ibs.framework.pages.blocks.MainBottomMenuPage;
import java.util.List;

public class SearchResultsPage extends BasePage {
    MainBottomMenuPage mainBottomMenuPage = new  MainBottomMenuPage();
    ProductCartPage productCartPage = new ProductCartPage();
    @FindBy(xpath = "//div[@data-id=\"product\"]")
    private List<WebElement> productList;

    @FindBy(xpath = "//a[contains(@class, 'pagination-widget__page-link_next')]")
    private WebElement nextPageButton;
    @FindBy(xpath = "//span[@class=\"buttons__link-icon cart-link-counter__icon\"]")
    private WebElement basketButton;

    public SearchResultsPage checkProductPresence(String article) {
        Assertions.assertTrue(productList.stream()
                .filter(element -> element.getAttribute("data-code")
                        .contains(article))
                        .findAny()
                        .isPresent(),
                "Выбранный товар отсутствует в списке"
        );
        return pageManager.getSearchResultsPage();
    }

    public WebElement getProductByArticle(String article) {
        return productList.stream()
                .filter(element -> element.getAttribute("data-code")
                        .contains(article))
                .findAny()
                .get();
    }
    public ProductCartPage chooseProductByArticle(String article) {
        scrollToElementJs(getProductByArticle(article)).click();
        return pageManager.getProductCartPage();
    }


    public BasketPage getBasketPage() {
        basketButton.click();
        return pageManager.getBasketPage();
    }

    public MainBottomMenuPage getMainBottomMenuPage() {
        return mainBottomMenuPage;
    }
    public ProductCartPage getProductCartPage() {
        return productCartPage;
    }
}
