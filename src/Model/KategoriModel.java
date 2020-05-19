package Model;

import DAO.Buku;
import DAO.Kategori;
import Koneksi.Koneksi;
import Objcet.ObjectClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;



public class KategoriModel  extends AbstractTableModel{

    ArrayList<Kategori> Kategori_list = new ArrayList<Kategori>();
    int inc;

    public KategoriModel() {
        this.inc = loadInc();
    }

    private final String TAB[] = {"ID Kategori", "Nama Kategori"};

    public ArrayList<Kategori> view() {
        return Kategori_list;
    }

    public Kategori getModelKategori(int index) {
        return Kategori_list.get(index);
    }

    public void insertObj(Integer inc, String nama_kategori) {

        Kategori_list.add(new Kategori(inc, nama_kategori));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void insertObj2(String nama_kategori) {
        System.out.println("" + this.inc);
        this.inc = this.inc + 1;
        System.out.println("" + this.inc);
        Kategori_list.add(new Kategori(inc, nama_kategori));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public boolean insert( String nama_kategori) {
        String sql = "INSERT INTO kategori values (null, '" + nama_kategori + "')";
        try {
            PreparedStatement statement = Koneksi.getConnection().prepareStatement(sql);

            int row = statement.executeUpdate();

            if (row > 0) {
                return true;
            }
            statement.close();
        } catch (Exception ex) {
            Logger.getLogger(KategoriModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void update(Kategori kat, int index) {
        Kategori_list.set(index, kat);
        fireTableRowsUpdated(index, index);
    }

    @Override
    public int getRowCount() {
        return Kategori_list.size();
    }

    @Override
    public int getColumnCount() {
        return TAB.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Kategori_list.get(rowIndex).getId_kategori();
            case 1:
                return Kategori_list.get(rowIndex).getNama_kategori();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return TAB[column];
    }

    public int loadInc() {

        String sql = "Select * from kategori";
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
            Logger.getLogger(KategoriModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inc2;
    }

    public ArrayList<Kategori> getAllUser() {
        System.out.println("" + inc);

        String sql = "Select * from kategori";

        try {
            if (Koneksi.getConnection() == null) {
                return null;
            } else {
                PreparedStatement statement = Koneksi.getConnection().prepareStatement(sql);

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    ObjectClass.kategoriMod.insertObj(
                            rs.getInt(1),
                            rs.getString(2));
                }

                statement.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(KategoriModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Kategori_list;
    }


}
