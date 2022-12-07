package TestOne;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataProviderEgOne {
  @Test(dataProvider = "dp")
  public void f(String n, String s) {
	  System.out.println(n.concat(s));
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "malaya", "lam" },
      new Object[] { "eng", "lish" },
      new Object[] {"mang","lish"},
      };
  }
}
