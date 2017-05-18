package org.obiba.mica.uitest.drupal;

import java.util.List;

import org.obiba.mica.uitest.utils.By;
import org.obiba.mica.uitest.utils.UITester;
import org.openqa.selenium.WebElement;

public class DrupalUITester extends UITester {

  protected void login_as_admin() {
    browser().navigate().to("http://localhost/drupal");

    browser().element(By.xpath("//a[contains(text(), 'Sign In')]")).click();

    browser().element(By.xpath("//input[@id='edit-name']")).type("administrator");
    browser().element(By.xpath("//input[@id='edit-pass']")).type("password");

    browser().element(By.xpath("//button[@id='edit-submit']")).click();
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
}
