package org.obiba.mica.uitest.drupal;

import org.junit.Test;
import org.openqa.selenium.By;

public class DrupalLogin extends DrupalUITester {

  @Test
  public void test_is_not_logged_in() {
    browser().navigate().to("http://localhost/drupal");
    assert !isIsLoggedIn();
  }

  @Test
  public void test_is_logged_in() {
    login_as_admin();
    assert isIsLoggedIn();
  }

  @Test
  public void test_is_logged_out() {
    login_as_admin();
    browser().element(By.xpath("//li[contains(@class, 'logout last')]/a")).click();
    assert  !isIsLoggedIn();
  }
}
