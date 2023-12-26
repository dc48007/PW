package com.playwrightdemo;

import com.microsoft.playwright.*;

public class BrowserInitialization {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
      BrowserContext context = browser.newContext();
      Page page = context.newPage();
      page.navigate("https://www.google.co.in");
      page.getByLabel("Sign in").click();
      page.getByLabel("Email or phone").click();
      page.getByLabel("Email or phone").fill("deepakchaudhary3191");
      page.getByLabel("Email or phone").press("Enter");
      page.getByLabel("Try again").click();
      page.getByLabel("Email or phone").click();
      page.getByLabel("Email or phone").fill("deepakchaudhary3191@gmail.com");
      page.getByLabel("Email or phone").press("Enter");
      page.getByText("Try using a different browser").click();
    }
  }
}
