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


