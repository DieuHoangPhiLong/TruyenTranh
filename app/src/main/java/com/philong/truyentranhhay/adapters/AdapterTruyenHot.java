package com.philong.truyentranhhay.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.philong.truyentranhhay.ChiTietTruyenActivity;
import com.philong.truyentranhhay.R;
import com.philong.truyentranhhay.models.Truyen;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Long on 6/9/2017.
 */

public class AdapterTruyenHot extends RecyclerView.Adapter<AdapterTruyenHot.ViewHolderTruyenHot>{

    private Context mContext;
    private List<Truyen> mListTruyen;



    public AdapterTruyenHot(Context context, List<Truyen> listTruyen) {
        mContext = context;
        mListTruyen = listTruyen;
    }

    @Override
    public ViewHolderTruyenHot onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_truyen_hot, parent, false);
        return new ViewHolderTruyenHot(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderTruyenHot holder, int position) {
        final Truyen truyen = mListTruyen.get(position);
        holder.txtTenTruyenHot.setText(truyen.getTenTruyen());
        Picasso.with(mContext).load(truyen.getLinkHinh()).error(R.drawable.placeholder).into(holder.imgHinhTruyenHot);
        holder.cardViewTruyenHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(ChiTietTruyenActivity.newIntent(mContext, truyen.getLinkTruyen(), truyen.getLinkHinh()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListTruyen.size();
    }



    public class ViewHolderTruyenHot extends RecyclerView.ViewHolder{

        public ImageView imgHinhTruyenHot;
        public TextView txtTenTruyenHot;
        public CardView cardViewTruyenHot;

        public ViewHolderTruyenHot(View itemView) {
            super(itemView);
            imgHinhTruyenHot = (ImageView) itemView.findViewById(R.id.imgHinhTruyenHot);
            txtTenTruyenHot = (TextView) itemView.findViewById(R.id.txtTenTruyenHot);
            cardViewTruyenHot = (CardView) itemView.findViewById(R.id.cardViewTruyenHot);
        }
    }
}
