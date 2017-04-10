package org.obiba.mica.uitest.drupal.network;


import org.junit.Test;
import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;

import static org.hamcrest.Matchers.startsWith;


public class NetworkStudiesTable extends UITester{



  @Test
  public void list_correct_studies_list(){
    get_network();
    browser().element(By.xpath("((//div[@class='markdown']))//p[1]")).hasText(startsWith("The Canadian Partnership for Tomorrow Project (CPTP)"));
    browser().element(By.xpath("(//div[@id='table-studies_info'])")).hasText("Showing 1 to 5 of 5 entries");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[1]")).hasText("Atlantic PATH");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[6]")).hasText("772");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[7]")).hasText("BCGP");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[12]")).hasText("772");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[13]")).hasText("CaG");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[19]")).hasText("OHS");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[25]")).hasText("TTP");

  }

  @Test
  public void navigate_to_studies() throws Exception {
    get_network();
    browser().element(By.xpath("((//div[@class='markdown']))//p[1]")).hasText(startsWith("The Canadian Partnership for Tomorrow Project (CPTP)"));
    browser().element(By.xpath("(//table[@id='table-studies']//td)[1]")).hasText("Atlantic PATH");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[1]//a")).click();
    detailStudyTest();
  }

  @Test
  public void navigate_to_study_variables() throws Exception {
    get_network();
    browser().element(By.xpath("(//table[@id='table-studies']//td)[1]")).hasText("Atlantic PATH");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[6]")).hasText("772");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[6]//a")).click();
    browser().pause(500);
    browser().element(By.ref("search-criterion", "[1]")).hasText("CPTP");
    browser().element(By.ref("search-criterion", "[2]")).hasText("Atlantic PATH");
    browser().element(By.ref("variables")).hasText("772");

  }

  @Test
  public void sort_studies_table() throws Exception{
    get_network();
    browser().element(By.xpath("(//div[@id='table-studies_info'])")).hasText("Showing 1 to 5 of 5 entries");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[1]")).hasText("Atlantic PATH");
    browser().element(By.xpath("(//table[@id='table-studies']//th)[1]")).click();
    browser().element(By.xpath("(//table[@id='table-studies']//td)[1]")).hasText("TTP");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[25]")).hasText("Atlantic PATH");

  }

  @Test
  public void search_in_studies_table() throws Exception{
    get_network();
    browser().element(By.xpath("(//div[@id='table-studies_info'])")).hasText("Showing 1 to 5 of 5 entries");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[1]")).hasText("Atlantic PATH");
    browser().element(By.xpath("(//input[@type='search'])[1]")).clear();
    browser().element(By.xpath("(//input[@type='search'])[1]")).type("cag");
    browser().element(By.xpath("(//table[@id='table-studies']//td)[1]")).hasText("CaG");

  }

  private void get_network(){
    browser().navigate().to("http://localhost/drupal/mica/networks");
    browser().element(By.xpath("(//h4)[3]")).hasText("CPTP - Canadian Partnership for Tomorrow Project");
    browser().element(By.xpath("(//h4)[3]//a")).click();
  }

  private void detailStudyTest(){
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
  }

}
