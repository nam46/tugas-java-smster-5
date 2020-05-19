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
public class User {

    private Integer id_user;
    private String nama;
    private String username;
    private String password;
    private String status;

    public User(String nama) {
        this.nama = nama;
    }

    public User() {

    }

    public User(Integer id_user, String nama, String username, String password, String status) {
        this.id_user = id_user;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Integer getId_User() {
        return id_user;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public void setId_User(Integer id_user) {
        this.id_user = id_user;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }
  
    
}
