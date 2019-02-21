package com.project.interndcr.utils;

public class APIUtils {
    public static final String BASE_URL = "https://raw.githubusercontent.com/appinion-dev/";

    public static APIInterface getAPIResponse(){
        return RetrofitClient.getClient(BASE_URL).create(APIInterface.class);
    }
}
