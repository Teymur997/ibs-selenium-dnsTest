package ru.ibs.framework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.ibs.framework.managers.DriverManager;
import ru.ibs.framework.managers.InitManager;
import ru.ibs.framework.managers.PageManager;
import ru.ibs.framework.managers.TestPropertiesManager;
import ru.ibs.framework.pages.*;
import ru.ibs.framework.pages.blocks.MainBottomMenuPage;
import ru.ibs.framework.utils.PropsConst;

public class BaseTests {
    public MainBottomMenuPage mainPage;
    public SearchResultsPage searchResultsPage;
    public ProductCartPage productCartPage;
    public BasketPage basketPage;

    protected  DriverManager driverManager = DriverManager.getInstance();
    protected PageManager pageManager = PageManager.getInstance();
    protected TestPropertiesManager testPropertiesManager = TestPropertiesManager.getInstance();

//    @BeforeAll
//    public static void beforeAll() {
//        InitManager.initFramework();
//    }

    @BeforeEach
    public void setUp() {
        InitManager.initFramework();
        driverManager.getDriver().get(testPropertiesManager.getProperty(PropsConst.MAIN_PAGE_URL));
    }
    @AfterEach
    void tearDown() {
        InitManager.quitFramework();
    }
}
