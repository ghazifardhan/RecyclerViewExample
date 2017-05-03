package com.app.ghazi.recyclerviewexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Riesto on 03/05/2017.
 */

public class CallbackAuction implements Serializable {

    public String status = "";
    public int count = -1;
    public int count_total = -1;
    public int pages = -1;

    @SerializedName("data")
    @Expose
    public List<Datum> products = new ArrayList<>();
    @SerializedName("meta")
    @Expose
    public Meta meta = null;
}
