import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Test;
import pl.epam.goman.page.Page;

public class PerformSearchByWord {

    private static final String SEARCH_ITEM = "Java";
    public static final String SEARCH_RESULT = "results for \"Java\"".toUpperCase();

    @Test
    public void searchByWord () {

        Page page = new Page();
        page.openMainPage();

        Assert.isTrue(page.isHeaderExists(), "Header not found");

        page.clickSearchButton();

        Assert.isTrue(page.isFrequentSearchesModalExists(), "Frequent searches modal not found");

        page.clickSearchField();
        page.enterSearchItem(SEARCH_ITEM);
        page.clickFindButton();
        page.switchContext();
        Assert.isTrue(page.isSearchResultsPageExists(), "Search results page not found");
        Assert.isTrue(page.getResultText().contains(SEARCH_RESULT), "Results not found");

        page.close();

    }
}
