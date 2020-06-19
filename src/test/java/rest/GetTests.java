package rest;


import io.restassured.response.Response;
import org.junit.Test;
import rest.helpers.ExpectedErrors;

import static rest.helpers.GetHelpers.*;

public class GetTests {

    final String targetUrl = "http://localhost:8080/api/v1/person";

    final String invalidTargetUrlVersion = "http://localhost:8080/api/v2/person";

    final String invalidTargetUrlPath = "http://localhost:8080/api/v2/person2";


    @Test
    public void testGetAllResponse200() {

        Response response = sendGetAllReturnResponse(targetUrl, 200);
        testResponseHeadersGet(response);
    }


    @Test
    public void testGetAllInvalidUrlVersionExpected404() {

        Response response = sendGetAllReturnResponse(invalidTargetUrlVersion, 404);
        testResponseHeadersGet(response);
        verifyErrorJsonResponse(response, ExpectedErrors.NOT_FOUND);
    }

    @Test
    public void testGetAllInvalidUrlPathExpected404() {

        Response response = sendGetAllReturnResponse(invalidTargetUrlPath, 404);
        testResponseHeadersGet(response);
        verifyErrorJsonResponse(response, ExpectedErrors.NOT_FOUND);
    }

    @Test
    public void testGetAllFindFirst200() {

        Response response = sendGetAllReturnResponse(targetUrl, 200);
        testResponseHeadersGet(response);
        final String firstIdFromResponse = getFirstIdFromGetAllResponse(response);
        System.out.println(firstIdFromResponse);
        // Only after deleteAll
        //verifyEmptyResponse(response);
    }
}
