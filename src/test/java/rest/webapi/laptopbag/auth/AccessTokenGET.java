package rest.webapi.laptopbag.auth;

import static io.restassured.RestAssured.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.internal.mapping.Jackson1Mapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class AccessTokenGET {

	@Test
	public void testUsingAccessToken() throws URISyntaxException{
		URI canvasTestUri=new URI("https://cybertekschool.test.instructure.com/api/v1/accounts/self/users");
		
		given().auth().oauth2("10960~8OnebM63Rl4QdSaAyvKiZsGRj3DbLVgrAGtGGgWU3snDbrclxURG8ItcjqdP1Ilq")
		.when().get(canvasTestUri)
		.thenReturn().body().prettyPrint();
	}
	
	@Test
	public void getDirectionsUsingGoogleMapsAPI() throws URISyntaxException{
		final String OAUTH_TOKEN="AIzaSyAMAIb29emHMnZBSuzfIgupNWqPWzRNkEY";
		URI uri=new URI("https://maps.googleapis.com/maps/api/directions/json");
		
		String directions=given().params("origin", "92612", "destination","11003")
		.auth().oauth2(OAUTH_TOKEN)
		.when().get(uri)
		.thenReturn().body().asString();
		
		JsonPath json=new JsonPath(directions);
		String distance=json.getString("routes.legs.distance.text");
		String duration=json.getString("routes.legs.duration.text");
		
		System.out.println("distance : " + distance);
		System.out.println("duration : " + duration);
		
		Response response=given().params("origin", "92612", "destination","11003")
				.auth().oauth2(OAUTH_TOKEN)
				.when().get(uri);
		System.out.println(response.getBody().jsonPath().getString("routes.legs.distance.text"));
		
	}
	
}
