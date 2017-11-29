package com.example.android.weatherp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Jindal on 11/24/2017.
 */

public class Model implements Serializable {
    @SerializedName("currently")
private Currently currently;

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }
    //    @SerializedName("name")
//    private String engName;
//    @SerializedName("hindi_name")
//    private String hindiName;
//    @SerializedName("product_id")
//    private String productId;
//
//    public String getEngName() {
//        return engName;
//    }
//
//    public void setEngName(String engName) {
//        this.engName = engName;
//    }
//
//    public String getHindiName() {
//        return hindiName;
//    }
//
//    public void setHindiName(String hindiName) {
//        this.hindiName = hindiName;
//    }
//
//    public String getProductId() {
//        return productId;
//    }
//
//    public void setProductId(String productId) {
//        this.productId = productId;
//    }
}
