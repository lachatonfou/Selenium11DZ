import PageObjects.HomePage;
import PageObjects.WebFormPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import steps.BaseSteps;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static PageObjects.HomePage.BASE_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebFormTest extends BaseSteps {

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
    void testUploadFile () throws IOException {
        String filePath = "src/main/resources/testFile.txt";

        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        System.out.println("Содержимое файла: " + content);

        //получаем урл файла и переводим его в абсолтный путь
        URL url = WebFormTest.class.getClassLoader().getResource("testFile.txt");
        String absolutePath = new File(url.getPath()).getAbsolutePath();

        webFormPage.getDownloadField().sendKeys(absolutePath);
        webFormPage.getSubmitButton().click();
        assertThat(webFormPage.getCurrentUrl().contains("testFile.txt"));
    }

}
