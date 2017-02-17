package com.firone.maelstrom.test.drupal.harmonizationdataset;

import com.firone.maelstrom.test.utils.UITester;
import org.junit.Test;
import org.openqa.selenium.By;

public class HarmonizationDatasetView extends UITester {

  @Test
  public void validate_navigation_to_harmonizationDataset_details() {

    browser().navigate().to("http://localhost/drupal");

    browser().element(byElementWithText("Datasets")).hoverWithMouse();
    browser().element(byElementWithText("Harmonization Datasets")).click();
    browser().element(By.xpath("(//h4)[1]")).hasText("CPTPCoreQx - CPTP Health and Risk Factor Questionnaire DataSchema");

    browser().element(By.xpath("(//h4)[1]/a")).click();
    browser().element(By.xpath("//*[text()='Acronym']/../td")).hasText("CPTPCoreQx");
  }

  @Test
  public void validate_view_of_harmonizationDatasets_details_page() {

    browser().navigate().to("http://localhost/drupal/mica/harmonization-dataset/cptp-coreqx");

    browser().element(By.xpath("//*[text()='Acronym']/../td")).hasText("CPTPCoreQx");
    browser().element(By.xpath("//*[text()='Dataset Type']/../td")).hasText("Harmonization Dataset");

    browser().element(firstLinkedStudy()).hasText("Atlantic PATH");

    browser().element(firstHarmonizedVariable()).hasText("A_ADM_STUDY_ID");
    browser().element(By.xpath("//div[@id='variables-table']//*[text()='Next']")).click();
    browser().element(firstHarmonizedVariable()).hasText("A_HS_DENTAL_VISIT_LAST");
  }

  private By firstHarmonizedVariable() {
    return By.xpath("(//div[@id='table-variables_wrapper']//tbody//td)[1]");
  }

  private By firstLinkedStudy() {
    return By.xpath("(//div[@id='studies-table']//tbody//td)[1]");
  }
}
