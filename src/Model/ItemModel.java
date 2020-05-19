package Model;

import DAO.Item;
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


public class ItemModel extends AbstractTableModel {

    ArrayList<Item> Item_list = new ArrayList<Item>();
    int inc;

    public ItemModel() {
        this.inc = loadInc();
    }

    private final String TAB[] = {"ID Item", "Tanggal"};

    public ArrayList<Item> view() {
        return Item_list;
    }

    public Item getModelBuku(int index) {
        return Item_list.get(index);
    }

    public void insertObj(Integer inc, String tanggal) {

        Item_list.add(new Item(inc, tanggal));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void insertObj2( String tanggal) {
        System.out.println("" + this.inc);
        this.inc = this.inc + 1;
        System.out.println("" + this.inc);
        Item_list.add(new Item(inc, tanggal));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public boolean insert(  String tanggal) {
        String sql = "INSERT INTO buku values (null,'" + tanggal + "')";
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

    public void update(Item buk, int index) {
        Item_list.set(index, buk);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        Item_list.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public int getRowCount() {
        return Item_list.size();
    }

    @Override
    public int getColumnCount() {
        return TAB.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Item_list.get(rowIndex).getId_item();
            case 1:
                return Item_list.get(rowIndex).getTanggal();
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

    public List<Item> getAllUser() {
        System.out.println("" + inc);

        String sql = "Select * from penjualan";

        try {
            if (Koneksi.getConnection() == null) {
                return null;
            } else {
                PreparedStatement statement = Koneksi.getConnection().prepareStatement(sql);

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    ObjectClass.itemMod.insertObj(
                            rs.getInt(1),
                            rs.getString(2));
                }

                statement.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Item_list;
    }

    public Item Search(String search) {
        Item buk = new Item();
        for (Item u : Item_list) {
            if (u.getTanggal().equals(search)) {
                buk = u;
            }
        }
        return buk;
    }
}