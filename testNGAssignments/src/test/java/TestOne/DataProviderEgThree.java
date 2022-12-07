package TestOne;

import org.testng.annotations.Test;

import TestTwo.DataProviderEgThreeDemo;

public class DataProviderEgThree {
  @Test(dataProvider = "dp", dataProviderClass = DataProviderEgThreeDemo.class, priority =1 )
  public void addition(Integer n, Integer s) {
	  System.out.println(n+s);
  }
  
  @Test(dataProvider = "dp", dataProviderClass = DataProviderEgThreeDemo.class, priority =2)
  public void subtratcion(Integer n, Integer s) {
	  System.out.println(n-s);
  }
}
