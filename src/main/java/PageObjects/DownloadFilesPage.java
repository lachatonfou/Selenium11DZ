package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DownloadFilesPage extends BasePage{
    private static final String DOWNLOAD_FILES_URL = "download.html";

    By webDriverManagerLogo = By.xpath("//a[@download = 'webdrivermanager.png']");
    By webDriverManagerDoc = By.xpath("//a[@download = 'webdrivermanager.pdf']");

    public DownloadFilesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get subpage Url")
    public String getDownloadFilesUrl() {
        return DOWNLOAD_FILES_URL;
    }

    @Step("Get WebDriverManager logo button")
    public WebElement getWebDriverManagerLogoButton() {
        return driver.findElement(webDriverManagerLogo);
    }

    @Step("Get WebDriverManager doc button")
    public WebElement getWebDriverManagerDocButton() {
        return driver.findElement(webDriverManagerDoc);
    }
}
