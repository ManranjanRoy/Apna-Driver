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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.Configss;
import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.Response.AddressResponse;
import com.manoranjan.apnadriver.Response.SuburbPostcodeResponse;
import com.manoranjan.apnadriver.Response.UpdateAddressresponse;
import com.manoranjan.apnadriver.model.AddressModel;
import com.manoranjan.apnadriver.model.SuburbPostCodeModel;
import com.manoranjan.apnadriver.service.CountryService;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    List<String> pickuplistspinner,pickuplistspinner2,hometype;
   // List<SuburbPostCodeModel> pickupSpinnerModels,pickupSpinnerModels2;
    Spinner spinner,spinner1,spinner2;
    String suburb,postcode,housetypes;
    EditText streetname,streetnumber;
    Button save;
    String tokencode;
    String[] country = { "House", "Unit/Apartment","Townhouse","Aged Care Facility","Hotel/Serviced Apartment","Other"};

    AddressModel addressModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        progressDialog=new ProgressDialog(MyAddressActivity.this);
        progressDialog.setMessage("Please wait while Loading ...");
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        tokencode = sharedPreferences.getString(Configss.tokencode,"Not Available");

        spinner=findViewById(R.id.spinner);
        spinner1=findViewById(R.id.spinner1);
        spinner2=findViewById(R.id.spinner2);
        streetname=findViewById(R.id.streetname);
        streetnumber=findViewById(R.id.streetnumber);
        save=findViewById(R.id.savebtn);
        hometype=new ArrayList<>();
        pickuplistspinner=new ArrayList<>();
       // pickupSpinnerModels=new ArrayList<>();
        pickuplistspinner2=new ArrayList<>();
        //pickupSpinnerModels2=new ArrayList<>();

        if (getservice()){
            if (getservice1())
           {
               getaddress();
               adddatatospinner();
           }

        }
       /* getservice();
        getservice1();*/
       // getaddress();
        //suburb
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // StaticData.pickup=country[position];
                String item = parent.getItemAtPosition(position).toString();
                suburb=item;
                //Toast.makeText(getApplicationContext(),suburb, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //postcode
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // StaticData.pickup=country[position];
                String item = parent.getItemAtPosition(position).toString();
                postcode=item;
                //Toast.makeText(getApplicationContext(),postcode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // StaticData.pickup=country[position];
                String item = parent.getItemAtPosition(position).toString();
                housetypes=item;
                //Toast.makeText(getApplicationContext(),item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatefiled()){
                    savedata();
                }
            }
        });
    }

    private  void getaddress(){
        progressDialog.show();
        new CountryService().getAPI().getaddress(ApiLinks.view_address,tokencode).enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                if (response.isSuccessful()&& response.body()!=null){
                    if (response.body().getStatus().equals("1")){
                        AddressModel addressModel=response.body().getData();
                        if (addressModel!=null){
                        streetname.setText(addressModel.getStreetName().toString());
                        streetnumber.setText(addressModel.getStreetNo().toString());
                            for (int i=0;i<country.length;i++) {
                                if (addressModel.getHouseType().equals(country[i])) {
                                    spinner1.setSelection(i);
                                    break;
                                }
                                if (pickuplistspinner.size()>0) {
                                    int value=0;
                                    for (int j = 0; j < pickuplistspinner.size(); j++) {
                                        Log.d("spinnertest",addressModel.getSuburb()+" / "+pickuplistspinner.get(j));
                                        if (addressModel.getSuburb().trim().equals(pickuplistspinner.get(j).trim())) {
                                            value=j;
                                            break;
                                        }
                                    }
                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MyAddressActivity.this,
                                            android.R.layout.simple_spinner_item, pickuplistspinner);
                                    // Drop down layout style - list view with radio button
                                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    // attaching data adapter to spinner
                                    spinner.setAdapter(dataAdapter);
                                    spinner.setSelection(value);
                                }else{
                                    getservice();
                                    getaddress();
                                   //Toast.makeText(MyAddressActivity.this, "empty spinner1", Toast.LENGTH_SHORT).show();
                                }
                            if (pickuplistspinner2.size()>0) {
                                int val2=0;
                                for (int k = 0; k < pickuplistspinner2.size(); k++) {
                                    if (addressModel.getPostCode().equals(pickuplistspinner2.get(k))) {
                                        val2=k;
                                        break;
                                    }
                                }
                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MyAddressActivity.this, android.R.layout.simple_spinner_item, pickuplistspinner2);

                                // Drop down layout style - list view with radio button
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                // attaching data adapter to spinner
                                spinner2.setAdapter(dataAdapter);
                                spinner2.setSelection(val2);
                            }
                            else{
                                getservice1();
                                getaddress();
                            }
                        }
                            progressDialog.dismiss();
                          // getsuburb(addressModel.getSuburb().toString());
                          // getpostcode(addressModel.getPostCode());
                            //  pickupSpinnerModels.add(new PickupSpinnerModel(String.valueOf(i),country[i]));
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
             progressDialog.dismiss();
            }
        });
    }
    private void savedata() {
        progressDialog.show();
        new CountryService().getAPI().updateaddress(ApiLinks.update_address,tokencode,housetypes,streetnumber.getText().toString(),
                streetname.getText().toString(),postcode,suburb).enqueue(new Callback<UpdateAddressresponse>() {
            @Override
            public void onResponse(Call<UpdateAddressresponse> call, Response<UpdateAddressresponse> response) {
                progressDialog.dismiss();
                if (response.body()!=null){
                    if (response.body().getStatus().equals("1")){
                       // Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                        if (StaticData.editaddresstype.equals("edit")){
                            StaticData.editaddresstype="add";
                            Intent i=new Intent(getApplicationContext(), MyDetailsActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateAddressresponse> call, Throwable t) {
        progressDialog.dismiss();
            }
        });
    }

    void adddatatospinner(){
        for (int i=0;i<country.length;i++){
            hometype.add(country[i]);
          //  pickupSpinnerModels.add(new PickupSpinnerModel(String.valueOf(i),country[i]));
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MyAddressActivity.this, android.R.layout.simple_spinner_item, hometype);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);


    }
    boolean getservice(){
        progressDialog.show();
        new CountryService().getAPI().getsuburbpostcode(ApiLinks.suburb).enqueue(new Callback<SuburbPostcodeResponse>() {
            @Override
            public void onResponse(Call<SuburbPostcodeResponse> call, Response<SuburbPostcodeResponse> response) {
                if (response.body()!=null && response.isSuccessful()){
                    pickuplistspinner.clear();
                    progressDialog.dismiss();
                    if (response.body().getStatus().equals("1")){
                        List<SuburbPostCodeModel> servicelists=response.body().getList();

                        //StaticData.pickup=servicelists.get(0).getPickupslot();
                        if (servicelists.size()>0) {
                            for (int i = 0; i < servicelists.size(); i++) {
                                pickuplistspinner.add(servicelists.get(i).getName());
                                //pickupSpinnerModels.add(new SuburbPostCodeModel(String.valueOf(i),servicelists.get(i).getName()));
                            }

                        }

                    }
                    else{
                        Toast.makeText(MyAddressActivity.this, "faild", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SuburbPostcodeResponse> call, Throwable t) {
                progressDialog.dismiss();
                getservice();
            }
        });
return true;
    }
    boolean getservice1(){
        progressDialog.show();
        new CountryService().getAPI().getsuburbpostcode(ApiLinks.suburb).enqueue(new Callback<SuburbPostcodeResponse>() {
            @Override
            public void onResponse(Call<SuburbPostcodeResponse> call, Response<SuburbPostcodeResponse> response) {
                if (response.body()!=null && response.isSuccessful()){
                    progressDialog.dismiss();
                    if (response.body().getStatus().equals("1")){
                        List<SuburbPostCodeModel> servicelists=response.body().getList();
                      //  StaticData.pickup=servicelists.get(0).getPickupslot();
                        if (servicelists.size()>0) {
                            for (int i = 0; i < servicelists.size(); i++) {
                                pickuplistspinner2.add(servicelists.get(i).getCode());
                                // pickupSpinnerModels2.add(new PickupSpinnerModel(String.valueOf(i),servicelists.get(i).getCode()));
                            }

                        }

                    }
                    else{
                        Toast.makeText(MyAddressActivity.this, "faild", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SuburbPostcodeResponse> call, Throwable t) {
                progressDialog.dismiss();
                getservice1();
            }
        });
return true;
    }

    public boolean validatefiled() {
        ////fname,lname,email,monileno,address1,address2,town,pinciode,password,cpassword
        final String streetname1 = streetname.getText().toString().trim();
        final  String streetnumber1=streetnumber.getText().toString().trim();
        final String housetype1 = housetypes;
        final String suburb1 =suburb;
        final String postcode1 = postcode;

        if(TextUtils.isEmpty(streetname1)) {
            streetname.setError("Please Enter Street Name");
            return false;
        } else if(TextUtils.isEmpty(streetnumber1)) {
            streetnumber.setError("Please Enter Streetnumber");
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


        return true;
    }

    void  getsuburb(String s){
        progressDialog.show();
        new CountryService().getAPI().getsuburbpostcode(ApiLinks.suburb).enqueue(new Callback<SuburbPostcodeResponse>() {
            @Override
            public void onResponse(Call<SuburbPostcodeResponse> call, Response<SuburbPostcodeResponse> response) {
                if (response.body()!=null){
                    progressDialog.dismiss();
                    if (response.body().getStatus().equals("1")){
                        List<SuburbPostCodeModel> servicelists=response.body().getList();
                        //StaticData.pickup=servicelists.get(0).getPickupslot();
                        for (int i=0;i<servicelists.size();i++){
                            pickuplistspinner.add(servicelists.get(i).getName());
                            //pickupSpinnerModels.add(new SuburbPostCodeModel(String.valueOf(i),servicelists.get(i).getName()));
                        }
                        int jj=0;
                        for (int j=0;j<pickuplistspinner.size();j++){
                            if (s.equals(pickuplistspinner.get(j)))
                            {
                                jj=j;
                                Log.d("checkmatch",s+"/"+pickuplistspinner.get(j)+String.valueOf(j));
                                //spinner.setSelection(j);
                                break;
                            }
                            else{
                                Log.d("checkmatch",s+"/"+pickuplistspinner.get(j)+String.valueOf(j));
                            }
                        }
                        spinner.setSelection(jj);



                    }
                    else{
                        Toast.makeText(MyAddressActivity.this, "faild", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SuburbPostcodeResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
    void  getpostcode(String postCode){
        progressDialog.show();
        new CountryService().getAPI().getsuburbpostcode(ApiLinks.suburb).enqueue(new Callback<SuburbPostcodeResponse>() {
            @Override
            public void onResponse(Call<SuburbPostcodeResponse> call, Response<SuburbPostcodeResponse> response) {
                if (response.body()!=null){
                    progressDialog.dismiss();
                    if (response.body().getStatus().equals("1")){
                        List<SuburbPostCodeModel> servicelists=response.body().getList();
                        //  StaticData.pickup=servicelists.get(0).getPickupslot();
                        for (int i=0;i<servicelists.size();i++){
                            pickuplistspinner2.add(servicelists.get(i).getCode());
                            // pickupSpinnerModels2.add(new PickupSpinnerModel(String.valueOf(i),servicelists.get(i).getCode()));
                        }

                            for (int k=0;k<pickuplistspinner2.size();k++){
                                if (postCode.equals(pickuplistspinner2.get(k)))
                                {
                                    spinner2.setSelection(k);
                                    break;
                                }
                            }


                    }
                    else{
                        Toast.makeText(MyAddressActivity.this, "faild", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SuburbPostcodeResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });

    }
}
