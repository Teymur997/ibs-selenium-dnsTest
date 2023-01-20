package ru.ibs.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ibs.framework.BaseTests;
import ru.ibs.framework.Product;

public class DnsTest extends BaseTests {
    @Test
    @DisplayName("Тест")
    public void test() {
        Product iphone = new Product("iphone", 0, Product.GuaranteeType.ONE_YEAR.toString(), 0, null, 0);
        Product airpods = new Product("Apple AirPods Pro 2", 0, Product.GuaranteeType.NONE.toString(), 0, null, 0);

        System.out.println("\nДанные продукта:" +
                        "Название: " + iphone.getProductName() +
                        "\nЦена: " + iphone.getProductPrice() +
                        "\nТип гарантии: " + iphone.getGuarantee() +
                        "\nЦена гарантии: " + iphone.getGuaranteePrice() +
                        "\nАртикль: " + iphone.getArticle() +
                        "\nКоличество: " + iphone.getCount());

        System.out.println("\nДанные продукта:" +
                        "Название: " + airpods.getProductName() +
                        "\nЦена: " + airpods.getProductPrice() +
                        "\nТип гарантии: " + airpods.getGuarantee() +
                        "\nЦена гарантии: " + airpods.getGuaranteePrice() +
                        "\nАртикль: " + airpods.getArticle() +
                        "\nКоличество: " + airpods.getCount());


        pageManager.getMainBottomMenuPage()
                .checkIfPageIsOpened()
                .fillSearchInputField(iphone.getProductName())
                .clickSearchButton()
                .chooseProductByArticle("5072935")
                .saveProductName(iphone)
                .saveProductPrice(iphone)
                .saveArticle(iphone)
                .clickGuaranteeButton()
                .checkGuaranteeButtonCondition()
                .chooseGuaranteeOption(iphone.getGuarantee())
                .checkPriceConditionChanging()
                .saveGuaranteePrice(iphone)
                .clickBuyButton()
                .getMainBottomMenuPage()
                .fillSearchInputField(airpods.getProductName())
                .clickSearchButton()
                .getProductCartPage()
                .saveProductName(airpods)
                .saveProductPrice(airpods)
                .saveArticle(airpods)
                .clickBuyButton()
                .getMainBottomMenuPage()
                .basketPriceCompare(
                        iphone.getProductPrice()
                        +iphone.getGuaranteePrice()
                        +airpods.getProductPrice()
                        +airpods.getGuaranteePrice())
                .getMainBottomMenuPage()
                .clickBasketButton()
                .checkIfProductGuaranteeButtonChecked(iphone)
                .checkProductPrice(iphone)
                .checkProductPrice(airpods)
                .saveCountValue(iphone)
                .saveCountValue(airpods)
                .deleteProduct(airpods)
                .basketPriceCompare(
                        iphone.getCount() * (iphone.getProductPrice() + iphone.getGuaranteePrice()))
                .checkProductAbsence(airpods)
                .addProduct(iphone, 2)
                .saveCountValue(iphone)
                .basketPriceCompare(
                        iphone.getCount() * (iphone.getGuaranteePrice() + iphone.getProductPrice()))
                .restoreLastRemovedProduct()
                .checkProductPresence(airpods)
                .saveCountValue(iphone)
                .saveCountValue(airpods)
                .basketPriceCompare(
                        (iphone.getCount() * (iphone.getGuaranteePrice()+ iphone.getProductPrice()))
                        + (airpods.getCount() * airpods.getProductPrice()));






        System.out.println("\nДанные продукта:" +
                "Название: " + iphone.getProductName() +
                "\nЦена: " + iphone.getProductPrice() +
                "\nТип гарантии: " + iphone.getGuarantee() +
                "\nЦена гарантии: " + iphone.getGuaranteePrice() +
                "\nАртикль: " + iphone.getArticle() +
                "\nКоличество: " + iphone.getCount());

        System.out.println("\nДанные продукта:" +
                "Название: " + airpods.getProductName() +
                "\nЦена: " + airpods.getProductPrice() +
                "\nТип гарантии: " + airpods.getGuarantee() +
                "\nЦена гарантии: " + airpods.getGuaranteePrice() +
                "\nАртикль: " + airpods.getArticle() +
                "\nКоличество: " + airpods.getCount());


        /*
        1. открыть Открыть dns-shop https://www.dns-shop.ru
        2. искать iphone
        3. найти и кликнуть на товар с артикулом 5072935
        4. Запомнить цену
        5. Доп.гарантия - выбрать любую доп гарантию
        6. Дождаться изменения цены и запомнить цену самой доп гарантии
        7. Нажать Купить
        8. Выполнить поиск "Apple AirPods Pro 2"
        9. Запомнить цену
        10. Нажать купить
        11. Проверить что цена корзины стала равна сумме покупок
        12. Перейти в корзину
        13. Проверить, что для телефона выбрана нужная гарантия
        14. Проверить цену каждого из товаров и сумму
        15. Удалить из корзины "Apple AirPods Pro 2"
        16. Проверить что "Apple AirPods Pro 2" нет больше в корзине, и что сумма
        уменьшилась на цену "Apple AirPods Pro 2"
        17. Добавить еще 2 iphone и проверить, что сумма верна
        18. Нажать "вернуть удаленный товар" и проверить, что "Apple AirPods Pro 2" появился в корзине и сумма увеличилась на его значение

        * Проработка пейджинатора(поиск товаров постранично)
        * Класс Product (можно сохранять простыми переменными)
        */
    }
}
