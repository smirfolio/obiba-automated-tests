package org.obiba.mica.uitest.drupal.paging;

import org.obiba.mica.uitest.utils.By;
import com.vtence.mario.WebElementDriver;

public class StudyListPagination extends Pagination {

  public String url() {
    return "http://localhost/drupal/mica/studies";
  }

  public WebElementDriver pagerElement() {
    return browser().element(By.ref("pager"));
  }

  protected String nextSymbol() {
    return "next";
  }

  protected String previousSymbol() {
    return "previous";
  }

  protected String firstSymbol() {
    return "first";
  }

  protected String lastSymbol() {
    return "last";
  }

  protected void assertThatWeAreOnFirstPage() {
    browser().element(By.ref("study", "[1]")).element(By.xpath("//h4")).hasText("Atlantic PATH - Atlantic Partnership for Tomorrow's Health (The)");
  }

  protected void assertThatWeAreOnSecondPage() {
    browser().element(By.ref("study", "[1]")).element(By.xpath("//h4")).hasText("OHS - Ontario Health Study");
  }

  protected void assertThatWeAreOnLastPage() {
    assertThatWeAreOnSecondPage();
  }
}
