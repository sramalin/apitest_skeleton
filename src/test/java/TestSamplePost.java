
/**
 * Created by sramalin on 10/01/17.
 */

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.ErrorLoggingFilter;
import config.ConfigFileObject;
import helpers.DataHelper;
import helpers.EndPointHelper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;

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
       /* String str = "/token";
        String strFormParams = "username=sramalin@thoughtworks.com&password=Password@123&grant_type=password";
        Map response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .accept("application/json")
                .body(strFormParams)
                .when().post(str).then()
                .statusCode(200)
                .extract()
                .as(Map.class);*/


public class TestSamplePost {

    private static String basicAuth ="";
    private static ConfigFileObject configObject;

    @BeforeClass
    public static void setup() {
        System.out.println("Environment chosen : "+ System.getProperty("environment"));
        String propertyFileName = System.getProperty("environment").toLowerCase()+"-testconfig.properties";
        System.out.println("Property file loaded: "+propertyFileName);

//        configObject = new ConfigFileObject(propertyFileName);
//        RestAssured.basePath = "/api/v1";
//        RestAssured.baseURI = configObject.strURL;
//        System.out.println("Base Path is "+basePath);
//        System.out.println("Base URI is "+baseURI);
//        basicAuth = configObject.basicAuth;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(ErrorLoggingFilter.errorLogger());
    }

    @Test
    public  void getQTAuthToken() {
        String strQTAuthToken = EndPointHelper.getQTToken("tester","tester",302);
        System.out.println("#### QT Auth token : "+ strQTAuthToken+ " ###");
        System.out.println("End of test : getQTAuthToken");
    }

    @Test
    public  void getTokenWithGrantTypePassword() {
        Map response = EndPointHelper.getToken(configObject.existingUser,configObject.existingUserPassword,200);
        System.out.println("Validated the status code - Got 200");
        System.out.println("access_token : " +response.get("access_token"));
        System.out.println("token_type : " +response.get("token_type"));
        System.out.println("expires_in : " +response.get("expires_in"));
        System.out.println("End of test : TestSamplePost");
    }

    @Test
    public void createUserTest(){
        Map userProfile= DataHelper.getDefaultUserProfileData();
        Map response = EndPointHelper.createUser(userProfile,basicAuth,201);
        System.out.println("Validated the status code - Got 201");
        System.out.println("success : " +response.get("success"));
        System.out.println("successDescription : " +response.get("successDescription"));
        System.out.println("status : " +response.get("status"));
    }
    @Test
    public void registrationTest_MEA(){
        String clientId="mea";
        Map userProfile= DataHelper.getDefaultRegistrationData();
        String emailId=userProfile.get("email").toString();
        EndPointHelper.register(userProfile,clientId,302);
        Map userData = DataHelper.getDefaultUserProfileData();
        userData.put("email",emailId);
        Map response =EndPointHelper.createUser(userData,basicAuth,400);
        Assert.assertTrue(response.get("error").equals("email_not_free"));
        System.out.println(emailId+" is registered successfully.");
    }

  //  @Test
  // TODO [SARATH] Reponse needs to be validated based on the data setup in DB
    public void check(){
        Map getTokenResponse = EndPointHelper.getToken(configObject.existingUser,configObject.existingUserPassword,200);
        String accessToken = (String)getTokenResponse.get("access_token");
        System.out.println("Access Token : " +accessToken);

        Map profileTypeResponse = EndPointHelper.getProfileType(accessToken,200);
        String profileType = (String)profileTypeResponse.get("profileTypeId");
        System.out.println("Profile Type for " +configObject.existingUser+" : "+ profileType);

        Map profileFieldResponse = EndPointHelper.getProfileFields(profileType,accessToken,200);
        System.out.println("Profile Field : "+profileFieldResponse);

    }
}