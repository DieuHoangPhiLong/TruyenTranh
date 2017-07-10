package com.philong.truyentranhhay;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.philong.truyentranhhay.adapters.AdapterChiTietTruyen;
import com.philong.truyentranhhay.models.Chuong;
import com.philong.truyentranhhay.models.Truyen;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChiTietTruyenActivity extends AppCompatActivity {

    public static final String LINK_TRUYEN = "LinkTruyen";
    public static final String LINK_HINH = "LinkHinh";

    private ImageView imgHinhTruyenChiTiet;
    private TextView txtTenTruyenChiTiet;
    private TextView txtChiTietTruyen;
    private TextView txtDiemChiTiet;
    private TextView txtChapMoiChiTiet;
    private TextView txtNoiDungChiTiet;
    //Chap truyen
    private RecyclerView mRecyclerViewChiTietTruyen;
    private AdapterChiTietTruyen mAdapterChiTietTruyen;
    private List<Chuong> mListChuong;
    //Toolbar
    private Toolbar mToolbar;

    public static Intent newIntent(Context context, String linkTruyen, String linkHinh){
        Intent intent = new Intent(context, ChiTietTruyenActivity.class);
        intent.putExtra(LINK_TRUYEN, linkTruyen);
        intent.putExtra(LINK_HINH, linkHinh);
        return intent;
    }

    public String getExtraLinkTruyen(){
        if(getIntent() != null){
            return getIntent().getStringExtra(LINK_TRUYEN);
        }
        return "";
    }

    public String getExtraLinkHinh(){
        if(getIntent() != null){
            return getIntent().getStringExtra(LINK_HINH);
        }
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_truyen);

        //setup toolbar;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(getSupportActionBar() == null){
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        imgHinhTruyenChiTiet = (ImageView) findViewById(R.id.imgHinhTruyenChiTiet);
        txtTenTruyenChiTiet = (TextView) findViewById(R.id.txtTenChiTiet);
        txtDiemChiTiet = (TextView) findViewById(R.id.txtDiemTruyenChiTiet);
        txtChiTietTruyen = (TextView) findViewById(R.id.txtChiTietTruyen);
        txtChapMoiChiTiet = (TextView) findViewById(R.id.txtChapMoiChiTiet);
        txtNoiDungChiTiet = (TextView) findViewById(R.id.txtNoiDungChiTiet);

        //Setup chi tiet truyen
        mRecyclerViewChiTietTruyen = (RecyclerView) findViewById(R.id.recyclerViewChiTietTruyen);
        mListChuong = new ArrayList<>();
        mRecyclerViewChiTietTruyen.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewChiTietTruyen.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewChiTietTruyen.setHasFixedSize(true);
        mRecyclerViewChiTietTruyen.setNestedScrollingEnabled(false);
        new GetChiTietTruyen().execute(getExtraLinkTruyen());
        System.out.println("Link: " + getExtraLinkTruyen());
    }

    //Get chi tiet truyen
    public class GetChiTietTruyen extends AsyncTask<String, Void, Truyen>{


        @Override
        protected Truyen doInBackground(String... params) {
            try {
                Truyen truyen = null;
                List<Chuong> listChuong = new ArrayList<>();
                Document doc = Jsoup.connect(params[0]).get();
                String tenTruyen = doc.select("div[class=media-body]").select("h1[class=title-manga]").text();
                String diemTruyen = doc.select("div[class=total-rating]").select("p").select("span[class=VoteScore]").text();
                String chiTiet = doc.select("p[class=description-update]").html();
                String noiDung = doc.select("div[class=manga-content]").text();
                Elements elements = doc.select("div[class=total-chapter] section a");
                for(Element element : elements){
                    String linkChap = element.attr("href");
                    String tenChap = element.text();
                    String ngayDang = element.select("span").text();
                    listChuong.add(new Chuong(tenChap, ngayDang, linkChap));
                }
                truyen = new Truyen(tenTruyen,getExtraLinkHinh(), diemTruyen, noiDung, chiTiet, listChuong);
                return truyen;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Truyen truyen) {
            super.onPostExecute(truyen);
            if(!mListChuong.isEmpty()){
                mListChuong.clear();
            }
            if(truyen != null){
                String chapter = truyen.getChuong().get(truyen.getChuong().size() - 1).getTenChuong().trim();
                String cutChapters[] = chapter.toLowerCase().split(" ");
                StringBuilder chapMoi = new StringBuilder();
                for (int i = 0; i < cutChapters.length; i++) {
                    if(cutChapters[i].equalsIgnoreCase("chap")){
                        chapMoi.append(cutChapters[i]).append(" " + cutChapters[i + 1]).toString().toUpperCase();
                        break;
                    }
                }

                txtChapMoiChiTiet.setText(String.valueOf(chapMoi.toString()));
                txtTenTruyenChiTiet.setText(truyen.getTenTruyen());
                txtChiTietTruyen.setText(Html.fromHtml(truyen.getChiTiet()));
                txtDiemChiTiet.setText(truyen.getDiemSo());
                txtNoiDungChiTiet.setText(truyen.getNoiDung());
                Picasso.with(ChiTietTruyenActivity.this).load(truyen.getLinkHinh()).error(R.drawable.placeholder).into(imgHinhTruyenChiTiet);
                mListChuong = truyen.getChuong();
                Collections.reverse(mListChuong);
                mAdapterChiTietTruyen = new AdapterChiTietTruyen(ChiTietTruyenActivity.this, mListChuong);
                mRecyclerViewChiTietTruyen.setAdapter(mAdapterChiTietTruyen);
                mAdapterChiTietTruyen.notifyDataSetChanged();
            }
        }
    }
}
