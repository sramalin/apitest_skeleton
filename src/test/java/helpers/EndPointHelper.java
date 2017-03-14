package helpers;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import config.ConfigFileObject;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by ksarath on 2/10/17.
 */
public class EndPointHelper{
    //200


    public static Map getToken(String username,String password,int responseCode){
        RestAssured.basePath = "/api/v1";
        String endPoint = "/token";
        String params = "username="+username+"&password="+password+"&grant_type=password";
         Map response = given().log().ifValidationFails()
                 .header("Content-Type", "application/x-www-form-urlencoded")
                 .accept("application/json")
                 .body(params)
                 .when().post(endPoint)
                 .then()
                 .statusCode(responseCode)
                 .extract()
                 .as(Map.class);
         return response;
    }
    //201
    public static Map createUser(Map userProfile,String basicAuth,int responseCode){
        RestAssured.basePath = "/api/v1";
        String endPoint = "/user";
        Map response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .header("Authorization",basicAuth)
                .accept("application/json")
                .body(userProfile)
                .when().post(endPoint)
                .then()
                .statusCode(responseCode)
                .extract()
                .as(Map.class);
        return response;
    }

    public static void register(Map userProfile,String clientId,int responseCode){
        RestAssured.basePath = "";
        String endPoint= "/sign-up?client_id="+clientId;
            given().log().ifValidationFails()
               .parameters(userProfile)
                .when().post(endPoint).then().log().ifValidationFails()
                .statusCode(responseCode);
    }

    public static Map getProfileType(String accessToken,int responseCode){
        RestAssured.basePath = "/api/v1";
        String endPoint= "/application";
        Map response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .header("Authorization",accessToken)
                .accept("application/json")
                .when().get(endPoint)
                .then()
                .statusCode(responseCode)
                .extract()
                .as(Map.class);
        return response;
    }

    public static Map getProfileFields(String profileType,String accessToken,int responseCode){
        RestAssured.basePath = "/api/v1";
        String endPoint= "/profileTypes/{profileType}";
        Map response = given().log().ifValidationFails()
                .pathParam("profileType", profileType)
                .header("Content-Type", "application/json")
                .header("Authorization",accessToken)
                .accept("application/json")
                .when().get(endPoint)
                .then()
                .statusCode(responseCode)
                .extract()
                .as(Map.class);
        return response;
    }



}