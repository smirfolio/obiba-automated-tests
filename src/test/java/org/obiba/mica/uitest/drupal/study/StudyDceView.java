package org.obiba.mica.uitest.drupal.study;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.startsWith;

public class StudyDceView extends UITester {

  @Test
  public void can_view_dce() {

    browser().navigate().to("http://localhost/drupal/mica/study/cag");

    browser().element(firstDceNameWithSinglePop()).hasText("CaG - Baseline Recruitment");
    browser().element(firstDceNameWithSinglePop()).click();

    browser().element(currentModalThenRefs("modal-dce-description")).hasText(startsWith("During the phase"));
    browser().element(currentModalThenRefs("modal-dce-startYear")).hasText("2007 (January)");
    browser().element(currentModalThenRefs("modal-dce-endYear")).hasText("2010 (October)");
    browser().element(currentModalThenRefs("modal-dce-dataSource", "[2]")).hasText("Physical measures");
    browser().element(currentModalThenRefs("modal-dce-biosample", "[1]")).hasText("Blood");
  }

  @Test
  public void can_view_dce_files() {

    browser().navigate().to("http://localhost/drupal/mica/study/cls");

    browser().element(firstDceNameWithSinglePop()).hasText("CLS wave 1");
    browser().element(firstDceNameWithSinglePop()).click();

    browser().element(currentModalThenRefs("file-search-result-list", "file-name", "[2]")).hasText("Wave 1 informant self-completion.pdf");
    browser().element(currentModalThenRefs("file-search-result-list", "file-type", "[2]")).hasText("FILE");
    browser().element(currentModalThenRefs("file-search-result-list", "file-size", "[2]")).hasText("152.26 KB");
    browser().element(currentModalThenRefs("file-search-result-list", "file-lastModification", "[1]")).hasText("3 months ago");
  }

  @Test
  public void can_view_dce_files__search_files() {

    browser().navigate().to("http://localhost/drupal/mica/study/cls");

    browser().element(firstDceNameWithSinglePop()).hasText("CLS wave 1");
    browser().element(firstDceNameWithSinglePop()).click();

    browser().element(currentModalThenRefs("file-search-input")).type("interview");
    browser().element(currentModalThenRefs("file-search-input")).enterTextUsingKeyboard(Keys.ENTER);
    browser().pause(500);

    browser().element(currentModalThenRefs("file-search-result-list", "file-name", "[2]")).hasText("Wave 1 informant interview.pdf");
    browser().element(currentModalThenRefs("file-search-result-list", "file-type", "[2]")).hasText("FILE");
    browser().element(currentModalThenRefs("file-search-result-list", "file-size", "[2]")).hasText("1.11 MB");
    browser().element(currentModalThenRefs("file-search-result-list", "file-lastModification", "[2]")).hasText("3 months ago");
    browser().element(currentModalThenRefs("file-search-result-list", "file-parent", "[2]")).hasText("/");
  }

  @Test
  public void can_view_dce_files__search_most_recently_modified_files() {

    browser().navigate().to("http://localhost/drupal/mica/study/cls");

    browser().element(firstDceNameWithSinglePop()).hasText("CLS wave 1");
    browser().element(firstDceNameWithSinglePop()).click();

    browser().element(currentModalThenRefs("file-search-result-list", "file-size", "[2]")).hasText("152.26 KB");
    browser().element(By.xpath("//a[@title='Search 10 most recently modified files']")).click();
    browser().pause(200);
    browser().element(currentModalThenRefs("file-search-result-list", "file-size", "[2]")).hasText("118.31 KB");
  }

  @Test
  public void can_view_dce_of_all_populations() {

    browser().navigate().to("http://localhost/drupal/mica/study/lasa");

    browser().element(dceNumberMultiplePop(1, 1)).hasText("LASA Wave B");
    browser().element(dceNumberMultiplePop(1, 2)).hasText("Face-to-face interview.");
    browser().element(dceNumberMultiplePop(1, 3)).hasText("1992 (September)");
    browser().element(dceNumberMultiplePop(1, 4)).hasText("1993 (September)");
    browser().element(dceNumberMultiplePop(1, 1)).click();
    browser().element(currentModalThenRefs("modal-dce-description")).hasText("Face-to-face interview.");
    browser().element(currentModalBy()).element(By.className("close")).click();
    browser().pause(300);

    browser().element(dceNumberMultiplePop(4, 1)).hasText("LASA Wave E");
    browser().element(dceNumberMultiplePop(4, 2)).hasText("Main face-to-face interview, medical in...");
    browser().element(dceNumberMultiplePop(4, 3)).hasText("2001 (September)");
    browser().element(dceNumberMultiplePop(4, 4)).hasText("2002 (October)");
    browser().element(dceNumberMultiplePop(4, 1)).click();
    browser().element(currentModalThenRefs("modal-dce-description")).hasText(startsWith("Main face-to-face interview,"));
    browser().element(currentModalBy()).element(By.className("close")).click();

    scrollUpManyTimes(4);

    browser().element(By.ref("population-tab", "[2]")).click();

    browser().element(dceNumberMultiplePop(1, 1)).hasText("LASA2 Wave A/B");
    browser().element(dceNumberMultiplePop(1, 2)).hasText("Main face-to-face interview and medical in-home...");
    browser().element(dceNumberMultiplePop(1, 3)).hasText("2002 (September)");
    browser().element(dceNumberMultiplePop(1, 4)).hasText("2003 (September)");
    scrollUpManyTimes(4);
    browser().element(dceNumberMultiplePop(1, 1)).click();
    browser().element(currentModalThenRefs("modal-dce-description")).hasText(startsWith("Main face-to-face interview"));
    browser().element(currentModalBy()).element(By.className("close")).click();

  }

  private org.openqa.selenium.By firstDceNameWithSinglePop() {
    return dceNumber(1, 1, false);
  }

  private org.openqa.selenium.By firstDceNameWithMultiplePop() {
    return dceNumber(1, 1, true);
  }

  private String activePopulationContentXpath() {
    return "//*[@test-ref='population-tab-content']/*[contains(@class,'active')]";
  }

  private org.openqa.selenium.By dceNumberSinglePop(int dceLine, int dceColumn) {
    return dceNumber(dceLine, dceColumn, false);
  }

  private org.openqa.selenium.By dceNumberMultiplePop(int dceLine, int dceColumn) {
    return dceNumber(dceLine, dceColumn, true);
  }

  private org.openqa.selenium.By dceNumber(int dceLine, int dceColumn, boolean multiplePopulations) {

    String finalXpath = String.format("((%s//*[@id='variables_overview']//tbody//tr)[%s]//td)[%s]",
            multiplePopulations ? activePopulationContentXpath() : uniquePopulationContentXpath(),
            dceLine, dceColumn);
    System.out.println(finalXpath);
    return By.xpath(finalXpath);
  }

  private String uniquePopulationContentXpath() {
    return "";
  }

  private void scrollUpManyTimes(int scrollUpTimesNumber) {
    assertThat(scrollUpTimesNumber, greaterThan(0));
    for (; scrollUpTimesNumber > 0; scrollUpTimesNumber--) {
      browser().element(By.xpath("(//*)[1]")).enterTextUsingKeyboard(Keys.PAGE_UP);
    }
    browser().pause(200);
  }
}
