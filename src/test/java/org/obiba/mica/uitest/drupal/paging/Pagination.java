package org.obiba.mica.uitest.drupal.paging;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import com.vtence.mario.WebElementDriver;
import org.junit.Test;

public abstract class Pagination extends UITester {

  @Test
  public void check_pagination() {

    browser().navigate().to(url());
    assertThatWeAreOnFirstPage();

    pagerElement().element(By.text("2")).click();
    assertThatWeAreOnSecondPage();

    pagerElement().element(By.text("1")).click();
    assertThatWeAreOnFirstPage();

    pagerElement().element(By.text(nextSymbol())).click();
    assertThatWeAreOnSecondPage();

    pagerElement().element(By.text(previousSymbol())).click();
    assertThatWeAreOnFirstPage();

    pagerElement().element(By.text(lastSymbol())).click();
    assertThatWeAreOnLastPage();

    pagerElement().element(By.text(firstSymbol())).click();
    assertThatWeAreOnFirstPage();
  }

  protected abstract String url();

  protected abstract WebElementDriver pagerElement();

  protected abstract String nextSymbol();

  protected abstract String previousSymbol();

  protected abstract String firstSymbol();

  protected abstract String lastSymbol();

  protected abstract void assertThatWeAreOnFirstPage();

  protected abstract void assertThatWeAreOnSecondPage();

  protected abstract void assertThatWeAreOnLastPage();
}
