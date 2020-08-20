Feature: Validating various Jira Cloud functionalites 

@Regression 
Scenario: Verify if user is able to create a project 
	Given Create Project Payload 
	When user calls "createProject" with "POST" http request 
	Then the project gets created with status code 201
	
Scenario: Verify if user is able to create a task for a project 
	Given Create Task Payload 
	When user calls "createTask" with "POST" http request 
	Then the task gets created with status code 201
	
	
Scenario: Verify if user is able to post a comment for a task 
	Given Post Comment Payload 
	When user calls "postComment" with "POST" http request 
	Then the comment gets created with status code 201
	
Scenario: Verify if user is able to update a comment for a task 
	Given Update Comment Payload 
	When user calls "updateComment" with "PUT" http request 
	Then the comment gets updated with status code 200
	
 
#Scenario: Verify if user is able to delete a Project
#	Given Delete Project Payload 
#	When user calls "deleteProject" with "DELETE" http request 
	#Then the project gets deleted with status code 204