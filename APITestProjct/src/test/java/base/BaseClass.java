package base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseClass {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="9506";
	
	public static Logger log;
	
	public void setup() {
		System.out.println("hi");
		log=Logger.getLogger("API Framework");
		PropertyConfigurator.configure("log4j.properties");
		log.setLevel(Level.DEBUG);
	}

}
