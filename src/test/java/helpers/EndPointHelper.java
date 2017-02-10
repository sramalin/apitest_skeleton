package helpers;

import com.jayway.restassured.RestAssured;
import config.ConfigFileObject;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by ksarath on 2/10/17.
 */
public class EndPointHelper{
    //200


    public static Map getToken(String username,String password,int responseCode){

        String endPoint = "/token";
        String params = "username="+username+"&password="+password+"&grant_type=password";
         Map response = given()
                 .header("Content-Type", "application/x-www-form-urlencoded")
                 .accept("application/json")
                 .body(params)
                 .when().post(endPoint).then()
                 .statusCode(responseCode)
                 .extract()
                 .as(Map.class);
         return response;
    }
    //201
    public static Map createUser(Map userProfile,String basicAuth,int responseCode){
        String endPoint = "/user";
        Map response = given()
                .header("Content-Type", "application/json")
                .header("Authorization",basicAuth)
                .accept("application/json")
                .body(userProfile)
                .when().post(endPoint).then()
                .statusCode(responseCode)
                .extract()
                .as(Map.class);
        return response;
    }
}