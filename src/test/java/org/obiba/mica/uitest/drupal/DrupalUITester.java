package org.obiba.mica.uitest.drupal;

import java.util.List;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.openqa.selenium.WebElement;

public class DrupalUITester extends UITester {

  protected void login_to_drupal_as_admin() {
    login_to_drupal("administrator", "password");
  }

  protected void login_to_drupal(String username, String password) {
    browser().navigate().to("http://localhost/drupal");

    if (!isIsLoggedInToDrupal()) {
      browser().element(By.xpath("//a[contains(text(), 'Sign In')]")).click();

      browser().element(By.xpath("//input[@id='edit-name']")).type(username);
      browser().element(By.xpath("//input[@id='edit-pass']")).type(password);

      browser().element(By.xpath("//button[@id='edit-submit']")).click();
    }
  }

  protected void toggle_all_variable_classification_graphics(boolean enable) {
    browser().element(By.xpath("//a[@id='toolbar-link-admin-config']")).click();
    browser().navigate().to("http://localhost/drupal/admin/config/obiba-mica/obiba-search-pages-settings");

    List<WebElement> checkboxes = browser().wrappedDriver()
        .findElements(By.xpath("//table[@id='edit-mica-taxonomy-figures']//tbody//input[@class='form-checkbox']"));

    for(WebElement checkbox : checkboxes) {
      if (checkbox.isSelected() == !enable) checkbox.click();
    }

    browser().element(By.xpath("//input[@id='edit-other-settings-action-submit']")).click();
  }

  boolean isIsLoggedInToDrupal() {
    List<WebElement> elements = browser().wrappedDriver().findElements(org.openqa.selenium.By.xpath("//div[@id='toolbar']"));
    return elements.size() > 0;
  }
}
