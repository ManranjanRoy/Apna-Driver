package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.manoranjan.apnadriver.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        findViewById(R.id.privacypolicy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PrivacyPolicyActivity.class));
            }
        });
        findViewById(R.id.termsandcondition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TermsconditionActivity.class));
            }

        });
        findViewById(R.id.contactus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ContactUsActivity.class));
            }
        });
        findViewById(R.id.support).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //https://77laundry.com.au/support-center
                String url = "https://77laundry.com.au/support-center/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                //startActivity(new Intent(getApplicationContext(),TermsconditionActivity.class));
            }
        });
    }
}
