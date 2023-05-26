package com.placelab.utilities;

import com.placelab.endpoints.Routes;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;

public class Authentication {

    private static String email;
    private static String password;
    public JSONObject requestBody;

    public Authentication() {
        email = GlobalValues.EMAIL;
        password = GlobalValues.PASSWORD;
        createBody();
    }

    public void createBody() {
        JSONObject data = new JSONObject();

        data.put("email", email);
        data.put("password", password);

        requestBody = data;
    }

    public String getToken() {
        JSONObject credentials = new JSONObject();

        credentials.put("email", email);
        credentials.put("password", password);

        return given()
                .baseUri(Routes.base_URL)
                .contentType(ContentType.JSON)
                .body(credentials.toString())

                .when()
                .post(Routes.auth_endpoint)
                .jsonPath().getString("api_token");
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        Authentication.email = email;
    }

    public void setPassword(String password) {
        Authentication.password = password;
    }
}
