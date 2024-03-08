package com.epam.tests;

import com.epam.runners.PlaywrightRunner;
import core.enums.MENU;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;


public class ContactUsTest extends PlaywrightRunner {

    @Feature("Smoke")
    @Story("524 - Contact Us")
    @Test(groups = {"smoke", "524"}, description = "[Header] Verify Contract Us link")
    @Description("Test cases: 592, 5954, 595")
    public void verifyContactUsDisplayed() {
        headerPage.verifyContactUsItemDisplayed();
        headerPage.verifyColorContactUsItem();
    }

    @Story("591 - Contact Us Information")
    @Test(groups = {"591"}, description = "[Contact Us] Verify Contact Us page displayed")
    @Description("Test cases: 643")
    public void verifyContactPageDisplayed() {
        headerPage.clickMainNavigationOption(MENU.CONTACT_US.getName());
        contactPage.verifyContactUsPageDisplayed();
        headerPage.clickMainNavigationOption(MENU.CAREERS.getName());
        headerPage.verifyContactUsItemDisplayed();
        headerPage.clickMainNavigationOption(MENU.CONTACT_US.getName());
        contactPage.verifyContactUsPageDisplayed();
    }

    @Feature("Smoke")
    @Story("591 - Contact Us Information")
    @Test(groups = {"smoke", "591"}, description = "[Contact Us] Verify Contact Us page content")
    @Description("Test cases: 639, 642")
    public void verifyContentOfContactUs() {
        String address = "41 University Drive • Suite 202, Newtown, PA 18940 • US";
        String p = "+1-267-759-9000";
        String f = "+1-267-759-8989";
        headerPage.clickMainNavigationOption(MENU.CONTACT_US.getName());
        contactPage.verifyContactUsPageDisplayed();
        contactPage.verifyBreadscumbLink(MENU.CONTACT_US.getName());
        contactPage.verifyContentOfContactUs(address, p, f);
    }
}
