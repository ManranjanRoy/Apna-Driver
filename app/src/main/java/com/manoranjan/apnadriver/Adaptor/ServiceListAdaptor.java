package com.manoranjan.apnadriver.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manoranjan.apnadriver.Activity.BookingSummaryActivity;
import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.Api.StaticData;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.model.DriverListModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Stack;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceListAdaptor extends RecyclerView.Adapter<ServiceListAdaptor.ImageViewHolder> {
private Context mContext;
private List<DriverListModel> mUploads;
    private OnitemClickListner mlistner;
    Button next;
    public interface  OnitemClickListner{
        void OnitemClick(int position);
    }
    public  void setonItemClickListner(OnitemClickListner listner){
        mlistner=listner;

    }

public ServiceListAdaptor(Context mContext, List<DriverListModel> mUploads, Button next) {
        this.mContext = mContext;
        this.mUploads = mUploads;
        this.next=next;
        }

@Override
public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_service, parent, false);
        return new ImageViewHolder(v,mlistner);
        }

@RequiresApi(api = Build.VERSION_CODES.M)
@Override
public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final DriverListModel uploadCurrent = mUploads.get(position);
    String imgurl= ApiLinks.baseimgurl+uploadCurrent.getImage();
    holder.itemView.setBackgroundColor(uploadCurrent.isSelected() ? mContext.getColor(R.color.tab_disable) : mContext.getColor(R.color.colorBackground));

            Picasso.with(mContext)
                    .load(imgurl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .fit()
                    .into(holder.img);
            // holder.imgca.setImageDrawable(mContext.getResources().getDrawable(uploadCurrent.getCode()));
            holder.title.setText(uploadCurrent.getName());
            holder.age.setText(uploadCurrent.getAge());
            holder.exp.setText(uploadCurrent.getExperience());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i=0;i<mUploads.size();i++){
                        if (!uploadCurrent.getId().equals(mUploads.get(i).getId())) {
                            if (mUploads.get(i).isSelected) {
                                mUploads.get(i).setSelected(false);
                            }
                        }
                    }
                    uploadCurrent.setSelected(!uploadCurrent.isSelected());
                    holder.itemView.setBackgroundColor(uploadCurrent.isSelected() ? mContext.getColor(R.color.tab_disable) : mContext.getColor(R.color.colorBackground));
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i=0;i<mUploads.size();i++){
                        if (mUploads.get(i).isSelected){
                                StaticData.drivername="";
                                StaticData.drivername = uploadCurrent.getName();
                                StaticData.bdriverid = uploadCurrent.getId();
                        }
                    }
                     StaticData.serviceListModelList=mUploads;
                    if (StaticData.edittype.equals("2")){
                        StaticData.edittype.equals("0");
                        Intent i = new Intent(mContext, BookingSummaryActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(i);
                    }else {
                        StaticData.edittype="0";
                        Intent i = new Intent(mContext, BookingSummaryActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(i);
                    }
                }
            });



        }

@Override
public int getItemCount() {
        return mUploads.size();
        }

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public TextView title,age,exp;
    ImageView img;
    LinearLayout ll;
    public ImageViewHolder(View itemView, final OnitemClickListner listner) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        age=itemView.findViewById(R.id.age);
        exp=itemView.findViewById(R.id.exp);
        img=itemView.findViewById(R.id.img);
        ll=itemView.findViewById(R.id.s1);
        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listner !=null){
                    int position =getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listner.OnitemClick(position);
                    }
                }
            }
        });*/


    }


}


}