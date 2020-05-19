/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author HAMBA NOTEBOOK
 */
public class Member {
    private Integer id_member;
     private String nama;
    private String alamat;
    private String no_telp;
        
    public Member(String nama) {
        this.nama = nama;
    }

    public Member() {

    }

    public Member(Integer id_member, String nama,String alamat, String no_telp) {
        this.id_member = id_member;
        this.nama = nama;
        this.alamat = alamat;
        this.no_telp = no_telp;
        }

    public Integer getId_member() {
        return id_member;
    }

    public void setId_member(Integer id_member) {
        this.id_member = id_member;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }
    
}

