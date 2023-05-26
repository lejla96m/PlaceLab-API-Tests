package com.placelab.utilities;

import org.assertj.core.api.Assertions;

public class Validations {

    public void validateAuthorizedStatusCode(final int statusCode) {
        Assertions.assertThat(statusCode)
                .as("Invalid status code")
                .isEqualTo(201);
    }

    public void validateUnauthorizedStatusCode(final int statusCode) {
        Assertions.assertThat(statusCode)
                .as("Invalid status code")
                .isEqualTo(401);
    }

    public void validateHeader(final String header) {
        Assertions.assertThat(header)
                .as("Wrong header")
                .isEqualTo("application/json");
    }
}
