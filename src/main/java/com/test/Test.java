package com.test;

import com.microsoft.playwright.*;

public class Test {
    public static void main(String[] args) {
        String url = "https://playwright.dev/";
        String expectedTitle = "Fast and reliable end-to-end testing for modern web apps";

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(true);

        try (Playwright playwright = Playwright.create()) {
            String[] browsers = {"chromium", "firefox"};

            for (String browserName : browsers) {
                System.out.println("Проверка в браузере: " + browserName);

                Browser browser = switch (browserName) {
                    case "chromium" -> playwright.chromium().launch(options);
                    case "firefox" -> playwright.firefox().launch(options);
                    default -> throw new IllegalArgumentException("Неизвестный браузер: " + browserName);
                };

                BrowserContext context = browser.newContext();
                Page page = context.newPage();
                page.navigate(url);

                String actualTitle = page.title();
                System.out.println("Заголовок страницы: " + actualTitle);

                if (!actualTitle.equals(expectedTitle)) {
                    System.out.println("Заголовок не совпадает в " + browserName);
                } else {
                    System.out.println("Заголовок совпадает в " + browserName);
                }

                browser.close();
            }
        }
    }


}

   /* 1. Задача по написанию тест-кейсов:
Позитивные тест-кейсы: 
Проверка отображения списка, проверка выбора страны, проверка сортировки, проверка соответствия кода стране, проверка поиска по названию, поддержка локализации
Негативные тест кейсы:
Ввод несуществующей страны, поле без выбора страны, проверка xss в поиске, проверка длинного ввода, неактивный список

    3. Задача по теории тестирования:
Решение: общее число шаров : 7(красные) + 5(зеленые) + 8(синие) = 20
Количество благоприятных исходов: 7(красные) + 5(зеленые) = 12
Вероятность: P(красный или зеленый) = 12/20 = 3/5 = 0.6
Ответ: 0.6 или 60%
       */
