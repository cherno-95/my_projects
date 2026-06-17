package tests;

import helper.BaseUITest;
import helper.TestListener;
import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import page.BasketPage;
import page.CatalogPage;

@Listeners({AllureTestNg.class, TestListener.class})
public class ProductPurchaseTest extends BaseUITest {

    @Test()
    @Description("Добавление товара в корзину")
    public void testProductPurchase() {
        // ТЕСТОВЫЕ ДАННЫЕ
        String url = "https://www.wildberries.by/catalog/264220770/detail.aspx";
        String goodsCount = "1";

        // ТЕСТОВЫЙ СЦЕНАРИЙ
        driver.get(url);
        CatalogPage catalogPage = new CatalogPage(driver)
                .waitProductPageLoaded();

        String productTitle = catalogPage.getProductTitle();

        BasketPage basketPage = catalogPage
                .clickAddInBasketButton()
                .clickBasketButton();
        basketPage
                .checkBasketItemsCount(goodsCount)
                .checkProductInBasket(productTitle);
    }
}