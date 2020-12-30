package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.Configss;
import com.manoranjan.apnadriver.MainActivity;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.Response.ConatctResponse;
import com.manoranjan.apnadriver.service.CountryService;

public class ContactUsActivity extends AppCompatActivity {
EditText comment;
    ProgressDialog progressDialog;
    String tokencode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        progressDialog=new ProgressDialog(ContactUsActivity.this);
        progressDialog.setMessage("Please Wait...");
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        tokencode = sharedPreferences.getString(Configss.tokencode,"Not Available");


        comment=findViewById(R.id.comment);
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatefiled()){
                    submitdatatoserver();
                }

            }
        });

    }

    private void submitdatatoserver() {
        progressDialog.show();
new CountryService().getAPI().contact_us(ApiLinks.contact_us,tokencode,
        comment.getText().toString()).enqueue(new Callback<ConatctResponse>() {
    @Override
    public void onResponse(Call<ConatctResponse> call, Response<ConatctResponse> response) {
        progressDialog.dismiss();
        if (response.isSuccessful() && response.body()!=null){
            if (response.body().getStatus().equals("true")){
                Toast.makeText(ContactUsActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }else{
                Toast.makeText(ContactUsActivity.this, response.body().getMessages(), Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void onFailure(Call<ConatctResponse> call, Throwable t) {

    }
});
    }

    public boolean validatefiled() {
        ////fname,lname,email,monileno,address1,address2,town,pinciode,password,cpassword
        final String name1 = comment.getText().toString().trim();


        if(TextUtils.isEmpty(name1)) {
            comment.setError("Please Enter Comment");
            return false;
        }

        return true;
    }
}
