package org.obiba.mica.uitest.drupal.network;

import org.junit.Test;
import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.openqa.selenium.Keys;

import static org.hamcrest.Matchers.startsWith;

public class NetworkGraphics extends UITester {

  @Test
  public void graphics_network_tab_navigation(){
    get_network();
    browser().element(By.xpath("//div[@class='title h4'][1]")).hasText("Distribution of studies by country of residence (N=4)");
    browser().element(By.text("Study Design")).click();
    browser().element(By.xpath("//div[@class='title h4'][1]")).hasText("Distribution of studies by study design (N=4)");
    browser().element(By.text("Participants")).click();
    browser().element(By.xpath("//div[@class='title h4'][1]")).hasText("Distribution of studies by number of participants (N=4)");
    browser().element(By.text("Collected Biological Samples")).click();
    browser().element(By.xpath("//div[@class='title h4'][1]")).hasText("Distribution of studies by biological samples (N=4)");
    browser().element(By.text("Geographical Distribution")).click();
    browser().element(By.xpath("//div[@class='title h4'][1]")).hasText("Distribution of studies by country of residence (N=4)");
  }

  @Test
  public void test_geo_graphic(){
    get_network();
    browser().element(By.xpath("//div[@class='title h4'][1]")).hasText("Distribution of studies by country of residence (N=4)");
    browser().element(By.xpath("//div[@chart-type='Table-GeoChart']//td[1]")).hasText("Canada");
    browser().element(By.xpath("//div[@obiba-chart]//*[name()='svg']//*[name()='g']//*[name()='path'][starts-with(@d, 'M190')]")).hoverWithMouse();
    browser().element(By.xpath("//div[@class='chart-tooltip']")).hasText("Canada 4");
    browser().element(By.xpath("//div[@chart-type='Table-GeoChart']//td[2]//a")).click();
    browser().element(By.ref("search-counts", "variables")).hasText("6,781");
    browser().element(By.ref("search-counts", "studies")).hasText("4");
  }

  @Test
  public void test_study_design(){
    get_network();
    browser().element(By.text("Study Design")).click();
    browser().element(By.text("Study Design")).enterTextUsingKeyboard(Keys.PAGE_DOWN);
    browser().element(By.xpath("//div[@obiba-chart]//*[name()='svg']//*[name()='g']//*[name()='rect'][1]")).hoverWithMouse();
    browser().element(By.xpath("//div[@class='chart-tooltip']//span")).hasText("Studies: 4");
    browser().element(By.xpath("//div[@chart-type]//*[text() = 'Cohort']/..//td[2]")).hasText("4");
    browser().element(By.xpath("//div[@chart-type]//*[text() = 'Cohort']/..//a")).click();
    browser().pause(500);
    browser().element(By.ref("search-counts", "studies")).hasText("4");
    browser().element(By.ref("search-counts", "variables")).hasText("6,781");
  }

  @Test
  public void test_participants(){
    get_network();
    browser().element(By.text("Participants")).click();
    browser().element(By.text("Participants")).enterTextUsingKeyboard(Keys.PAGE_DOWN);
    browser().element(By.xpath("//div[@obiba-chart]//*[name()='svg']//*[name()='g']//*[name()='path'][starts-with(@d, 'M-80,9')]")).hoverWithMouse();
    browser().element(By.xpath("//div[@class='chart-tooltip']//span")).hasText("50,000 to 99,999: 1");

    browser().element(By.xpath("//*[text() = '1,000 to 4,999']/..//a")).click();
    browser().element(By.ref("search-counts", "studies")).hasText("2");
    browser().element(By.ref("search-counts", "variables")).hasText("4,964");
    return_back("Participants");

    browser().element(By.xpath("//*[text() = '10,000 to 49,999']/..//a")).click();
    browser().element(By.ref("search-counts", "studies")).hasText("1");
    browser().element(By.ref("search-counts", "variables")).hasText("772");
    return_back("Participants");

    browser().element(By.xpath("//*[text() = '50,000 to 99,999']/..//a")).click();
    browser().element(By.ref("search-counts", "studies")).hasText("1");
    browser().element(By.ref("search-counts", "variables")).hasText("1,045");
  }

  @Test
  public void test_bio_samples(){
    get_network();
    browser().element(By.text("Collected Biological Samples")).click();
    browser().element(By.text("Collected Biological Samples")).enterTextUsingKeyboard(Keys.PAGE_DOWN);
    browser().element(By.xpath("//div[@obiba-chart]//*[name()='svg']//*[name()='g']//*[name()='g'][contains(@transform,'0,24')]")).hoverWithMouse();
    browser().element(By.xpath("//div[@class='chart-tooltip']//span")).hasText("Studies: 3");
    browser().element(By.xpath("//div[@obiba-chart]//*[name()='svg']//*[name()='g']//*[name()='g'][contains(@transform,'0,72')]")).hoverWithMouse();
    browser().element(By.xpath("//div[@class='chart-tooltip']//span")).hasText("Studies: 3");
    browser().element(By.xpath("//div[@obiba-chart]//*[name()='svg']//*[name()='g']//*[name()='g'][contains(@transform,'0,121')]")).hoverWithMouse();
    browser().element(By.xpath("//div[@class='chart-tooltip']//span")).hasText("Studies: 2");

    browser().element(By.xpath("//*[text() = 'Blood']/..//a")).click();
    browser().element(By.ref("search-counts", "studies")).hasText("3");
    browser().element(By.ref("search-counts", "variables")).hasText("5,898");
    return_back("Collected Biological Samples");

    browser().element(By.xpath("//*[text() = 'Urine']/..//a")).click();
    browser().element(By.ref("search-counts", "studies")).hasText("3");
    browser().element(By.ref("search-counts", "variables")).hasText("5,898");
    return_back("Collected Biological Samples");

    browser().element(By.xpath("//*[text() = 'Saliva']/..//a")).click();
    browser().element(By.ref("search-counts", "studies")).hasText("2");
    browser().element(By.ref("search-counts", "variables")).hasText("4,853");
    return_back("Collected Biological Samples");

  }

  private void return_back(String tab){
    browser().element(By.ref("search-counts", "networks")).hasText("1");
    browser().element(By.ref("search-counts", "networks")).click();
    browser().element(By.xpath("//a[@ng-href='/drupal/mica/network/qsc']")).click();
    browser().element(By.text(tab)).enterTextUsingKeyboard(Keys.PAGE_DOWN);
    browser().pause(100);
    browser().element(By.text(tab)).click();
  }

  private void get_network(){
    browser().navigate().to("http://localhost/drupal/mica/network/qsc");
    browser().element(By.xpath("((//div[@class='markdown']))//p[1]")).hasText(startsWith("The Quebec Study Catalogue is a Maelstrom Research initiative aiming to document and promote the scientific"));
  }
}
