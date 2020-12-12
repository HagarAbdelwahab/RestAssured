
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class BaseTest {

    private static RequestSpecification requestSpec;
    private static Cookie tokenCookie;

    /*
    * this is to set the req spec for each end point
    * param: query param name and value
     */
    public static RequestSpecification setRequestSpec(String URI,String paramName, String paramValue) {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(URI).addQueryParam(paramName,paramValue).build();
        return requestSpec;
    }

    /*
    this is to test the whole body of the response
    *param: response and Expected body
     */
    public static void validateResponseBody(Response response,String expectedBody ){
        String actualBody = response.getBody().asString();
        Assert.assertEquals(actualBody,expectedBody);
        System.out.println(actualBody);
        System.out.println(expectedBody);
        //read expected from external file
        //if/else in case of no provided validation for some part
    }

    /*
    this is to test the whole status code of the response
    *param: response and Expected status code
     */
    public static void validateStatusCode(Response response,int expectedStatusCode){
        //read expected from external file
        //if/else in case of no provided validation for some part
        int actualStatucCode = response.getStatusCode();
        Assert.assertEquals(actualStatucCode,expectedStatusCode);
    }

     /*
    this is to get the cookie for the first request
    *param: cookie name
     */
    public static Cookie getTokenCookie(String cookieName) {
        //  CookieFilter cookieFilter = new CookieFilter();
        String token = given().spec(requestSpec)
                // .contentType(ContentType.JSON)
                .log()
                .all()
                .when()
                .get("")
                .then().extract().cookie(cookieName);
         return tokenCookie = new Cookie.Builder(cookieName, token).build();
    }

    /*
    * this is to build the cookie into the reqSpec
     */
    public static void setTokenCookie(Cookie tokenCookie){
        requestSpec.cookie(tokenCookie);
    }

    /*
    * this is to validate the header value
    * param: Response, header name, header value
     */
    public static void ValidateHeaderValue(Response response,String headerName,String expectedHeaderValue){
       String actualHeaderValue = response.getHeader(headerName);
       Assert.assertEquals(actualHeaderValue,expectedHeaderValue);
    }

    public static void validateResponseTime(Response response){

    }


}
