package org.obiba.mica.uitest.drupal.network;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import com.vtence.mario.WebElementDriver;
import org.junit.Test;
import org.openqa.selenium.Keys;

public class NetworkList extends UITester {

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

  @Test
  public void can_filter_list() {

    browser().navigate().to("http://localhost/drupal//mica/networks?search-sort=acronym");

    browser().element(By.id("edit-search-query")).enterTextUsingKeyboard("cls");
    browser().element(By.id("edit-search-query")).enterTextUsingKeyboard(Keys.ENTER);

    browser().element(By.ref("network", "[1]")).element(By.tagName("h4")).hasText("CCLSA - Constances/CLSA");
    browser().element(By.ref("network", "[2]")).element(By.tagName("h4")).hasText("RIFA - Recherche Internationale sur la Fragilité des Aînés");

    browser().element(By.id("edit-search-sort-order")).element(By.xpath("//*[@value='desc']")).click();

    browser().element(By.ref("network", "[1]")).element(By.tagName("h4")).hasText("RIFA - Recherche Internationale sur la Fragilité des Aînés");
    browser().element(By.ref("network", "[2]")).element(By.tagName("h4")).hasText("CCLSA - Constances/CLSA");
  }

  private WebElementDriver networkNameNumber(int networkNumber) {
    return browser().element(By.ref("network", "[" + networkNumber + "]")).element(By.tagName("h4"));
  }
}
