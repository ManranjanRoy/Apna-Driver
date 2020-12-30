package com.manoranjan.apnadriver.service;


import com.manoranjan.apnadriver.Response.AddressResponse;
import com.manoranjan.apnadriver.Response.BookingCountResponse;
import com.manoranjan.apnadriver.Response.BookingListResponse;
import com.manoranjan.apnadriver.Response.Bookingresponse;
import com.manoranjan.apnadriver.Response.CarTypeResponse;
import com.manoranjan.apnadriver.Response.ConatctResponse;
import com.manoranjan.apnadriver.Response.HourResponse;
import com.manoranjan.apnadriver.Response.PickupSpinnerResponse;
import com.manoranjan.apnadriver.Response.DriverListResponse;
import com.manoranjan.apnadriver.Response.PricingResponse;
import com.manoranjan.apnadriver.Response.SuburbPostcodeResponse;
import com.manoranjan.apnadriver.Response.UpdateAddressresponse;
import com.manoranjan.apnadriver.Response.LoginResponse;
import com.manoranjan.apnadriver.Response.ProfileResponse;
import com.manoranjan.apnadriver.Response.SendFinalResponse;
import com.manoranjan.apnadriver.Response.SignupResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * This class represents the Countries API, all endpoints can stay here.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 09/03/18.
 * Jesus loves you.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST
    Call<SignupResponse> createUser(
            @Url String url,
            @Field("name") String firstname,
            //@Field("lname") String lastname,
            @Field("mobile") String mobile_no,
            @Field("email") String email_id,
            //@Field("address") String address,
            @Field("password") String password,
            @Field("cpassword") String cpassword,
            @Field("login_type") String login_type);

    @FormUrlEncoded
    @POST
    Call<SignupResponse> forgotpass(
            @Url String url,
            @Field("email_id") String email_id);
    @FormUrlEncoded
    @POST
    Call<SignupResponse> check_suburb(
            @Url String url,
            @Field("name") String name);


    @FormUrlEncoded
    @POST
    Call<LoginResponse> login(
            @Url String url,
            @Field("email_id") String mobile,
            @Field("password") String password);


    @FormUrlEncoded
    @POST
    Call<ProfileResponse> updateprofile(
            @Url String url,
            @Field("access_token") String access_token,
            @Field("name") String name,
            @Field("email_id") String email,
            @Field("mobile_no") String mobile
            );

    @FormUrlEncoded
    @POST
    Call<ProfileResponse> profile(
            @Url String url,
            @Field("access_token") String access_token);
    @FormUrlEncoded
    @POST
    Call<BookingListResponse> getbookinglist(
            @Url String url,
            @Field("access_token") String access_token);
    @FormUrlEncoded
    @POST
    Call<BookingListResponse> getcurrentbooking(
            @Url String url,
            @Field("access_token") String access_token);
    @FormUrlEncoded
    @POST
    Call<BookingCountResponse> getbooking_count(
            @Url String url,
            @Field("access_token") String access_token);
    @FormUrlEncoded
    @POST
    Call<SignupResponse> cancel_booking(
            @Url String url,
            @Field("access_token") String access_token,
            @Field("booking_id") String booking_id);

    @FormUrlEncoded
   @POST
    Call<DriverListResponse> getservicelist(
            @Url String url,
              @Field("cartype_id") String cartype_id,
            @Field("area_id") String area_id);

    @GET
    Call<HourResponse> gethours(
            @Url String url);

    @FormUrlEncoded
    @POST
    Call<PricingResponse> getprice(
            @Url String url,
            @Field("cartype_id") String cartype_id,
            @Field("hour") String hour);


    @GET
    Call<SuburbPostcodeResponse> getsuburbpostcode(
            @Url String url);
    @GET
    Call<CarTypeResponse> getcartype(
            @Url String url);

    @FormUrlEncoded
    @POST
    Call<Bookingresponse> bookservice(
            @Url String url,
            @Field("access_token") String access_token,
            @Field("area_id") String area_id,
            @Field("cartype_id") String cartype_id,
            @Field("driver_id") String driver_id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("address") String address,
           // @Field("location") String location,
            @Field("reporting_address") String reporting_address,
            @Field("nearest_landmark") String nearest_landmark,
            @Field("mobile_optional") String mobile_optional,
            @Field("booking_hour") String booking_hour,
            @Field("desination_address") String desination_address,
            @Field("booking_timing") String booking_timing,
            @Field("price") String price,
            @Field("company_price") String company_price
    );

    @POST @Multipart
    Call<SendFinalResponse> send_final_data(@Url String url,
                                            @Part("access_token") RequestBody access_token,
                                            @Part("customersign_cardid") RequestBody customersign_cardid,
                                            @Part("gold_silver") RequestBody gold_silver,
                                            @Part("estimate_ammount") RequestBody estimate_ammount,
                                            @Part("payable_amount") RequestBody payable_amount,
                                            @Part("point_amount") RequestBody point_amount,
                                            @Part("bank_username") RequestBody bank_username,
                                            @Part("account_number") RequestBody account_number,
                                            @Part("ifsc_code") RequestBody ifsc_code,
                                            @Part("branch_name") RequestBody branch_name,
                                            @Part MultipartBody.Part bill_image);



    @FormUrlEncoded
    @POST
    Call<ConatctResponse> contact_us(
            @Url String url,
            @Field("access_token") String access_token,
            @Field("comment") String comment);
    @FormUrlEncoded
    @POST
    Call<UpdateAddressresponse> updateaddress(
            @Url String url,
            @Field("access_token") String access_token,
            @Field("house_type") String house_type,
            @Field("street_no") String street_no,
            @Field("street_name") String street_name,
            @Field("post_code") String post_code,
            @Field("suburb") String suburb
    );
    @FormUrlEncoded
    @POST
    Call<AddressResponse> getaddress(
            @Url String url,
            @Field("access_token") String access_token);
}
