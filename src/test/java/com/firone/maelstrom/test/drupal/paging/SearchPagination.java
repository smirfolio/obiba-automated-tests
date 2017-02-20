package com.firone.maelstrom.test.drupal.paging;

import com.firone.maelstrom.test.utils.By;
import com.vtence.mario.WebElementDriver;

public class SearchPagination extends Pagination {

  public String url() {
    return "http://localhost/drupal/mica/repository#/search?query=variable(in(Mlstr_area.Lifestyle_behaviours,Alcohol))";
  }

  public WebElementDriver pagerElement() {
    return browser().element(By.ref("pager"));
  }

  protected String nextSymbol() {
    return "›";
  }

  protected String previousSymbol() {
    return "‹";
  }

  protected String firstSymbol() {
    return "«";
  }

  protected String lastSymbol() {
    return "»";
  }

  protected void assertThatWeAreOnFirstPage() {
    browser().pause(500);
    browser().element(By.ref("search-results", "name", "[1]")).hasText("v75");
  }

  protected void assertThatWeAreOnSecondPage() {
    browser().pause(500);
    browser().element(By.ref("search-results", "name", "[1]")).hasText("ALC_EVER_TRM");
  }

  protected void assertThatWeAreOnLastPage() {
    browser().pause(500);
    browser().element(By.ref("search-results", "name", "[1]")).hasText("W676");
  }
}
