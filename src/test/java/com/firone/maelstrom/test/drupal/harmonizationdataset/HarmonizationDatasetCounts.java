package com.firone.maelstrom.test.drupal.harmonizationdataset;

import com.firone.maelstrom.test.utils.By;
import com.firone.maelstrom.test.utils.UITester;
import org.junit.Test;

public class HarmonizationDatasetCounts extends UITester {

  @Test
  public void check_that_networks_count_in_list_is_valid() {

    browser().navigate().to("http://localhost/drupal/mica/datasets/harmonization-datasets");

    browser().element(By.ref("dataset", "[1]", "networkCount")).hasText("1 Network");
    browser().element(By.ref("dataset", "[1]", "networkCount")).click();
    browser().element(By.ref("search-criterion")).hasText("CPTPCoreQx");

    browser().element(By.ref("search-counts", "variables")).hasText("772");
    browser().element(By.ref("search-counts", "datasets")).hasText("1");
    browser().element(By.ref("search-counts", "studies")).hasText("5");
    browser().element(By.ref("search-counts", "networks")).hasText("2");

    browser().element(By.ref("search-results", "acronym", "[1]")).hasText("CPTP");
  }

  @Test
  public void check_that_study_count_in_list_is_valid() {

    browser().navigate().to("http://localhost/drupal/mica/datasets/harmonization-datasets");

    browser().element(By.ref("dataset", "[1]", "studyCount")).hasText("5 Studies");
    browser().element(By.ref("dataset", "[1]", "studyCount")).click();
    browser().element(By.ref("search-criterion")).hasText("CPTPCoreQx");

    browser().element(By.ref("search-counts", "variables")).hasText("772");
    browser().element(By.ref("search-counts", "datasets")).hasText("1");
    browser().element(By.ref("search-counts", "studies")).hasText("5");
    browser().element(By.ref("search-counts", "networks")).hasText("2");

    browser().element(By.ref("search-results", "acronym", "[1]")).hasText("Atlantic PATH");
  }

  @Test
  public void check_that_variables_count_in_list_is_valid() {

    browser().navigate().to("http://localhost/drupal/mica/datasets/harmonization-datasets");

    browser().element(By.ref("dataset", "[1]", "variableCount")).hasText("772 Variables");
    browser().element(By.ref("dataset", "[1]", "variableCount")).click();
    browser().element(By.ref("search-criterion")).hasText("CPTPCoreQx");

    browser().element(By.ref("search-counts", "variables")).hasText("772");
    browser().element(By.ref("search-counts", "datasets")).hasText("1");
    browser().element(By.ref("search-counts", "studies")).hasText("5");
    browser().element(By.ref("search-counts", "networks")).hasText("2");

    browser().element(By.ref("search-results", "name", "[1]")).hasText("A_ADM_STUDY_ID");
  }
}
