package rest.webapi.laptopbag.get;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PerformGetRequest {

	/*
	 * When I perform get request to ping method Then status code should be 200
	 * And Message should be hi aydin
	 */

	@Test
	public void testPingMethod() throws URISyntaxException {

		URI pingURI = new URI("http://localhost:8085/laptop-bag/webapi/api/ping/aydin");

		Response response = when().get(pingURI);
		assertEquals(200, response.statusCode());
		assertEquals("Hi! aydin", response.body().asString());
	}

	@Test
	public void testPingWithNOArg() throws URISyntaxException {
		URI pingURI = new URI("http://localhost:8085/laptop-bag/webapi/api/ping/");
		System.out.println(pingURI.getPort());
		System.out.println(pingURI.getHost());
		System.out.println(pingURI.getPath());

		Response response = when().get(pingURI);
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		assertEquals(404, statusCode);
	}

	@Test
	public void testAllMethod() throws URISyntaxException {
		URI allURI = new URI("http://localhost:8085/laptop-bag/webapi/api/all");

		Response response = given().accept(ContentType.JSON).when().get(allURI);
		assertEquals(200, response.getStatusCode());
		System.out.print(response.body().asString());

		// 2 nd way with built in assertion

		given().accept(ContentType.JSON).when().get(allURI).then().assertThat().statusCode(200).and().assertThat()
				.contentType(ContentType.JSON);
	}

	@Test
	public void testFindWithID() throws URISyntaxException {
		URI findURI = new URI("http://localhost:8085/laptop-bag/webapi/api/find/100");
		given().accept(ContentType.JSON).when().get(findURI).then().assertThat().statusCode(200).and().assertThat()
				.body("Id", equalTo(100), "BrandName", equalToIgnoringCase("samsung"));

	}

}
