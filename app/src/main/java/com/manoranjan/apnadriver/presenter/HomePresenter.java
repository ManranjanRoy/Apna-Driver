package com.manoranjan.apnadriver.presenter;

import com.manoranjan.apnadriver.model.Profile;

import java.util.List;

public interface HomePresenter {

    void loadProfile(String tokencode);
    void getprofile(List<Profile> profiles);

}
