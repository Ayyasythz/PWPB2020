package com.example.ulanganpwpbsqlite;

public class DataBuku {
    int id;
    String nama_buku;
    String pengarang;
    String penerbit;

    public DataBuku( String nama_buku, String pengarang, String penerbit) {
        this.nama_buku = nama_buku;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
    }

    public DataBuku(int id, String nama_buku, String pengarang, String penerbit){
        this.id = id;
        this.nama_buku = nama_buku;
        this.pengarang = pengarang;
        this.penerbit = penerbit;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_buku() {
        return nama_buku;
    }

    public void setNama_buku(String nama_buku) {
        this.nama_buku = nama_buku;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
}
