package com.epam.tests;

import com.epam.runners.PlaywrightRunner;
import core.enums.MENU;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Tag("search")
public class SearchTest extends PlaywrightRunner {
    @DataProvider(name = "filterSearching")
    public Object[][] navOptions() {
        return new Object[][]{
                {"Remote"},
                {"Office"},
                {"Open to Relocation"}
        };
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        headerPage.clickMainNavigationOption(MENU.CAREERS.getName());
    }

    @Story("531 - Careers Search")
    @Test(groups = {"531"}, description = "[Careers][Search] Verify that the Search section displays as the design")
    @Description("Test cases: 664")
    public void verifySearchSessionDisplayed() {
        careersPage.verifySearchLabel();
        careersPage.verifyPlaceHolderSearch();
    }

    @Story("531 - Careers Search")
    @Test(groups = {"531"}, description = "[Careers][Search] Verify that the searching displays the result automatically without clicking on Find button")
    @Description("Test cases: 674")
    public void verifySearchResultWithoutInput() {
        careersPage.clickOnFindButton();
        careersPage.verifyResultTitle();
    }

    @Story("531 - Careers Search")
    @Test(groups = {"531"}, description = "[Careers][Search] Verify the message if there is no search result")
    @Description("Test cases: 673")
    public void verifyNoResultMessage() {
        careersPage.inputJobID("abc23avc");
        careersPage.clickOnFindButton();
        careersPage.verifyNoResultMessage("Don’t see the dream job you were hoping to find?");
    }

    @Story("531 - Careers Search")
    @Test(groups = {"531"}, description = "[Careers][Search] Verify that user is able to search the jobs of all countries and skills")
    @Description("Test cases: 666")
    public void verifySearchJobOfAllCountryAndSkill() {
        careersPage.clickOnFindYourDreamJob();
        careersPage.verifyResultTitle();
    }

    @Story("531 - Careers Search")
    @Test(groups = {"531"}, description = "[Careers][Search] Verify that user is able to search the job matching the keywords")
    @Description("Test cases: 667")
    public void verifySearchMatchingWithKeyword() {
        String jobId = "test";
        careersPage.inputJobID(jobId);
        careersPage.clickOnFindButton();
        careersPage.verifyTitleResultWithKeyword(jobId);
    }

    @Story("531 - Careers Search")
    @Test(groups = {"531"}, description = "[Careers][Search] Verify that the user is able to search the jobs matching the selected skills")
    @Description("Test cases: 670, 677")
    public void verifySearchMatchingWithSkill() {
        careersPage.selectCountry("Argentina", "All Cities in Argentina");
        careersPage.selectSkill("Business and Data Analysis");
        careersPage.clickOnFindButton();
        careersPage.verifySearchResultOfSelection();
        careersPage.verifySortOption("Relevance");
    }

    @Story("531 - Careers Search")
    @Test(groups = {"531"}, description = "[Careers][Search] Verify that the user is able to search the jobs matching their selected location")
    @Description("Test cases: 669")
    public void verifySearchWithLocation() {
        careersPage.selectCountry("Argentina", "All Cities in Argentina");
        careersPage.clickOnFindButton();
        careersPage.verifySearchResultOfSelection();
    }

    @Feature("Smoke")
    @Story("531 - Careers Search")
    @Test(groups = {"smoke", "531"}, description = "[Careers][Search] Verify that user is able to search the jobs with combinations of some fields in search section")
    @Description("Test cases: 671")
    public void verifySearchWithCombination() {
        careersPage.inputJobID("automation");
        careersPage.selectCountry("Singapore", "All Cities in Singapore");
        careersPage.clickOnFindButton();
        careersPage.verifySearchResultOfSelection();
    }

    @Story("531 - Careers Search")
    @Test(groups = {"531"}, description = "[Careers][Search] Verify that user is able to add the filter in searching",
            dataProvider = "filterSearching")
    @Description("Test cases: 672")
    public void verifySearchWithFilterSearching(String filter) {
        careersPage.clickOnFilterSearching(filter);
        careersPage.verifySearchResultOfSelection();
    }
}
