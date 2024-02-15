package com.insider.cases;

import com.insider.WebDriverFactory;
import com.insider.page.BasePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseClass {
    WebDriver driver;
    WebDriverWait wait;
    protected final static String base_url = "https://useinsider.com/";
    protected final static String qa_url = "https://useinsider.com/careers/quality-assurance/";

    private String getBrowserName() {
        String browserDefault = "chrome";
        String browserSentFromCmd = System.getProperty("browser");

        if (browserSentFromCmd == null) {
            return browserDefault;
        } else {
            return browserSentFromCmd;
        }
    }

    @BeforeEach
    public void set_up() {
        String browser = getBrowserName();
        try {
            driver = WebDriverFactory.getWebDriverForBrowser(browser);
            wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Browser Initialization failed. Check the Stack Trace. " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    public <T extends BasePage> T goToUrl(T page, String url) {
        driver.get(url);
        return page;
    }
}