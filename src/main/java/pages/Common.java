package pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class Common {
    private final Page common;

    public Common(Page common) {
        this.common = common;
    }

    @Step("Hover {itemName} on Menu Item")
    public void hoverMenuItem(String itemName) {
        common.locator(itemName).hover();
    }
}
