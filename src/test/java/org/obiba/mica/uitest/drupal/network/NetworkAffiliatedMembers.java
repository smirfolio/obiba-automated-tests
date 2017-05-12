package org.obiba.mica.uitest.drupal.network;


import org.junit.Test;
import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;


public class NetworkAffiliatedMembers extends UITester {

  @Test
  public void validate_open_network_members_popup() throws Exception {
    networkOpenMembersPopup();
    browser().element(currentModalBy()).element(By.className("modal-title")).hasText("Affiliated Members");
    browser().element(By.xpath("(//div[@id='person-table_wrapper']//td)[2]")).hasText("info@clsa-elcv.ca");

  }

  @Test
  public void validate_search_in_network_members_popup() throws Exception {
    networkOpenMembersPopup();
    browser().pause(200);
    //Search name
    browser().element(By.xpath("(//input[@type='search'])[1]")).clear();
    browser().element(By.xpath("(//input[@type='search'])[1]")).type("Dr. José");
    browser().element(By.xpath("(//div[@id='person-table_wrapper']//td)[1]")).hasText("Dr. José A. Morais");
    //Search mail
    browser().element(By.xpath("(//input[@type='search'])[1]")).clear();
    browser().element(By.xpath("(//input[@type='search'])[1]")).type("clsa-elcv.ca");
    browser().element(By.xpath("(//div[@id='person-table_wrapper']//td)[2]")).hasText("info@clsa-elcv.ca");
    //Search study
    browser().element(By.xpath("(//input[@type='search'])[1]")).clear();
    browser().element(By.xpath("(//input[@type='search'])[1]")).type("nuage");
    browser().element(By.xpath("(//div[@id='person-table_wrapper']//td)[3]")).hasText("NuAge (Investigators)");
    //Search network
    browser().element(By.xpath("(//input[@type='search'])[1]")).clear();
    browser().element(By.xpath("(//input[@type='search'])[1]")).type("qsc");
    browser().element(By.xpath("(//div[@id='person-table_wrapper']//td)[4]")).hasText("QSC (Investigators)");
  }

  @Test
  public void validate_pager_in_network_members_popup() throws Exception {
    networkOpenMembersPopup();
    browser().pause(200);
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

  @Test
  public void validate_modal_close_in_network_members_popup() throws Exception {
    networkOpenMembersPopup();
    browser().element(By.xpath("//div[@id='associated-people']//h3")).hasText("Affiliated Members");
    browser().element(By.xpath("(//div[@id='associated-people']//button)[1]")).hasText("×");
    browser().element(By.xpath("(//div[@id='associated-people']//button)[1]")).click();
  }

  private void networkOpenMembersPopup() {
    browser().navigate().to("http://localhost/drupal/mica/networks");
    browser().element(By.xpath("(//h4)[3]")).hasText("QSC - Quebec Study Catalogue");

    browser().element(By.xpath("(//h4)[3]/a")).click();
    browser().element(By.xpath("(//button[@data-target='#associated-people'])[1]")).hasText("Affiliated Members");
    browser().element(By.xpath("(//button[@data-target='#associated-people'])[1]")).click();
  }

  private org.openqa.selenium.By inPager(String xpath) {
    return By.xpath("//*[@id='person-table_paginate']" + xpath);
  }

  private void assertThatWeAreOnFirstPage() {
    browser().element(By.xpath("(//div[@id='person-table_wrapper']//td)[1]")).hasText("Canadian Longitudinal Study on Aging");
  }

  private void assertThatWeAreOnSecondPage() {
    browser().element(By.xpath("(//div[@id='person-table_wrapper']//td)[1]")).hasText("Claude Galand");
  }
}
