package org.obiba.mica.uitest.drupal.study;

import java.util.List;

import org.obiba.mica.uitest.drupal.DrupalUITester;
import org.obiba.mica.uitest.utils.By;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class StudyView extends DrupalUITester {

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

  @Test
  public void validate_study_network_link_sends_to_network() {
    browser().navigate().to("http://localhost/drupal/mica/study/cls");
    browser().element(By.xpath("//section[@id='networks']//table//tbody/tr/td/a[text()='IALSA']")).click();
    browser().pause(200);
    browser().element(By.xpath("//h1")).hasText(startsWith("IALSA"));
  }

  @Test
  public void validate_study_network_counts_sends_to_search() {
    browser().navigate().to("http://localhost/drupal/mica/study/cls");
    browser().element(By.xpath("//section[@id='networks']//table//tbody/tr/td/a[text()='7']/../..//a[text()='7']")).click();
    browser().pause(200);
    browser().element(By.ref("search-counts", "studies")).hasText("7");
  }

  @Test
  public void has_variables_classification_graphics() {
    login_to_drupal_as_admin();
    toggle_all_variable_classification_graphics(true);

    browser().navigate().to("http://localhost/drupal/mica/study/cag");
    browser().element(By.xpath("//section[@id='coverage']/h2")).hasText("Variables Classification");
    browser().element(By.xpath("//section[@id='coverage']//div[contains(@class, 'title h4')]")).hasText("Areas of Information");
  }

  @Test
  public void check_population_dce_popups_when_more_then_one_population() {
    browser().navigate().to("http://localhost/drupal/mica/study/cls");

    WebElement populationTab = browser().wrappedDriver().findElement(By.xpath("//li[@test-ref='population-tab'][1]/a"));
    String href = populationTab.getAttribute("href");
    String tabContentId = href.substring(href.indexOf('#') + 1);
    String dceLinksXpath = "//div[@id='" + tabContentId + "']" + "//table[@id='variables_overview']//tbody//a[@data-target]";

    List<WebElement> dceLinks = browser().wrappedDriver().findElements(By.xpath(dceLinksXpath));
    Actions actions = new Actions(browser().wrappedDriver());

    for(WebElement dceLink : dceLinks) {
      String description = browser().wrappedDriver()
          .findElement(By.xpath("//div[@id='" + tabContentId + "']//tbody//a[@data-target='" + dceLink.getAttribute("data-target") + "']" + "/../..//div[@class='markdown']"))
          .getText().replace("...", "");

      String dcePopupTarget = dceLink.getAttribute("data-target").substring(1);

      actions.moveToElement(dceLink).click().perform();
      browser()
          .element(By.xpath("//div[@id='" + dcePopupTarget + "']//div[@test-ref='modal-dce-description']//div[@class='markdown']/p"))
          .hasText(startsWith(description));
      browser().element(By.xpath("//div[@id='" + dcePopupTarget + "']//button[@data-dismiss='modal']")).click();
      browser().pause(500);
    }
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
