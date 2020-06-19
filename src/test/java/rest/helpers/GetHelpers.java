package rest.helpers;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetHelpers {

    // Verifies response code and sends back the REST-assured Response object
    public static Response sendGetAllReturnResponse(String targetUrl, Integer expectedStatusCode) {

        return given().log().all().
            when().get(targetUrl).
            then().log().all().statusCode(expectedStatusCode).
            extract().response();
    }

    // To verify headers in the response
    public static void testResponseHeadersGet(Response response) {

        assertThat(response.getHeader("Content-Type"), equalTo("application/json"));
        // Test other headers like Transfer-Encoding, Keep-Alive, Connection
        // ...
    }

    // Verifies the content of the response JSON in case of a unsuccessful request
    public static void verifyErrorJsonResponse(Response response, ExpectedErrors expectedErrors) {

        assertEquals(expectedErrors.getStatus(), (response.body().path("status")));
        assertEquals(expectedErrors.getError(), (response.body().path("error")));
        assertEquals(expectedErrors.getMessage(), (response.body().path("messsage")));
    }

    public static String getFirstIdFromGetAllResponse(Response response) {

        return response.jsonPath().getString("id[0]");
    }


}
