package org.obiba.mica.uitest.drupal.studydataset;

import java.util.List;

import org.obiba.mica.uitest.drupal.DrupalUITester;
import org.obiba.mica.uitest.utils.By;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.google.common.collect.Lists;

public class StudyDatasetView extends DrupalUITester {

  @Test
  public void validate_navigation_to_studyDataset_details() {

    browser().navigate().to("http://localhost/drupal");

    browser().element(By.text("Datasets ")).click();
    browser().element(By.text("Study Datasets")).click();
    browser().element(By.xpath("(//h4)[1]")).hasText("CLS-Wave1");

    browser().element(By.xpath("(//h4)[1]/a")).click();
    browser().element(By.xpath("//*[text()='Acronym']/../td")).hasText("CLS-Wave1");
  }

  @Test
  public void validate_view_of_studyDatasets_details_page() {

    browser().navigate().to("http://localhost/drupal/mica/study-dataset/lasa-3");

    browser().element(By.xpath("//*[text()='Overview']/..//*[text()='Acronym']/../td")).hasText("LASA 3");
    browser().element(By.xpath("//*[text()='Overview']/..//*[text()='Description']/../td")).hasText("Third cohort");
    browser().element(By.xpath("//*[text()='Overview']/..//*[text()='Dataset Type']/../td")).hasText("Study Dataset");
    browser().element(By.xpath("//*[text()='Overview']/..//*[text()='Number of Variables']/../td")).hasText("150");

    browser().element(By.xpath("//h2[contains(text(),'Study')]/..//*[text()='Acronym']/../td")).hasText("LASA");
    browser().element(By.xpath("//h2[contains(text(),'Study')]/..//*[text()='Name']/../td")).hasText("Longitudinal Aging Study Amsterdam");
    browser().element(By.xpath("//h2[contains(text(),'Study')]/..//*[text()='Population']/../td")).hasText("LASA third cohort");
    browser().element(By.xpath("//h2[contains(text(),'Study')]/..//*[text()='Data Collection Event']/../td")).hasText("LASA3 wave A/B");
    browser().element(By.xpath("//h2[contains(text(),'Study')]/..//*[text()='Study design']/../td")).hasText("Cohort");
    browser().element(By.xpath("//h2[contains(text(),'Study')]/..//*[text()='Number of participants']/../td")).hasText("5,132");
    browser().element(By.xpath("//h2[contains(text(),'Study')]/..//*[text()='Countries']/../td")).hasText("Netherlands");
  }

  @Test
  public void has_variables_classification_graphics() {
    login_to_drupal_as_admin();
    toggle_all_variable_classification_graphics(true);

    browser().navigate().to("http://localhost/drupal/mica/study-dataset/cls-wave1");
    browser().element(By.xpath("//section[@id='coverage']/h2")).hasText("Variables Classification");

    List<WebElement> graphicTitles = browser().wrappedDriver().findElements(
        By.xpath("//div/obiba-nv-chart/nvd3/div[contains(@class, 'title h4')]"));

    List<String> titles = Lists.newArrayList(
        "Additional information", "Areas of Information",
        "Constructs for cognitive functioning and mental health",
        "Constructs for general health", "Constructs for the social domain");

    for(WebElement graphicTitle : graphicTitles) {
      assert titles.indexOf(graphicTitle.getText()) > -1;
    }
  }
}
