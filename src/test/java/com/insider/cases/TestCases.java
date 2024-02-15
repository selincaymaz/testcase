package com.insider.cases;

import com.insider.page.HomePage;
import com.insider.page.QualityAssurancePage;
import org.junit.jupiter.api.Test;


public class TestCases extends BaseClass {

    HomePage homePage;
    QualityAssurancePage qualityAssurancePage;

    @Test
    public void checkHomeAndCareersPage() throws InterruptedException {
        homePage = goToUrl(new HomePage(driver, wait), base_url);
        homePage.checkButton();
        homePage.openCareerPage();
        homePage.checkSeeAllTeamsPage();
        homePage.checkOtherTitles();
    }

    @Test
    public void checkQAPage() throws InterruptedException {
        qualityAssurancePage = goToUrl(new QualityAssurancePage(driver, wait), qa_url);
        qualityAssurancePage.checkJobsPage();
        qualityAssurancePage.checkJobsTitle();
        qualityAssurancePage.checkFormPage();
    }
}
