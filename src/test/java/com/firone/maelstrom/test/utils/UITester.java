package com.firone.maelstrom.test.utils;

import com.vtence.mario.BrowserDriver;
import com.vtence.mario.UnsynchronizedProber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UITester {

  private static BrowserDriver browser;

  @BeforeClass
  public static void setUp() throws Exception {
    browser = new BrowserDriver(new UnsynchronizedProber(2000, 50), new FirefoxDriver());
  }

  @AfterClass
  public static void tearDown() throws Exception {
    browser.quit();
  }

  protected BrowserDriver browser() {
    return browser;
  }

  protected By byElementWithText(String uiText) {
    return By.xpath(String.format("//*[text() = '%s']", uiText.replaceAll("'", "\\'")));
  }
}
