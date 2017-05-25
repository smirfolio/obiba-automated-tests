package org.obiba.mica.uitest.integration.login;

import org.junit.Test;
import org.obiba.mica.uitest.integration.IntegrationUITester;
import org.openqa.selenium.By;

public class MicaAndAgateLogin extends IntegrationUITester {

  @Test
  public void test_mica_login() {
    login_to_mica("administrator", "password");
    browser().element(By.xpath("//h1[contains(text(), 'Welcome to Mica!')]")).exists();
  }

  @Test
  public void test_agate_login() {
    login_to_agate("administrator", "password");
    browser().element(By.xpath("//h1/span[contains(text(), 'Welcome to Agate!')]")).exists();
  }
}
