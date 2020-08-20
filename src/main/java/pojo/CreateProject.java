package pojo;

public class CreateProject {

	private String description;
	private String url;
	private String projectTemplateKey;
	private long avatarId;
	private String name;
	private String assigneeType;
	private String projectTypeKey;
	private String key;
	private String leadAccountId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProjectTemplateKey() {
		return projectTemplateKey;
	}

	public void setProjectTemplateKey(String projectTemplateKey) {
		this.projectTemplateKey = projectTemplateKey;
	}

	public long getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(long avatarId) {
		this.avatarId = avatarId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAssigneeType() {
		return assigneeType;
	}

	public void setAssigneeType(String assigneeType) {
		this.assigneeType = assigneeType;
	}

	public String getProjectTypeKey() {
		return projectTypeKey;
	}

	public void setProjectTypeKey(String projectTypeKey) {
		this.projectTypeKey = projectTypeKey;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLeadAccountId() {
		return leadAccountId;
	}

	public void setLeadAccountId(String leadAccountId) {
		this.leadAccountId = leadAccountId;
	}

}