package com.epam.tests;

import com.epam.runners.PlaywrightRunner;
import core.enums.MENU;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HeaderMainNavigationTest extends PlaywrightRunner {

    @DataProvider(name = "navOptionsEachPage")
    public Object[][] navOptions() {
        return new Object[][]{
                {MENU.SERVICES.getName()},
                {MENU.INSIGHTS.getName()},
                {MENU.ABOUT.getName()},
                {MENU.CAREERS.getName()}
        };
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        homePage.navigate();
    }

    @Story("517 - Main Navigation")
    @Test(groups = {"517"}, description = "[Main Navigation] Verify showing label of menu item when hovering over")
    @Description("Test cases: 584")
    public void verifyLabelDisplayed() {
        headerPage.verifyCareerLabelDisplayed();
        headerPage.verifyAboutsLabelDisplayed();
        headerPage.verifyIndustriesLabelDisplayed();
        headerPage.verifyInsightsLabelDisplayed();
        headerPage.verifyServicesLabelDisplayed();
    }

    @Feature("Smoke")
    @Story("517 - Main Navigation")
    @Test(groups = {"smoke", "517"}, description = "[Main Navigation] Verify Main Navigation menu items")
    @Description("Test cases: 580, 586")
    public void verifyMainNavigationAlwaysAvailable() {
        headerPage.clickMainNavigationOption(MENU.SERVICES.getName());
        headerPage.verifyAboutsLabelDisplayed();
        headerPage.verifyIndustriesLabelDisplayed();
        headerPage.verifyInsightsLabelDisplayed();
        headerPage.verifyServicesLabelDisplayed();
    }

    @Story("517 - Main Navigation")
    @Test(groups = {"517"}, description = "[Main Navigation] Verify the display of Main Navigation")
    @Description("Test cases: 542")
    public void verifyMainNavigationDisplay() {
        headerPage.verifyMainNavigationDisplayedTop();
    }

    @Story("517 - Main Navigation")
    @Test(groups = {"517"}, description = "[Main Navigation] Verify color changing when hovering over menu item")
    @Description("Test cases: 585")
    public void verifyColorChangeOfMainNavigation() {
        headerPage.verifyColorChangeOfAbout();
        headerPage.verifyColorChangeOfCareer();
        headerPage.verifyColorChangeOfInsight();
        headerPage.verifyColorChangeOfServices();
    }
}
