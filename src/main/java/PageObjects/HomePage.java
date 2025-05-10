package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    public static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    public HomePage(WebDriver driver) {
        super(driver);
        openHome();
    }

    @Step("Open homepage")
    private void openHome() {
        driver.get(BASE_URL);
    }

    @Step("Open Web form page")
    public WebFormPage openWebFormPage() {
        driver.findElement(By.xpath("//a[@href = 'web-form.html']")).click();
        return new WebFormPage(driver);
    }

    @Step("Open download files page")
    public DownloadFilesPage openNavigationPage() {
        driver.findElement(By.xpath("//a[@href = 'download.html']")).click();
        return new DownloadFilesPage(driver);
    }

    @Step("Get web title")
    public String getWebTitle() {
        return driver.getTitle();
    }
}
