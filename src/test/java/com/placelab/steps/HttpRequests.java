package com.placelab.steps;

import com.placelab.endpoints.Routes;
import com.placelab.utilities.Authentication;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class HttpRequests {

    public static <T> Response sendPostRequest(final String endpoint, final T requestBody) {
        Authentication authentication = new Authentication();

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authentication.getToken())
                .log().all()
                .body(requestBody.toString())
                .baseUri(Routes.base_URL)
                .post(endpoint);
    }
}
