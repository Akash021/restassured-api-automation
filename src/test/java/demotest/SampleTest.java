package demotest;

import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.jsonpath.JsonPath;
import helpers.UserAPI;
import io.restassured.response.Response;


public class SampleTest extends UserAPI {
	
	//comment for worktree test
	final String randomString = RandomStringUtils.randomAlphabetic(10).toLowerCase();
	final String testUser = "akash."+randomString;
	final String testEmailID = testUser+"@example.com";

	
	@Test(priority=1)
	public void createUser() {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("email", testEmailID);
		jsonObject.put("password", "mySecretsPass234");
		jsonObject.put("username", testUser);
		String createUserPayload = jsonObject.toString();
		
		Response res = UserAPI.createUserWithPayload(createUserPayload);
		System.out.println("Create User Response::\n"+ res.asString());
		Assert.assertEquals(res.statusCode(), 200);
		
		String jsonResponseString = res.asString();
		String username = JsonPath.read(jsonResponseString, "$.account.username");
		String emailID = JsonPath.read(jsonResponseString, "$.account.email");
		
		Assert.assertEquals(username, testUser);
		Assert.assertEquals(emailID, testEmailID);
	}
	
	@Test(priority=2)
	public void createUserWithDuplicateID() {
		
		Response res = UserAPI.readUser(testUser);
		String jsonResponseString = res.asString();
		System.out.println("Read Response::\n"+ jsonResponseString);
		
		String userID = JsonPath.read(jsonResponseString, "$.account.id");
		System.out.println("User ID :: "+ userID);
		
		String randomChars = RandomStringUtils.randomAlphabetic(10).toLowerCase();
		String newTestUser = "akash."+randomChars;
		String newTestEmailID = newTestUser+"@example.com";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("email", newTestEmailID);
		jsonObject.put("password", "mySecretsPass234");
		jsonObject.put("username", newTestUser);
		jsonObject.put("id", userID);
		
		String createUserPayload = jsonObject.toString();
		
		Response response = UserAPI.createUserWithPayload(createUserPayload);
		System.out.println("Create User Response::\n"+ response.asString());
		Assert.assertEquals(response.statusCode(), 500);

	}
	
	@Test(priority=3)
	public void createUserMissingMandatoryFields() {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("email", testEmailID);
		jsonObject.put("password", "mySecretsPass234");
		String createUserPayload = jsonObject.toString();
		
		Response res = UserAPI.createUserWithPayload(createUserPayload);
		System.out.println("Create User Response::\n"+ res.asString());
		
		Assert.assertEquals(res.statusCode(), 500);
		String jsonResponseString = res.asString();
		
		String errorMessageExpected = "username and email cannot be blank";
		String errorMessageActual = JsonPath.read(jsonResponseString, "$.Detail");
		
		Assert.assertEquals(errorMessageActual, errorMessageExpected);
		
	}
	
	@Test(priority=4)
	public void createUserMissingOptionalFields() {
		
		String randomChars = RandomStringUtils.randomAlphabetic(10).toLowerCase();
		String newTestUser = "akash."+randomChars;
		String newTestEmailID = newTestUser+"@example.com";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("email", newTestEmailID);
		jsonObject.put("password", "mySecretsPass234");
		jsonObject.put("username", newTestUser);
		String createUserPayload = jsonObject.toString();
	
		Response res = UserAPI.createUserWithPayload(createUserPayload);
		System.out.println("Create User Response::\n"+ res.asString());
		
		Assert.assertEquals(res.statusCode(), 200);
		
		String jsonResponseString = res.asString();
		String username = JsonPath.read(jsonResponseString, "$.account.username");
		String emailID = JsonPath.read(jsonResponseString, "$.account.email");
		
		Assert.assertEquals(username, newTestUser);
		Assert.assertEquals(emailID, newTestEmailID);
	}
	
	@Test(priority=5)
	public void updateUser() {

		String updatedEmail = "testuseremailupdated@example.com";
		
		Response res = UserAPI.readUser(testUser);
		String jsonResponseString = res.asString();
		System.out.println("Read Response::\n"+ jsonResponseString);
		
		String userID = JsonPath.read(jsonResponseString, "$.account.id");
		System.out.println("User ID :: "+ userID);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", userID);
		jsonObject.put("email", updatedEmail);
		
		
		String updatePayload = jsonObject.toString();
		Response updateResponse = UserAPI.updateUser(updatePayload);
		Assert.assertEquals(updateResponse.statusCode(), 200);
		
		Response newReadResponse = UserAPI.readUser(testUser);
		String response = newReadResponse.asString();
		System.out.println("Update Response::\n"+ response);
		
		String actualEmailIDFromResponse = JsonPath.read(response, "$.account.email");
		
		Assert.assertEquals(actualEmailIDFromResponse, updatedEmail);
		
	}
	
	@Test(priority=6)
	public void deleteUser() {
		Response res = UserAPI.readUser(testUser);
		String jsonResponseString = res.asString();
		System.out.println("Read Response::\n"+ jsonResponseString);
		String userID = JsonPath.read(jsonResponseString, "$.account.id");
		System.out.println("User ID :: "+ userID);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", userID);
		
		String deletePayload = jsonObject.toString();
		Response deleteResponse = UserAPI.deleteUser(deletePayload);
		Assert.assertEquals(deleteResponse.statusCode(), 200);
		
		System.out.println("Delete Response::\n"+ deleteResponse.asString());
		
	}

}
