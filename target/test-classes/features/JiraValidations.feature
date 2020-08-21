Feature: Validating various Jira Cloud functionalites 

@Regression 
Scenario: Verify if user is able to create a project 
	Given Create Project Payload 
	When user calls "createProject" with "POST" http request 
	Then the project gets created with the expected status code 
	
Scenario: Verify if user is able to create a task for a project 
	Given Create Task Payload 
	When user calls "createTask" with "POST" http request 
	Then the task gets created with the expected status code 
	
	
Scenario: Verify if user is able to post a comment for a task 
	Given Post Comment Payload 
	When user calls "postComment" with "POST" http request 
	Then the comment gets created with the expected status code 
	
Scenario: Verify if user is able to update a comment for a task 
	Given Update Comment Payload 
	When user calls "updateComment" with "PUT" http request 
	Then the comment gets updated with the expected status code 
	
	
Scenario: Verify if user is able to get the Project 
	Given Get the project details 
	When user calls "getProject" with "GET" http request 
	Then the response is successful with the expected status code 
	
Scenario: Verify if user is able to delete the Project 
	Given Delete the project details 
	When user calls "deleteProject" with "DELETE" http request 
	Then the project gets deleted with the expected status code 
	
	
	
	
