
/**
 * Created by sramalin on 10/01/17.
 */
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.ErrorLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import org.json.*;


import static com.jayway.restassured.RestAssured.DEFAULT_URL_ENCODING_ENABLED;
import static com.jayway.restassured.RestAssured.given;


public class myFirstTest {

    @BeforeClass
    public static void setup() {

        RestAssured.basePath = "/api/v1/";
        RestAssured.baseURI = "https://login-sqe.pace.schneider-electric.com";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(ErrorLoggingFilter.errorLogger());

    }
    @Test
    public  void sampleGetTest()

    {
        // Sample body through Json object
        //        JSONObject jsonobj = new JSONObject();
//        try {
//            jsonobj.put("username","sramalin@thoughtworks.com");
//            jsonobj.put("password","Password@123");
//            jsonobj.put("grant_type","password");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
       // Sample body through Hashmap
//        Map<String, Object> userCredentials = new HashMap<String, Object>();
//        userCredentials.put("username","sramalin@thoughtworks.com");
//        userCredentials.put("password","Password@123");
//        userCredentials.put("grant_type","password");

        //Post call - "Content-Type", "application/x-www-form-urlencoded": Gson/Jackson will be able to seriealize the request from Json
        // However if the form params are enoded, the request should be in string format.
        String str = "/token";
        String strFormParams = "username=sramalin@thoughtworks.com&password=Password@123&grant_type=password";
         Map response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .accept("application/json")
                .body(strFormParams)
                .when().post(str).then()
                .statusCode(200)
                 .extract()
                 .as(Map.class);
        System.out.println("Response body: "+response.toString());

//        given()
//                .when().get().then()
//                .statusCode(200);
    }

}
