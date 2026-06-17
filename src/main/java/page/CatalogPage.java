package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CatalogPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    private final By productTitle = By.xpath("//*[contains(@class,'productTitle')]");
    private final By addInBasketButton = By.xpath("//button[@aria-label='Добавить в корзину']");
    private final By inBasketButton = By.xpath("//*[contains(@class,'actionsBlock')]/descendant::button[@aria-label='В корзине']");
    private final By basketButton = By.xpath("//*[@data-testid='basket-header']/span");

    @Step("Ожидаем загрузку страницы с товаром")
    public CatalogPage waitProductPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
        return this;
    }

    @Step("Нажимаем на кнопку 'Добавить в корзину'")
    public CatalogPage clickAddInBasketButton() {
        driver.findElement(addInBasketButton).click();
        driver.findElement(addInBasketButton).isEnabled();
        driver.findElement(inBasketButton).isEnabled();
        return this;
    }

    @Step("Получаем название товара")
    public String getProductTitle() {
        String titleText = driver.findElement(productTitle).getText().trim();
        return titleText;
    }

    @Step("Нажимаем на кнопку 'Корзина'")
    public BasketPage clickBasketButton() {
        driver.findElement(basketButton).click();
        return new BasketPage(driver);
    }
}