import PageObjects.HomePage;
import PageObjects.WebFormPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import ui.BaseTest;

import static PageObjects.HomePage.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebFormDownloadTest extends BaseTest {

    HomePage homePage;
    WebFormPage webFormPage;

    @BeforeEach
    void setupWebForm() {
        homePage = new HomePage(driver);
        webFormPage = homePage.openWebFormPage();
    }

    @Test
    void openWebForm () {
        String currentUrl = webFormPage.getCurrentUrl();
        String webFormUrl = webFormPage.getWebFormUrl();
        assertEquals(BASE_URL + webFormUrl, currentUrl);

        WebElement title = webFormPage.getTitle();
        assertEquals("Web form", title.getText());
    }

    @Test
    void testDownloadFile () {




    }

}
