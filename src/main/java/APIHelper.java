import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class APIHelper {
	
	private static String token = System.getProperty("apikey");
	private static String baseURL = System.getProperty("baseurl");
	

    public static Response post(String path, String requestPayload){
    	String apiPath = baseURL + path;
        return given().
        		contentType(ContentType.JSON).
        		accept(ContentType.JSON).
        		header("Authorization", "Bearer "+token).
                body(requestPayload).
                when().
                post(apiPath).
                then().
                extract().
                response();
    }

    public static Response get(String path){
    	String apiPath = baseURL + path;
        return given().
        		contentType(ContentType.JSON).
        		accept(ContentType.JSON).
        		header("Authorization", "Bearer "+token).
        		when().
        		get(apiPath).
        		then().
        		extract().
        		response();
    }

    public static Response put(String path, String requestPayload){
    	String apiPath = baseURL + path;
        return given().
        		contentType(ContentType.JSON).
        		accept(ContentType.JSON).
        		header("Authorization", "Bearer "+token).
                body(requestPayload).
                when().
                put(apiPath).
                then().
                extract().
                response();
    }
    
    public static Response delete(String path, String requestPayload){
    	String apiPath = baseURL + path;
        return given().
        		contentType(ContentType.JSON).
        		accept(ContentType.JSON).
        		header("Authorization", "Bearer "+token).
                body(requestPayload).
                when().
                delete(apiPath).
                then().
                extract().
                response();
    }
}
