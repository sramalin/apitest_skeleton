package helpers;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.RestAssuredResponseImpl;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import config.ConfigFileObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by ksarath on 2/10/17.
 */
public class EndPointHelper{
    //200

    public static String getQTToken(String username, String password, int responseCode) {
        RestAssured.baseURI = "https://platform.staging.quintype.com";

        String endPoint = "/login";
        String params = "username="+username+"&password="+password;


        Response response = (Response) given().log().ifValidationFails()
                .header("content-type", "application/x-www-form-urlencoded")
                .body(params)
                .when()
                .redirects().follow(true).redirects().max(100)
                .post(endPoint)
                .then()
                .statusCode(responseCode)
                .contentType("application/octet-stream")
                .extract().response();

        Object location = ((RestAssuredResponseImpl) response).getGroovyResponse().getResponseHeaders();
        System.out.println(((Headers) location).getValue("Location"));
        String redirect_uri1 = ((Headers) location).getValue("Location");
        String[] baseurl = redirect_uri1.split("\\?");
        String[] queryparams = baseurl[1].split("&");

        RestAssured.baseURI = baseurl[0];
        Response RedirectedResponse1 = (Response) given().log().ifValidationFails()
                .header("content-type", "application/x-www-form-urlencoded")
                .queryParam(queryparams[0])
                .queryParam(queryparams[1])
                .queryParam(queryparams[2])
                .queryParam(queryparams[3])
                .when()
                .redirects().follow(false)
                .get()
                .then()
                .statusCode(responseCode)
                .cookie("session-cookie")
                .extract().response();

        System.out.println("Session cookie from response "+ RedirectedResponse1.cookies().get("session-cookie").toString());
        String decodedCookie = null;
        try {
            decodedCookie = URLDecoder.decode(RedirectedResponse1.cookies().get("session-cookie"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodedCookie ;
    }

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