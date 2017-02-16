package com.firone.maelstrom.test.utils;

import com.vtence.mario.BrowserDriver;
import com.vtence.mario.UnsynchronizedProber;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UITester {

  private BrowserDriver browser;

  @Before
  public void setUp() throws Exception {
    browser = new BrowserDriver(new UnsynchronizedProber(2000, 50), new FirefoxDriver());
  }

  @After
  public void tearDown() throws Exception {
    browser().quit();
  }

  protected BrowserDriver browser() {
    return browser;
  }

  protected By byElementWithText(String uiText) {
    return By.xpath(String.format("//*[text() = '%s']", uiText.replaceAll("'", "\\'")));
  }
}
