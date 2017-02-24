package com.firone.maelstrom.test.drupal.study;

import com.firone.maelstrom.test.utils.By;
import com.firone.maelstrom.test.utils.UITester;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

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

  @Test
  public void validate_searchVariables_button_navitates_to_correct_page() {

    browser().navigate().to("http://localhost/drupal/mica/study/cag");

    browser().element(By.text("Search Variables")).click();

    browser().element(By.ref("search-criterion")).hasText("CaG");
    browser().element(By.ref("search-results", "name", "[1]")).hasText("A_ADM_STUDY_ID");
  }

  @Test
  public void can_view_contact_details() {

    browser().navigate().to("http://localhost/drupal/mica/study/cag");

    validateFirstMembership();

    browser().element(currentModalBy()).element(By.className("close")).click();

    validateThirdMembership();
  }

  @Test
  public void can_view_dce() {

    browser().navigate().to("http://localhost/drupal/mica/study/cag");

    browser().element(firstDce()).hasText("CaG - Baseline Recruitment");
    browser().element(firstDce()).click();

    browser().element(currentModalThenRefs("modal-dce-description")).hasText(startsWith("During the phase"));
    browser().element(currentModalThenRefs("modal-dce-startYear")).hasText("2007 (January)");
    browser().element(currentModalThenRefs("modal-dce-endYear")).hasText("2010 (October)");
    browser().element(currentModalThenRefs("modal-dce-dataSource", "[2]")).hasText("Physical measures");
    browser().element(currentModalThenRefs("modal-dce-biosample", "[1]")).hasText("Blood");
  }

  @Test
  public void can_view_dce_files() {

    browser().navigate().to("http://localhost/drupal/mica/study/cls");

    browser().element(firstDce()).hasText("CLS wave 1");
    browser().element(firstDce()).click();

    browser().element(currentModalThenRefs("file-search-result-list", "file-name", "[2]")).hasText("Wave 1 informant self-completion.pdf");
    browser().element(currentModalThenRefs("file-search-result-list", "file-type", "[2]")).hasText("FILE");
    browser().element(currentModalThenRefs("file-search-result-list", "file-size", "[2]")).hasText("152.26 KB");
    browser().element(currentModalThenRefs("file-search-result-list", "file-lastModification", "[1]")).hasText("3 months ago");
  }

  @Test
  public void can_view_dce_files__search_files() {

    browser().navigate().to("http://localhost/drupal/mica/study/cls");

    browser().element(firstDce()).hasText("CLS wave 1");
    browser().element(firstDce()).click();

    browser().element(currentModalThenRefs("file-search-input")).type("interview");
    browser().element(currentModalThenRefs("file-search-input")).enterTextUsingKeyboard(Keys.ENTER);

    browser().element(currentModalThenRefs("file-search-result-list", "file-name", "[2]")).hasText("Wave 1 informant interview.pdf");
    browser().element(currentModalThenRefs("file-search-result-list", "file-type", "[2]")).hasText("FILE");
    browser().element(currentModalThenRefs("file-search-result-list", "file-size", "[2]")).hasText("1.11 MB");
    browser().element(currentModalThenRefs("file-search-result-list", "file-lastModification", "[2]")).hasText("3 months ago");
    browser().element(currentModalThenRefs("file-search-result-list", "file-parent", "[2]")).hasText("/");
  }

  @Test
  public void can_view_dce_files__search_most_recently_modified_files() {

    browser().navigate().to("http://localhost/drupal/mica/study/cls");

    browser().element(firstDce()).hasText("CLS wave 1");
    browser().element(firstDce()).click();

    browser().element(currentModalThenRefs("file-search-result-list", "file-size", "[2]")).hasText("152.26 KB");
    browser().element(By.xpath("//a[@title='Search 10 most recently modified files']")).click();
    browser().pause(200);
    browser().element(currentModalThenRefs("file-search-result-list", "file-size", "[2]")).hasText("118.31 KB");
  }

  private org.openqa.selenium.By firstDce() {
    return By.xpath("(//*[@id='variables_overview']//tbody//td)[1]");
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
