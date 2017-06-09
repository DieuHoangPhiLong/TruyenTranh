package com.philong.truyentranhhay.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.philong.truyentranhhay.HienThiTruyenActivity;
import com.philong.truyentranhhay.R;
import com.philong.truyentranhhay.models.Chuong;

import java.util.List;

/**
 * Created by Long on 6/9/2017.
 */

public class AdapterChiTietTruyen extends RecyclerView.Adapter<AdapterChiTietTruyen.ViewHolderChiTietTruyen>{

    private Context mContext;
    private List<Chuong> mListChuong;

    public AdapterChiTietTruyen(Context context, List<Chuong> listChuong) {
        mContext = context;
        mListChuong = listChuong;
    }

    @Override
    public ViewHolderChiTietTruyen onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_chi_tiet_truyen, parent, false);
        return new ViewHolderChiTietTruyen(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderChiTietTruyen holder, int position) {
        final Chuong chuong = mListChuong.get(position);
        holder.txtTenChapTruyen.setText(chuong.getTenChuong());
        holder.cardViewChapTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(HienThiTruyenActivity.newIntent(mContext, chuong.getLinkChuong()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListChuong.size();
    }

    public class ViewHolderChiTietTruyen extends RecyclerView.ViewHolder{

        public TextView txtTenChapTruyen;
        public CardView cardViewChapTruyen;

        public ViewHolderChiTietTruyen(View itemView) {
            super(itemView);
            txtTenChapTruyen = (TextView) itemView.findViewById(R.id.txtTenChapTruyen);
            cardViewChapTruyen = (CardView) itemView.findViewById(R.id.cardViewChapTruyen);
        }
    }
}
