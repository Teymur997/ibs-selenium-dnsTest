package ru.ibs.framework.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.ibs.framework.utils.PropsConst;


public class DriverManager {
    private static DriverManager INSTANCE;
    private TestPropertiesManager testProperties = TestPropertiesManager.getInstance();
    private WebDriver driver;




    public static DriverManager getInstance () {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }
    private DriverManager() {
    }

    public WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }


    private WebDriver createDriver() {
        switch (testProperties.getMavenProperty("browser", "chrome")) {
            case "chrome":
                System.setProperty(PropsConst.PATH_CHROME_DRIVER, testProperties.getProperty(PropsConst.PATH_CHROME_DRIVER));
                return new ChromeDriver();
            case "firefox":
                System.setProperty(PropsConst.PATH_FIREFOX_DRIVER, testProperties.getProperty(PropsConst.PATH_FIREFOX_DRIVER));
                return new FirefoxDriver();
            case "edge":
                System.setProperty(PropsConst.PATH_EDGE_DRIVER, testProperties.getProperty(PropsConst.PATH_EDGE_DRIVER));
                return new EdgeDriver();
            default:
                System.setProperty(PropsConst.PATH_FIREFOX_DRIVER, testProperties.getProperty(PropsConst.PATH_FIREFOX_DRIVER));
                return new FirefoxDriver();
        }
    }






























//    public WebDriver getDriver() {
//        if (driver == null) {
//            String browserName = "webdriver." + System.getProperty("browser", "gecko") + ".driver";
//            this.driver = createDriver(browserName);
//            executor = (JavascriptExecutor) driver;
//            actions = new Actions(driver);
//            wait = new WebDriverWait(driver, 5, 500);
//        }
//        return this.driver;
//    }
    public void quitDriver() {
        if (driver !=null) {
            driver.quit();
            driver =null;
        }
    }

}
