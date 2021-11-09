import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class SampleTest extends UserAPI {
	
	@Test
	public void createUserTest() {
		Response res = UserAPI.createUser("abcdfe@example.com", "new31234");
		Assert.assertEquals(res.statusCode(), 200);
		System.out.println("Post Response::\n"+ res.asString());
	}
	
	@Test
	public void UpdateUser() {
	}
	
	@Test
	public void deleteUser() {
	}
}
