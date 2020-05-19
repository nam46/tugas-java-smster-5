/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

public class Buku {

private Integer id_buku;
private Integer id_kategori;
private String judul;
private String pengarang;
private String harga_satuan;
private String stok;



    public Buku(String judul) {
        this.judul = judul;
    }

    public Buku() {

    }

    public Buku(Integer id_buku, Integer id_kategori, String judul, String pengarang, String harga_satuan, String stok) {
        this.id_buku = id_buku;
        this.id_kategori = id_kategori;
        this.judul = judul;
        this.pengarang = pengarang;
        this.harga_satuan = harga_satuan;
        this.stok = stok;

    }

    public Integer getId_buku() {
        return id_buku;
    }

    public void setId_buku(Integer id_buku) {
        this.id_buku = id_buku;
    }

    public Integer getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(Integer id_kategori) {
        this.id_kategori = id_kategori;
    }    
    
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(String harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

}
