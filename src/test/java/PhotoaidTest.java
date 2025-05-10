import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.BaseSteps;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class PhotoaidTest extends BaseSteps {

    @Test
    void uploadTest() throws InterruptedException, IOException {
        String filePath = "src/main/resources/bug.png";

        open("https://photoaid.com/ru/ru/tools/greyscale");

        SelenideElement fileInput = $("input[type='file']");

        //загружаем файл
        URL url = PhotoaidTest.class.getClassLoader().getResource("bug.png");
        String absolutePath = new File(url.getPath()).getAbsolutePath();
        fileInput.sendKeys(absolutePath);

        SelenideElement downloadButton = $("a[download]");
        downloadButton.shouldBe(visible);

        //скачиваем файл
        downloadButton.click();

        Thread.sleep(2000);
        //WebElement downloadedPic = driver.findElement(By.xpath("//canvas[@id = 'canvas']"));
        SelenideElement downloadedPic = $(By.xpath("//img[@id = 'photoSize']"));
        //downloadedPic.shouldBe(visible);
        String imgURL = downloadedPic.getAttribute("src");

        File pngFile = new File(".", "downloadBug.png");
        allureSteps.download(imgURL, pngFile);
        Thread.sleep(2000);
        assertThat(pngFile).exists();
    }
}
