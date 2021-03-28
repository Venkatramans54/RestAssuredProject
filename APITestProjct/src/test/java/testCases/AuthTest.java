package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import junit.framework.Assert;

public class AuthTest extends BaseClass {
	
	String URI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
	
	@Test
	public void getEmp() {
		log.info("Authentication Test");
		RestAssured.baseURI = URI;
		
		PreemptiveBasicAuthScheme auth=new PreemptiveBasicAuthScheme();
		auth.setUserName("ToolsQA");
		auth.setPassword("TestPassword");
		
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/");
		log.info("Response Body=> "+response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
