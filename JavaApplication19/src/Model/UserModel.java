/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.User;
import Objects.ObjectClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import Koneksi.Koneksi;

/**
 *
 * @author KelompokPro
 */
public class UserModel extends AbstractTableModel {

    ArrayList<User> Users_List = new ArrayList<User>();
    int inc;

    public UserModel() {
        this.inc = loadInc();
    }

    private final String TAB[] = {"ID User", "Nama", "Username", "Password", "Status"};

    public ArrayList<User> view() {
        return Users_List;
    }

    public User getModelUser(int index) {
        return Users_List.get(index);
    }

    public void insertObj(Integer inc, String nama, String username, String password, String status) {

        Users_List.add(new User(inc, nama, username, password, status));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void insertObj2(String nama, String username, String password, String status) {
        System.out.println("" + this.inc);
        this.inc = this.inc + 1;
        System.out.println("" + this.inc);
        Users_List.add(new User(inc, nama, username, password, status));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public boolean insert(String nama, String username, String password, String status) {
        String sql = "INSERT INTO user values (null, '" + nama + "', '" + username + "', '" + password + "', '" + status + "')";
        try {
            PreparedStatement statement = Koneksi.getConnection().prepareStatement(sql);

            int row = statement.executeUpdate();

            if (row > 0) {
                return true;
            }
            statement.close();
        } catch (Exception ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void update(User usr, int index) {
        Users_List.set(index, usr);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        Users_List.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public int getRowCount() {
        return Users_List.size();
    }

    @Override
    public int getColumnCount() {
        return TAB.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Users_List.get(rowIndex).getId_User();
            case 1:
                return Users_List.get(rowIndex).getNama();
            case 2:
                return Users_List.get(rowIndex).getUsername();
            case 3:
                return Users_List.get(rowIndex).getPassword();
            case 4:
                return Users_List.get(rowIndex).getStatus();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return TAB[column];
    }

    public int loadInc() {

        String sql = "Select * from user";
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

    public List<User> getAllUser() {
        System.out.println("" + inc);

        String sql = "Select * from user";

        try {
            if (Koneksi.getConnection() == null) {
                return null;
            } else {
                PreparedStatement statement = Koneksi.getConnection().prepareStatement(sql);

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    ObjectClass.userMod.insertObj(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5));
                }

                statement.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Users_List;
    }

    public User Search(String search) {
        User usr = new User();
        for (User u : Users_List) {
            if (u.getNama().equals(search)) {
                usr = u;
            }
        }
        return usr;
    }
}
