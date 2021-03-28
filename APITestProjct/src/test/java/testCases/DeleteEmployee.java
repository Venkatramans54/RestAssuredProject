package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class DeleteEmployee extends BaseClass {
	
	@BeforeClass
	public void deleteEmployee() {
		
		String URI = "http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		response=httpRequest.request(Method.GET, "/employees");
		JsonPath jsonPath =response.jsonPath();
		
		String empid=jsonPath.get("[0].empid");
		response=httpRequest.request(Method.DELETE,"/delete/"+empid);
		
	}
	
	@Test
	public void checkResponse() {
		log.info("DeleteEmployee Test");
		String responseBody=response.getBody().asString();
		log.info("Response Body=> "+responseBody);
		Assert.assertEquals(responseBody.toLowerCase().contains("deleted records"), true);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
