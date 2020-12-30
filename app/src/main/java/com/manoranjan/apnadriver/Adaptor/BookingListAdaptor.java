package com.manoranjan.apnadriver.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.model.BookingListModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class BookingListAdaptor extends RecyclerView.Adapter<BookingListAdaptor.ImageViewHolder> {
private Context mContext;
private List<BookingListModel> mUploads;
    private OnitemClickListner mlistner;

    public interface  OnitemClickListner{

        void OnitemClick(int position);
        void OnCancelClick(int position);
    }
    public  void setonItemClickListner(OnitemClickListner listner){
        mlistner=listner;

    }

public BookingListAdaptor(Context mContext, List<BookingListModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
        }

@Override
public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_bookinghistory, parent, false);
        return new ImageViewHolder(v,mlistner);
        }

@Override
public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final BookingListModel uploadCurrent = mUploads.get(position);
   /* String imgurl= ApiLinks.baseimgurl+uploadCurrent.getImage();
    Picasso.with(mContext)
            .load(imgurl)
            .placeholder(R.drawable.ic_launcher_background)
            .fit()
            .into(holder.catimg);*/
            // holder.imgca.setImageDrawable(mContext.getResources().getDrawable(uploadCurrent.getCode()));
            holder.servicename.setText(uploadCurrent.getCartypeName());
    if (uploadCurrent.getStatus().equals("0")){
        holder.servicestatus.setText("Booked");
    }else
        if (uploadCurrent.getStatus().equals("1")){
                holder.servicestatus.setText("Confirmed");
            }else
                if (uploadCurrent.getStatus().equals("2")){
                holder.cancel.setVisibility(View.GONE);
                holder.servicestatus.setText("Completed");
              }else if (uploadCurrent.getStatus().equals("3")){
                holder.servicestatus.setText("Cancelled");
                holder.cancel.setVisibility(View.GONE);
            }

            holder.drivarname.setText(uploadCurrent.getDriver_name());
            holder.drivernumber.setText("+91"+uploadCurrent.getDriver_mobile());
            holder.pickupdate.setText(uploadCurrent.getReportingAddress()+" "+uploadCurrent.getAreaName());
            holder.pickuptime.setText("");
            holder.destination.setText(uploadCurrent.getDesinationAddress());
            holder.deliverydate.setText(uploadCurrent.getBookingTiming()+" For");
            holder.deliverytime.setText(uploadCurrent.getBookingHour()+"hrs");
            holder.amount.setText("₹ "+uploadCurrent.getTotal_price() +" (Paid )");



        }

@Override
public int getItemCount() {
        return mUploads.size();
        }

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public TextView servicename,servicestatus,pickupdate,pickuptime,
            deliverydate,deliverytime,destination,drivarname,drivernumber,amount;
    Button cancel;
    ImageView catimg;
    LinearLayout ll;
    public ImageViewHolder(View itemView, final OnitemClickListner listner) {
        super(itemView);
        servicename = itemView.findViewById(R.id.servicename);
        servicestatus=itemView.findViewById(R.id.servicestatus);
        pickupdate = itemView.findViewById(R.id.pickupdate);
        pickuptime=itemView.findViewById(R.id.pickuptime);
        deliverydate = itemView.findViewById(R.id.deliverydate);
        deliverytime=itemView.findViewById(R.id.deliverytime);
        destination=itemView.findViewById(R.id.destination);
        drivarname=itemView.findViewById(R.id.drivername);
        drivernumber=itemView.findViewById(R.id.drivernumber);
        amount=itemView.findViewById(R.id.amount);
        cancel=itemView.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listner !=null){
                    int position =getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listner.OnCancelClick(position);
                    }
                }
            }
        });


    }


}


}