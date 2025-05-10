package steps;

import com.google.common.io.Files;
import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;

import java.io.*;

import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;

public class AllureSteps {
    @Attachment(value = "Screenshot", type = "image/png")
    @Step("Capture screenshot")
    public byte[] captureScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    @Step("Capture screenshot with Selenide")
    public byte[] captureScreenshotSelenide() throws IOException {
        return Files.toByteArray(takeScreenShotAsFile());
    }

    @Attachment(value = "Screenshot", type = "image/png")
    @Step("Capture screenshot with Playwright")
    public byte[] captureScreenshotPlaywright(Page page) {
        return page.screenshot();
    }

    @Step("Capture screenshot (spoiler)")
    public void captureScreenshotSpoiler(WebDriver driver) {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Capture screenshot (extension)")
    public void captureScreenshotSpoiler() {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) BaseSteps.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Capture screenshot with Selenide (extension)")
    public void captureScreenshotSelenideSpoiler() throws IOException {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(Files.toByteArray(takeScreenShotAsFile())));
    }

    @Step("Capture screenshot with Playwright (extension)")
    public void captureScreenshotPlaywrightSpoiler(Page page) {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(page.screenshot()));
    }

    @Step("Download file: {destination}")
    public void download(String link, File destination) throws IOException {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpUriRequestBase request = new HttpGet(link);
            client.execute(request, (HttpClientResponseHandler<byte[]>) response -> {

                // создаем временный файл для хранения содержимого
                File tempFile = File.createTempFile("temp", ".tmp");

                try (InputStream inputStream = response.getEntity().getContent()) {
                    // сохраняем содержимое во временный файл
                    FileUtils.copyInputStreamToFile(inputStream, tempFile);
                }

                // копируем из временного файла в целевой
                FileUtils.copyFile(tempFile, destination);

                // добавляем вложение в алюр из временного файла
                try (InputStream is = new FileInputStream(tempFile)) {
                    Allure.addAttachment(destination.getName(), is);
                }

                // удаляем временный файл
                tempFile.delete();

                return null;
            });
        }
    }
}
