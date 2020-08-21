package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.PayLoadBuilder;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	PayLoadBuilder payLoad = new PayLoadBuilder();
	static String projectId;
	static String taskId;
	static String commentId;
	static String taskKey;
	APIResources resourceAPI;

	@Given("Create Project Payload")
	public void create_Project_Payload() throws IOException {

		res = given().spec(requestSpecification()).body(payLoad.createProjectPayLoad());

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpType) {

		resourceAPI = APIResources.valueOf(resource);

		if (httpType.equalsIgnoreCase("POST")) {

			response = res.when().post(resourceAPI.getResource());
		}

		else if (httpType.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceAPI.getResource());
		} else if (httpType.equalsIgnoreCase("PUT")) {
			response = res.when().put(resourceAPI.getResource());
		} else {
			response = res.when().delete(resourceAPI.getResource());
		}

	}

	@Then("the project gets created with the expected status code")
	public void the_project_gets_created_with_status_code() {
		assertEquals(response.getStatusCode(), 201);
		projectId = getJsonPath(response, "id");
		System.out.println("Id of the project is: " + projectId);
	}

	@Given("Create Task Payload")
	public void create_Task_Payload() throws IOException {

		res = given().spec(requestSpecification()).body(payLoad.createTaskPayLoad(projectId));

	}

	@Then("the task gets created with the expected status code")
	public void the_task_gets_created_with_status_code() {
		taskId = getJsonPath(response, "id");
		taskKey = getJsonPath(response, "key");
		System.out.println("Task id is :" + taskId + " and Task Key is : " + taskKey);
		assertEquals(response.getStatusCode(), 201);

	}

	@Given("Post Comment Payload")
	public void post_Comment_Payload() throws IOException {

		res = given().spec(requestSpecification()).pathParam("issueId", taskId).body(payLoad.postAComment());

	}

	@Then("the comment gets created with the expected status code")
	public void the_comment_gets_created_with_status_code() {
		commentId = getJsonPath(response, "id");
		System.out.println("Comment ID is : " + commentId);
		assertEquals(response.getStatusCode(), 201);

	}

	@Given("Update Comment Payload")
	public void update_Comment_Payload() throws IOException {

		res = given().spec(requestSpecification()).pathParam("issueId", taskId).pathParam("id", commentId)
				.body(payLoad.updateComment());

	}

	@Then("the comment gets updated with the expected status code")
	public void the_comment_gets_updated_with_status_code() {

		assertEquals(response.getStatusCode(), 200);
		System.out.println("Comment got updated succesfully");

	}

	@Given("Get the project details")
	public void get_the_project_details() throws IOException {
		res = given().spec(requestSpecification()).pathParam("projectId", projectId);

	}

	@Then("the response is successful with the expected status code")
	public void the_response_is_successful_with_the_expected_status_code() throws IOException {
		assertEquals(response.getStatusCode(), 200);
		System.out.println("Get response is successful");
	}

	@Given("Delete the project details")
	public void delete_the_project_details() throws IOException {

		res = given().spec(requestSpecification()).pathParam("projectId", projectId);

	}

	@Then("the project gets deleted with the expected status code")
	public void the_project_gets_deleted_with_the_expected_status_code() {

		assertEquals(response.getStatusCode(), 204);
		System.out.println("Deleted the project successfully!");

	}

}
