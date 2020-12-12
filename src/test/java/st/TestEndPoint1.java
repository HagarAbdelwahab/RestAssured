package st;

import io.restassured.response.Response;
import io.vertx.core.cli.annotations.Description;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

 class TestEndPoint1 {
    public static String endPoint1 = "https://reqres.in/api/users";
    public static int expectedStatusCode= 200;
    public static String expectedContentType= "application/json; charset=utf-8";
    public static String expectedConnection = "keep-alive";

    @Order(1)
    @Description("this is to validate the response components; headers, status code, etc..  ")
    @Test
         void getAllUsersInfo() {
        Response response = given().spec(devcom.vodafone.cyta.BaseTest.setRequestSpec(endPoint1,"page","2")).//queryParam("page", "2").
                when().get("").
                then().extract().response();
                //log().all().//assertThat().body(("data[0].email"),equalTo("michael.lawson@reqres.in"));
        devcom.vodafone.cyta.BaseTest.validateStatusCode(response,expectedStatusCode);
       // BaseTest.validateResponseBody(response, EndPoint1.getBody());
        devcom.vodafone.cyta.BaseTest.ValidateHeaderValue(response,"connection",expectedConnection);
        devcom.vodafone.cyta.BaseTest.ValidateHeaderValue(response,"Content-Type",expectedContentType);
    }
    
    @Description("this is to validate the number of the users in the second page")
    @Tag("Number_of_users_validation")
    @Test
      void validateNumberOfUsers(){
      /* String ActualBody = */given().spec(devcom.vodafone.cyta.BaseTest.setRequestSpec(endPoint1,"page","2")).//queryParam("page", "2").
                when().get("").
                then().log().all().
               assertThat().body(("data.size"),equalTo(6))
               /* .extract().body().asString()*/;
     //   Assertions.assertEquals(EndPoint1.getBody(),ActualBody);
    }
}
