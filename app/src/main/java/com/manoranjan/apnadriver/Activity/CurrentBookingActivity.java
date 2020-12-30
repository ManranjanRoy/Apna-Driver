package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.manoranjan.apnadriver.Adaptor.BookingListAdaptor;
import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.Configss;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.Response.BookingListResponse;
import com.manoranjan.apnadriver.Response.SignupResponse;
import com.manoranjan.apnadriver.model.BookingListModel;
import com.manoranjan.apnadriver.service.CountryService;

import java.util.List;

public class CurrentBookingActivity extends AppCompatActivity {
    RecyclerView chapterrecycler;

    BookingListAdaptor catagorylistAdaptor;
    ProgressDialog progressDialog;
    String tokencode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);
        progressDialog=new ProgressDialog(CurrentBookingActivity.this);
        progressDialog.setMessage("Please wait while Loading ...");
        progressDialog.setCancelable(false);
        chapterrecycler=findViewById(R.id.recyclerview);
        chapterrecycler.setHasFixedSize(true);
        chapterrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        tokencode = sharedPreferences.getString(Configss.tokencode, "Not Available");

        getbooking();

    }

    void getbooking(){
        progressDialog.show();
        new CountryService().getAPI().getbookinglist(ApiLinks.booking_list,tokencode).enqueue(new Callback<BookingListResponse>() {
            @Override
            public void onResponse(Call<BookingListResponse> call, Response<BookingListResponse> response) {
                progressDialog.dismiss();
                if (response.body()!=null){
                    if (response.body().getStatus().equals("1")){
                        List<BookingListModel> bookingListModels=response.body().getData();
                        catagorylistAdaptor = new BookingListAdaptor(getApplicationContext(), bookingListModels);
                        chapterrecycler.setAdapter(catagorylistAdaptor);
                        catagorylistAdaptor.setonItemClickListner(new BookingListAdaptor.OnitemClickListner() {
                            @Override
                            public void OnitemClick(int position) {

                            }

                            @Override
                            public void OnCancelClick(int position) {
                                 cancelbooking(bookingListModels.get(position).getBookingId());
                            }
                        });
                    }
                    else{
                        Toast.makeText(CurrentBookingActivity.this, "faild", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<BookingListResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void cancelbooking(String bookingid) {
        progressDialog.show();
        new CountryService().getAPI().cancel_booking(ApiLinks.cancel_booking,tokencode,bookingid).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                progressDialog.dismiss();
                if (response.body()!=null){
                    if (response.body().getStatus().equals("true")){
                         Toast.makeText(getApplicationContext(),"Order has been cancelled",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getApplicationContext(),CurrentBookingActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                 progressDialog.dismiss();
            }
        });
    }
}
