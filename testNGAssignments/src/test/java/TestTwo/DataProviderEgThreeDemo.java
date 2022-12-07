package TestTwo;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataProviderEgThreeDemo {
 
  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 12, 18 },
      new Object[] { 25, 8 },
    };
  }
}
