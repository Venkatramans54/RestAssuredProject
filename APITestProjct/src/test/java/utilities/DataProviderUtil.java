package utilities;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {
	
	@DataProvider(name="provide-data")
	  public static Object[][] provideData() {
		BaseUtils utils=new BaseUtils();
	    return utils.parseExcel("EmployeeDetails", 3);
	  }

}
