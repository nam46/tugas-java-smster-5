/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

public class Item {

  private Integer id_item;
  private String tanggal;
    

    public Item(String tanggal) {
        
        this.tanggal = tanggal;
    }

    public Item() {

    }

    public Item(Integer id_item,String tanggal ) {
        this.id_item = id_item;
        this.tanggal = tanggal;
    }

    

    public Integer getId_item() {
        return id_item;
    }

    public void setId_item(Integer id_item) {
        this.id_item = id_item;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String Tanggal) {
        this.tanggal = tanggal;
    }

    
}

   