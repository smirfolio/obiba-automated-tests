package com.firone.maelstrom.test.drupal.study;

import com.firone.maelstrom.test.utils.By;
import com.firone.maelstrom.test.utils.UITester;
import org.junit.Test;

public class StudyView extends UITester {

  @Test
  public void validate_navigation_to_study_details() throws Exception {

    browser().navigate().to("http://localhost/drupal");

    browser().element(By.text("Studies")).click();
    browser().element(By.xpath("(//h4)[1]")).hasText("Atlantic PATH - Atlantic Partnership for Tomorrow's Health (The)");

    browser().element(By.xpath("(//h4)[1]/a")).click();
    browser().element(By.text("Atlantic PATH Website")).hasAttribute("href", "http://atlanticpath.ca/");
  }

  @Test
  public void validate_view_of_study_details_page() {

    browser().navigate().to("http://localhost/drupal/mica/study/atlantic-path");

    browser().element(By.text("Atlantic PATH Website")).hasAttribute("href", "http://atlanticpath.ca/");
    browser().element(By.xpath("//*[text() = 'Contact']/..//li[1]")).hasText("Dr. Louise Parker (Dalhousie University )");
    browser().element(By.xpath("//*[text() = 'Investigator']/..//li[1]")).hasText("Dr. David W Hoskin (Dalhousie University )");

    browser().element(By.xpath("(//*[@id='variables_overview']//tr)[2]/td[1]")).hasText("Atlantic PATH - Pilot");
    browser().element(By.xpath("(//*[@id='variables_overview']//tr)[2]/td[3]")).hasText("2009");
    browser().element(By.xpath("(//*[@id='variables_overview']//tr)[2]/td[4]")).hasText("2009");

    browser().element(By.xpath("(//*[@id='variables_overview']//tr)[3]/td[1]")).hasText("Atlantic PATH - Baseline Recruitment");
    browser().element(By.xpath("(//*[@id='variables_overview']//tr)[3]/td[2]")).hasText("The baseline recruitment is ongoing (planned...");
    browser().element(By.xpath("(//*[@id='variables_overview']//tr)[3]/td[3]")).hasText("2009");
    browser().element(By.xpath("(//*[@id='variables_overview']//tr)[3]/td[4]")).hasText("2015");

    browser().element(firstLinkedNetwork()).hasText("CPTP");
    browser().element(firstLinkedDataset()).hasText("CPTP Health and Risk Factor Questionnaire DataSchema");
  }

  private org.openqa.selenium.By firstLinkedDataset() {
    return By.xpath("(//div[@id='datasetsDisplay']//td)[1]");
  }

  private org.openqa.selenium.By firstLinkedNetwork() {
    return By.xpath("(//section[@id='networks']//tr)[2]//a");
  }
}
