package com.philong.truyentranhhay.models;

import java.util.List;

/**
 * Created by Long on 6/9/2017.
 */

public class Truyen {

    private String mTenTruyen;
    private String mDiemSo;
    private String mLuotXem;
    private String mTheLoai;
    private String mTacGia;
    private String mNguonTruyen;
    private String mTrangThai;
    private String mNoiDung;
    private String mLinkHinh;
    private String mLinkTruyen;
    private String mChiTiet;
    private List<Chuong> mChuong;

    public Truyen(String tenTruyen, String diemSo, String luotXem, String theLoai, String tacGia, String nguonTruyen, String trangThai, String noiDung, String linkHinh, String linkTruyen, String chiTiet, List<Chuong> chuong) {
        mTenTruyen = tenTruyen;
        mDiemSo = diemSo;
        mLuotXem = luotXem;
        mTheLoai = theLoai;
        mTacGia = tacGia;
        mNguonTruyen = nguonTruyen;
        mTrangThai = trangThai;
        mNoiDung = noiDung;
        mLinkHinh = linkHinh;
        mLinkTruyen = linkTruyen;
        mChiTiet = chiTiet;
        mChuong = chuong;
    }

    public Truyen(String tenTruyen, String linkHinh, String linkTruyen) {
        mTenTruyen = tenTruyen;
        mLinkHinh = linkHinh;
        mLinkTruyen = linkTruyen;
    }

    public Truyen(String tenTruyen, String theLoai, String linkHinh, String linkTruyen) {
        mTenTruyen = tenTruyen;
        mTheLoai = theLoai;
        mLinkHinh = linkHinh;
        mLinkTruyen = linkTruyen;
    }

    public Truyen(String tenTruyen,String linkHinh, String diemSo, String noiDung, String chiTiet, List<Chuong> chuong) {
        mTenTruyen = tenTruyen;
        mDiemSo = diemSo;
        mNoiDung = noiDung;
        mChiTiet = chiTiet;
        mChuong = chuong;
        mLinkHinh = linkHinh;
    }

    public String getTenTruyen() {
        return mTenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        mTenTruyen = tenTruyen;
    }

    public String getDiemSo() {
        return mDiemSo;
    }

    public void setDiemSo(String diemSo) {
        mDiemSo = diemSo;
    }

    public String getLuotXem() {
        return mLuotXem;
    }

    public void setLuotXem(String luotXem) {
        mLuotXem = luotXem;
    }

    public String getTheLoai() {
        return mTheLoai;
    }

    public void setTheLoai(String theLoai) {
        mTheLoai = theLoai;
    }

    public String getTacGia() {
        return mTacGia;
    }

    public void setTacGia(String tacGia) {
        mTacGia = tacGia;
    }

    public String getNguonTruyen() {
        return mNguonTruyen;
    }

    public void setNguonTruyen(String nguonTruyen) {
        mNguonTruyen = nguonTruyen;
    }

    public String getTrangThai() {
        return mTrangThai;
    }

    public void setTrangThai(String trangThai) {
        mTrangThai = trangThai;
    }

    public String getNoiDung() {
        return mNoiDung;
    }

    public void setNoiDung(String noiDung) {
        mNoiDung = noiDung;
    }

    public String getLinkHinh() {
        return mLinkHinh;
    }

    public void setLinkHinh(String linkHinh) {
        mLinkHinh = linkHinh;
    }

    public String getLinkTruyen() {
        return mLinkTruyen;
    }

    public void setLinkTruyen(String linkTruyen) {
        mLinkTruyen = linkTruyen;
    }

    public String getChiTiet() {
        return mChiTiet;
    }

    public void setChiTiet(String chiTiet) {
        mChiTiet = chiTiet;
    }

    public List<Chuong> getChuong() {
        return mChuong;
    }

    public void setChuong(List<Chuong> chuong) {
        mChuong = chuong;
    }
}
