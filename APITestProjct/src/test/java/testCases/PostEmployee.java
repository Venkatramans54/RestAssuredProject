 package testCases;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;
import utilities.BaseUtils;
import utilities.DataProviderUtil;

public class PostEmployee extends BaseClass {

	String URI = "http://dummy.restapiexample.com/api/v1";
	
	@BeforeClass
	public void beforeTest() {
		log.info("PostEmployees Test");
		RestAssured.baseURI = URI;
	}
	
	@Test(dataProviderClass = DataProviderUtil.class, dataProvider = "provide-data")
	public void createEmployee(String name, String salary, String age) {
		
		httpRequest = RestAssured.given();
		
		JSONObject obj=new JSONObject();
		obj.put("name", name);
		obj.put("salary", salary);
		obj.put("age", age);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(obj.toJSONString());
		response=httpRequest.request(Method.POST,"/create");
		System.out.println(response.getBody().asString());
		log.info("Response Body=> "+response.getBody().asString());
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(name), true);
		Assert.assertEquals(responseBody.contains(salary), true);
		Assert.assertEquals(responseBody.contains(age), true);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
