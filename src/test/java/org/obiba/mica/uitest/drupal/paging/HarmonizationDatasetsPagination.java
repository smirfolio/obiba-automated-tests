package org.obiba.mica.uitest.drupal.paging;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.junit.Test;

public class HarmonizationDatasetsPagination extends UITester {

  @Test
  public void check_pagination() {

    browser().navigate().to("http://localhost/drupal/mica/harmonization-dataset/cptp-coreqx");
    assertThatWeAreOnFirstPage();

    browser().element(inPager("//*[text()='2']")).click();
    assertThatWeAreOnSecondPage();

    browser().element(inPager("//*[text()='1']")).click();
    assertThatWeAreOnFirstPage();

    browser().element(inPager("//*[text()='Next']")).click();
    assertThatWeAreOnSecondPage();

    browser().element(inPager("//*[text()='Previous']")).click();
    assertThatWeAreOnFirstPage();
  }

  private org.openqa.selenium.By inPager(String xpath) {
    return By.xpath("//*[@id='table-variables_paginate']" + xpath);
  }

  private void assertThatWeAreOnFirstPage() {
    browser().pause(500);
    browser().element(By.xpath("(//*[@id='table-variables']//td)[1]")).hasText("A_ADM_STUDY_ID");
  }

  private void assertThatWeAreOnSecondPage() {
    browser().pause(500);
    browser().element(By.xpath("(//*[@id='table-variables']//td)[1]")).hasText("A_HS_DENTAL_VISIT_LAST");
  }
}
