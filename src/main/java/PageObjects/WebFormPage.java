package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebFormPage extends BasePage{
    private static final String WEB_FORM_URL = "web-form.html";

    By download = By.name("my-file");
    By submit = By.xpath("//button[@type = 'submit']");

    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get subpage Url")
    public String getWebFormUrl() {
        return WEB_FORM_URL;
    }

    @Step("Get download field")
    public WebElement getDownloadField() {
        return driver.findElement(download);
    }

    @Step("Get submit button")
    public WebElement getSubmitButton() {
        return driver.findElement(submit);
    }
}
