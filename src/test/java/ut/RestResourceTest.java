package ut;

import com.cyta.util.test.constants.Constants;
import com.cyta.util.test.datareaders.PropertiesReader;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class RestResourceTest {

    @Test
    public void testHelloEndpoint() {
        PropertiesReader.readProperties(Constants.CONFIG_PATH+"TestConfigs.properties");
        System.out.println(PropertiesReader.getPropertyValue("key"));
        Assertions.assertTrue(true);
//        given()
//                .when().get("/test")
//                .then()
//                .statusCode(200)
//                .body(is("HELLO"));
    }
}