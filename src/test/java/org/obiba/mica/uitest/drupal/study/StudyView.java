package org.obiba.mica.uitest.drupal.study;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

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
    browser().element(By.xpath("//*[text() = 'Contacts']/..//li[1]")).hasText("Dr. Louise Parker (Dalhousie University)");
    browser().element(By.xpath("//*[text() = 'Investigators']/..//li[1]")).hasText("Dr. David W Hoskin (Dalhousie University)");

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

  @Test
  public void validate_searchVariables_button_navigates_to_correct_page() {

    browser().navigate().to("http://localhost/drupal/mica/study/cag");

    browser().element(By.text("Search Variables")).click();
    browser().pause(300);

    browser().element(By.ref("search-criterion")).hasText("CaG");
    browser().element(By.ref("search-results", "name", "[1]")).hasText("A_ADM_STUDY_ID");
  }

  @Test
  public void can_view_contact_details() {

    browser().navigate().to("http://localhost/drupal/mica/study/cag");

    validateFirstMembership();

    browser().element(currentModalBy()).element(By.className("close")).click();
    browser().pause(300);
    validateThirdMembership();
  }

  private void validateFirstMembership() {
    browser().element(By.ref("membership", "[1]")).click();
    browser().element(currentModalBy()).element(By.className("modal-title")).hasText("Prof. Philip Awadalla");
    browser().element(currentModalThenRefs("modal-body", "email")).hasText("philip.awadalla@umontreal.ca");
    browser().element(currentModalThenRefs("modal-body", "institutionIdentifier")).hasText("CHU Sainte-Justine Research Centre");
    browser().element(currentModalThenRefs("modal-body", "institutionAddress")).hasText(is(
            "3175 Chemin de la Cote-Sainte-Catherine\n" +
                    "Montreal\n" +
                    "H3T 1C5\n" +
                    "Quebec, Canada"));
  }

  private void validateThirdMembership() {
    browser().element(By.ref("membership", "[3]")).click();
    browser().element(currentModalBy()).element(By.className("modal-title")).hasText("Guy Rouleau");
    browser().element(currentModalThenRefs("modal-body", "institutionIdentifier")).hasText("Université de Montréal");
  }

  private org.openqa.selenium.By firstLinkedDataset() {
    return By.xpath("(//div[@id='datasetsDisplay']//td)[1]");
  }

  private org.openqa.selenium.By firstLinkedNetwork() {
    return By.xpath("(//section[@id='networks']//tr)[2]//a");
  }
}
