import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class SampleTest extends APIHelper {

	final static String ROOT_URI = "https://api.m3o.com/v1/user";
	
//	@Test
//	public void createUser() {
//		String token = System.getProperty("apikey");
//		Response response = given().
//				contentType(ContentType.JSON)
//				.accept(ContentType.JSON)
//				.header("Authorization", "Bearer "+token)
//				.body("{\r\n"
//						+ "  \"email\": \"jod123@example.com\",\r\n"
//						+ "  \"id\": \"usrid-15\",\r\n"
//						+ "  \"password\": \"mySecretPass12345\",\r\n"
//						+ "  \"username\": \"usrname-15\"\r\n"
//						+ "}")
//				.when()
//				.post(ROOT_URI + "/Create");
//		System.out.println("Post Response::\n"+ response.asString());
//	}
	
//	@Test
//	public void UpdateUser() {
//		String token = System.getProperty("apikey");
//		Response response = given().
//				contentType(ContentType.JSON)
//				.accept(ContentType.JSON)
//				.header("Authorization", "Bearer "+token)
//				.body("{\r\n"
//						+ "  \"email\": \"jod13@example.com\",\r\n"
//						+ "  \"id\": \"usrid-15\"\r\n"
//						+ "}")
//				.when()
//				.post(ROOT_URI + "/Update");
//		System.out.println("Post Response::\n"+ response.asString());
//	}
//	
	@Test
	public void deleteUser() {
		String token = System.getProperty("apikey");
		Response response = given().
				contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", "Bearer "+token)
				.body("{\r\n"
						+ "  \"id\": \"usrid-15\"\r\n"
						+ "}")
				.when()
				.post(ROOT_URI + "/Delete");
		System.out.println("Post Response::\n"+ response.asString());
	}
}
