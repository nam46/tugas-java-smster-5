
package DAO;


public class Penjualan {
  private Integer id_penjualan;
  private String jumlah_bayar;
  private String jumlah_buku;
  
    public Penjualan(String jumlah_buku) {
        
        this.jumlah_buku = jumlah_buku;
    }

    public Penjualan() {

    }


    public Penjualan(Integer id_penjualan,String jumlah_bayar,String jumlah_buku) {
       this.id_penjualan=id_penjualan;
       this.jumlah_buku = jumlah_buku;
       this.jumlah_bayar = jumlah_bayar;
    }

    public Integer getId_penjualan() {
        return id_penjualan;
    }

    public void setId_penjualan(Integer id_penjualan) {
        this.id_penjualan = id_penjualan;
    }

    
    public String getJumlah_bayar() {
        return jumlah_bayar;
    }

    public void setJumlah_bayar(String jumlah_bayar) {
        this.jumlah_bayar = jumlah_bayar;
    }

    public String getJumlah_buku() {
        return jumlah_buku;
    }

    public void setJumlah_buku(String jumlah_buku) {
        this.jumlah_buku = jumlah_buku;
    }


}
