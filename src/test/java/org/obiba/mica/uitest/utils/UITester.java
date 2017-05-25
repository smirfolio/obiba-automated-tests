package org.obiba.mica.uitest.utils;

import com.google.common.collect.ImmutableMap;
import com.vtence.mario.BrowserDriver;
import com.vtence.mario.UnsynchronizedProber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class UITester {

  private static BrowserDriver browser;
  private static String homeDriver;

  @BeforeClass
  public static void setUp() throws Exception {
    if (System.getenv("WEBDRIVER") != null && System.getenv("WEBDRIVER").contains("chrome")) {
      DesiredCapabilities capabilities = DesiredCapabilities.chrome();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--incognito");
      options.addArguments("--window-size=1400,1000");
      options.addArguments("--window-position=5,5");
      capabilities.setCapability(ChromeOptions.CAPABILITY, options);
      homeDriver = System.getenv("HOME");
      ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
              .usingDriverExecutable(new File(homeDriver + "/chromeDriver/chromedriver"))
              .usingAnyFreePort()
              .withEnvironment(ImmutableMap.of("DISPLAY", ":2"))
              .build();
      chromeDriverService.start();
      ChromeDriver chromeDriver = new ChromeDriver(chromeDriverService, options);
      browser = new BrowserDriver(new UnsynchronizedProber(3000, 500), chromeDriver);
    } else {
      FirefoxDriver fireFoxdriver = new FirefoxDriver();
      fireFoxdriver.manage().window().setSize(new Dimension(1400, 100));
      fireFoxdriver.manage().window().setPosition(new Point(5, 5));
      browser = new BrowserDriver(new UnsynchronizedProber(3000, 50), fireFoxdriver);
    }
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
