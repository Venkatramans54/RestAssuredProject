package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class GetSingleEmployee extends BaseClass{
	String empid="";
	
	@BeforeClass
	public void getEmployee() {
		String URI = "http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		response=httpRequest.request(Method.GET, "/employees");
		JsonPath jsonPath =response.jsonPath();
		
		empid=jsonPath.get("[0].empid");
		
		response=httpRequest.request(Method.GET,"/employee/"+empid);
	}
	
	@Test
	public void checkResponse() {
		log.info("GetSingleEmployee Test");
		String responseBody=response.getBody().asString();
		log.info("Response Body=> "+responseBody);
		Assert.assertEquals(responseBody.contains(empid), true);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
