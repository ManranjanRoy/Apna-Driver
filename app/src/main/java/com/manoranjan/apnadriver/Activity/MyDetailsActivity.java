package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.Response.SuburbPostcodeResponse;
import com.manoranjan.apnadriver.model.PickupSpinnerModel;
import com.manoranjan.apnadriver.model.SuburbPostCodeModel;
import com.manoranjan.apnadriver.service.CountryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class MyDetailsActivity extends AppCompatActivity {

    EditText fname,addresset,email,mobileno;
    Button next,save;

    ProgressDialog progressDialog;
    String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_details);
        progressDialog=new ProgressDialog(MyDetailsActivity.this);
        progressDialog.setMessage("Please wait while Loading...");
        next=findViewById(R.id.next);
        fname=findViewById(R.id.fname);
        email=findViewById(R.id.emailid);
        mobileno=findViewById(R.id.phoneno);
        addresset=findViewById(R.id.address);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatefiled()) {

                    StaticData.bname=fname.getText().toString();
                    StaticData.bemail=email.getText().toString();
                    StaticData.bnumber=mobileno.getText().toString();
                    StaticData.baddress=addresset.getText().toString();
                    Intent i = new Intent(getApplicationContext(), BookingDetailsActivity.class);
                    startActivity(i);
                }
            }
        });


    }
    public boolean validatefiled() {

        final String fname1 = fname.getText().toString().trim();
        final  String email1=email.getText().toString().trim();
        final String mob = mobileno.getText().toString().trim();
        final String address1 = addresset.getText().toString().trim();


        if(TextUtils.isEmpty(fname1)) {
            fname.setError("Please Enter Your Name");
            return false;
        } else if(TextUtils.isEmpty(email1)) {
            email.setError("Please Enter your Email");
            return false;
        }else if (!TextUtils.isEmpty(email1) && !Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            email.setError("Enter Valid Email");
            return false;
        }
        else if(TextUtils.isEmpty(mob)) {
            mobileno.setError("Enter Your Mobile No");
            return false ;
        }
        else if(mob.length()>10 ||mob.length()<10) {
            mobileno.setError("Enter Your Mobile No");
            return false;
        }
        else if(TextUtils.isEmpty(address1)) {
            addresset.setError("Enter Your Address");
            return false ;
        }
        return true;
    }
  




}

