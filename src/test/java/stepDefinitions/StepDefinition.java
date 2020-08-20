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

		resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		if (httpType.equalsIgnoreCase("POST")) {
			if (resource.equalsIgnoreCase("postComment")) {
				response = res.when().post(resourceAPI.getResource() + taskId + "/comment");
			} else {
				response = res.when().post(resourceAPI.getResource());
			}
		} else if (httpType.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceAPI.getResource());
		} else if (httpType.equalsIgnoreCase("PUT")) {
			if (resource.equalsIgnoreCase("updateComment")) {
				response = res.when().put(resourceAPI.getResource() + taskId + "/comment/"+Integer.parseInt(commentId));
			} else {
				response = res.when().put(resourceAPI.getResource());
			}
		} else if(httpType.equalsIgnoreCase("DELETE")) {
			response = res.when().delete(resourceAPI.getResource()+Integer.parseInt(projectId));
		}

	}

	@Then("the project gets created with status code {int}")
	public void the_project_gets_created_with_status_code(Integer statusCode) {

		assertEquals(response.getStatusCode(), 201);
		projectId = getJsonPath(response, "id");
		System.out.println("Id of the project is: " + projectId);
	}

	@Given("Create Task Payload")
	public void create_Task_Payload() throws IOException {

		res = given().spec(requestSpecification()).body(payLoad.createTaskPayLoad(projectId));
		user_calls_with_http_request("createTask", "POST");
	}

	@Then("the task gets created with status code {int}")
	public void the_task_gets_created_with_status_code(Integer statusCode) {

		assertEquals(response.getStatusCode(), 201);
		taskId = getJsonPath(response, "id");
		taskKey = getJsonPath(response, "key");
		System.out.println("Task ID is : " + taskId);
		System.out.println("Task Key is : " + taskKey);

	}

	@Given("Post Comment Payload")
	public void post_Comment_Payload() throws IOException {

		res = given().spec(requestSpecification()).body(payLoad.postAComment());
		user_calls_with_http_request("postComment", "POST");

	}

	@Then("the comment gets created with status code {int}")
	public void the_comment_gets_created_with_status_code(Integer statusCode) {

		assertEquals(response.getStatusCode(), 201);
		commentId = getJsonPath(response, "id");
		System.out.println("Comment ID is : " + commentId);

	}

	@Given("Update Comment Payload")
	public void update_Comment_Payload() throws IOException {

		res = given().spec(requestSpecification()).body(payLoad.updateComment());
		user_calls_with_http_request("updateComment", "PUT");

	}

	@Then("the comment gets updated with status code {int}")
	public void the_comment_gets_updated_with_status_code(Integer statusCode) {

		assertEquals(response.getStatusCode(), 200);
		commentId = getJsonPath(response, "id");
		System.out.println("Comment ID update is : " + commentId);

	}

	@Given("Delete Project Payload")
	public void delete_Project_Payload() throws IOException {

		res = given().spec(requestSpecification());
		user_calls_with_http_request("deleteProject", "DELETE");
	}
}
//	@Then("the project gets deleted with status code {int}")
//	public void the_Project_gets_deleted_with_status_code(Integer statusCode) {
//
//		assertEquals(response.getStatusCode(), 204);
//
//	}
//
//}
