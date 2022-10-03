package pl.epam.goman.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.epam.goman.service.WebDriverCreate;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Page {
    private static final Logger LOG = Logger.getLogger(Page.class.getName());

    public static final Duration TIMEOUT = Duration.ofSeconds(10);
    private final WebDriver driver;

    public Page() {
        this.driver = WebDriverCreate.getDriver();
    }

    private static final By HEADER = By.xpath("//*[@class='header-ui']");
    private static final By SEARCH_BTN = By.xpath("//*[contains(@class,'header-search__button')]");
    private static final By SEARCH_FIELD = By.xpath("//input[@placeholder='What are you looking for?']");
    private static final By FREQUENT_SEARCHES_MODAL = By.xpath("//*[@class='frequent-searches-ui']");
    private static final By FIND_BTN = By.xpath("//button[text()='Find']");
    private static final By FREQUENT_SEARCH_ITEM = By.xpath("//li[text()='Automation']");
    private static final By SEARCH_RESULT_PAGE = By.linkText("Search");
    private static final By SEARCH_RESULTS_MESSAGE = By.xpath("//h2[contains(text(),'results for')]");
    private static final By LAST_RESULT_LINK = By.xpath("(//a[@class='search-results__title-link'])[1]");
    private static final By OPEN_RESULT_PAGE_LINK = By.linkText("Blogs");
    private static final By INVALID_SEARCH_RESULTS_MESSAGE = By.xpath("//div[text()='Sorry, but your search returned no results. Please try another query.']");

    private static final String PAGE_URL = "https://www.epam.com/";

    public void openMainPage() {
        this.driver.get(PAGE_URL);
    }

    public void clickSearchButton() {
        getElementWithWait(SEARCH_BTN).click();
    }

    public void clickSearchField() {
        getElementWithWait(SEARCH_FIELD).click();
    }

    public void chooseSearchItem() {
        getElementWithWait(FREQUENT_SEARCH_ITEM).click();
    }

    public void enterSearchItem(String text) {
        WebElement item = getElementWithWait(SEARCH_FIELD);
        item.click();
        item.sendKeys(text);
    }

    public void clickFindButton() {
        getElementWithWait(FIND_BTN).click();
    }

    public boolean isHeaderExists() {
        return isElementExists(HEADER);
    }

    public boolean isFrequentSearchesModalExists() {
        return isElementExists(FREQUENT_SEARCHES_MODAL);
    }

    public void switchContext() {
        driver.switchTo().activeElement();
    }

    public boolean isSearchResultsPageExists() {
        return isElementExists(SEARCH_RESULT_PAGE);
    }

    public boolean isOpenResultLinkExists() {
        return isElementExists(OPEN_RESULT_PAGE_LINK);
    }

    public String getResultText() {
        return getTextFrom(SEARCH_RESULTS_MESSAGE);
    }

    public void openFirstResultLink() {
        WebElement item = getElementWithWait(LAST_RESULT_LINK);
        item.click();
    }

    public String getInvalidSearchResult() {
        return getTextFrom(INVALID_SEARCH_RESULTS_MESSAGE);
    }

    public void close() {
        driver.close();
    }

    private WebElement getElementWithWait(By by) {
        return new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    private WebElement getElementWithTimeout(By by) {
        return new WebDriverWait(driver, TIMEOUT)
                .until(webDriver -> webDriver.findElement(by));
    }

    private String getTextFrom(By by) {
        return getElementWithTimeout(by).getText();
    }

    private boolean isElementExists(By by) {
        try {
            return getElementWithTimeout(by) != null;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Something went wrong", e);
            return false;
        }
    }

}
