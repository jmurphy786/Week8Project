package com.qa.Week8Project;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.junit.Test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class TestCases {
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	
	
	@Given("^a vet$")
	public void a_vet() throws Throwable {
	    System.out.println("As a Vet User.");
	}

	@When("^I click on some records$")
	public void i_click_on_some_records() throws Throwable {
		given().contentType(ContentType.JSON).when().get("http://10.0.10.10:9966/petclinic/api/pets/1").then().statusCode(200);
		System.out.println("When I click on some records");
	}

	@Then("^I can see the care available for animals$")
	public void i_can_see_the_care_available_for_animals() throws Throwable {
		request = given();
		request.header("Content-Type", "application/json");
		request.header("petId", "1");
		response = request.get("http://10.0.10.10:9966/petclinic/api/pets/1");
		System.out.println("Then I can see the records for an animal");
		System.out.println(response.asString() + "\n");
		
	}

	@Given("^an admin$")
	public void an_admin() throws Throwable {
	    System.out.println("As an admin User.");
	}

	@When("^I update a record$")
	public void i_update_a_record() throws Throwable {
		
		RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/api/owners/2";
		
		JSONObject requestParams = new JSONObject();
		
		JSONObject owner = new JSONObject();
		JSONObject type = new JSONObject();
		JSONObject pet = new JSONObject();
		
		JSONArray jsonfarr = new JSONArray();
		JSONArray jsonfarr2 = new JSONArray();
		
		
		HashMap<String, Object> nestedPetAsMap = new HashMap<String, Object>();
		HashMap<String, Object> nestedVisitsAsMap = new HashMap<String, Object>();

		
		
		request = given();
		
		request.header("Content-Type", "application/json;charset=UTF-8");
		request.header("ownerId", "2");
			
		nestedPetAsMap.put("id", 2);
		nestedPetAsMap.put("name", "William");	
		nestedPetAsMap.put("birthDate", "20-18-97");
		nestedPetAsMap.put("type", type);
		type.put("id", 2);
		type.put("name", "cat");
		
		nestedPetAsMap.put("owner", 2);	
		
		nestedVisitsAsMap.put("date", "2018/04/12");
		nestedVisitsAsMap.put("description", "Had lice");
		nestedVisitsAsMap.put("id", 2);
		nestedVisitsAsMap.put("pet", pet);
		
		jsonfarr2.add(nestedVisitsAsMap);
		
		nestedPetAsMap.put("visits", jsonfarr2);	
		
		jsonfarr.add(nestedPetAsMap);
		
		requestParams.put("address", "Armagh");
		requestParams.put("city", "Armagh");
		requestParams.put("firstName", "William");
		requestParams.put("id", 2);
		requestParams.put("lastName", "Dadidson");
		requestParams.put("pets", jsonfarr);
		requestParams.put("telephone", "074616334");
		
		System.out.println("When i update the records");
		System.out.println(requestParams.toString());

		request.body(requestParams.toString());
		response = request.put("/");
		json = response.then().statusCode(204);
		System.out.println(response.getStatusCode() + "\n");


	}

	@Then("^the correct details are now shown$")
	public void the_correct_details_are_now_shown() throws Throwable {
		request = given();
		request.header("Content-Type", "application/json");
		request.header("ownerId", "2");
		response = request.get("http://10.0.10.10:9966/petclinic/api/owners/2");
		System.out.println("Then the correct details are shown");
		System.out.println(response.asString() + "\n");
	}

	
	@When("^I delete a animal$")
	public void i_delete_a_animal() throws Throwable {
		Response response = given()
  				.contentType(ContentType.JSON)
  				.header("petId", "5")
  				.delete("http://10.0.10.10:9966/petclinic/api/pets/5");
		System.out.println("When I delete an animal from the database");
		System.out.println("Delete Status Code is: " + response.statusCode());
		System.out.println(response.asString() + "\n");
	}

	@Then("^emails arent sent to deceased annimals$")
	public void emails_arent_sent_to_deceased_annimals() throws Throwable {
		Response response = given()
  				.contentType(ContentType.JSON)
  				.header("petId", "5")
  				.get("http://10.0.10.10:9966/petclinic/api/pets/5");
		System.out.println("Checking the animal is deceased: " + response.statusCode());
		if(response.statusCode() == 404) {
		System.out.println("Then the animal has been removed from the databse.\n");
		}
	}

	@When("^I add new records$")
	public void i_add_new_records() throws Throwable {
		
		RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/api/pettypes";
		
		System.out.println("When I add new records");
		request = given();
		request.header("Content-Type", "application/json");
		
		JSONObject addNewPet = new JSONObject();
		addNewPet.put("id", 9);
		addNewPet.put("name", "grizzly bear");
		
		request.body(addNewPet.toString());
		response = request.post("/");
		json = response.then().statusCode(201);
		
		
	}

	@Then("^the records are correct$")
	public void the_records_are_correct() throws Throwable {
		
		System.out.println("Then the records are correct");
		request = given();
		request.header("Content-Type", "application/json;charset=UTF-8");
		response = request.get("http://10.0.10.10:9966/petclinic/api/pettypes/9");
		json = response.then().statusCode(200);
		System.out.println(response.getStatusCode() + "\n");
		
	}

	@When("^I add new owners to the records$")
	public void i_add_new_owners_to_the_records() throws Throwable {
		System.out.println("When I add new owners to the records");
		
		RestAssured.baseURI = "http://10.0.10.10:9966/petclinic/api/owners";
		
		JSONObject requestParams = new JSONObject();
		
		JSONObject owner = new JSONObject();
		JSONObject type = new JSONObject();
		JSONObject pet = new JSONObject();
		
		JSONArray jsonfarr = new JSONArray();
		JSONArray jsonfarr2 = new JSONArray();
		
		
		HashMap<String, Object> nestedPetAsMap = new HashMap<String, Object>();
		HashMap<String, Object> nestedVisitsAsMap = new HashMap<String, Object>();

		
		
		request = given();
		
		request.header("Content-Type", "application/json");
		request.header("ownerId", "2");
			
		nestedPetAsMap.put("id", 2);
		nestedPetAsMap.put("name", "William");	
		nestedPetAsMap.put("birthDate", "20-18-97");
		nestedPetAsMap.put("type", type);
		type.put("id", 2);
		type.put("name", "cat");
		
		nestedPetAsMap.put("owner", 2);	
		
		nestedVisitsAsMap.put("date", "2018/04/12");
		nestedVisitsAsMap.put("description", "Had lice");
		nestedVisitsAsMap.put("id", 2);
		nestedVisitsAsMap.put("pet", pet);
		
		jsonfarr2.add(nestedVisitsAsMap);
		
		nestedPetAsMap.put("visits", jsonfarr2);	
		
		jsonfarr.add(nestedPetAsMap);
		
		requestParams.put("address", "Armagh");
		requestParams.put("city", "Armagh");
		requestParams.put("firstName", "William");
		requestParams.put("id", 10);
		requestParams.put("lastName", "Dadidson");
		requestParams.put("pets", jsonfarr);
		requestParams.put("telephone", "074616334");
		
		System.out.println(requestParams.toString());

		request.body(requestParams.toString());
		response = request.post("/");
		json = response.then().statusCode(201);
		System.out.println(response.getStatusCode() + "\n");
	}

	@Then("^the details show the change$")
	public void the_details_show_the_change() throws Throwable {
		System.out.println("Then the details are shown");
		request = given();
		request.header("Content-Type", "application/json");
		request.header("ownerId", "10");
		response = request.get("http://10.0.10.10:9966/petclinic/api/owners/10");
		System.out.println(response.getStatusCode() + "\n");
		
	}

}
