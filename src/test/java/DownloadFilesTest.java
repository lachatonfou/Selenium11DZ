import PageObjects.DownloadFilesPage;
import PageObjects.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import steps.BaseSteps;

import java.io.File;
import java.io.IOException;

import static PageObjects.HomePage.BASE_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DownloadFilesTest extends BaseSteps {
    HomePage homePage;
    DownloadFilesPage downloadFilesPage;

    @BeforeEach
    void setupWebForm() {
        homePage = new HomePage(driver);
        downloadFilesPage = homePage.openDownloadFilesPage();
    }

    @Test
    void openWebForm () {
        String currentUrl = downloadFilesPage.getCurrentUrl();
        String webFormUrl = downloadFilesPage.getDownloadFilesUrl();
        assertEquals(BASE_URL + webFormUrl, currentUrl);

        WebElement title = downloadFilesPage.getTitle();
        assertEquals("Download files", title.getText());
    }

    @Test
    void downloadLogoTest () throws IOException {
        downloadFilesPage.getWebDriverManagerLogoButton().click();
        File pngFile = new File(".", "webdrivermanager.png");
        allureSteps.download(downloadFilesPage.getWebDriverManagerLogoButton().getAttribute("href"), pngFile);
        assertThat(pngFile).exists();
    }

    @Test
    void downloadDocTest () throws IOException {
        downloadFilesPage.getWebDriverManagerDocButton().click();
        File pdfFile = new File(".", "webdrivermanager.pdf");
        allureSteps.download(downloadFilesPage.getWebDriverManagerDocButton().getAttribute("href"), pdfFile);
        assertThat(pdfFile).exists();
    }
}
