package TestTwo;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataProviderEgFourDemo {
  

  @DataProvider
  public Object[][] dpOne() {
    return new Object[][] {
      new Object[] { 55, 66 },
      new Object[] { 33, 88 },
    };
  }
  
  @DataProvider
  public Object[][]dpTwo(){
	  return new Object[][] {
		  new Object[] {22, 77},
		  new Object[] { 99, 11},
	  };
  }
}
