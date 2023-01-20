package ru.ibs.framework.managers;

import ru.ibs.framework.pages.*;
import ru.ibs.framework.pages.blocks.MainBottomMenuPage;

public class PageManager {
    private static PageManager INSTANCE;
    private MainBottomMenuPage mainBottomMenuPage;
    private SearchResultsPage searchResultsPage;
    private ProductCartPage productCartPage;
    private BasketPage basketPage;

    public static PageManager getInstance () {
        if (INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }
    public MainBottomMenuPage getMainBottomMenuPage() {
        if (mainBottomMenuPage == null) {
            mainBottomMenuPage = new MainBottomMenuPage();
        }
        return mainBottomMenuPage;
    }

    public SearchResultsPage getSearchResultsPage () {
        if (searchResultsPage == null) {
            searchResultsPage = new SearchResultsPage();
        }
        return searchResultsPage;
    }
    public ProductCartPage getProductCartPage () {
        if (productCartPage == null) {
            productCartPage = new ProductCartPage();
        }
        return productCartPage;
    }

    public BasketPage getBasketPage () {
        if (basketPage == null) {
            basketPage = new BasketPage();
        }
        return basketPage;
    }
}
