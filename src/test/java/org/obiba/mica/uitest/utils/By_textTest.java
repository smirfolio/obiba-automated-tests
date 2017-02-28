package org.obiba.mica.uitest.utils;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class By_textTest {

  @Test
  public void can_search_simple_text() {
    assertThat(By.xpathFindingDisplayedString("study"), is("//*[text()='study']"));
  }

  @Test
  public void can_search_with_spaces() {
    assertThat(By.xpathFindingDisplayedString("study with spaces"), is("//*[text()='study with spaces']"));
  }

  @Test
  public void can_search_with_apostrophe() {
    assertThat(By.xpathFindingDisplayedString("study ' with spaces and '"), is("//*[text()='study \\' with spaces and \\'']"));
  }
}
