package Model;


import DAO.Buku;
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


public class BukuModel extends AbstractTableModel {

    ArrayList<Buku> Buku_list = new ArrayList<Buku>();
    int inc;

    public BukuModel() {
        this.inc = loadInc();
    }

    private final String TAB[] = {"ID Buku", "Judul", "Pengarang", "Harga_satuan", "Stok"};

    public ArrayList<Buku> view() {
        return Buku_list;
    }

    public Buku getModelBuku(int index) {
        return Buku_list.get(index);
    }

    public void insertObj(Integer inc,Integer id_kategori, String judul, String pengarang, String harga_satuan, String stok) {

        Buku_list.add(new Buku(inc, id_kategori,  judul, pengarang, harga_satuan, stok));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void insertObj2(Integer id_kategori, String judul, String pengarang, String harga_satuan, String stok) {
        System.out.println("" + this.inc);
        this.inc = this.inc + 1;
        System.out.println("" + this.inc);
        Buku_list.add(new Buku(inc, id_kategori, judul, pengarang, harga_satuan, stok));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public boolean insert(  String judul, String pengarang, String harga_satuan, String stok) {
        String sql = "INSERT INTO buku values (null, '" + judul + "', '" + pengarang + "', '" + harga_satuan + "', '" + stok + "')";
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

    public void update(Buku buk, int index) {
        Buku_list.set(index, buk);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        Buku_list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public int getRowCount() {
        return Buku_list.size();
    }

    @Override
    public int getColumnCount() {
        return TAB.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Buku_list.get(rowIndex).getId_buku();
            case 1:
                return Buku_list.get(rowIndex).getJudul();
            case 2:
                return Buku_list.get(rowIndex).getPengarang();
            case 3:
                return Buku_list.get(rowIndex).getHarga_satuan();
            case 4:
                return Buku_list.get(rowIndex).getStok();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return TAB[column];
    }

    public int loadInc() {

        String sql = "Select * from buku";
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

    public List<Buku> getAllUser() {
        System.out.println("" + inc);

        String sql = "Select * from buku";

        try {
            if (Koneksi.getConnection() == null) {
                return null;
            } else {
                PreparedStatement statement = Koneksi.getConnection().prepareStatement(sql);

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    ObjectClass.bukuMod.insertObj(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6));
                }

                statement.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Buku_list;
    }

    public Buku Search(String search) {
        Buku buk = new Buku();
        for (Buku u : Buku_list) {
            if (u.getJudul().equals(search)) {
                buk = u;
            }
        }
        return buk;
    }

    public void insert(String text, String text0, String text1, String text2, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}