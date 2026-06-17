package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class BasketPage {

    private final WebDriver driver;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By basketNotifyBadgeIcon = By.xpath("//*[@data-testid='basket-header']//*[contains(@class, 'notify')]");
    private final By productTitle = By.xpath("//*[contains(@class,'productTitle')]");

    @Step("Проверяем количество товаров в корзине: '{productCount}'")
    public BasketPage checkBasketItemsCount(int productCount) {
        int actual = Integer.parseInt(driver.findElement(basketNotifyBadgeIcon)
                        .getText()
                        .replaceAll("\\D+", ""));
        assertEquals("Количество товаров в корзине не соответствует ожидаемому", productCount, actual);
        return this;
    }

    @Step("Проверяем наличие товара '{productName}' в корзине")
    public BasketPage checkProductInBasket(String productName) {
        boolean exists = driver.findElements(productTitle)
                .stream()
                .anyMatch(el -> el.getText().trim().equals(productName));
        assertTrue("Товар '" + productName + "' не найден в корзине", exists);
        return this;
    }
}