package core;

import com.microsoft.playwright.*;
import core.enums.BROWSER;

import java.nio.file.Paths;
import java.util.Properties;

public class PlaywrightFactory {

    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();
    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return playwright.get();
    }

    public static Browser getBrowser() {
        return browser.get();
    }

    public static BrowserContext getBrowserContext() {
        return browserContext.get();
    }

    public static Page getPage() {
        return page.get();
    }

    /**
     * Purpose: init a new browser based on params
     *
     * @param config - load env properties
     * @return Page
     */
    public Page initBrowser(Properties config) {
        playwright.set(Playwright.create());

        BROWSER browserName;
        try {
            browserName = BROWSER.valueOf(config.getProperty("browserName").toUpperCase());
        } catch (IllegalArgumentException ex) {
            browserName = BROWSER.CHROME;
        }
        boolean isHeadless = Boolean.parseBoolean(config.getProperty("isHeadless"));
        boolean isMobile = Boolean.parseBoolean(config.getProperty("isMobile"));

        switch (browserName) {
            case FIREFOX:
                browser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless)));
                break;
            case SAFARI:
                browser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless)));
                break;
            case EDGE:
                browser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(isHeadless)));
                break;
            default:
                browser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(isHeadless)));
                break;
        }

        int width = isMobile ? Integer.parseInt(config.getProperty("mobileWidth"))
                : Integer.parseInt(config.getProperty("deskWidth"));
        int height = isMobile ? Integer.parseInt(config.getProperty("mobileHeight"))
                : Integer.parseInt(config.getProperty("deskHeight"));

        browserContext.set(getBrowser().newContext(new Browser.NewContextOptions()
                .setViewportSize(width, height)
                .setBaseURL(Configuration.get().getProperty("url"))));
        getBrowserContext().setDefaultTimeout(Integer.parseInt(config.getProperty("timeout")));

        page.set(getBrowserContext().newPage());
        return getPage();
    }

    public void startTracing() {
        getBrowserContext().tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(false).setSources(false));
    }

    public void stopTracing(String testName) {
        getBrowserContext().tracing().stop(new Tracing.StopOptions().setPath(Paths.get("traces/" + testName.replace("()", "") + ".zip")));
    }
}