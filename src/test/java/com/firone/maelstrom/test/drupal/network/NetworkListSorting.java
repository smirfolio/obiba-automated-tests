package com.firone.maelstrom.test.drupal.network;

import com.firone.maelstrom.test.utils.By;
import com.firone.maelstrom.test.utils.UITester;
import com.vtence.mario.WebElementDriver;
import org.junit.Test;

public class NetworkListSorting extends UITester {

  @Test
  public void can_sort_networks_by_acronym() {

    browser().navigate().to("http://localhost/drupal//mica/networks");

    browser().element(By.id("edit-search-sort")).element(By.xpath("//*[@value='acronym']")).click();
    networkNameNumber(2).hasText("CCLSA - Constances/CLSA");

    browser().element(By.id("edit-search-sort-order")).element(By.xpath("//*[@value='desc']")).click();
    networkNameNumber(2).hasText("QSC - Quebec Study Catalogue");
  }

  @Test
  public void can_sort_networks_by_name() {

    browser().navigate().to("http://localhost/drupal/mica/networks");

    browser().element(By.id("edit-search-sort")).element(By.xpath("//*[@value='name']")).click();
    networkNameNumber(2).hasText("CPTP - Canadian Partnership for Tomorrow Project");

    browser().element(By.id("edit-search-sort-order")).element(By.xpath("//*[@value='desc']")).click();
    networkNameNumber(2).hasText("QSC - Quebec Study Catalogue");
  }

  private WebElementDriver networkNameNumber(int networkNumber) {
    return browser().element(By.ref("network", "[" + networkNumber + "]")).element(By.tagName("h4"));
  }
}
