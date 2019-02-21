package com.project.interndcr.model;

import com.google.gson.annotations.SerializedName;

public class ProductGroup {
    @SerializedName("id")
    private String id;
    @SerializedName("product_group")
    private String product_group;

    public String getProductGroup(){
        return product_group;
    }

    public String getId(){
        return id;
    }
}
