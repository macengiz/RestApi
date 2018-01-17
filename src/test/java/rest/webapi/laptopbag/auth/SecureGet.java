package rest.webapi.laptopbag.auth;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;

public class SecureGet {
	
	/*
	 * 1. Authorization using a request header
	 * 2. RestAssured methods and variables
	 */
	
	@Test
	public void getAllWithAuthorization() throws URISyntaxException{
		URI secureALL=new URI("http://localhost:8085/laptop-bag/webapi/secure/all");
		
		with().accept(ContentType.JSON)
		.when().get(secureALL)
		.then().assertThat().statusCode(401);
		
		with().header("Authorization", "Basic YWRtaW46d2VsY29tZQ==")
		.and().accept(ContentType.JSON)
		.when().get(secureALL)
		.then().statusCode(200);
	}
	
	@Test
	public void getAllWithAuthorizationRestAssuredMethod() throws URISyntaxException{
		/*
		 * auth()
		 */	
		URI secureALL=new URI("http://localhost:8085/laptop-bag/webapi/secure/all");
		
		given().auth().preemptive().basic("admin", "welcome")
		.when().get(secureALL)
		.then().assertThat().statusCode(Matchers.anyOf(Matchers.equalTo(200),Matchers.equalTo(204)));
	}
	
	@Test
	public void AuthorizationUsingEnvVariable(){
		baseURI="http://localhost";
		port=8085;
		basePath="/laptop-bag/webapi/secure";
		authentication=preemptive().basic("admin", "welcome");
		
		
		given().relaxedHTTPSValidation()
		.accept(ContentType.JSON)
		.when().get("/all")
		.then().assertThat().statusCode(200);
		
		
		
	}	
	
}
