package com.project.interndcr.model;

import com.google.gson.annotations.SerializedName;

public class PhysicianSample {
    @SerializedName("id")
    private String id;
    @SerializedName("sample")
    private String sample;

    public String getSample() {
        return sample;
    }

    public String getId() {
        return id;
    }
}
