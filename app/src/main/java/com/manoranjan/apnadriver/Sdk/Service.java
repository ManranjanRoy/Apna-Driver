package com.manoranjan.apnadriver.Sdk;



import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Service {

    @GET("{input}")
    Call<JsonObject> getdoctorlist1(@Path("input") String input,
                                    @Query("x-api-key") String x_api_key);

    @POST()
    @Multipart
    Call<OtherReponse> getaddrawquantity(@Url String url,
                                         @Part("access_token") RequestBody address,
                                         @Part("grade") RequestBody grade,
                                         @Part("quantities") RequestBody quantities,
                                         @Part MultipartBody.Part userfile[]);
    @POST()
    @Multipart
    Call<OtherReponse> getaddprocessingresponse(@Url String url,
                                                @Part("access_token") RequestBody access_token,
                                                @Part("raw_batch_no") RequestBody raw_batch_no,
                                                @Part("total_quantities") RequestBody total_quantities,
                                                @Part("net_weight") RequestBody net_weight,
                                                @Part("rejection") RequestBody rejection,
                                                @Part("shell") RequestBody shell,
                                                @Part MultipartBody.Part userfile[]);


    @POST()
    @Multipart
    Call<OtherReponse> getStock(@Url String url,
                                @Part("access_token") RequestBody access_token,
                                @Part("raw") RequestBody p_batch_no,
                                @Part("shell") RequestBody status,
                                @Part("peel") RequestBody boiling,
                                @Part("finished") RequestBody cutting,
                                @Part MultipartBody.Part userfile[]);


}
