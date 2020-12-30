package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.Response.SignupResponse;
import com.manoranjan.apnadriver.service.CountryService;

public class ForgotPasswordActivity extends AppCompatActivity {
EditText emailid;
Button send;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        progressDialog=new ProgressDialog(ForgotPasswordActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        emailid=findViewById(R.id.edittextmobileno);
        send=findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailid.getText().toString().isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Enter the email address associated with your account", Toast.LENGTH_SHORT).show();
                }
                else{
                   // forgotpass();
                }
            }
        });
    }

    private void forgotpass() {
        progressDialog.show();
new CountryService().getAPI().forgotpass(ApiLinks.forgot_pass,emailid.getText().toString()).enqueue(new Callback<SignupResponse>() {
    @Override
    public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
        progressDialog.dismiss();
        if (response.body()!=null){
            if (response.body().getStatus().equals("1")){
                Toast.makeText(getApplicationContext(),"An email has been sent to the supplied email address to recover the login details",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ForgotPasswordActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(),response.body().getMessages(),Toast.LENGTH_SHORT).show();
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
