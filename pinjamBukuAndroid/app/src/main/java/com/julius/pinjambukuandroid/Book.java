package com.julius.pinjambukuandroid;

public class Book {
    private String judul , tahun,author,penerbit,halaman;

    public int getIdbuku() {
        return idbuku;
    }

    public void setIdbuku(int idbuku) {
        this.idbuku = idbuku;
    }

    private  int idbuku, status;

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getHalaman() {
        return halaman;
    }

    public void setHalaman(String halaman) {
        this.halaman = halaman;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Book(int idbuku, String judul, String tahun, String author,
                String penerbit, String halaman, int status){
        this.idbuku=idbuku;
    this.judul=judul;
    this.author=author;
    this.penerbit=penerbit;
    this.tahun=tahun;
    this.halaman=halaman;
    this.status=status;
}
}
