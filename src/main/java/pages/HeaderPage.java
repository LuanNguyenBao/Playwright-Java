package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import core.enums.LOCATION;
import core.enums.MENU;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Objects;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HeaderPage extends Common{
    private final Page headerPage;
    private SoftAssert sortAssert;

    private static final String LOCATION_MENU_BTN = "button.location-selector__button";
    private static final String CONTACTUS_MENU_BTN = ".header__content>a[data-gtm-category='header-contact-cta']>span";
    private static final String SERVICES_LINK = "li>span>a[href='/services']";
    private static final String INSIGHT= "li>span>a[href='/insights']";
    private static final String HOVER_ITEM=".js-opened";
    private static final String ABOUT = "li>span>a[href='/about']";
    private static final String CAREER = "li>span>a[href='/careers']";

    private static final String MAIN_NAVIGATION = ".top-navigation-ui-23";
    private static final String INDUSTRIES = "ul>li[class='top-navigation__item epam']:nth-child(2)";

    public HeaderPage(Page page) {
        super(page);
        this.headerPage = page;
        this.sortAssert = new SoftAssert();
    }

    @Step("Click on main navigation {mainNavigationName} option")
    public void clickMainNavigationOption(String mainNavigationName) {
        if (Objects.equals(mainNavigationName, MENU.CONTACT_US.getName())) {
            headerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(MENU.CONTACT_US.getName().toUpperCase())).click();
        }
        else {
            headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(mainNavigationName)).last().click();
        }
        headerPage.waitForLoadState();
    }

    @Step("Click on Hamburger menu")
    public void clickHamburgerMenu() {
        headerPage.getByRole(AriaRole.BANNER).getByRole(AriaRole.BUTTON).first().click();
    }

    @Step("Click on Location menu")
    public void clickLocationMenu() {
        headerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Global (EN)")).click();
    }

    @Step("Click on Location {locationName} option")
    public void clickLocationOption(String locationName) {
        headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locationName)).click();
    }

    /* ================================ VERIFY ================================= */

    @Step("Verify Location default text is displayed")
    public void verifyLocationDefaultTextDisplayed() {
        assertThat(headerPage.locator(LOCATION_MENU_BTN)).hasText("Global (EN)");
    }

    @Step("Verify corresponding navigation {navigationName}")
    public void verifyCorrespondingNavigation(String navigationName) {
        assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(navigationName)).first()).isVisible();

    }

    @Step("Verify Location menu is displayed")
    public void verifyLocationMenuDisplayed() {
        List<String> locations = LOCATION.getLocationList();
        for (String location  : locations) {
            sortAssert.assertTrue(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(location)).isVisible());
        }
        sortAssert.assertAll();
    }

    @Step("Verify switch Location successfully")
    public void verifySwitchLocationSuccessful(String title, String url) {
        headerPage.waitForURL(url);
        sortAssert.assertEquals(headerPage.title(), title);
        sortAssert.assertEquals(headerPage.url(), url);
        sortAssert.assertAll();
    }

    @Step("Verify Contact Us item is displayed")
    public void verifyContactUsItemDisplayed() {
        assertThat(headerPage.locator(CONTACTUS_MENU_BTN)).isVisible();
        assertThat(headerPage.locator(CONTACTUS_MENU_BTN)).hasText("CONTACT US");
    }

    @Step("Verify color of Contact Us item")
    public void verifyColorContactUsItem() {
        headerPage.locator(CONTACTUS_MENU_BTN).hover();
        assertThat(headerPage.locator(CONTACTUS_MENU_BTN)).hasCSS("background-color", "rgb(255, 255, 255)");
    }

    @Step("Verify Services label is displayed")
    public void verifyServicesLabelDisplayed() {
        headerPage.locator(SERVICES_LINK).hover();
        assertThat(headerPage.locator(HOVER_ITEM)).isVisible();
    }

    @Step("Verify Insights label is displayed")
    public void verifyInsightsLabelDisplayed() {
        headerPage.locator(INSIGHT).hover();
        assertThat(headerPage.locator(HOVER_ITEM)).isVisible();
    }

    @Step("Verify Abouts label is displayed")
    public void verifyAboutsLabelDisplayed() {
        headerPage.locator(ABOUT).hover();
        assertThat(headerPage.locator(HOVER_ITEM)).isVisible();
    }

    @Step("Verify Career label is displayed")
    public void verifyCareerLabelDisplayed() {
        headerPage.locator(CAREER).hover();
        assertThat(headerPage.locator(HOVER_ITEM)).isVisible();
    }

    @Step("Verify Industries label is displayed")
    public void verifyIndustriesLabelDisplayed() {
        headerPage.locator(INDUSTRIES).hover();
        assertThat(headerPage.locator(HOVER_ITEM)).isVisible();
    }

    @Step("Verify main navigation is displayed on top")
    public void verifyMainNavigationDisplayedTop() {
        assertThat(headerPage.locator(MAIN_NAVIGATION)).isVisible();
    }

    @Step("Verify color change of Services")
    public void verifyColorChangeOfServices() {
        assertThat(headerPage.locator(SERVICES_LINK)).hasCSS("color", "rgb(255, 255, 255)");
        headerPage.locator(SERVICES_LINK).hover();
        assertThat(headerPage.locator(SERVICES_LINK)).hasCSS("color", "rgb(0, 246, 255)");
    }

    @Step("Verify color change of Industries")
    public void verifyColorChangeOfIndustries() {
        assertThat(headerPage.locator(INDUSTRIES)).hasCSS("color", "rgb(255, 255, 255)");
        headerPage.locator(INDUSTRIES).hover();
        assertThat(headerPage.locator(INDUSTRIES)).hasCSS("color", "rgb(0, 246, 255)");
    }

    @Step("Verify color change of Insight")
    public void verifyColorChangeOfInsight() {
        assertThat(headerPage.locator(INSIGHT)).hasCSS("color", "rgb(255, 255, 255)");
        headerPage.locator(INSIGHT).hover();
        assertThat(headerPage.locator(INSIGHT)).hasCSS("color", "rgb(0, 246, 255)");
    }

    @Step("Verify color change of About")
    public void verifyColorChangeOfAbout() {
        assertThat(headerPage.locator(ABOUT)).hasCSS("color", "rgb(255, 255, 255)");
        headerPage.locator(ABOUT).hover();
        assertThat(headerPage.locator(ABOUT)).hasCSS("color", "rgb(0, 246, 255)");
    }

    @Step("Verify color change of Career")
    public void verifyColorChangeOfCareer() {
        assertThat(headerPage.locator(CAREER)).hasCSS("color", "rgb(255, 255, 255)");
        headerPage.locator(CAREER).hover();
        assertThat(headerPage.locator(CAREER)).hasCSS("color", "rgb(0, 246, 255)");
    }

}