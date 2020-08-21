package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

import pojo.CreateProject;

public class PayLoadBuilder {
	List<String> data;
	Faker faker = new Faker();

	public PayLoadBuilder() {
		data = new ArrayList<String>();
		data.add(faker.book().title() + faker.artist().name() + faker.gameOfThrones().character());
		data.add(faker.book().publisher());
		data.add(faker.name().firstName().substring(0, 3).toUpperCase());
		data.add(faker.ancient().titan() + faker.name().fullName());
		data.add(faker.animal().name() + faker.app().author() + faker.hashCode());

	}

	public CreateProject createProjectPayLoad() throws IOException {

		CreateProject cp = new CreateProject();
		cp.setUrl(Utils.getGlobalValue("baseUrl"));
		cp.setAvatarId(Long.parseLong((Utils.getGlobalValue("avatarId"))));
		cp.setDescription(data.get(1));
		cp.setProjectTemplateKey(Utils.getGlobalValue("projectTemplateKey"));
		cp.setAssigneeType(Utils.getGlobalValue("assigneeType"));
		cp.setLeadAccountId(Utils.getGlobalValue("leadAccountId"));
		cp.setProjectTypeKey(Utils.getGlobalValue("projectTypeKey"));
		cp.setName(data.get(0));
		cp.setKey(data.get(2));
		return cp;

	}

	public String createTaskPayLoad(String projectID) {

		return "{\r\n" + "    \"fields\": {\r\n" + "        \"summary\": \"" + data.get(3) + "\",\r\n"
				+ "        \"issuetype\": {\r\n" + "            \"id\": \"10002\"\r\n" + "        },\r\n"
				+ "        \"project\": {\r\n" + "            \"id\": \"" + projectID + "\"\r\n" + "        },\r\n"
				+ "        \"description\": {\r\n" + "            \"type\": \"doc\",\r\n"
				+ "            \"version\": 1,\r\n" + "            \"content\": [\r\n" + "                {\r\n"
				+ "                    \"type\": \"paragraph\",\r\n" + "                    \"content\": [\r\n"
				+ "                        {\r\n" + "                            \"text\": \"" + data.get(4) + "\",\r\n"
				+ "                            \"type\": \"text\"\r\n" + "                        }\r\n"
				+ "                    ]\r\n" + "                }\r\n" + "            ]\r\n" + "        }\r\n"
				+ "    }\r\n" + "}   ";

	}

	public String postAComment() {

		return "{\r\n" + "   \"body\": {\r\n" + "        \"type\": \"doc\",\r\n" + "        \"version\": 1,\r\n"
				+ "        \"content\": [\r\n" + "            {\r\n" + "                \"type\": \"paragraph\",\r\n"
				+ "                \"content\": [\r\n" + "                    {\r\n"
				+ "                        \"text\": \"" + data.get(0) + "\",\r\n"
				+ "                        \"type\": \"text\"\r\n" + "                    }\r\n"
				+ "                ]\r\n" + "            }\r\n" + "        ]\r\n" + "    }\r\n" + "}";

	}

	public String updateComment() {
		// this.data();
		return "{\r\n" + "    \"body\": {\r\n" + "        \"type\": \"doc\",\r\n" + "        \"version\": 1,\r\n"
				+ "        \"content\": [\r\n" + "            {\r\n" + "                \"type\": \"paragraph\",\r\n"
				+ "                \"content\": [\r\n" + "                    {\r\n"
				+ "                        \"text\": \"" + data.get(2) + "\",\r\n"
				+ "                        \"type\": \"text\"\r\n" + "                    }\r\n"
				+ "                ]\r\n" + "            }\r\n" + "        ]\r\n" + "    }\r\n" + "}";

	}

}
