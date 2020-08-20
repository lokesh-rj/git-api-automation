package resources;

import java.io.IOException;

import com.github.javafaker.Faker;

import pojo.CreateProject;

public class PayLoadBuilder {

	public String[] data() {
		Faker faker = new Faker();
		String[] data = { faker.book().title()+faker.artist().name(), faker.book().publisher(),
				faker.name().firstName().substring(0, 3).toUpperCase(), faker.ancient().titan()+faker.name().fullName(),
				faker.animal().name()+faker.app().author()+faker.hashCode() };
		return data;

	}

	public CreateProject createProjectPayLoad() throws IOException {
		CreateProject cp = new CreateProject();
		cp.setUrl(Utils.getGlobalValue("baseUrl"));
		cp.setAvatarId(Long.parseLong((Utils.getGlobalValue("avatarId"))));
		String[] data = data();
		cp.setDescription(data[1]);
		cp.setProjectTemplateKey(Utils.getGlobalValue("projectTemplateKey"));
		cp.setAssigneeType(Utils.getGlobalValue("assigneeType"));
		cp.setLeadAccountId(Utils.getGlobalValue("leadAccountId"));
		cp.setProjectTypeKey(Utils.getGlobalValue("projectTypeKey"));
		cp.setName(data[0]);
		cp.setKey(data[2]);
		return cp;

	}

	public String createTaskPayLoad(String projectID) {
		String[] data = data();

		return "{\r\n" + "    \"fields\": {\r\n" + "        \"summary\": \"" + data[3] + "\",\r\n"
				+ "        \"issuetype\": {\r\n" + "            \"id\": \"10002\"\r\n" + "        },\r\n"
				+ "        \"project\": {\r\n" + "            \"id\": \"" + projectID + "\"\r\n" + "        },\r\n"
				+ "        \"description\": {\r\n" + "            \"type\": \"doc\",\r\n"
				+ "            \"version\": 1,\r\n" + "            \"content\": [\r\n" + "                {\r\n"
				+ "                    \"type\": \"paragraph\",\r\n" + "                    \"content\": [\r\n"
				+ "                        {\r\n" + "                            \"text\": \"" + data[4] + "\",\r\n"
				+ "                            \"type\": \"text\"\r\n" + "                        }\r\n"
				+ "                    ]\r\n" + "                }\r\n" + "            ]\r\n" + "        }\r\n"
				+ "    }\r\n" + "}   ";

	}

	public String postAComment() {
		String[] data = data();

		return "{\r\n" + "   \"body\": {\r\n" + "        \"type\": \"doc\",\r\n" + "        \"version\": 1,\r\n"
				+ "        \"content\": [\r\n" + "            {\r\n" + "                \"type\": \"paragraph\",\r\n"
				+ "                \"content\": [\r\n" + "                    {\r\n"
				+ "                        \"text\": \"" + data[0] + "\",\r\n"
				+ "                        \"type\": \"text\"\r\n" + "                    }\r\n"
				+ "                ]\r\n" + "            }\r\n" + "        ]\r\n" + "    }\r\n" + "}";

	}

	public String updateComment() {
		
		String[] data = data();
		return "{\r\n" + 
				"    \"body\": {\r\n" + 
				"        \"type\": \"doc\",\r\n" + 
				"        \"version\": 1,\r\n" + 
				"        \"content\": [\r\n" + 
				"            {\r\n" + 
				"                \"type\": \"paragraph\",\r\n" + 
				"                \"content\": [\r\n" + 
				"                    {\r\n" + 
				"                        \"text\": \""+data[2]+"\",\r\n" + 
				"                        \"type\": \"text\"\r\n" + 
				"                    }\r\n" + 
				"                ]\r\n" + 
				"            }\r\n" + 
				"        ]\r\n" + 
				"    }\r\n" + 
				"}";
			
	}

}
