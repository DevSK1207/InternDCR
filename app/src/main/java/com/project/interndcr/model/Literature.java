package com.project.interndcr.model;

import com.google.gson.annotations.SerializedName;

public class Literature {
    @SerializedName("id")
    private String id;
    @SerializedName("literature")
    private String literature;

    public String getLiterature() {
        return literature;
    }

    public String getId() {
        return id;
    }
}
