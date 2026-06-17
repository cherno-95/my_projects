package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class BasketPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By basketNotifyBadgeIcon = By.xpath("//*[@data-testid='basket-header']//*[contains(@class, 'notify')]");
    private final By productTitle = By.xpath("//*[contains(@class,'productTitle')]");

    @Step("Проверяем количество товаров в корзине: '{expectedCount}'")
    public BasketPage checkBasketItemsCount(String expectedCount) {
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(basketNotifyBadgeIcon)).getText().trim();
        assertEquals("Количество товаров в корзине не соответствует ожидаемому", text, expectedCount);
        return this;
    }

    @Step("Проверяем наличие товара '{productName}' в корзине")
    public BasketPage checkProductInBasket(String productName) {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
        String text = title.getText();
        assertEquals("Товар '" + productName + "' не найден в корзине", text, productName);
        return this;
    }
}