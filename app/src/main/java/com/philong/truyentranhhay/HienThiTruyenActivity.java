package com.philong.truyentranhhay;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.philong.truyentranhhay.adapters.AdapterHienThiTruyen;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HienThiTruyenActivity extends AppCompatActivity {

    public static final String LINK_CHAP = "LINK_CHAP";

    private RecyclerView mRecyclerViewHienThiTruyen;
    private AdapterHienThiTruyen mAdapterHienThiTruyen;
    private List<String> mListTruyenTranh;


    public static Intent newIntent(Context context, String linkChap){
        Intent intent = new Intent(context, HienThiTruyenActivity.class);
        intent.putExtra(LINK_CHAP, linkChap);
        return intent;
    }

    public String getExtraLinkChap(){
        if(getIntent() != null){
            return getIntent().getStringExtra(LINK_CHAP);
        }
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_truyen);
        mListTruyenTranh = new ArrayList<>();
        mRecyclerViewHienThiTruyen = (RecyclerView) findViewById(R.id.recyclerViewHienThiTruyen);
        mRecyclerViewHienThiTruyen.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewHienThiTruyen.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewHienThiTruyen.setHasFixedSize(true);
        new GetTruyen().execute(getExtraLinkChap());
    }

    public class GetTruyen extends AsyncTask<String, Void, List<String>>{

        @Override
        protected List<String> doInBackground(String... params) {
            try {
                List<String> listTruyenTranh = new ArrayList<>();
                Document doc = Jsoup.connect(params[0]).get();
                Elements elements = doc.select("div[class=each-page] img");
                for(Element element : elements){
                    String linkHinh = element.attr("src");
                    listTruyenTranh.add(linkHinh);
                }
                return listTruyenTranh;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            if(!mListTruyenTranh.isEmpty()){
                mListTruyenTranh.clear();
            }
            if(strings != null){
                mListTruyenTranh = strings;
                mAdapterHienThiTruyen = new AdapterHienThiTruyen(HienThiTruyenActivity.this, mListTruyenTranh);
                mRecyclerViewHienThiTruyen.setAdapter(mAdapterHienThiTruyen);
                mAdapterHienThiTruyen.notifyDataSetChanged();
            }
        }
    }
}
