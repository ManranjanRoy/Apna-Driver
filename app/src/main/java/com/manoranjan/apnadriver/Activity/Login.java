package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.manoranjan.apnadriver.MainActivity;
import com.manoranjan.apnadriver.PresenterImp.LoginPresenterImp;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.SuburbActivity;
import com.manoranjan.apnadriver.presenter.LoginPresenter;
import com.manoranjan.apnadriver.view.LoginView;

public class Login extends AppCompatActivity
    implements LoginView {
        TextView signup;
        private EditText editTextEmail;
        private EditText editTextPassword;
        TextView linkforgotpass;
        private Button buttonLogin;
        LoginPresenter loginPresenter;
        ProgressDialog progressDialog;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            signup = findViewById(R.id.linkSignupadmin);
            loginPresenter=new LoginPresenterImp(this);
            loginPresenter.getcontext(getApplicationContext());
            progressDialog=new ProgressDialog(Login.this);
            progressDialog.setMessage("Please Wait login");

            editTextEmail = (EditText) findViewById(R.id.edittextmobileno);
            editTextPassword = findViewById(R.id.editTextPassword);
            //linkforgotpass = (TextView) findViewById(R.id.linkforgotpass);
            buttonLogin = (Button) findViewById(R.id.buttonLogin);
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(Login.this, Signup.class));
                }
            });
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginPresenter.loginfunction(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                   // startActivity(new Intent(Login.this, MainActivity.class));
                }
            });
            findViewById(R.id.termsandcondition).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(Login.this, TermsconditionActivity.class));
                }
            });
            findViewById(R.id.privacypolicy).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(Login.this, PrivacyPolicyActivity.class));
                }
            });
            TextView forgottext=findViewById(R.id.forgot_txt);
            String html = "<u>Forgot password?</u>";
            forgottext.setText(Html.fromHtml(html));
            findViewById(R.id.forgot_txt).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //loginPresenter.loginfunction(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                    startActivity(new Intent(Login.this, ForgotPasswordActivity.class));
                }
            });
        }
        @Override
        public void onSucess() {

            //Toast.makeText(getApplicationContext(),"Sucessfully Login",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Login.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        @Override
        public void onError(String msg) {
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
       /* Intent intent = new Intent(Login.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();*/
            //Toast.makeText(getApplicationContext(),"Login faild wrong email password",Toast.LENGTH_LONG).show();
            // startActivity(new Intent(getApplicationContext(),Login.class));
        }

        @Override
        public boolean validatefiled() {
            final String email = editTextEmail.getText().toString().trim();
            final String password = editTextPassword.getText().toString().trim();
        /*Intent intent = new Intent(Login.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
            // finish();
            if (email.isEmpty() && password.isEmpty() ){
                Toast.makeText(getApplicationContext(),"Enter a username and password",Toast.LENGTH_SHORT).show();
                return false;
            }
            if (email.isEmpty()){
                Toast.makeText(getApplicationContext(),"The  username or password is incorrect",Toast.LENGTH_SHORT).show();
                return false;
            } else if (password.isEmpty()) {
                Toast.makeText(getApplicationContext(),"The  username or password is incorrect",Toast.LENGTH_SHORT).show();
                return false;
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
           //

        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            finish();
            System.exit(0);
        }
    }
