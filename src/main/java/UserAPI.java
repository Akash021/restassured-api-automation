import org.json.JSONObject;
import io.restassured.response.Response;

public class UserAPI extends APIHelper {
	
	public static Response createUser(String emailAddress, String userName) { 
		String apiPath = "/Create";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("email", emailAddress);
		jsonObject.put("id", "ad-123");
		jsonObject.put("password", "mySecretPass234");
		jsonObject.put("username", userName);
		
		String payloadString = jsonObject.toString();
		Response createUserResponse = APIHelper.post(apiPath, payloadString);
		
		return createUserResponse;
	}
	
	public static Response createUserWithPayload(String createUserPayload) { 
		String apiPath = "/Create";
		
		Response createUserWithPayloadResponse = APIHelper.post(apiPath, createUserPayload);
		return createUserWithPayloadResponse;
	}
	
	public static Response updateUser(String userName) { 
		String apiPath = "/Update";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", userName);
		
		String payloadString = jsonObject.toString();
		Response updateUserResponse = APIHelper.put(apiPath, payloadString);
		
		return updateUserResponse;
	}
	
	public static Response readUser(String userName) { 
		String apiPath = "/Read";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", userName);
		
		String payloadString = jsonObject.toString();
		Response readUserResponse = APIHelper.post(apiPath, payloadString);
		
		return readUserResponse;
	}
	
	public static Response deleteUser(String userID) { 
		String apiPath = "/Delete";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", userID);
		
		String payloadString = jsonObject.toString();
		Response deleteUserResponse = APIHelper.delete(apiPath, payloadString);
		
		return deleteUserResponse;
	}
}
