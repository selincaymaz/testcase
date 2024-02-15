package com.insider.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private static final Logger logger = LogManager.getLogger(HomePage.class);

    @FindBy(linkText = "Get a Demo")
    WebElement getDemoButton;
    @FindAll(
            {
                    @FindBy(css = "#navbarDropdownMenuLink")
            }
    )
    List<WebElement> dropdownMenus;
    @FindBy(linkText = "Careers")
    WebElement careersButton;
    @FindBy(linkText = "See all teams")
    WebElement seeAllTeamsButton;
    @FindBy(css = ".elementor-element-21cea83 .elementor-heading-title")
    WebElement lifeAtInsederText;
    @FindBy(css = ".ml-0")
    WebElement ourLocationsText;
    @FindBy(linkText = "Find your dream job")
    WebElement findYourDreamJobButton;
    @FindBy(css = ".job-item:nth-child(1)")
    WebElement firstItem;
    @FindBy(css = ".job-item:nth-child(29)")
    WebElement lastItem;


    public void checkButton() {
        waitForElement(driver, getDemoButton);
        assertTrue(getDemoButton.isDisplayed());
        logger.info("Demo button is checked");
    }

    public void openCareerPage() {
        dropdownMenus.get(4).click();
        logger.info("Company button is clicked");
        waitForElement(driver, careersButton);
        careersButton.click();
        logger.info("Careers button is clicked");
    }

    public void checkSeeAllTeamsPage() throws InterruptedException {
        waitForElement(driver,findYourDreamJobButton);
        scrollDown(2600);
        Thread.sleep(2000L);
        logger.info("See All Items is displayed on the page");
        seeAllTeamsButton.click();
        logger.info("See All Items button is clicked");
        assertTrue(firstItem.isEnabled());
        scrollDown(2900);
        waitForElement(driver,lastItem);
        assertTrue(lastItem.isEnabled());
        logger.info("See All Items is checked");
    }

    public void checkOtherTitles() {
        scrollDown(200);
        assertEquals("Our Locations", ourLocationsText.getText());
        scrollDown(200);
        assertEquals("Life at Insider", lifeAtInsederText.getText());
        logger.info("Our locations and Life at Insider tabs are checked");
    }
}
