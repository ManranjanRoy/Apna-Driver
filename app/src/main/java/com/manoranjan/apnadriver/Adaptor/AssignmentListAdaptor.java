package com.manoranjan.apnadriver.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manoranjan.apnadriver.R;
import com.manoranjan.apnadriver.model.AssignmentModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AssignmentListAdaptor extends RecyclerView.Adapter<AssignmentListAdaptor.ImageViewHolder> {
private Context mContext;
private List<AssignmentModel> mUploads;
    private OnitemClickListner mlistner;

    public interface  OnitemClickListner{

        void OnitemClick(int position);
    }
    public  void setonItemClickListner(OnitemClickListner listner){
        mlistner=listner;

    }

public AssignmentListAdaptor(Context mContext, List<AssignmentModel> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
        }

@Override
public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_assignment, parent, false);
        return new ImageViewHolder(v,mlistner);
        }

@Override
public void onBindViewHolder(final ImageViewHolder holder, int position) {

        final AssignmentModel uploadCurrent = mUploads.get(position);
   /* String imgurl= ApiLinks.baseimgurl+uploadCurrent.getImage();
    Picasso.with(mContext)
            .load(imgurl)
            .placeholder(R.drawable.ic_launcher_background)
            .fit()
            .into(holder.catimg);*/
            // holder.imgca.setImageDrawable(mContext.getResources().getDrawable(uploadCurrent.getCode()));
            holder.title.setText(uploadCurrent.getTitle());
            holder.subject.setText(uploadCurrent.getSubject());





        }

@Override
public int getItemCount() {
        return mUploads.size();
        }

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public TextView title,subject;
    ImageView catimg;
    LinearLayout ll;
    public ImageViewHolder(View itemView, final OnitemClickListner listner) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        subject=itemView.findViewById(R.id.subject);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listner !=null){
                    int position =getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listner.OnitemClick(position);
                    }
                }
            }
        });


    }


}


}