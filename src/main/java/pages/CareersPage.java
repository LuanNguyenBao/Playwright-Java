package pages;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
public class CareersPage {
    private final Page careerPage;
    private SoftAssert sortAssert;
    private static final String SEARCH_RESULT_TITLE = ".search-result__heading-23";
    private static final String REMOTE= "#jobSearchFilterForm";
    private static final String LOCATION = ".select2-selection__rendered";

    public CareersPage(Page page) {
        this.careerPage = page;
        this.sortAssert = new SoftAssert();
    }

    @Step("Click on find button")
    public void clickOnFindButton() {
        careerPage.locator(REMOTE).scrollIntoViewIfNeeded();
        careerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Find")).click();
    }

    @Step("Input job Id")
    public void inputJobID(String jobID) {
        careerPage.locator(REMOTE).scrollIntoViewIfNeeded();
        careerPage.getByRole(AriaRole.TEXTBOX).first().fill(jobID);
    }

    @Step("Click on find your dream job")
    public void clickOnFindYourDreamJob() {
        careerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Find Your Dream Job")).first().click();
    }

    @Step("Select country")
    public void selectCountry(String countryName, String city) {
        careerPage.locator(REMOTE).scrollIntoViewIfNeeded();
        careerPage.locator(LOCATION).click();
        careerPage.getByText(countryName, new Page.GetByTextOptions().setExact(true)).click();
        careerPage.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(city)).click();
    }

    @Step("Select skill")
    public void selectSkill(String skill) {
        careerPage.locator(REMOTE).scrollIntoViewIfNeeded();
        careerPage.getByText("All Skills").click();
        careerPage.getByRole(AriaRole.TREEITEM, new Page.GetByRoleOptions().setName(skill)).getByText(skill).click();
    }

    @Step("Click on filter searching")
    public void clickOnFilterSearching(String filterName) {
        careerPage.getByText(filterName).first().click();
    }

    /* ================================ VERIFY ================================= */

    @Step("Verify search result of section")
    public void verifySearchResultOfSelection() {
        careerPage.getByText("We found 26 job openings for you").isVisible();
    }

    @Step("Verify sort option")
    public void verifySortOption(String option) {
        careerPage.getByText(option).isVisible();
    }

    @Step("Verify search label")
    public void verifySearchLabel(){
        sortAssert.assertTrue(careerPage.getByLabel("Keyword or job ID").isVisible());
        sortAssert.assertTrue(careerPage.getByLabel("Location").first().isVisible());
        sortAssert.assertTrue(careerPage.getByLabel("Skills").isVisible());
    }

    @Step("Verify placeholder search")
    public void verifyPlaceHolderSearch() {
        assertThat(careerPage.getByPlaceholder("Keyword")).isVisible();
        assertThat(careerPage.getByText("All Skills")).isVisible();
    }

    @Step("Verify result title")
    public void verifyResultTitle() {
        careerPage.locator(SEARCH_RESULT_TITLE).scrollIntoViewIfNeeded();
        assertThat(careerPage.locator(SEARCH_RESULT_TITLE)).isVisible();
    }

    @Step("Verify no result message")
    public void verifyNoResultMessage(String message) {
        assertThat(careerPage.getByText(message));
    }

    @Step("Verify title result with keyword")
    public void verifyTitleResultWithKeyword(String keyword) {
        careerPage.locator(SEARCH_RESULT_TITLE).scrollIntoViewIfNeeded();
        assertThat(careerPage.locator(SEARCH_RESULT_TITLE)).containsText(keyword);
    }

}