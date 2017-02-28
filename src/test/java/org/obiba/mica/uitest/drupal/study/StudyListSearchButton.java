package org.obiba.mica.uitest.drupal.study;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.junit.Test;

public class StudyListSearchButton extends UITester {

  @Test
  public void search_button_on_network_list_must_return_search_result_for_all_networks() {

    browser().navigate().to("http://localhost/drupal/mica/studies");

    browser().element(By.text("Search Studies")).click();

    browser().element(By.ref("search-criterion")).hasText("Acronym:any");
    browser().element(By.ref("search-results", "acronym", "[1]")).hasText("Atlantic PATH");
  }
}
