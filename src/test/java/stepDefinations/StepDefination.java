package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
//import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.io.IOException;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	TestDataBuild testDataBuild = new TestDataBuild();
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecification;
	Response response;
	static String place_id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with_markettime_hindi_vesu_piplod(String name, String language, String address)
			throws IOException {

		requestSpecification = given().spec(requestSpecification())
				.body(testDataBuild.addPlacePayLoad(name, language, address));
		
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
	}

	@When("User call {string} with {string} http request")
	public void user_call_with_http_request(String resource, String method) {
		APIResources apiResources = APIResources.valueOf(resource);
		System.out.println(apiResources.getResource());
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
		if (method.equalsIgnoreCase("POST")) {
			response = requestSpecification.when().post(apiResources.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			response = requestSpecification.when().get(apiResources.getResource());
		}

	}

	@Then("the API call got success with status {int}")
	public void the_api_call_got_success_with_status(Integer int1) {

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {

		assertEquals(getJsonPath(response, keyValue), expectedValue);

	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {

		requestSpecification = given().spec(requestSpecification()).body(testDataBuild.deletePlacePayLoad(place_id));

	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {

		// requestSpec
		place_id = getJsonPath(response, "place_id");
		requestSpecification = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_call_with_http_request(resource, "GET");
		String actualName = getJsonPath(response, "name");
//		assertEquals(actualName, expectedName);

	}
}
