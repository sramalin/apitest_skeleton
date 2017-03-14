package helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ksarath on 2/10/17.
 */
public class DataHelper {

    public static Map getDefaultUserProfileData(){
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
//        String newUserName = "sramalin" + UUID.randomUUID().toString()+"@mailinator.com";
        String newUserName = "sch_register+" + UUID.randomUUID().toString().substring(0,7)+"@mailinator.com";
        userProfile.put("email",newUserName);
        userProfile.put("password","Password@123");
        userProfile.put("firstName","firstname");
        userProfile.put("lastName","lastname");
        userProfile.put("companyName","company123");
        userProfile.put("country","FR");
        // channel and subChannel are removed from 1.43
        // userProfile.put("channel","OM");
        // userProfile.put("subChannel","SI3");
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

        return userProfile;
    }

    public static Map getUserProfileForMEA(){
        Map<String, Object> userProfile = new HashMap<String, Object>();
        Map<String, Object> companyDetails = new HashMap<String, Object>();
        //TODO Create MEA specific user profile obj.

        return userProfile;
    }

    public static Map getUserProfileForECOREALAD(){
        Map<String, Object> userProfile = new HashMap<String, Object>();
        Map<String, Object> companyDetails = new HashMap<String, Object>();
        //TODO Create ECODEREAL specific user profile obj.

        return userProfile;
    }

    public static Map getDefaultRegistrationData(){
        Map<String, Object> userProfile = new HashMap<String, Object>();
        String email = "sch_register" + UUID.randomUUID().toString().substring(0,7)+"@mailinator.com";
        System.out.println("Email : "+email);
        userProfile.put("email",email);
        userProfile.put("updateFields","false");
        userProfile.put("givenName","API Automation");
        userProfile.put("sn","feb24");
        userProfile.put("password","Password@123");
        userProfile.put("company","company123");
        userProfile.put("country","FR");
        userProfile.put("language","en");
        userProfile.put("termsandcon","on");
        userProfile.put("editprofilebutton","Submit");
        userProfile.put("submitted","true");
        userProfile.put("organizationName","org");
        //Accouting --Z016
        userProfile.put("jobFunction","Z016");
        return userProfile;

    }
}
