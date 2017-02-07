
/**
 * Created by sramalin on 10/01/17.
 */

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.ErrorLoggingFilter;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;


public class TestSamplePost {

    @BeforeClass
    public static void setup() {

        System.out.println("Test setup for TestSamplePost");
        RestAssured.basePath = Reader.getProperty("SQA.basePath", "testconfig.properties");
        RestAssured.baseURI = Reader.getProperty("SQA.baseURL", "testconfig.properties");
          System.out.println(baseURI);
        System.out.println(basePath);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(ErrorLoggingFilter.errorLogger());

    }
    @Test
    public  void getTokenTest()

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
        ///System.out.println("Response body: "+response.toString());
        System.out.println("Validated the status code - Got 200");
        System.out.println("access_token : " +response.get("access_token"));
        System.out.println("token_type : " +response.get("token_type"));
        System.out.println("expires_in : " +response.get("expires_in"));
        System.out.println("End of test : TestSamplePost");


    }

    @Test
    public void createUserTest(){
        Map<String, Object> userProfile = new HashMap<String, Object>();
        Map<String, Object> companyDetails = new HashMap<String, Object>();
        companyDetails.put("id",0);
        companyDetails.put("name","test");
        companyDetails.put("city","Bangalore");
        companyDetails.put("zipCode","560068");
        companyDetails.put("state","karnataka");
        companyDetails.put("country","IN");
        companyDetails.put("currencyCode","");
        companyDetails.put("customerClass","");
        companyDetails.put("marketSegment","");
        companyDetails.put("poBox","560067");
        String newUserName = "sramalin" + UUID.randomUUID().toString()+"@mailinator.com";
        userProfile.put("email",newUserName);
        userProfile.put("password","Password@123");
        userProfile.put("firstName","firstname");
        userProfile.put("lastName","lastname");
        userProfile.put("companyName","company123");
        userProfile.put("country","FR");
        userProfile.put("channel","OM");
        userProfile.put("subChannel","SI3");
        userProfile.put("preferredLanguage","fr");
        userProfile.put("jobFunction","Z003");
        userProfile.put("street","street");
        userProfile.put("partnerId","pace");

        userProfile.put("addInfoAddress","address");
        userProfile.put("cell","+91 9999999999");
        userProfile.put("phone","+91 9999999997");
        userProfile.put("localityName","Bangalore");
        userProfile.put("postalCode","560067");
        userProfile.put("state","KA");
        userProfile.put("prefComEmail","U");
        userProfile.put("company",companyDetails);

        String str = "/user";
        Map response = given()
                .header("Content-Type", "application/json")
                .header("Authorization","Basic cGFjZTpwYWNl")
                .accept("application/json")
                .body(userProfile)
                .when().post(str).then()
                .statusCode(201)
                .extract()
                .as(Map.class);
        ///System.out.println("Response body: "+response.toString());
        System.out.println("Validated the status code - Got 201");
        System.out.println("Username : " + newUserName);
        System.out.println("success : " +response.get("success"));
        System.out.println("successDescription : " +response.get("successDescription"));
        System.out.println("status : " +response.get("status"));


    }

}