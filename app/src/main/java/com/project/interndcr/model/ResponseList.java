package com.project.interndcr.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseList {
    @SerializedName("product_group_list")
    public List<ProductGroup> productGroups;

    @SerializedName("literature_list")
    public List<Literature> literatureList;

    @SerializedName("physician_sample_list")
    public List<PhysicianSample> physicianSampleList;

    @SerializedName("gift_list")
    public List<GiftList> giftLists;
}
