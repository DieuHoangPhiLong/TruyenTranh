package com.philong.truyentranhhay.models;

/**
 * Created by Long on 6/9/2017.
 */

public class Chuong {

    private String mTenChuong;
    private String mNgayDang;
    private String mLinkChuong;

    public Chuong(String tenChuong, String ngayDang, String linkChuong) {
        mTenChuong = tenChuong;
        mNgayDang = ngayDang;
        mLinkChuong = linkChuong;
    }

    public String getTenChuong() {
        return mTenChuong;
    }

    public void setTenChuong(String tenChuong) {
        mTenChuong = tenChuong;
    }

    public String getNgayDang() {
        return mNgayDang;
    }

    public void setNgayDang(String ngayDang) {
        mNgayDang = ngayDang;
    }

    public String getLinkChuong() {
        return mLinkChuong;
    }

    public void setLinkChuong(String linkChuong) {
        mLinkChuong = linkChuong;
    }
}
