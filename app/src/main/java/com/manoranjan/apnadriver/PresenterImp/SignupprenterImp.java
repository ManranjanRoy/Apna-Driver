package com.manoranjan.apnadriver.PresenterImp;



import android.content.Context;
import android.content.SharedPreferences;

import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.Configss;
import com.manoranjan.apnadriver.Response.SignupResponse;
import com.manoranjan.apnadriver.presenter.SignupPresenter;
import com.manoranjan.apnadriver.service.CountryService;
import com.manoranjan.apnadriver.view.SignupView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupprenterImp  implements SignupPresenter {
    SignupView signupView;
    Context context;


    public SignupprenterImp(SignupView signupView, Context applicationContext) {
        this.signupView = signupView;
        this.context=applicationContext;
    }

    public void signup(String fname,String mobileno, String email, String password, String cpassword,String logintype) {
           if (signupView.validatefiled()){
               signupView.Showprogess();
               CountryService countryService=new CountryService();
               countryService.getAPI().createUser(ApiLinks.register,fname,mobileno,email,password,cpassword,logintype)
                       .enqueue(new Callback<SignupResponse>() {
                           @Override
                           public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                              SignupResponse data=response.body();
                              signupView.dismissproggress();

                               if (data != null && data.getStatus() != null) {
                                   if (data.getStatus().equals("true")) {
                                       SharedPreferences sharedPreferences =context.getSharedPreferences
                                               (Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                                       //Creating editor to store values to shared preferences
                                       SharedPreferences.Editor editor = sharedPreferences.edit();
                                       //Adding values to editor
                                       editor.putBoolean(Configss.LOGGEDIN_SHARED_PREF, true);
                                       //  editor.putString(Config.login_role,role);
                                       editor.putString(Configss.login_role,"0");
                                       editor.putString(Configss.tokencode,response.body().getToken_code());
                                       editor.commit();
                                       signupView.onSucess();
                                   }
                                   else{
                                       signupView.onError(data.getMessages());
                                   }
                                   //List<Country> result = data.getRestResponse().getResult();
                                   // countryView.countriesReady(result);
                               }
                           }

                           @Override
                           public void onFailure(Call<SignupResponse> call, Throwable t) {
                                signupView.dismissproggress();
                           }
                       });


           }


    }
}
