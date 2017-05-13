package org.obiba.mica.uitest.drupal.network;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.junit.Test;

public class NetworkCounts extends UITester {

  @Test
  public void check_that_studies_count_in_list_is_valid() {

    browser().navigate().to("http://localhost/drupal/mica/networks");

    browser().element(By.ref("network", "[3]", "studyCount")).hasText("4 Studies");
    browser().element(By.ref("network", "[3]", "studyCount")).click();
    browser().element(By.ref("search-criterion")).hasText("QSC");

    browser().element(By.ref("search-counts", "variables")).hasText("6,781");
    browser().element(By.ref("search-counts", "datasets")).hasText("7");
    browser().element(By.ref("search-counts", "studies")).hasText("4");
    browser().element(By.ref("search-counts", "networks")).hasText("1");

    browser().element(By.ref("search-results", "acronym", "[1]")).hasText("CLSA");
  }

  @Test
  public void check_that_studiesWithVariables_count_in_list_is_valid() {

    browser().navigate().to("http://localhost/drupal/mica/networks");

    browser().element(By.ref("network", "[3]", "studyWithVariablesCount")).hasText("3 Studies with Variables");
    browser().element(By.ref("network", "[3]", "studyWithVariablesCount")).click();
    browser().element(By.ref("search-criterion", "[1]")).hasText("QSC");
    browser().element(By.ref("search-criterion", "[2]")).hasText("Study");

    browser().element(By.ref("search-counts", "variables")).hasText("6,009");
    browser().element(By.ref("search-counts", "datasets")).hasText("6");
    browser().element(By.ref("search-counts", "studies")).hasText("3");
    browser().element(By.ref("search-counts", "networks")).hasText("1");

    browser().element(By.ref("search-results", "acronym", "[1]")).hasText("CLSA");
  }

  @Test
  public void check_that_datasets_count_in_list_is_valid() {

    browser().navigate().to("http://localhost/drupal/mica/networks");

    browser().element(By.ref("network", "[3]", "datasetCount")).hasText("6 Datasets");
    browser().element(By.ref("network", "[3]", "datasetCount")).click();
    browser().element(By.ref("search-criterion", "[1]")).hasText("QSC");

    browser().element(By.ref("search-counts", "variables")).hasText("6,781");
    browser().element(By.ref("search-counts", "datasets")).hasText("7");
    browser().element(By.ref("search-counts", "studies")).hasText("4");
    browser().element(By.ref("search-counts", "networks")).hasText("1");

    browser().element(By.ref("search-results", "acronym", "[1]")).hasText("CLSA");
  }

  @Test
  public void check_that_studyVariables_count_in_list_is_valid() {

    browser().navigate().to("http://localhost/drupal/mica/networks");

    browser().element(By.ref("network", "[3]", "studyVariableCount")).hasText("6,009 Study Variables");
    browser().element(By.ref("network", "[3]", "studyVariableCount")).click();
    browser().element(By.ref("search-criterion", "[1]")).hasText("QSC");
    browser().element(By.ref("search-criterion", "[2]")).hasText("Study");

    browser().element(By.ref("search-counts", "variables")).hasText("6,009");
    browser().element(By.ref("search-counts", "datasets")).hasText("6");
    browser().element(By.ref("search-counts", "studies")).hasText("3");
    browser().element(By.ref("search-counts", "networks")).hasText("1");

    browser().element(By.ref("search-results", "name", "[1]")).hasText("startdate");
  }
}
