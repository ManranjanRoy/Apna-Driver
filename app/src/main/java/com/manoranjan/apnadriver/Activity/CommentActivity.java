package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.R;

public class CommentActivity extends AppCompatActivity {
EditText comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        comment=findViewById(R.id.comment);

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    StaticData.comments = comment.getText().toString();
                    Intent i = new Intent(getApplicationContext(), BookingSummaryActivity.class);
                    startActivity(i);

            }
        });
    }
}