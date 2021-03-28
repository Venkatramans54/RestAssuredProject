package testCases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class CookiesTest extends BaseClass {
	
	String URI = "http://dummy.restapiexample.com/api/v1";

	@BeforeClass
	public void getEmp() {
		RestAssured.baseURI = URI;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "employees");
	}
	
	@Test
	public void checkCookieParams() {
		log.info("Cookies Test");
		System.out.println(response.getCookies().size());
		Map<String, String> cookie=response.getCookies();
		for(Map.Entry<String, String> entry:cookie.entrySet()) {
			System.out.println(entry.getKey()+": "+entry.getValue());
			log.info(entry.getKey()+": "+entry.getValue());
		}
		Assert.assertTrue(!cookie.isEmpty());
	}

}
