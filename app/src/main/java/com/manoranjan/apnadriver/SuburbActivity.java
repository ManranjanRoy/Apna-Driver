package com.manoranjan.apnadriver;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.manoranjan.apnadriver.Activity.Signup;
import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.Response.SignupResponse;
import com.manoranjan.apnadriver.service.CountryService;

public class SuburbActivity extends AppCompatActivity {
EditText suburb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suburb);

        suburb=findViewById(R.id.suburb);
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (suburb.getText().toString().isEmpty()){
                    Toast.makeText(SuburbActivity.this, "Enter Suburb or Postcode ", Toast.LENGTH_SHORT).show();
                }else {
                    checksuburb();
                    //startActivity(new Intent(getApplicationContext(), Signup.class));
                }
            }
        });
    }

    private void checksuburb() {
        ProgressDialog progressDialog=new ProgressDialog(SuburbActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        new CountryService().getAPI().check_suburb(ApiLinks.check_suburb,suburb.getText().toString()).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                progressDialog.dismiss();
                if (response.body()!=null){
                    if (response.body().getStatus().equals("1")){
                        StaticData.suberb=suburb.getText().toString();
                      //  Toast.makeText(getApplicationContext(),"Sucess",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Signup.class));
                    }else  if (response.body().getStatus().equals("0")){
                        Toast.makeText(getApplicationContext(),
                                "Sorry,we are not in your area yet",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {

            }
        });
    }
}
