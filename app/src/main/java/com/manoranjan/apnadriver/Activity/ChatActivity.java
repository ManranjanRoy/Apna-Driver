package com.manoranjan.apnadriver.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.manoranjan.apnadriver.Adaptor.MessageAdaptor;
import com.manoranjan.apnadriver.model.MessageModel;
import com.manoranjan.apnadriver.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatActivity extends AppCompatActivity {
    private EditText editText;
    RecyclerView msgRecyclerView;
    MessageAdaptor messageAdaptor;
    ProgressDialog progressDialog;
    String tokencode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        editText = (EditText) findViewById(R.id.editText);

        msgRecyclerView = (RecyclerView)findViewById(R.id.messages_view);

        // Set RecyclerView layout manager.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);

        // Create the initial data list.
        final List<MessageModel> messageModels = new ArrayList<>();
        messageModels.add(new MessageModel("hello",MessageModel.SEND_MSG));
        messageModels.add(new MessageModel("Hi",MessageModel.RECIEVED_MSG));
        messageModels.add(new MessageModel("How are you",MessageModel.SEND_MSG));
        messageModels.add(new MessageModel("fine",MessageModel.RECIEVED_MSG));
        messageModels.add(new MessageModel("hello",MessageModel.SEND_MSG));
        messageModels.add(new MessageModel("Hi",MessageModel.RECIEVED_MSG));
        messageModels.add(new MessageModel("How are you",MessageModel.SEND_MSG));
        messageModels.add(new MessageModel("fine",MessageModel.RECIEVED_MSG));
        messageModels.add(new MessageModel("hello",MessageModel.SEND_MSG));
        messageModels.add(new MessageModel("Hi",MessageModel.RECIEVED_MSG));
        messageModels.add(new MessageModel("How are you",MessageModel.SEND_MSG));
        messageModels.add(new MessageModel("fine",MessageModel.RECIEVED_MSG));



        // Create the data adapter with above data list.
       messageAdaptor = new MessageAdaptor(ChatActivity.this,messageModels);

        // Set data adapter to RecyclerView.
        msgRecyclerView.setAdapter(messageAdaptor);

        final EditText msgInputText = (EditText)findViewById(R.id.editText);

        ImageButton msgSendButton = (ImageButton)findViewById(R.id.sendmessage);

        msgSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msgContent = msgInputText.getText().toString();
                if(!TextUtils.isEmpty(msgContent))
                {
                    // Add a new sent message to the list.
                    MessageModel msgDto = new MessageModel( msgContent,MessageModel.SEND_MSG);
                    messageModels.add(msgDto);

                    int newMsgPosition = messageModels.size() - 1;

                    // Notify recycler view insert one new data.
                    messageAdaptor.notifyItemInserted(newMsgPosition);

                    // Scroll RecyclerView to the last message.
                    msgRecyclerView.scrollToPosition(newMsgPosition);

                    // Empty the input edit text box.
                    msgInputText.setText("");
                }
            }
        });
    }


    private String getRandomColor() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer("#");
        while(sb.length() < 7){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            //scaledrone.publish("observable-room", message);
            editText.getText().clear();
        }
    }
}
