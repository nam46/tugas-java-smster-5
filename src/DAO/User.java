/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

public class User {
    private Integer id_user;
    private String nama_user;
    private String username;
    private String password;
    private String status;
    
 public User(String nama_user) {
        this.nama_user = nama_user;
    }

    public User() {

    }
    
    public User(Integer id_user, String nama_user, String username, String password, String status) {
        this.id_user = id_user;
        this.nama_user = nama_user;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
