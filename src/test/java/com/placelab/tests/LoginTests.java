package com.placelab.tests;

import com.github.javafaker.Faker;
import com.placelab.endpoints.Routes;
import com.placelab.steps.HttpRequests;
import java.util.UUID;
import java.util.logging.Logger;
import com.placelab.utilities.Authentication;
import com.placelab.utilities.Validations;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {

    Authentication authentication = new Authentication();
    Validations validations = new Validations();
    Faker faker = new Faker();
    private static final Logger LOGGER = Logger.getLogger("PlaceLab Login Test");

    @Parameters({"email", "password"})
    @Test (priority = 1, testName = "Login Test", description = "Validate that the user is able to login with valid email and correct password", suiteName = "PlaceLab Login Test")
    public void validCredentialsLogin() {

        Response response = HttpRequests.sendPostRequest(Routes.auth_endpoint, authentication.requestBody);

        LOGGER.info("Submit authentication POST request");

        validations.validateAuthorizedStatusCode(response.statusCode());
        validations.validateHeader(response.header("Content-Type"));
    }

    @Parameters("password")
    @Test (priority = 2, testName = "Login Test", description = "Try to login with an invalid Email and valid password", suiteName = "PlaceLab Login Test")
    void invalidEmailLogin() {
        final String fakeEmail = faker.internet().emailAddress();
        authentication.setEmail(fakeEmail);
        authentication.createBody();

        Response response = HttpRequests.sendPostRequest(Routes.auth_endpoint, authentication.requestBody);

        LOGGER.info("Submit authentication POST request");

        validations.validateUnauthorizedStatusCode(response.statusCode());
        validations.validateHeader(response.header("Content-Type"));
    }

    @Parameters("email")
    @Test (priority = 3, testName = "Login Test", description = "Try to login with a valid email and an incorrect password", suiteName = "PlaceLab Login Test")
    void incorrectPasswordLogin() {
        final String randomPassword = UUID.randomUUID().toString();
        authentication.setPassword(randomPassword);
        authentication.createBody();

        Response response = HttpRequests.sendPostRequest(Routes.auth_endpoint, authentication.requestBody);

        LOGGER.info("Submit authentication POST request");

        validations.validateUnauthorizedStatusCode(response.statusCode());
        validations.validateHeader(response.header("Content-Type"));
    }

    @Test (priority = 4, testName = "Login Test", description = "Try to login without credentials", suiteName = "PlaceLab Login Test")
    void loginWithoutCredentials() {
        authentication.setEmail("");
        authentication.setPassword("");
        authentication.createBody();

        Response response = HttpRequests.sendPostRequest(Routes.auth_endpoint, authentication.requestBody);

        LOGGER.info("Submit authentication POST request");

        validations.validateUnauthorizedStatusCode(response.statusCode());
        validations.validateHeader(response.header("Content-Type"));
    }
}
