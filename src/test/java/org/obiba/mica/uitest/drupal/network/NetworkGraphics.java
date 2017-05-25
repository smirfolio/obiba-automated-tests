package org.obiba.mica.uitest.drupal.network;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.openqa.selenium.WebElement;

import com.google.common.collect.Lists;

import static org.hamcrest.Matchers.startsWith;

public class NetworkGraphics extends UITester {

  @Test
  public void graphics_network_tab_navigation(){
    get_network();
    List<WebElement> tabs = browser().wrappedDriver()
        .findElements(By.xpath("//div[@graphic-main-charts]/div/ul/li/a"));

    ArrayList<String> graphicTitles = Lists.newArrayList("Distribution of studies by country of residence (N=4)",
        "Distribution of studies by study design (N=4)", "Distribution of studies by number of participants (N=4)",
        "Distribution of studies by biological samples (N=4)");

    for(WebElement tab : tabs) {
      tab.click();
      String text = browser().wrappedDriver()
          .findElement(By.xpath("//div[@graphic-main-charts]//*/div[contains(@class, 'title h4')]")).getText();
      assert graphicTitles.indexOf(text) > -1;
    }
  }

  @Test
  public void test_geo_graphic() {
    get_network();

    browser().element(By.xpath("//div[@graphic-main-charts]/div/ul/li/a[text()='Geographical Distribution']")).click();
    browser().element(By.xpath("//div[@graphic-main-charts]//*[name()='svg']")).exists();

    List<WebElement> affectedCountries = browser().wrappedDriver().findElements(By.xpath(
        "//div[@graphic-main-charts]//*[name()='svg']//*[name()='path' and not(contains(@style, 'fill: rgb(204, 204, 204); stroke: rgb(255, 255, 255);'))]"));
    List<WebElement> tableRows = browser().wrappedDriver()
        .findElements(By.xpath("//div[@graphic-main-charts]//div[@obiba-table]/table/tbody/tr"));

    assert affectedCountries.size() == tableRows.size();
  }

  @Test
  public void test_geo_table_search_navigation() {
    get_network();

    browser().element(By.xpath("//div[@graphic-main-charts]/div/ul/li/a[text()='Geographical Distribution']")).click();
    List<WebElement> tableRows = browser().wrappedDriver()
        .findElements(By.xpath("//div[@graphic-main-charts]//div[@obiba-table]/table/tbody/tr"));

    if (tableRows.size() > 0) {
      click_studies_count_and_check(tableRows.get(0).findElement(By.xpath("//a[contains(@ng-click, 'updateCriteria')]")));
    }
  }

  @Test
  public void test_study_design_graphic() {
    get_network();

    browser().element(By.xpath("//div[@graphic-main-charts]/div/ul/li/a[text()='Study Design']")).click();
    browser().element(By.xpath("//div[@graphic-main-charts]//*[name()='svg']")).exists();

    List<WebElement> designs = browser().wrappedDriver()
        .findElements(By.xpath("//div[@graphic-main-charts]//*[name()='svg']//*[name()='rect']"));
    List<WebElement> tableRows = browser().wrappedDriver()
        .findElements(By.xpath("//div[@graphic-main-charts]//div[@obiba-table]/table/tbody/tr"));

    assert designs.size() == tableRows.size();
  }

  @Test
  public void test_study_design_table_search_navigation() {
    get_network();

    browser().element(By.xpath("//div[@graphic-main-charts]/div/ul/li/a[text()='Study Design']")).click();
    List<WebElement> tableRows = browser().wrappedDriver()
        .findElements(By.xpath("//div[@graphic-main-charts]//div[@obiba-table]/table/tbody/tr"));

    if (tableRows.size() > 0) {
      click_studies_count_and_check(tableRows.get(0).findElement(By.xpath("//a[contains(@ng-click, 'updateCriteria')]")));
    }
  }

  @Test
  public void test_participants_graphic() {
    get_network();

    browser().element(By.xpath("//div[@graphic-main-charts]/div/ul/li/a[text()='Participants']")).click();
    browser().element(By.xpath("//div[@graphic-main-charts]//*[name()='svg']")).exists();

    List<WebElement> participantSlices = browser().wrappedDriver()
        .findElements(By.xpath("//div[@graphic-main-charts]//*[name()='svg']//*[name()='g' and @class='nv-slice']"));
    List<WebElement> tableRows = browser().wrappedDriver()
        .findElements(By.xpath("//div[@graphic-main-charts]//div[@obiba-table]/table/tbody/tr"));

    assert participantSlices.size() == tableRows.size();
  }

  @Test
  public void test_participants_table_search_navigation() {
    get_network();

    browser().element(By.xpath("//div[@graphic-main-charts]/div/ul/li/a[text()='Participants']")).click();
    List<WebElement> tableRows = browser().wrappedDriver()
        .findElements(By.xpath("//div[@graphic-main-charts]//div[@obiba-table]/table/tbody/tr"));

    if (tableRows.size() > 0) {
      click_studies_count_and_check(tableRows.get(0).findElement(By.xpath("//a[contains(@ng-click, 'updateCriteria')]")));
    }
  }

  @Test
  public void test_bio_samples_graphic() {
    get_network();

    browser().element(By.xpath("//div[@graphic-main-charts]/div/ul/li/a[text()='Collected Biological Samples']")).click();
    browser().element(By.xpath("//div[@graphic-main-charts]//*[name()='svg']")).exists();

    List<WebElement> bioSamples = browser().wrappedDriver().findElements(By.xpath(
        "//div[@graphic-main-charts]//*[name()='svg']//*[name()='g' and contains(@class, 'nv-barsWrap')]//*[name()='g' and contains(@class, 'nv-bar')]"));
    List<WebElement> tableRows = browser().wrappedDriver()
        .findElements(By.xpath("//div[@graphic-main-charts]//div[@obiba-table]/table/tbody/tr"));

    assert bioSamples.size() == tableRows.size();
  }

  @Test
  public void test_bio_samples_table_search_navigation() {
    get_network();

    browser().element(By.xpath("//div[@graphic-main-charts]/div/ul/li/a[text()='Collected Biological Samples']")).click();
    List<WebElement> tableRows = browser().wrappedDriver()
        .findElements(By.xpath("//div[@graphic-main-charts]//div[@obiba-table]/table/tbody/tr"));

    if (tableRows.size() > 0) {
      click_studies_count_and_check(tableRows.get(0).findElement(By.xpath("//a[contains(@ng-click, 'updateCriteria')]")));
    }
  }

  private void get_network() {
    browser().navigate().to("http://localhost/drupal/mica/network/qsc");
    browser().element(By.xpath("((//div[@class='markdown']))//p[1]")).hasText(startsWith("The Quebec Study Catalogue is a Maelstrom Research initiative aiming to document and promote the scientific"));
  }

  private void click_studies_count_and_check(WebElement link) {
    String count = link.getText();
    link.click();
    browser().element(By.ref("search-counts", "studies")).hasText(count);
  }
}
