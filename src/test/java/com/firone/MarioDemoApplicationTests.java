package com.firone;

import com.vtence.mario.BrowserDriver;
import com.vtence.mario.UnsynchronizedProber;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MarioDemoApplicationTests {

  private BrowserDriver browser;

  @Before
  public void setUp() throws Exception {
    browser = new BrowserDriver(new UnsynchronizedProber(1000, 50), new FirefoxDriver());
  }

  @After
  public void tearDown() throws Exception {
    browser.navigate().to("http://mica-demo.obiba.org/#/logout");
    browser.quit();
  }

  @Test
  public void can_login() {
    loginDefaultUser();
  }

  @Test
  public void can_create_and_delete_study() throws Exception {

    loginDefaultUser();

    browser.element(By.linkText("Studies")).click();
    browser.element(By.linkText("Add Study")).click();

    browser.element(schemaFormElement("_name")).type("It's me, Mario !");
    browser.element(schemaFormElement("_acronym")).type("Mario 64");
    browser.element(schemaFormElement("_objectives")).type("Have 120 stars !");

    browser.element(buttonWithText("Save")).click();

    browser.element(By.xpath("//a[@href=\"#/study/mario-64\"]"));
    browser.element(By.xpath("//*[contains(@ng-model, '_objectives')]/div//p")).hasText("Have 120 stars !");


    browser.element(By.xpath("//button[contains(text(), \"Draft\")]")).click();
    browser.element(By.xpath("//span[text()=\"To Deleted\"]")).click();
    browser.element(By.xpath("//span[text()=\"Delete\"]")).click();
  }

  private void loginDefaultUser() {
    browser.navigate().to("http://mica-demo.obiba.org");
    browser.element(By.id("username")).type("administrator");
    browser.element(By.id("password")).type("password");

    browser.element(loginButton()).click();

    browser.element(By.xpath("//h1")).hasText("Welcome to Mica!");
  }

  private By loginButton() {
    return By.xpath("//button[@type=\"submit\"]");
  }

  private By schemaFormElement(String fieldId) {
    return By.xpath("//*[@ng-model=\"model['" + fieldId + "'][locale]\"]");
  }

  private By buttonWithText(String buttonText) {
    return By.xpath("//button/span[text()='" + buttonText + "']");
  }
}
