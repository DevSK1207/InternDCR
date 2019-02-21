package com.project.interndcr.model;

import com.google.gson.annotations.SerializedName;

public class GiftList {
    @SerializedName("id")
    private String id;
    @SerializedName("gift")
    private String gift;

    public String getGift() {
        return gift;
    }

    public String getId() {
        return id;
    }
}
