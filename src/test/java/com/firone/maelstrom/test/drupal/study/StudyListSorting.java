package com.firone.maelstrom.test.drupal.study;

import com.firone.maelstrom.test.drupal.paging.StudyListPagination;
import com.firone.maelstrom.test.utils.By;
import com.firone.maelstrom.test.utils.UITester;
import com.vtence.mario.WebElementDriver;
import org.junit.Test;

public class StudyListSorting extends UITester {

  @Test
  public void can_sort_studies_by_acronym() {

    browser().navigate().to("http://localhost/drupal/mica/studies?search-sort=numberOfParticipants.participant.number");

    browser().element(By.id("edit-search-sort")).element(By.xpath("//*[@value='acronym']")).click();
    studyNameNumber(3).hasText("CLS - Canberra Longitudinal Study");

    browser().element(By.id("edit-search-sort-order")).element(By.xpath("//*[@value='desc']")).click();
    studyNameNumber(3).hasText("SHIP - Study of Health in Pomerania");
  }

  @Test
  public void can_sort_studies_by_name() {

    browser().navigate().to("http://localhost/drupal/mica/studies?search-sort=numberOfParticipants.participant.number");

    browser().element(By.id("edit-search-sort")).element(By.xpath("//*[@value='name']")).click();
    studyNameNumber(3).hasText("CaG - CARTaGENE");

    browser().element(By.id("edit-search-sort-order")).element(By.xpath("//*[@value='desc']")).click();
    studyNameNumber(4).hasText("NuAge - Quebec Longitudinal Study on Nutrition and Successful Aging");
  }

  @Test
  public void can_sort_studies_by_targetNumber() {

    browser().navigate().to("http://localhost/drupal/mica/studies");

    browser().element(By.id("edit-search-sort")).element(By.xpath("//*[@value='numberOfParticipants.participant.number']")).click();
    studyNameNumber(1).hasText("CLS - Canberra Longitudinal Study");

    browser().element(By.id("edit-search-sort-order")).element(By.xpath("//*[@value='desc']")).click();
    studyNameNumber(1).hasText("LifeLines - LifeLines Cohort Study &amp; Biobank");
  }

  @Test
  public void can_paging_after_sorting() {

    browser().navigate().to("http://localhost/drupal/mica/studies");

    browser().element(By.id("edit-search-sort")).element(By.xpath("//*[@value='numberOfParticipants.participant.number']")).click();
    studyNameNumber(1).hasText("CLS - Canberra Longitudinal Study");

    new StudyListPagination().pagerElement().element(By.text("next")).click();
    studyNameNumber(1).hasText("CaG - CARTaGENE");
  }

  private WebElementDriver studyNameNumber(int studyNumber) {
    return browser().element(By.ref("study", "[" + studyNumber + "]")).element(By.tagName("h4"));
  }
}
