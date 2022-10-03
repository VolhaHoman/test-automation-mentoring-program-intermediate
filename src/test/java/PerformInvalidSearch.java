import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Test;
import pl.epam.goman.page.Page;

public class PerformInvalidSearch {

    private static final String SEARCH_ITEM = "=";
    public static final String SEARCH_RESULT = "Sorry, but your search returned no results. Please try another query.";

    @Test
    public void openSearchResult() {

        Page page = new Page();
        page.openMainPage();

        Assert.isTrue(page.isHeaderExists(), "Header not found");

        page.clickSearchButton();

        Assert.isTrue(page.isFrequentSearchesModalExists(), "Frequent searches modal not found");

        page.clickSearchField();
        page.enterSearchItem(SEARCH_ITEM);
        page.clickFindButton();

        Assert.isTrue(page.getInvalidSearchResult().contains(SEARCH_RESULT), "Results not found");

        page.close();

    }
}
