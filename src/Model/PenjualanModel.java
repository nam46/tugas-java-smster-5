/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.Penjualan;
import Objcet.ObjectClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import Koneksi.Koneksi;


public class PenjualanModel extends AbstractTableModel {

    ArrayList<Penjualan> Penjualan_list = new ArrayList<Penjualan>();
    int inc;

    public PenjualanModel() {
        this.inc = loadInc();
    }

    private final String TAB[] = {"ID Item","Jumlah Barang", "Bayar"};

    public ArrayList<Penjualan> view() {
        return Penjualan_list;
    }

    public Penjualan getModelPenjualan(int index) {
        return Penjualan_list.get(index);
    }

    public void insertObj(Integer inc, String jumlah, String bayar) {

        Penjualan_list.add(new Penjualan(inc, jumlah,bayar));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void insertObj2( String jumlah, String bayar) {
        System.out.println("" + this.inc);
        this.inc = this.inc + 1;
        System.out.println("" + this.inc);
        Penjualan_list.add(new Penjualan(inc, jumlah, bayar));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public boolean insert( String jumlah_barang, String bayar) {
        String sql = "INSERT INTO buku values (null,'"+jumlah_barang+"', '" + bayar + "')";
        try {
            PreparedStatement statement = Koneksi.getConnection().prepareStatement(sql);

            int row = statement.executeUpdate();

            if (row > 0) {
                return true;
            }
            statement.close();
        } catch (Exception ex) {
            Logger.getLogger(BukuModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void update(Penjualan buk, int index) {
        Penjualan_list.set(index, buk);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        Penjualan_list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public int getRowCount() {
        return Penjualan_list.size();
    }

    @Override
    public int getColumnCount() {
        return TAB.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Penjualan_list.get(rowIndex).getId_penjualan();
            case 1:
                return Penjualan_list.get(rowIndex).getJumlah_bayar();
            case 2 :
                return Penjualan_list.get(rowIndex).getJumlah_buku();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return TAB[column];
    }

    public int loadInc() {

        String sql = "Select * from penjualan";
        int inc2 = 0;
        try {

            PreparedStatement statement = Koneksi.getConnection().prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                inc2 = rs.getInt(1);
                System.out.println("" + inc2);
            }

            statement.close();

        } catch (Exception ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inc2;
    }

    public List<Penjualan> getAllUser() {
        System.out.println("" + inc);

        String sql = "Select * from penjualan";

        try {
            if (Koneksi.getConnection() == null) {
                return null;
            } else {
                PreparedStatement statement = Koneksi.getConnection().prepareStatement(sql);

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    ObjectClass.pejualanMod.insertObj(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3));
                }

                statement.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Penjualan_list;
    }

    public Penjualan Search(String search) {
        Penjualan buk = new Penjualan();
        for (Penjualan u : Penjualan_list) {
            if (u.getJumlah_buku().equals(search)) {
                buk = u;
            }
        }
        return buk;
    }
}