package com.philong.truyentranhhay;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.philong.truyentranhhay.adapters.AdapterTruyenHot;
import com.philong.truyentranhhay.adapters.AdapterTruyenMoi;
import com.philong.truyentranhhay.models.Truyen;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //URL
    public static final String URL_HOME = "http://truyentranh.net/";

    //Truyen hot
    private RecyclerView mRecyclerViewTruyenHot;
    private AdapterTruyenHot mAdapterTruyenHot;
    private List<Truyen> mListTruyenHot;
    //Truyen moi
    private RecyclerView mRecyclerViewTruyenMoi;
    private AdapterTruyenMoi mAdapterTruyenMoi;
    private List<Truyen> mListTruyenMoi;
    //Toolbar
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(getSupportActionBar() == null){
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(R.string.app_name);
        }
        //Setup truyen hot
        mRecyclerViewTruyenHot = (RecyclerView) findViewById(R.id.recyclerViewTruyenHot);
        mListTruyenHot = new ArrayList<>();
        mRecyclerViewTruyenHot.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewTruyenHot.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewTruyenHot.setHasFixedSize(true);
        mRecyclerViewTruyenHot.setNestedScrollingEnabled(false);
        new GetTruyenHot().execute();

        //Setup truyen moi
        mRecyclerViewTruyenMoi = (RecyclerView) findViewById(R.id.recyclerViewTruyenMoi);
        mListTruyenMoi = new ArrayList<>();
        mRecyclerViewTruyenMoi.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewTruyenMoi.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewTruyenMoi.setHasFixedSize(true);
        mRecyclerViewTruyenMoi.setNestedScrollingEnabled(false);
        new GetTruyenMoi().execute();
    }

    //Get Truyen Hot
    public class GetTruyenHot extends AsyncTask<Void, Void, List<Truyen>>{

        @Override
        protected List<Truyen> doInBackground(Void... params) {
            try {
                List<Truyen> listTruyen = new ArrayList<>();
                Document doc = Jsoup.connect(MainActivity.URL_HOME).get();
                Elements elements = doc.select("div[class=thumbnails]");
                for(Element element : elements){
                    String linkTruyen = element.select("a").attr("href");
                    String tenTruyen = element.select("a").attr("title");
                    String linkHinh = element.select("a").select("img").attr("src");
                    listTruyen.add(new Truyen(tenTruyen, linkHinh, linkTruyen));
                }
                return listTruyen;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Truyen> truyens) {
            super.onPostExecute(truyens);
            if(!mListTruyenHot.isEmpty()){
                mListTruyenHot.clear();
            }
            if(!truyens.isEmpty() && truyens != null){
                mListTruyenHot = truyens;
                mAdapterTruyenHot = new AdapterTruyenHot(MainActivity.this, mListTruyenHot);
                mRecyclerViewTruyenHot.setAdapter(mAdapterTruyenHot);
                mAdapterTruyenHot.notifyDataSetChanged();
            }
        }
    }

    //Get truyen moi
    public class GetTruyenMoi extends AsyncTask<Void, Void, List<Truyen>>{

        @Override
        protected List<Truyen> doInBackground(Void... params) {
            try {
                List<Truyen> listTruyen = new ArrayList<>();
                Document doc = Jsoup.connect(MainActivity.URL_HOME).get();
                Elements elements = doc.select("div[class=media mainpage-manga]");
                for(Element element : elements){
                    String linkTruyen = element.select("div[class=media-left cover-manga]").select("a").attr("href");
                    String linkHinh = element.select("div[class=media-left cover-manga]").select("a").select("img").attr("src");
                    Elements elementsTheLoai = element.select("div[class=media-left cover-manga").select("a").select("span").select("p[class=description]");
                    String theLoai = elementsTheLoai.html();
                    String tenTruyen = element.select("div[class=media-body]").select("a").select("h4[class=manga-newest]").text();
                    listTruyen.add(new Truyen(tenTruyen, theLoai, linkHinh, linkTruyen));
                }
                return listTruyen;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Truyen> truyens) {
            super.onPostExecute(truyens);
            if(!mListTruyenMoi.isEmpty()){
                mListTruyenMoi.clear();
            }
            if(!truyens.isEmpty() && truyens != null){
                mListTruyenMoi = truyens;
                mAdapterTruyenMoi = new AdapterTruyenMoi(MainActivity.this, mListTruyenMoi);
                mRecyclerViewTruyenMoi.setAdapter(mAdapterTruyenMoi);
                mAdapterTruyenMoi.notifyDataSetChanged();
            }
        }
    }
}
