package com.drivers;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class DriverUtils extends DriverManagerFactory {

    private static Logger logger = Logger.getLogger(DriverUtils.class);

    public static void getDriver(DriverProperty property) throws DriverCreationException {
        logger.debug(String.format("Creating the %s driver", property.getDriverType().name().toString()));
        createWebDriver(property);
    }

    public static void maximizeBrowser() {
        try {
            logger.debug("Maximize browser");
            DriverManagerFactory.getDriver().manage().window().maximize();
        } catch (Exception e) {
            logger.error("An error occurred when maximizing browser" + e.getMessage());
        }
    }

    public static void setBrowserSize(int width, int height) {
        try {
            logger.debug("Resizing browser");
            Dimension browserSize = new Dimension(width, height);
            DriverManagerFactory.getDriver().manage().window().setSize(browserSize);
        } catch (Exception e) {
            logger.error("An error occurred when resizing browser" + e.getMessage());
        }
    }

    public static WebDriver getWebDriver() {
        return getDriver();
    }

    public static void quitBrowser() {
        try {
            logger.debug("Quit browser");
            getDriver().close();
            getDriver().quit();
        } catch (Exception e) {
            logger.error("An error occurred when quiting browser" + e.getMessage());
        }

    }

    public static void setTimeOut(int timeoutSec) {
        DriverManagerFactory.setTimeOut(timeoutSec);
    }

    public static int getTimeOut() {
        return DriverManagerFactory.getTimeOut();
    }

    public static void setShortTimeOut(int timeoutSec) {
        DriverManagerFactory.setShortTimeOut(timeoutSec);
    }

    public static int getShortTimeOut() {
        return DriverManagerFactory.getShortTimeOut();
    }

    public static String getAlertText() {
        return getDriver().switchTo().alert().getText();
    }

    public static void waitForAlertDisplay() {
        int i = 0;
        while (i++ < 120) {
            if (isAlertDisplayed()) {
                break;
            }
            delay(1);
        }
    }

    public static void acceptAlert() {
        delay(1);// wait for alert displays
        getDriver().switchTo().alert().accept();
        delay(1);// wait for alert close
    }

    public static boolean isAlertDisplayed() {
        try {
            getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public static void waitForPageLoad() {
        delay(5);
    }

    public static void delay(int timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getURL() {
        return DRIVERS.get().url;
    }

    public static void setURL(String url) {
        if (url != null) {
            DRIVERS.get().url = url;
        }
    }

    public static void navigate(String url) {
        logger.debug("Navigate to " + url);
        try {
            getDriver().get(url);
        } catch (Exception e) {
            logger.error("An error occurred when nagivating " + e.getMessage());
        }
    }
}
