package org.obiba.mica.uitest.drupal.network;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class NetworkView extends UITester {

  @Test
  public void validate_navigation_to_network_details() throws Exception {

    browser().navigate().to("http://localhost/drupal");

    browser().element(By.text("Networks")).click();
    browser().element(By.xpath("(//h4)[4]")).hasText("IALSA - Integrative Analysis of Longitudinal Studies of Aging");

    browser().element(By.xpath("(//h4)[4]/a")).click();
    browser().element(By.text("IALSA website")).hasAttribute("href", "http://www.ialsa.org/");
  }

  @Test
  public void validate_view_of_network_details_page() {

    browser().navigate().to("http://localhost/drupal/mica/network/ialsa");

    browser().element(By.text("IALSA website")).hasAttribute("href", "http://www.ialsa.org/");
    browser().element(By.ref("membership", "[1]")).hasText("Scott M. Hofer, PhD. (Oregon Health & Science University)");

    browser().element(By.text("Participants")).click();
    browser().pause(600);
    browser().element(By.xpath("//*[text() = '1,000 to 4,999']/..//a")).click();
    browser().element(By.xpath("(//div[@display='search.display']//td)[1]")).hasText("CLS");
  }

  @Test
  public void validate_searchVariables_button_navitates_to_correct_page() {

    browser().navigate().to("http://localhost/drupal/mica/network/qsc");

    browser().element(By.text("Search Variables")).click();

    browser().element(By.ref("search-criterion")).hasText("QSC");
    browser().element(By.ref("search-results", "name", "[1]")).hasText("startdate");
  }

  @Test
  public void can_view_contact_details() {

    browser().navigate().to("http://localhost/drupal/mica/network/qsc");

    validateSecondMembership();

    browser().element(currentModalBy()).element(By.className("close")).click();
    browser().pause(500);

    validateFirstMembership();
  }

  private void validateFirstMembership() {
    browser().element(By.ref("membership", "[1]")).click();
    browser().element(currentModalBy()).element(By.className("modal-title")).hasText("Dr. Isabel Fortier");
    browser().element(currentModalThenRefs("modal-body", "email")).hasText("isabel.fortier@mail.mcgill.ca");
    browser().element(currentModalThenRefs("modal-body", "institutionIdentifier")).hasText("Reseach Institute of the McGill University Health Centre");
    browser().element(currentModalThenRefs("modal-body", "institutionAddress")).hasText(is(
            "2155 Guy Street, 4th Floor\n" +
                    "Montreal\n" +
                    "H3H 2R9\n" +
                    "Quebec, Canada"));
  }

  private void validateSecondMembership() {
    browser().element(By.ref("membership", "[2]")).click();
    browser().element(currentModalBy()).element(By.className("modal-title")).hasText("Dr. Vincent Ferretti");
    browser().element(currentModalThenRefs("modal-body", "email")).hasText("vincent.ferretti@oicr.on.ca");
    browser().element(currentModalThenRefs("modal-body", "phone")).hasText("+1 (416) 673 8509");
    browser().element(currentModalThenRefs("modal-body", "institutionIdentifier")).hasText("Ontario Institute for Cancer Research");
    browser().element(currentModalThenRefs("modal-body", "institutionAddress")).hasText("Ontario, Canada");
  }
}
