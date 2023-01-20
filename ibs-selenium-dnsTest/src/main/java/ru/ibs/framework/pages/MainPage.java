package ru.ibs.framework.pages;

import ru.ibs.framework.pages.blocks.MainBottomMenuPage;

public class MainPage extends BasePage {
    MainBottomMenuPage mainBottomMenuPage = new  MainBottomMenuPage();

    public MainBottomMenuPage getMainBottomMenuPage() {
        return mainBottomMenuPage;
    }
}
