package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.R;

public class HomeDetailsActivity extends AppCompatActivity {

    EditText address,suberb,pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);
        address=findViewById(R.id.address);
        pincode=findViewById(R.id.pincode);
        suberb=findViewById(R.id.suburb);
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatefields()) {
                  //  StaticData.address = address.getText().toString();
                    StaticData.suberb = suberb.getText().toString();
                    StaticData.pincode = pincode.getText().toString();
                   /* Intent i = new Intent(getApplicationContext(), PickupActivity.class);
                    startActivity(i);*/
                }
            }
        });
    }

    boolean validatefields(){
        String address1=address.getText().toString();
        String suberb1=suberb.getText().toString();
        String pincode1=pincode.getText().toString();
        if(TextUtils.isEmpty(address1)) {
            address.setError("Please Enter Your Address");
            return false;
        } else if(TextUtils.isEmpty(suberb1)) {
            suberb.setError("Please Enter Suberb");
            return false;
        }
        else if(TextUtils.isEmpty(pincode1)) {
            pincode.setError("Enter Your Pincode");
            return false ;
        }
        /*else if(pincode1.length()>6 ||pincode1.length()<6) {
            pincode.setError("Enter Your Valid pin");
            return false;
        }*/
        return true;
    }
}
