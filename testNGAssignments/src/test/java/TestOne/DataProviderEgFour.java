package TestOne;

import org.testng.annotations.Test;

import TestTwo.DataProviderEgFourDemo;
import TestTwo.DataProviderEgThreeDemo;

public class DataProviderEgFour {
	@Test(dataProvider = "dpOne", dataProviderClass = DataProviderEgFourDemo.class, priority =1 )
	  public void addition(Integer n, Integer s) {
		  System.out.println(n+s);
	  }
	  
	  @Test(dataProvider = "dpTwo", dataProviderClass = DataProviderEgFourDemo.class, priority =2)
	  public void subtratcion(Integer n, Integer s) {
		  System.out.println(n-s);
	  }
 
}
