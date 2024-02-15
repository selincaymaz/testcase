package com.insider.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QualityAssurancePage extends BasePage {
    public QualityAssurancePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private static final Logger logger = LogManager.getLogger(QualityAssurancePage.class);

    @FindBy(linkText = "See all QA jobs")
    WebElement seeAllQaJobsButton;
    @FindBy(css = ".mb-2")
    WebElement mainTitle;
    @FindBy(id = "select2-filter-by-location-container")
    WebElement locationDropdown;
    @FindBy(css = ".position-list-item:nth-child(1) > .position-list-item-wrapper")
    WebElement firstJob;
    @FindBy(linkText = "View Role")
    WebElement viewRoleButton;


    public void checkJobsPage() {
        waitForElement(driver, seeAllQaJobsButton);
        seeAllQaJobsButton.click();
        logger.info("See All Jobs button is clicked");
        waitForElement(driver, mainTitle);
        assertEquals("All open positions", mainTitle.getText());
    }

    public void checkJobsTitle() throws InterruptedException {
        locationDropdown.click();
        logger.info("Location dropdown is opened");
        downThenEnter();
        scrollDown(500);
        Thread.sleep(2000L);
        for (int i = 1; i < 4; i++) {
            assertEquals("Quality Assurance", driver.findElement(By.cssSelector(".position-list-item:nth-child(" + i + ") .position-department")).getText());
            assertEquals("Istanbul, Turkey", driver.findElement(By.cssSelector(".position-list-item:nth-child(" + i + ") .position-location")).getText());
        }
        logger.info("All jobs infos is checked");
    }

    public void checkFormPage() {
        moveToMouse(firstJob);
        viewRoleButton.click();
        logger.info("View button is clicked");
    }
}
