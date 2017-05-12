package org.obiba.mica.uitest.drupal.studydataset;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.junit.Test;

public class StudyDatasetCounts extends UITester {

  @Test
  public void check_that_networks_count_in_list_is_valid() {

    browser().navigate().to("http://localhost/drupal/mica/datasets/study-datasets");

    browser().element(By.ref("dataset", "[5]", "networkCount")).hasText("4 Networks");
    browser().element(By.ref("dataset", "[5]", "networkCount")).click();
    browser().element(By.ref("search-criterion")).hasText("CLSA");

    browser().element(By.ref("search-counts", "variables")).hasText("1,045");
    browser().element(By.ref("search-counts", "datasets")).hasText("1");
    browser().element(By.ref("search-counts", "studies")).hasText("1");
    browser().element(By.ref("search-counts", "networks")).hasText("4");

    browser().element(By.ref("search-results", "acronym", "[1]")).hasText("CCLSA");
  }

  @Test
  public void check_that_study_count_in_list_is_valid() {

    browser().navigate().to("http://localhost/drupal/mica/datasets/study-datasets");

    browser().element(By.ref("dataset", "[5]", "studyCount")).hasText("1 Study");
    browser().element(By.ref("dataset", "[5]", "studyCount")).click();
    browser().element(By.ref("search-criterion")).hasText("CLSA");

    browser().element(By.ref("search-counts", "variables")).hasText("1,045");
    browser().element(By.ref("search-counts", "datasets")).hasText("1");
    browser().element(By.ref("search-counts", "studies")).hasText("1");
    browser().element(By.ref("search-counts", "networks")).hasText("4");

    browser().element(By.ref("search-results", "acronym", "[1]")).hasText("CLSA");
  }

  @Test
  public void check_that_variables_count_in_list_is_valid() {

    browser().navigate().to("http://localhost/drupal/mica/datasets/study-datasets?search-sort=name&search-sort-order=asc");

    browser().element(By.ref("dataset", "[5]", "variableCount")).hasText("1,045 Variables");
    browser().element(By.ref("dataset", "[5]", "variableCount")).click();
    browser().element(By.ref("search-criterion")).hasText("CLSA");

    browser().element(By.ref("search-counts", "variables")).hasText("1,045");
    browser().element(By.ref("search-counts", "datasets")).hasText("1");
    browser().element(By.ref("search-counts", "studies")).hasText("1");
    browser().element(By.ref("search-counts", "networks")).hasText("4");

    browser().element(By.ref("search-results", "name", "[1]")).hasText("startdate");
  }
}
