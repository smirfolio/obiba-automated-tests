package org.obiba.mica.uitest.utils;
import java.util.Arrays;
import com.vtence.mario.BrowserDriver;
import com.vtence.mario.UnsynchronizedProber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class UITester {

  private static BrowserDriver browser;
  private static String envVar;
  @BeforeClass
  public static void setUp() throws Exception {
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    String[] switches = {"--incognito"};
    capabilities.setCapability("chrome.switches", Arrays.asList(switches));
    System.setProperty("HOME", "/home/samir");
    envVar = System.getProperty("HOME");
    System.setProperty("webdriver.chrome.driver",envVar + "/chromeDriver/chromedriver");

   browser = new BrowserDriver(new UnsynchronizedProber(3000, 50), new ChromeDriver());
//    System.setProperty("webdriver.firefox.bin", "/usr/bin/firefox");
//    BrowserDriver browser = new BrowserDriver(new UnsynchronizedProber(1000, 50), new FirefoxDriver());
    browser.wrappedDriver().manage().window().maximize();
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
