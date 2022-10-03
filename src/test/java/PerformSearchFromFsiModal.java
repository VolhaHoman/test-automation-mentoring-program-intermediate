import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Test;
import pl.epam.goman.page.Page;

public class PerformSearchFromFsiModal {

    public static final String SEARCH_RESULT = "results for \"Automation\"".toUpperCase();

    @Test
    public void searchFromFsiModal() {

        Page page = new Page();
        page.openMainPage();

        Assert.isTrue(page.isHeaderExists(), "Header not found");

        page.clickSearchButton();

        Assert.isTrue(page.isFrequentSearchesModalExists(), "Frequent searches modal not found");

        page.clickSearchField();
        page.chooseSearchItem();
        page.clickFindButton();
        page.switchContext();

        Assert.isTrue(page.isSearchResultsPageExists(), "Search results page not found");
        Assert.isTrue(page.getResultText().contains(SEARCH_RESULT), "Results not found");

        page.openFirstResultLink();

        Assert.isTrue(page.isOpenResultLinkExists(), "Page not found");

        page.close();

    }
}
