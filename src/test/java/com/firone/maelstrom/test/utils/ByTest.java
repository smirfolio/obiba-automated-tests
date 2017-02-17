package com.firone.maelstrom.test.utils;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ByTest {

  @Test
  public void can_search_without_count() {
    assertThat(By.mapSyntax("study"), is("//*[@test-ref='study']"));
    assertThat(By.mapSyntax("study", "networkCount"), is("//*[@test-ref='study']//*[@test-ref='networkCount']"));
  }

  @Test
  public void can_search_with_one_count() {
    assertThat(By.mapSyntax("study", "[2]", "networkCount"), is("(//*[@test-ref='study'])[2]//*[@test-ref='networkCount']"));
    assertThat(By.mapSyntax("search", "acronym", "[2]"), is("(//*[@test-ref='search']//*[@test-ref='acronym'])[2]"));
  }

  @Test
  public void can_search_with_many_counts() {
    assertThat(By.mapSyntax("search", "[10]", "acronym", "[2]"), is("((//*[@test-ref='search'])[10]//*[@test-ref='acronym'])[2]"));
  }
}
