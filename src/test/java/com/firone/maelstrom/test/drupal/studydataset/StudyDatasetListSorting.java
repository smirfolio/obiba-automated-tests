package com.firone.maelstrom.test.drupal.studydataset;

import com.firone.maelstrom.test.utils.By;
import com.firone.maelstrom.test.utils.UITester;
import com.vtence.mario.WebElementDriver;
import org.junit.Test;

public class StudyDatasetListSorting extends UITester {

  @Test
  public void can_sort_networks_by_name_and_by_acronym() {

    browser().navigate().to("http://localhost/drupal/mica/datasets/study-datasets");
    networkNameNumber(2).hasText("CLS-Wave2");

    browser().element(By.id("edit-search-sort")).element(By.xpath("//*[@value='acronym']")).click();
    networkNameNumber(3).hasText("CLS-Wave3");

    browser().element(By.id("edit-search-sort-order")).element(By.xpath("//*[@value='desc']")).click();
    networkNameNumber(2).hasText("ULSAM-82");

    browser().element(By.id("edit-search-sort")).element(By.xpath("//*[@value='name']")).click();
    networkNameNumber(3).hasText("ULSAM-77");
  }

  private WebElementDriver networkNameNumber(int datasetNumber) {
    return browser().element(By.ref("dataset", "[" + datasetNumber + "]")).element(By.tagName("h4"));
  }
}
