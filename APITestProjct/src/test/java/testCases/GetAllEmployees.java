package testCases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetAllEmployees extends BaseClass {

	String URI = "http://dummy.restapiexample.com/api/v1";

	@BeforeClass
	public void getEmp() {
		RestAssured.baseURI = URI;
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "employees");
	}
	
	@Test
	public void checkResponse() {
		log.info("GetAllEmployees Test");
		checkResponseBody();
		checkStatusCode();
		checkResponseTime();
	}
	
	@Test
	public void checkHeaders() {
		checkStatusLine();
		checkContentType();
		checkServerType();
		checkContentEnoding();
	}
	
	public void checkResponseBody() {
		String responseBody=response.getBody().asString();
		log.info("Response Body=> "+responseBody);
		System.out.println(response.getBody().asString());
		Assert.assertTrue(responseBody!=null);
	}

	public void checkStatusCode() {
		int statusCode=response.getStatusCode();
		System.out.println("StatusCode: "+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	public void checkResponseTime() {
		long responseTime=response.getTime();
		System.out.println("Response Time: "+responseTime);
		Assert.assertTrue(responseTime<7000);
	}
	
	public void checkStatusLine() {
		String statusLine=response.getStatusLine();
		System.out.println("Status line: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	public void checkContentType() {
		String contentType=response.getContentType();
		System.out.println("Content Type: "+contentType);
		Assert.assertEquals(contentType, "application/json");
	}
	
	public void checkServerType() {
		String serverType=response.header("Server");
		System.out.println("Server Type: "+serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	
	public void checkContentEnoding() {
		String contentEncoding=response.header("Content-Encoding");
		System.out.println("Content Encoding: "+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

}
