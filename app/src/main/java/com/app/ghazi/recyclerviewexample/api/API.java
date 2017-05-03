package com.app.ghazi.recyclerviewexample.api;

import com.app.ghazi.recyclerviewexample.model.CallbackAuction;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Riesto on 03/05/2017.
 */

public interface API {

    @GET("auction")
    Call<CallbackAuction> getAuctionList(@Query("page") Integer page_no);
    //Call<Auction> getAuctionList(@Query("page") Integer page_no);
}
