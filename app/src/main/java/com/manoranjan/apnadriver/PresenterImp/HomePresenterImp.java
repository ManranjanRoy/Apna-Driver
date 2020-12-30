package com.manoranjan.apnadriver.PresenterImp;


import android.content.Context;

import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.Response.ProfileResponse;
import com.manoranjan.apnadriver.model.Profile;
import com.manoranjan.apnadriver.presenter.HomePresenter;
import com.manoranjan.apnadriver.service.CountryService;
import com.manoranjan.apnadriver.view.HomeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class HomePresenterImp implements HomePresenter {

    HomeView homeView;
    Context context;

    public HomePresenterImp(HomeView homeView, Context context) {
        this.homeView = homeView;
        this.context=context;
    }
    @Override
    public void loadProfile(String tokencode) {
   homeView.Showprogess();
        new CountryService().getAPI().profile(ApiLinks.view_profile,tokencode).enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                homeView.dismissproggress();
                if (response.body()!=null) {
                    if (response.body().getStatus().equals("1")) {
                         Profile profiles=response.body().getData();
                        StaticData.profile=profiles;

                    }
                 }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                homeView.dismissproggress();
            }
        });

    }

    @Override
    public void getprofile(List<Profile> profiles) {

    }
}
