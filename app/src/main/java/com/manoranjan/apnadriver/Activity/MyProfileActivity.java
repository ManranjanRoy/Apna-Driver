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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.Configss;
import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.MainActivity;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.Response.ProfileResponse;
import com.manoranjan.apnadriver.model.Profile;
import com.manoranjan.apnadriver.service.CountryService;

public class MyProfileActivity extends AppCompatActivity
implements View.OnClickListener{
        ImageView beditnamee,beditlname,beditphonenumberr,beditcardnumberr,beditemaill,beditaddresss,beditpincodee;
        TextView textname,textlname,textphonenumberr,textcardnumberr,textemaill,textaddresss,textpincodee;
        EditText editname,editlname,editphonenumberr,editcardnumberr,editemaill,editaddresss,editpincodee;
        String tokencode;
        ProgressDialog progressDialog;
        TextView headname,heademail;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_my_profile);
            findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            });

            progressDialog=new ProgressDialog(MyProfileActivity.this);
            progressDialog.setMessage("Please wait while Loading...");
            SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            //Fetching the boolean value form sharedpreferences
            tokencode = sharedPreferences.getString(Configss.tokencode, "default");


            beditnamee=findViewById(R.id.beditname);
            beditlname=findViewById(R.id.beditlname);
            beditphonenumberr=findViewById(R.id.beditphonenumber);
            beditcardnumberr=findViewById(R.id.beditcardnumber);
            beditemaill=findViewById(R.id.beditemail);
            beditaddresss=findViewById(R.id.beditaddress);
            beditpincodee=findViewById(R.id.beditpincode);


            beditnamee.setTag(R.drawable.edit_icon);
            beditlname.setTag(R.drawable.edit_icon);
            beditphonenumberr.setTag(R.drawable.edit_icon);
            beditcardnumberr.setTag(R.drawable.edit_icon);
            beditemaill.setTag(R.drawable.edit_icon);
            beditaddresss.setTag(R.drawable.edit_icon);
            beditpincodee.setTag(R.drawable.edit_icon);

            textname=findViewById(R.id.textname);
            textlname=findViewById(R.id.textlname);
            textphonenumberr=findViewById(R.id.textphonenumber);
            textcardnumberr=findViewById(R.id.textcardnumber);
            textemaill=findViewById(R.id.textemail);
            textaddresss=findViewById(R.id.textaddress);
            textpincodee=findViewById(R.id.textpincode);

            editname=findViewById(R.id.editname);
            editlname=findViewById(R.id.editlname);
            editphonenumberr=findViewById(R.id.editphonenumber);
            editcardnumberr=findViewById(R.id.editcardnumber);
            editemaill=findViewById(R.id.editemail);
            editaddresss=findViewById(R.id.editaddress);
            editpincodee=findViewById(R.id.editpincode);
            headname=findViewById(R.id.headname);
            heademail=findViewById(R.id.heademail);
            loadProfile();
           // printdata();

            beditnamee.setOnClickListener(this);
            beditlname.setOnClickListener(this);
            beditphonenumberr.setOnClickListener(this);
            beditcardnumberr.setOnClickListener(this);
            beditemaill.setOnClickListener(this);
            beditaddresss.setOnClickListener(this);
            beditpincodee.setOnClickListener(this);
        }

        @Override
        protected void onResume() {
            super.onResume();
            //printdata();
        }

        private void printdata() {
            headname.setText(StaticData.profile.getFname());
            heademail.setText(StaticData.profile.getEmailId());
            textname.setText(StaticData.profile.getFname());
            textphonenumberr.setText(StaticData.profile.getMobileNo());
            //textcardnumberr.setText(StaticData.profile.getFirstname());
            textemaill.setText(StaticData.profile.getEmailId());
           // textpincodee.setText(StaticData.profile.getId());

            editname.setText(StaticData.profile.getFname());
            editphonenumberr.setText(StaticData.profile.getMobileNo());
          //  editcardnumberr.setText(StaticData.profile.getFirstname());
            editemaill.setText(StaticData.profile.getEmailId());
           // editpincodee.setText(StaticData.profile.getId());


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.beditname:
                    checkimagetype(beditnamee,textname,editname);
                    break;
                case R.id.beditlname:
                    checkimagetype(beditlname,textlname,editlname);
                    break;
                case R.id.beditphonenumber:
                    checkimagetype(beditphonenumberr,textphonenumberr,editphonenumberr);
                    break;
                case R.id.beditcardnumber:
                    checkimagetype(beditcardnumberr, textcardnumberr, editcardnumberr);
                    break;
                case R.id.beditemail:
                    checkimagetype(beditemaill, textemaill, editemaill);
                    break;
                case R.id.beditaddress:
                    checkimagetype(beditaddresss, textaddresss, editaddresss);
                    break;
                case R.id.beditpincode:
                    checkimagetype(beditpincodee, textpincodee, editpincodee);
                    break;
                default:
                    break;
            }

        }
        private int getDrawableId(ImageView iv) {
            return (Integer) iv.getTag();
        }
        void checkimagetype(ImageView iv, TextView text, EditText edit){
            switch(getDrawableId(iv)) {
                case R.drawable.save:
                    text.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                    updateprofile();
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.edit_icon));
                    iv.setTag(R.drawable.edit_icon);
                    break;
                case R.drawable.edit_icon:
                    text.setVisibility(View.GONE);
                    edit.setVisibility(View.VISIBLE);
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.save));
                    iv.setTag(R.drawable.save);
                    break;
                default:
                    iv.setImageDrawable(getResources().getDrawable(R.drawable.edit_icon));
                    break;
            }
        }
        void  updateprofile(){
            progressDialog.show();
            new CountryService().getAPI().updateprofile(ApiLinks.update_profile,tokencode,editname.getText().toString()
                    ,editemaill.getText().toString(),
                    editphonenumberr.getText().toString()).enqueue(new Callback<ProfileResponse>() {
                @Override
                public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                    progressDialog.dismiss();
                    ProfileResponse data = response.body();
                    if (data != null && data.getStatus() != null) {
                        if (data.getStatus().equals("1")) {
                            loadProfile();
                            Toast.makeText(getApplicationContext(),data.getMessages(),Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),data.getMessages(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<ProfileResponse> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
        }
        public void loadProfile() {
            progressDialog.show();
            new CountryService().getAPI().profile(ApiLinks.view_profile,tokencode).enqueue(new Callback<ProfileResponse>() {
                @Override
                public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                    progressDialog.dismiss();
                    if (response.body()!=null) {
                        Log.d("tttt",response.body().getStatus());
                        if (response.body().getStatus().equals("1")) {
                            Profile profiles=response.body().getData();
                            StaticData.profile=profiles;
                            printdata();

                        }
                    }
                }
                @Override
                public void onFailure(Call<ProfileResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    loadProfile();
                }
            });

        }
    }
