package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.MainActivity;
import com.manoranjan.apnadriver.PresenterImp.SignupprenterImp;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.presenter.SignupPresenter;
import com.manoranjan.apnadriver.view.SignupView;

public class Signup extends AppCompatActivity implements SignupView {
    EditText fname,lname,email,mobileno,password,cpassword;
    //EditText address1;
    Button buttonsignup;
    SignupPresenter signupPresenter;
    ProgressDialog progressDialog;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        progressDialog=new ProgressDialog(Signup.this);
        progressDialog.setMessage("Please Wait...");
        fname=findViewById(R.id.fname);
        lname=findViewById(R.id.lname);
        email=findViewById(R.id.emailid);
        mobileno=findViewById(R.id.phoneno);
       // address1=findViewById(R.id.addressline1);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.password1);
        buttonsignup=findViewById(R.id.buttonsignup);
        checkBox=findViewById(R.id.checkbox);

        signupPresenter=new SignupprenterImp(this,getApplicationContext());

        //fname,lname,email,monileno,address1,address2,town,pinciode,password,cpassword
        findViewById(R.id.termsandcondition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(Signup.this, TermsconditionActivity.class));
            }
        });
        findViewById(R.id.privacypolicy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(Signup.this, PrivacyPolicyActivity.class));
            }
        });
        findViewById(R.id.buttonsignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupPresenter.signup(fname.getText().toString(),
                        mobileno.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        cpassword.getText().toString(), "1");
                //startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });//linkSignupadmin
        findViewById(R.id.signup_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });//
    }

    @Override
    public void onSucess() {
        //Toast.makeText(getApplicationContext(),"Registered Sucessfully",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Signup.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
        /*Toast.makeText(getApplicationContext(),"Sucessfully Registered",Toast.LENGTH_LONG).show();
        Intent i=new Intent(getApplicationContext(),Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);*/
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean validatefiled() {
        ////fname,lname,email,monileno,address1,address2,town,pinciode,password,cpassword
        final String fname1 = fname.getText().toString().trim();
      //  final String lname1 = lname.getText().toString().trim();
        final  String email1=email.getText().toString().trim();
        final String mob = mobileno.getText().toString().trim();
        //final String address11 = address1.getText().toString().trim();
        final String password1 = password.getText().toString().trim();
        final String cpass1 = cpassword.getText().toString().trim();

        if(TextUtils.isEmpty(fname1)) {
            fname.setError("Please Enter Your Name");
            return false;
     /*   } else   if(TextUtils.isEmpty(lname1)) {
            lname.setError("Please Enter Your Last Name");
            return false;*/
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
      /*  else if(TextUtils.isEmpty(address11)) {
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            address1.setError("Enter Your Address ");
            return false;
        }
        else if(TextUtils.isEmpty(town1)) {
            town.setError("Enter Your Town");
            return false ;
        }
        else if(TextUtils.isEmpty(pincode1)) {
            pincode.setError("Enter Your 6 digit pincode");
            return false ;
        }


        else if(TextUtils.isEmpty(address11)) {
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            address1.setError("Enter Your Address ");
            return false;
        }
        else if(TextUtils.isEmpty(address22)) {
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            address2.setError("Enter Your Address ");
            return false;
        }*/
        else if(TextUtils.isEmpty(password1)) {
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            password.setError("Enter Your Password ");
            return false;

        }
        else if(TextUtils.isEmpty(cpass1)) {
            //Toast.makeText(getApplicationContext(),"Enter Your Confirm Password",Toast.LENGTH_SHORT).show();
            cpassword.setError(" Enter Your Confirm Password ");
            return false;
        }
        else if(!password1.equals(cpass1)) {
            //Toast.makeText(getApplicationContext(),"Enter Your Same  Password",Toast.LENGTH_SHORT).show();
            cpassword.setError(" Enter Same Password  ");
            return false;
        }
        else if (!checkBox.isChecked()){
            Toast.makeText(getApplicationContext(),"Aggree to terms and privacy policy to continue",Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }

    @Override
    public void Showprogess() {
         progressDialog.show();
    }

    @Override
    public void dismissproggress() {
         progressDialog.dismiss();

    }
}
