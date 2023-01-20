package ru.ibs.framework.managers;

import ru.ibs.framework.utils.PropsConst;
import java.util.concurrent.TimeUnit;

public class InitManager {
    private static final TestPropertiesManager testPropertiesManager = TestPropertiesManager.getInstance();
    private static final DriverManager driverManager = DriverManager.getInstance();
    public static void initFramework() {
        driverManager.getDriver().manage().timeouts()
                .pageLoadTimeout(Long.parseLong(testPropertiesManager.getProperty(PropsConst.PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts()
                .implicitlyWait(Long.parseLong(testPropertiesManager.getProperty(PropsConst.IMPLICITLY_WAIT)), TimeUnit.SECONDS);
        driverManager.getDriver().manage().window().maximize();
    }
    public static void quitFramework() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driverManager.quitDriver();
    }
}
