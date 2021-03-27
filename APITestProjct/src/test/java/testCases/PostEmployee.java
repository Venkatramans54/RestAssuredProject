package testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import utilities.BaseUtils;

public class PostEmployee extends BaseClass {

	String URI = "http://dummy.restapiexample.com/api/v1";
	BaseUtils utils=new BaseUtils();
	String name= utils.randName();
	String salary= utils.randSalary();
	String age=utils.randAge();
	
	
	@BeforeClass
	public void createEmployee() {
		RestAssured.baseURI = URI;
		httpRequest = RestAssured.given();
		
		JSONObject obj=new JSONObject();
		obj.put("name", name);
		obj.put("salary", salary);
		obj.put("age", age);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(obj.toJSONString());
		response=httpRequest.request(Method.POST,"/create");
		System.out.println(response.getBody().asString());
	}
	
	@Test
	public void checkResponse() {
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(name), true);
		Assert.assertEquals(responseBody.contains(salary), true);
		Assert.assertEquals(responseBody.contains(age), true);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
