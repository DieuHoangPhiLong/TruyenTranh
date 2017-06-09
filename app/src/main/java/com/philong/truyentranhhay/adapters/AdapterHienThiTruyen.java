package com.philong.truyentranhhay.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.philong.truyentranhhay.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Long on 6/9/2017.
 */

public class AdapterHienThiTruyen extends RecyclerView.Adapter<AdapterHienThiTruyen.ViewHolderHienThiTruyen>{

    private Context mContext;
    private List<String> mListTruyenTranh;

    public AdapterHienThiTruyen(Context context, List<String> listTruyenTranh) {
        mContext = context;
        mListTruyenTranh = listTruyenTranh;
    }

    @Override
    public ViewHolderHienThiTruyen onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hien_thi_truyen, parent, false);
        return new ViewHolderHienThiTruyen(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderHienThiTruyen holder, int position) {
        String linkTruyenTranh = mListTruyenTranh.get(position);
        Picasso.with(mContext).load(linkTruyenTranh).error(R.drawable.placeholder).into(holder.imgHienThiTruyen);
    }

    @Override
    public int getItemCount() {
        return mListTruyenTranh.size();
    }

    public class ViewHolderHienThiTruyen extends RecyclerView.ViewHolder{

        public ImageView imgHienThiTruyen;

        public ViewHolderHienThiTruyen(View itemView) {
            super(itemView);
            imgHienThiTruyen = (ImageView) itemView.findViewById(R.id.imgHienThiTruyen);
        }
    }

}
