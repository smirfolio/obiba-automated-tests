package org.obiba.mica.uitest.search;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.obiba.mica.uitest.drupal.DrupalUITester;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DrupalSearch extends DrupalUITester {

  private static final String QSC_ALCOHOL_NEOPLASM_SOCIAL_NETWORK_SEARCH = "http://localhost/drupal/mica/repository#/search?query=variable(or(or(in(Mlstr_area.Lifestyle_behaviours,Alcohol),in(Mlstr_area.Diseases,Neoplasms)),in(Mlstr_area.Social_environment,Soc_network))),network(in(Mica_network.id,qsc))";
  private static final String[] TEST_DISPLAY_TABS = new String[] {"datasets", "studies", "networks"};
  private static final String[] TEST_BUCKET_TABS = new String[] {"study"};
  private static final String[] TEST_GRAPHICS = new String[] {"Geographical Distribution", "Study Design", "Participants", "Collected Biological Samples"};

  @Test
  public void test_list_display_counts() {
    browser().navigate().to("http://localhost/drupal");

    for(String tab : TEST_DISPLAY_TABS) {
      browser().navigate().to(String.format(QSC_ALCOHOL_NEOPLASM_SOCIAL_NETWORK_SEARCH + "&display=list&type=%s", tab));
      List<WebElement> variableCountElements = new WebDriverWait(browser().wrappedDriver(), 30)
          .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
              "//tbody[@test-ref='search-results']/tr//a[./localized-number[contains(@value, 'ariables')]]")));
      if (variableCountElements.size() > 0)
        verify_random_variable_count(variableCountElements.size(),
          "//tbody[@test-ref='search-results']/tr[%d]//a[./localized-number[contains(@value, 'ariables')]]");
    }
  }

  @Test
  public void test_coverage_display_counts() {
    browser().navigate().to("http://localhost/drupal");

    for(String tab : TEST_BUCKET_TABS) {
      browser().navigate().to(String.format(QSC_ALCOHOL_NEOPLASM_SOCIAL_NETWORK_SEARCH + "&display=coverage&bucket=%s", tab));
      coverageVariableCounts();
    }
  }

  @Test
  public void test_coverage_display_totals() {
    browser().navigate().to("http://localhost/drupal");

    for(String tab : TEST_BUCKET_TABS) {
      browser().navigate().to(String.format(QSC_ALCOHOL_NEOPLASM_SOCIAL_NETWORK_SEARCH + "&display=coverage&bucket=%s", tab));
      coverageVariableTotals();
    }
  }

  @Test
  public void test_graphics_display_counts() {
    browser().navigate().to("http://localhost/drupal");

    for(String tab : TEST_GRAPHICS) {
      browser().navigate().to(QSC_ALCOHOL_NEOPLASM_SOCIAL_NETWORK_SEARCH + "&display=graphics");
      List<WebElement> studyCountElements = new WebDriverWait(browser().wrappedDriver(), 30)
          .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
              String.format("//div[./div[contains(text(), '%s')]]//tbody//a[.//localized-number]", tab))));
      if (studyCountElements.size() > 0)
        verify_random_graphic_study_count(studyCountElements.size(), tab,
          "(//div[./div[contains(text(), '%s')]]//tbody//a[.//localized-number])[%d]");
    }
  }

  private void verify_random_variable_count(int size, String formattedXpath) {
    int index = new Random(new Date().getTime()).nextInt(size) + 1; // xpath is not zero-based
    WebElement variableCountElement = new WebDriverWait(browser().wrappedDriver(), 30)
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(formattedXpath, index))));

    String count = variableCountElement.getText();
    variableCountElement.click();
    browser().element(By.xpath("//ul[@test-ref='search-counts']//a/span[@test-ref='variables']")).hasText(count);
  }

  private void verify_random_graphic_study_count(int size, String graphicTab, String formattedXpath) {
    int index = new Random(new Date().getTime()).nextInt(size) + 1; // xpath is not zero-based
    WebElement variableCountElement = new WebDriverWait(browser().wrappedDriver(), 30)
        .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(formattedXpath, graphicTab, index))));

    String count = variableCountElement.getText();
    variableCountElement.click();
    browser().element(By.xpath("//ul[@test-ref='search-counts']//a/span[@test-ref='studies']")).hasText(count);
  }

  private void coverageVariableCounts() {
    List<WebElement> coverageVariableCounts = new WebDriverWait(browser().wrappedDriver(), 30)
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tbody//a[.//localized-number]")));
    if (coverageVariableCounts.size() > 0)
      verify_random_variable_count(coverageVariableCounts.size(),
        "(//tbody//a[.//localized-number])[%d]");
  }

  private void coverageVariableTotals() {
    List<WebElement> coverageVariableCounts = new WebDriverWait(browser().wrappedDriver(), 30)
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//tfoot//a[.//localized-number]")));
    if (coverageVariableCounts.size() > 0)
      verify_random_variable_count(coverageVariableCounts.size(),
          "(//tfoot//a[.//localized-number])[%d]");
  }
}
