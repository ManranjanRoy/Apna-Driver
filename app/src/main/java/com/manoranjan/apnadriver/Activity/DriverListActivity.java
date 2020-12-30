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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.manoranjan.apnadriver.Adaptor.ServiceListAdaptor;
import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.Configss;
import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.Response.DriverListResponse;
import com.manoranjan.apnadriver.model.DriverListModel;
import com.manoranjan.apnadriver.service.CountryService;

import java.util.List;

public class DriverListActivity extends AppCompatActivity {
    RecyclerView chapterrecycler;

    ServiceListAdaptor catagorylistAdaptor;
    ProgressDialog progressDialog;
    String tokencode;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);
        progressDialog=new ProgressDialog(DriverListActivity.this);
        progressDialog.setMessage("Please wait while Loading ...");
        progressDialog.setCancelable(false);
        chapterrecycler=findViewById(R.id.recyclerview);
        next=findViewById(R.id.next);
        chapterrecycler.setHasFixedSize(true);
        chapterrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        tokencode = sharedPreferences.getString(Configss.tokencode, "Not Available");

       getservice();

       StaticData.drivername="";
       StaticData.bdriverid="";

        next=findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BookingSummaryActivity.class);
                startActivity(i);
            }
        });

    }
    void getservice(){
        progressDialog.show();
        new CountryService().getAPI().getservicelist(ApiLinks.driver_list,StaticData.bcartype,StaticData.barea).enqueue(new Callback<DriverListResponse>() {
            @Override
            public void onResponse(Call<DriverListResponse> call, Response<DriverListResponse> response) {
                if (response.body()!=null){
                    progressDialog.dismiss();
                    if (response.body().getStatus().equals("true")){
                       List<DriverListModel> servicelists=response.body().getList();
                        catagorylistAdaptor = new ServiceListAdaptor(getApplicationContext(), servicelists,next);
                        chapterrecycler.setAdapter(catagorylistAdaptor);
                    }
                    else{
                        Toast.makeText(DriverListActivity.this, "faild", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DriverListResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}
