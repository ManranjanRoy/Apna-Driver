package com.manoranjan.apnadriver.view;

public interface AddCustomerView {
    void onSucess(String message);
    void onError(String message);
    boolean validatefiled();
    void Showprogess();
    void dismissproggress();


}

