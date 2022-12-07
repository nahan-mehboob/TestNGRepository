package TestTwo;

import org.testng.annotations.Test;

import TestOne.DataProviderEgTwo;

public class DataProviderEgTwoDemo {
  @Test(dataProvider = "dp", dataProviderClass = DataProviderEgTwo.class)
  public void f(float n, float s) {
	  float l = n/s;
	  System.out.println(l);
  }
}
