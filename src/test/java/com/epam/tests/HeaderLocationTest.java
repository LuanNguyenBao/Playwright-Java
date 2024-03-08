package com.epam.tests;

import com.epam.runners.PlaywrightRunner;
import core.Configuration;
import core.enums.MENU;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.JsonReader;

public class HeaderLocationTest extends PlaywrightRunner {
    @DataProvider(name = "navOptions")
    public Object[][] navOptions() {
        return new Object[][]{
                {MENU.SERVICES.getName()},
                {MENU.INSIGHTS.getName()},
                {MENU.ABOUT.getName()},
                {MENU.CAREERS.getName()},
                {MENU.CONTACT_US.getName()}
        };
    }

    @DataProvider(name = "locationData")
    public Object[][] locationData() {
        return JsonReader.load("testData/LocationData.json");
    }

    @BeforeMethod(alwaysRun = true)
    public void setConditions() {
        homePage.navigate();
        if (Boolean.parseBoolean(Configuration.get().getProperty("isMobile"))) {
            headerPage.clickHamburgerMenu();
        }
    }

    @Story("525 - Location button")
    @Test(groups = {"525"}, description = "[Location] Verify default text on Location button")
    @Description("Test cases: 571")
    public void verifyLocationDefaultText() {
        headerPage.clickLocationMenu();
        headerPage.verifyLocationDefaultTextDisplayed();
    }

    @Feature("Smoke")
    @Story("525 - Location button")
    @Test(groups = {"smoke", "525"}, description = "[Location] Verify all required location are listed out")
    @Description("Test cases: 573")
    public void verifyAllLocationAreDisplayed() {
        headerPage.clickLocationMenu();
        headerPage.verifyLocationMenuDisplayed();
    }

    @Story("525 - Location button")
    @Test(groups = {"525"}, description = "[Location] Verify the corresponding navigation link",
            dataProvider = "locationData")
    @Description("Test cases: 575, 579")
    public void verifySwitchLocation(String location, String title, String url) {
        headerPage.clickLocationMenu();
        headerPage.clickLocationOption(location);
        headerPage.verifySwitchLocationSuccessful(title, url);
    }

    @Story("525 - Location button")
    @Test(groups = {"525"}, description = "[Location] Verify the display of Location button",
            dataProvider = "navOptions")
    @Description("Test cases: 577")
    public void verifyLocationDisplayedOnAnyPages(String mainNavigationName) {
        headerPage.clickMainNavigationOption(mainNavigationName);
        if (Boolean.parseBoolean(Configuration.get().getProperty("isMobile"))) {
            headerPage.clickHamburgerMenu();
        }
        headerPage.clickLocationMenu();
        headerPage.verifyLocationMenuDisplayed();
    }
}
