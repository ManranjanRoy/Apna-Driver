package com.manoranjan.apnadriver.view;

public interface SignupView {
    void onSucess();
    void onError(String msg);
    boolean validatefiled();
    void Showprogess();
    void dismissproggress();
}
