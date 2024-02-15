package com.insider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WebDriverFactory {
    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
    private static WebDriver driver;

    public static WebDriver getWebDriverForBrowser(String browser) throws Exception {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.setExperimentalOption("useAutomationExtension", false);
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-gpu");
                options.addArguments("--start-maximized");
                options.addArguments("--incognito");
                options.addArguments("--disable-features=NetworkService");
                options.addArguments("--dns-prefetch-disable");
                options.addArguments("--disable-extensions");
                options.setPageLoadStrategy(PageLoadStrategy.NONE);
                driver = new ChromeDriver(options);
                logger.info("Chrome Browser invoked");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                logger.info("Firefox Browser invoked");
                break;
            default:
                logger.fatal("No such browser is implemented.Browser name sent: " + browser);
                throw new Exception("No such browser is implemented.Browser name sent: " + browser);
        }

        driver.manage().window().maximize();
        logger.info("Driver maximized");
        return driver;
    }

    public void scrollDown(int i) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + i + ")");
    }

    public void moveToMouse(WebElement element) {
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
    }

    public void downThenEnter() {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ARROW_DOWN);
        builder.sendKeys(Keys.ENTER);
        builder.perform();
    }

    public static void waitForElement(WebDriver driver, WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(10000))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitElementForClickable(WebDriver driver, WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(100))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}