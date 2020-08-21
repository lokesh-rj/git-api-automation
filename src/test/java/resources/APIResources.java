package resources;

//enum is special class in java which has collection of constants or  methods
public enum APIResources {

	createProject("/rest/api/3/project/"),
	createTask("/rest/api/3/issue/"), 
	postComment("/rest/api/3/issue/{issueId}/comment"), 
	updateComment("/rest/api/3/issue/{issueId}/comment/{id}"),
	getProject("/rest/api/3/project/{projectId}"),
	deleteProject("/rest/api/3/project/{projectId}");

	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
