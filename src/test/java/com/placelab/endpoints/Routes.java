package com.placelab.endpoints;

import com.placelab.utilities.GlobalValues;

public class Routes {

    public static final String base_URL = "https://demo.placelab.com/api/v2";
    public static final String auth_endpoint = "/sessions";
    public static final String login_endpoint = "email=" + GlobalValues.EMAIL + "&password=" + GlobalValues.PASSWORD;
}
