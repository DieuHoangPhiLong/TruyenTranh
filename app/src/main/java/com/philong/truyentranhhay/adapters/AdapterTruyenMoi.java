package com.philong.truyentranhhay.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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

public class AdapterTruyenMoi extends RecyclerView.Adapter<AdapterTruyenMoi.ViewHolderTruyenMoi> {

    private Context mContext;
    private List<Truyen> mListTruyen;

    public AdapterTruyenMoi(Context context, List<Truyen> listTruyen) {
        mContext = context;
        mListTruyen = listTruyen;
    }

    @Override
    public ViewHolderTruyenMoi onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_truyen_moi, parent, false);
        return new ViewHolderTruyenMoi(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderTruyenMoi holder, int position) {
        final Truyen truyen = mListTruyen.get(position);
        holder.txtTenTruyenMoi.setText(truyen.getTenTruyen());
        holder.txtTheLoaiTruyenMoi.setText(Html.fromHtml(truyen.getTheLoai()));
        Picasso.with(mContext).load(truyen.getLinkHinh()).error(R.drawable.placeholder).into(holder.imgHinhTruyenMoi);
        holder.cardViewTruyenMoi.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolderTruyenMoi extends RecyclerView.ViewHolder{

        public ImageView imgHinhTruyenMoi;
        public TextView txtTenTruyenMoi;
        public TextView txtTheLoaiTruyenMoi;
        public CardView cardViewTruyenMoi;

        public ViewHolderTruyenMoi(View itemView) {
            super(itemView);
            imgHinhTruyenMoi = (ImageView) itemView.findViewById(R.id.imgHinhTruyenMoi);
            txtTenTruyenMoi = (TextView) itemView.findViewById(R.id.txtTenTruyenMoi);
            txtTheLoaiTruyenMoi = (TextView) itemView.findViewById(R.id.txtTheLoaiTruyenMoi);
            cardViewTruyenMoi = (CardView) itemView.findViewById(R.id.cardViewTruyenMoi);
        }
    }
}
