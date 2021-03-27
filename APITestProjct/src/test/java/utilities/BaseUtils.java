package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class BaseUtils {
	
	public String randName() {
		return "John"+RandomStringUtils.randomAlphabetic(2);		
	}
	
	public String randSalary() {
		return RandomStringUtils.randomNumeric(6);
	}
	
	public String randAge() {
		return RandomStringUtils.randomNumeric(2);
	}

}
