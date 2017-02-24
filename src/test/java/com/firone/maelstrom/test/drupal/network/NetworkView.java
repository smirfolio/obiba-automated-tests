package com.firone.maelstrom.test.drupal.network;

import com.firone.maelstrom.test.utils.By;
import com.firone.maelstrom.test.utils.UITester;
import org.junit.Test;

public class NetworkView extends UITester {

  @Test
  public void validate_navigation_to_network_details() throws Exception {

    browser().navigate().to("http://localhost/drupal");

    browser().element(By.text("Networks")).click();
    browser().element(By.xpath("(//h4)[1]")).hasText("IALSA - Integrative Analysis of Longitudinal Studies of Aging");

    browser().element(By.xpath("(//h4)[1]/a")).click();
    browser().element(By.text("IALSA website")).hasAttribute("href", "http://www.ialsa.org/");
  }

  @Test
  public void validate_view_of_network_details_page() {

    browser().navigate().to("http://localhost/drupal/mica/network/ialsa");

    browser().element(By.text("IALSA website")).hasAttribute("href", "http://www.ialsa.org/");
    browser().element(By.xpath("//*[text() = 'Investigator']/..//li[1]")).hasText("Scott M. Hofer, PhD. (Oregon Health & Science University )");

    browser().element(By.text("Participants")).click();
    browser().element(By.xpath("//*[text() = '1,000 to 4,999']/..//a")).click();
    browser().element(By.xpath("(//div[@display='search.display']//td)[1]")).hasText("CLS");
  }

  @Test
  public void validate_searchVariables_button_navitates_to_correct_page() {

    browser().navigate().to("http://localhost/drupal/mica/network/qsc");

    browser().element(By.text("Search Variables")).click();

    browser().element(By.ref("search-criterion")).hasText("QSC");
    browser().element(By.ref("search-results", "name", "[1]")).hasText("ADL_ABLAP_TRM");
  }
}
