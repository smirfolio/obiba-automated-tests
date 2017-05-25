package org.obiba.mica.uitest.integration;

import org.obiba.mica.uitest.drupal.DrupalUITester;
import org.openqa.selenium.By;

public class IntegrationUITester extends DrupalUITester {

  protected void login_to_mica(String username, String password) {
    browser().navigate().to("http://localhost:8082/#/login");

    browser().element(By.xpath("//input[@id='username']")).enterTextUsingKeyboard(username);
    browser().element(By.xpath("//input[@id='password']")).enterTextUsingKeyboard(password);

    browser().element(By.xpath("//button[@type='submit']")).click();
  }

  protected void login_to_agate(String username, String password) {
    browser().navigate().to("http://localhost:8081/#/login");

    browser().element(By.xpath("//input[@id='username']")).enterTextUsingKeyboard(username);
    browser().element(By.xpath("//input[@id='password']")).enterTextUsingKeyboard(password);

    browser().element(By.xpath("//button[@type='submit']")).click();
  }
}
