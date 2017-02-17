package com.firone.maelstrom.test.utils;

public abstract class By extends org.openqa.selenium.By {

  public static org.openqa.selenium.By ref(String... references) {
    if (references == null)
      throw new IllegalArgumentException(
              "Cannot find elements without reference");

    return new ByXPath(mapSyntax(references));
  }

  static String mapSyntax(String... simplifiedSyntax) {
    String xpathSyntax = "";

    for (String element : simplifiedSyntax) {
      if (element.matches("\\[(\\d+)\\]"))
        xpathSyntax = "(" + xpathSyntax + ")" + element;
      else
        xpathSyntax += "//*[@test-ref='" + element + "']";
    }
    return xpathSyntax;
  }
}
