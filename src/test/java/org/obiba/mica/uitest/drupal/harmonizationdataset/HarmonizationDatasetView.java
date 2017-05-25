package org.obiba.mica.uitest.drupal.harmonizationdataset;

import org.junit.Test;
import org.obiba.mica.uitest.drupal.DrupalUITester;
import org.obiba.mica.uitest.utils.By;

public class HarmonizationDatasetView extends DrupalUITester {

  @Test
  public void validate_navigation_to_harmonizationDataset_details() {

    browser().navigate().to("http://localhost/drupal");
    browser().element(By.text("Datasets ")).click();
    browser().element(By.text("Harmonization Datasets")).click();
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

  @Test
  public void has_variables_classification_graphics() {
    login_to_drupal_as_admin();
    toggle_all_variable_classification_graphics(true);

    browser().navigate().to("http://localhost/drupal/mica/harmonization-dataset/cptp-coreqx");
    browser().element(By.xpath("//section[@id='coverage']/h2")).hasText("Variables Classification");

    browser().element(By.xpath("//section[@id='coverage']//div/obiba-nv-chart/nvd3/div[contains(@class, 'title h4')]"))
        .hasText("Areas of Information");
  }

  private org.openqa.selenium.By firstHarmonizedVariable() {
    return By.xpath("(//div[@id='table-variables_wrapper']//tbody//td)[1]");
  }

  private org.openqa.selenium.By firstLinkedStudy() {
    return By.xpath("(//div[@id='studies-table']//tbody//td)[1]");
  }
}
