package com.manoranjan.apnadriver.PresenterImp;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.Configss;
import com.manoranjan.apnadriver.Response.LoginResponse;
import com.manoranjan.apnadriver.presenter.LoginPresenter;
import com.manoranjan.apnadriver.service.CountryService;
import com.manoranjan.apnadriver.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class LoginPresenterImp  implements LoginPresenter {

    LoginView loginView;
    Context context;

    public LoginPresenterImp(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void loginfunction(String email, String password) {
       // loginView.onError();
       if (loginView.validatefiled()){
            loginView.Showprogess();
            CountryService countryService=new CountryService();
            countryService.getAPI().login(ApiLinks.login,email,password).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Log.d("response", response.body().toString());
                    loginView.dismissproggress();
                    if (response.body()!=null) {
                        if (response.body().getStatus().equals("true")) {
                            Log.d("response", response.body().toString());
                            LoginResponse loginResponse = response.body();
                            //List<Profile> profiles = loginResponse.getData();
                            Log.d("accesstoken",response.body().getToken_code());
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
                            loginView.onSucess();
                        } else {
                            loginView.onError(response.body().getMessages());
                        }
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    loginView.dismissproggress();
                }
            });
        }

       // loginView.onSucess();

    }

    @Override
    public void getcontext(Context context) {
        this.context=context;

    }
}
