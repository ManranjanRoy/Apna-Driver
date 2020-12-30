package com.manoranjan.apnadriver.Adaptor;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manoranjan.apnadriver.model.MessageModel;
import com.manoranjan.apnadriver.R;


import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MessageAdaptor extends RecyclerView.Adapter<MessageAdaptor.ImageViewHolder> {
private Context mContext;
private List<MessageModel> mUploads;
    private  OnitemClickListner mlistner;

    public interface  OnitemClickListner{

        void OnItemCLiCK(int position);
        void Deleteaddress(int position);
    }
    public  void setonItemClickListner(OnitemClickListner listner){
        mlistner=listner;

    }
public MessageAdaptor(Context mContext, List<MessageModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
        }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_messagelayout, parent, false);
            return new ImageViewHolder(v,mlistner);
            }

    @Override
        public void onBindViewHolder(final ImageViewHolder holder, int position) {

            final MessageModel uploadCurrent = mUploads.get(position);
  if (uploadCurrent.getMessagetype().equals(MessageModel.SEND_MSG))
  {
    holder.mymessagelayout.setVisibility(View.VISIBLE);
    holder.theirmessagelayout.setVisibility(View.GONE);
      holder.mymessage.setText(uploadCurrent.getSender());
  }else if (uploadCurrent.getMessagetype().equals(MessageModel.RECIEVED_MSG))
  {
      holder.mymessagelayout.setVisibility(View.GONE);
      holder.theirmessagelayout.setVisibility(View.VISIBLE);
      holder.theirmessage.setText(uploadCurrent.getSender());
  }





        }

@Override
public int getItemCount() {
        return mUploads.size();
        }

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public TextView name,theirmessage,mymessage;
    RelativeLayout theirmessagelayout,mymessagelayout;


    public ImageViewHolder(View itemView, final OnitemClickListner listner) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        theirmessage=itemView.findViewById(R.id.message_body);
        mymessage=itemView.findViewById(R.id.my_message_body);
        theirmessagelayout=itemView.findViewById(R.id.theirmessagelayout);
        mymessagelayout=itemView.findViewById(R.id.mymessagelayout);

        //ll=itemView.findViewById(R.id.rr);


    }


}


}