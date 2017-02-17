package com.firone.maelstrom.test.utils;

public abstract class By extends org.openqa.selenium.By {

  public static org.openqa.selenium.By ref(String... references) {
    if (references == null)
      throw new IllegalArgumentException(
              "Cannot find elements without reference");

    return new ByXPath(mapRefToXpath(references));
  }

  public static org.openqa.selenium.By text(String uiText) {
    return org.openqa.selenium.By.xpath(xpathFindingDisplayedString(uiText));
  }

  static String mapRefToXpath(String... simplifiedSyntax) {
    String xpathSyntax = "";

    for (String element : simplifiedSyntax) {
      if (element.matches("\\[(\\d+)\\]"))
        xpathSyntax = "(" + xpathSyntax + ")" + element;
      else
        xpathSyntax += "//*[@test-ref='" + element + "']";
    }
    return xpathSyntax;
  }

  static String xpathFindingDisplayedString(String uiText) {
    return String.format("//*[text()='%s']", uiText.replaceAll("'", "\\\\\\'"));
  }
}
