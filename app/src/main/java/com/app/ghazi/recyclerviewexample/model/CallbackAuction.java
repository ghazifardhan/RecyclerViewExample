package com.app.ghazi.recyclerviewexample.model;

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

    public List<Datum> products = new ArrayList<>();
    public Meta meta = null;
}
