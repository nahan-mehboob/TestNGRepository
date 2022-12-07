package TestOne;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataProviderEgTwo {
 
  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 4f, 94f },
      new Object[] { 2f, 114f },
      new Object[] {3f, 666f},
      new Object[] { 7f, 1779f},
      };
  }
}

