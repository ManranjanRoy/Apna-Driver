package com.manoranjan.apnadriver.Adaptor;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manoranjan.apnadriver.Api.ApiLinks;
import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.model.VideoCatagoryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class VideocatagoryAdaptor extends RecyclerView.Adapter<VideocatagoryAdaptor.ImageViewHolder> {
private Context mContext;
private List<VideoCatagoryModel> mUploads;
    private OnitemClickListner mlistner;

    public interface  OnitemClickListner{

        void OnitemClick(int position);
    }
    public  void setonItemClickListner(OnitemClickListner listner){
        mlistner=listner;

    }

public VideocatagoryAdaptor(Context mContext, List<VideoCatagoryModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
        }

@Override
public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_video_catagory, parent, false);
        return new ImageViewHolder(v,mlistner);
        }

@Override
public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final VideoCatagoryModel uploadCurrent = mUploads.get(position);
    String imgurl= ApiLinks.baseimgurl+uploadCurrent.getImage();
    Picasso.with(mContext)
            .load(imgurl)
            .placeholder(R.drawable.ic_launcher_background)
            .fit()
            .into(holder.catimg);
            // holder.imgca.setImageDrawable(mContext.getResources().getDrawable(uploadCurrent.getCode()));
            holder.catname.setText(uploadCurrent.getName());
            holder.catdesc.setText(uploadCurrent.getName());
    holder.ll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*Intent i=new Intent(mContext, VideoListActivity.class);
            i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //i.putExtra("url",uploadCurrent.getId());
            StaticData.cat_id=uploadCurrent.getId();
            mContext.startActivity(i);*/
        }
    });



        }

@Override
public int getItemCount() {
        return mUploads.size();
        }

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public TextView catname,catdesc;
    ImageView catimg;
    LinearLayout ll;
    public ImageViewHolder(View itemView, final OnitemClickListner listner) {
        super(itemView);
        catname = itemView.findViewById(R.id.catname);
        catdesc=itemView.findViewById(R.id.catdesc);
        catimg=itemView.findViewById(R.id.catimg);
        ll=itemView.findViewById(R.id.videocard);
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