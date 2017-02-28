package org.obiba.mica.uitest.utils;

import com.vtence.mario.BrowserDriver;
import com.vtence.mario.UnsynchronizedProber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UITester {

  private static BrowserDriver browser;

  @BeforeClass
  public static void setUp() throws Exception {
    browser = new BrowserDriver(new UnsynchronizedProber(3000, 50), new FirefoxDriver());
  }

  @AfterClass
  public static void tearDown() throws Exception {
    browser.quit();
  }

  protected BrowserDriver browser() {
    return browser;
  }

  protected org.openqa.selenium.By currentModalThenRefs(String... references) {
    return By.xpath(By.mapRefToXpathWithPrefix(currentModalPath(), references));
  }

  protected org.openqa.selenium.By currentModalBy() {
    return By.xpath(currentModalPath());
  }

  private String currentModalPath() {
    return "//*[@class='modal fade in']";
  }
}
