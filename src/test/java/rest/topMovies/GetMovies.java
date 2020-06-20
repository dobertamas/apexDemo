package rest.topMovies;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetMovies {

    final static String findAllOneHundred = "http://199.83.14.77:8080/api/v1/topMovies?count=100";

    final String findSingleId = "http://199.83.14.77:8080/api/v1/movie/";

    final static String findSingleIdPath = "http://199.83.14.77:8080/api/v1/movie/c7c45e5e-dfcd-4edf-8864-719b7a1ba3c7";

    final String singleIdExample = "c7c45e5e-dfcd-4edf-8864-719b7a1ba3c7";

    @Test
    public void GetAllOneHundred() {

        given().log().all().
            when().get(findAllOneHundred).
            then().log().all().statusCode(200).
            extract().response();
    }

    @Test
    public void getSingleItem() {

        Response response = given().log().all().
            when().get(findSingleIdPath).
            then().log().all().statusCode(200).
            extract().response();

        final String title = response.jsonPath().getString("title");
        System.out.println(title);
        System.out.println(getID(response));

        final Response singleItem = getSingleItem(singleIdExample);
        System.out.println(getID(singleItem));

    }

    @Test
    public void findDifferences() {

        // System.out.println(getOneHundredIds());

        final List<LinkedHashMap<Object, Object>> oneHundred = getOneHundredIds();
        System.out.println(oneHundred);
        for (LinkedHashMap<Object, Object> item : oneHundred) {
            System.out.println(item.size());
            Object id = item.get("id");
            Object title = item.get("title");
            Object releaseYear = item.get("releaseYear");
            Object rank = item.get("rank");

            // get the single item for the comparisons
            final Response singleItem = getSingleItem(id.toString());
            final String title1 = getTitle(singleItem);
            if (!title.toString().equals(title1) ) {
                System.out.println(
                    "title from 100: " + title.toString() + " title from single call: " + title1 + " for the id= " + id.toString());
            }
            // assertThat(title.toString(),equalTo(title1));



        }



    }

    private static List<LinkedHashMap<Object, Object>> getOneHundredIds() {

        return given().log().all().
            when().get(findAllOneHundred).
            then().log().all().statusCode(200).
            extract().response().jsonPath().get();


    }

    private Response getSingleItem(String id) {

        return given().log().all().
            when().get(findSingleId + id).
            then().log().all().statusCode(200).
            extract().response();

    }

    private static String getID(Response response) {

        return response.jsonPath().getString("id");

    }

    private static String getRank(Response response) {

        return response.jsonPath().getString("rank");
    }

    private static String getTitle(Response response) {

        return response.jsonPath().getString("title");
    }

    private static String getReleaseYear(Response response) {

        return response.jsonPath().getString("releaseYear");
    }

    private static String getRating(Response response) {

        return response.jsonPath().getString("rating");
    }

    private static String getDirector(Response response) {

        return response.jsonPath().getString("director");
    }
}
