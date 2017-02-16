package com.firone.maelstrom.test.drupal;

import com.firone.maelstrom.test.utils.UITester;
import org.junit.Test;
import org.openqa.selenium.By;

public class StudyDatasetView extends UITester {

  @Test
  public void validate_navigation_to_studyDataset_details() {

    browser().navigate().to("http://localhost/drupal");

    browser().element(byElementWithText("Datasets")).hoverWithMouse();
    browser().element(byElementWithText("Study Datasets")).click();
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
    browser().element(By.xpath("//h2[contains(text(),'Study')]/..//*[text()='Study design']/../td")).hasText("cohort study");
    browser().element(By.xpath("//h2[contains(text(),'Study')]/..//*[text()='Number of participants']/../td")).hasText("5,132");
    browser().element(By.xpath("//h2[contains(text(),'Study')]/..//*[text()='Countries']/../td")).hasText("Netherlands");
  }
}
