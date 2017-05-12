package org.obiba.mica.uitest.drupal.study;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.junit.Test;

public class StudyCounts extends UITester {

  @Test
  public void check_that_network_count_in_list_is_valid() throws Exception {

    browser().navigate().to("http://localhost/drupal/mica/studies?page=1");

    browser().element(By.ref("study", "[2]", "networkCount")).hasText("3 Networks");
    browser().element(By.ref("study", "[2]", "networkCount")).click();
    browser().element(By.ref("search-criterion")).hasText("NuAge");

    browser().element(By.ref("search-counts", "variables")).hasText("14,480");
    browser().element(By.ref("search-counts", "datasets")).hasText("29");
    browser().element(By.ref("search-counts", "studies")).hasText("9");
    browser().element(By.ref("search-counts", "networks")).hasText("3");

    browser().element(By.ref("search-results", "acronym", "[1]")).hasText("IALSA");
  }

  @Test
  public void check_that_datasets_count_in_list_is_valid() throws Exception {

    browser().navigate().to("http://localhost/drupal/mica/studies?page=1");

    browser().element(By.ref("study", "[2]", "datasetCount")).hasText("4 Datasets");
    browser().element(By.ref("study", "[2]", "datasetCount")).click();
    browser().element(By.ref("search-criterion")).hasText("NuAge");

    browser().element(By.ref("search-counts", "variables")).hasText("4,081");
    browser().element(By.ref("search-counts", "datasets")).hasText("4");
    browser().element(By.ref("search-counts", "studies")).hasText("1");
    browser().element(By.ref("search-counts", "networks")).hasText("3");

    browser().element(By.ref("search-results", "acronym", "[1]")).hasText("NuAge-T3");
  }

  @Test
  public void check_that_studyVariables_count_in_list_is_valid() throws Exception {

    browser().navigate().to("http://localhost/drupal/mica/studies?page=1");

    browser().element(By.ref("study", "[2]", "studyVariableCount")).hasText("4,081 Study Variables");
    browser().element(By.ref("study", "[2]", "studyVariableCount")).click();
    browser().element(By.ref("search-criterion", "[1]")).hasText("NuAge");
    browser().element(By.ref("search-criterion", "[2]")).hasText("Study");

    browser().element(By.ref("search-counts", "variables")).hasText("4,081");
    browser().element(By.ref("search-counts", "datasets")).hasText("4");
    browser().element(By.ref("search-counts", "studies")).hasText("1");
    browser().element(By.ref("search-counts", "networks")).hasText("3");

    browser().element(By.ref("search-results", "name", "[1]")).hasText("A1BT3");
  }
}
