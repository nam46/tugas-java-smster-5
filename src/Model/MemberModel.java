/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.Member;
import Koneksi.Koneksi;
import Objcet.ObjectClass;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;




public class MemberModel extends AbstractTableModel{

    ArrayList<Member> Member_List = new ArrayList<Member>();
    int inc;

    public MemberModel() {
        this.inc = loadInc();
    }

    private final String TAB[] = {"ID Member", "Nama", "Alamat", "No Telp"};

    public ArrayList<Member> view() {
        return Member_List;
    }

    public Member getModelMember(int index) {
        return Member_List.get(index);
    }

    public void insertObj(Integer inc, String nama, String alamat, String no_telp) {

        Member_List.add(new Member(inc, nama, alamat, no_telp));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void insertObj2(String nama, String alamat,String no_telp ) {
        System.out.println("" + this.inc);
        this.inc = this.inc + 1;
        System.out.println("" + this.inc);
        Member_List.add(new Member(inc, nama, alamat, no_telp));
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public boolean insert(String nama, String alamat, String no_telp) {
        String sql = "INSERT INTO member values (null, '" + nama + "', '" + alamat + "')";
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

    public void update(Member mem, int index) {
        Member_List.set(index, mem);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        Member_List.remove(index);
        fireTableRowsDeleted(index, index);
    }

    @Override
    public int getRowCount() {
        return Member_List.size();
    }

    @Override
    public int getColumnCount() {
        return TAB.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Member_List.get(rowIndex).getId_member();
            case 1:
                return Member_List.get(rowIndex).getNama();
            case 2:
                return Member_List.get(rowIndex).getAlamat();
            case 3:
                return Member_List.get(rowIndex).getNo_telp();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return TAB[column];
    }

    public int loadInc() {

        String sql = "Select * from member";
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
            Logger.getLogger(MemberModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inc2;
    }

    public List<Member> getAllMember() {
        System.out.println("" + inc);

        String sql = "Select * from member";

        try {
            if (Koneksi.getConnection() == null) {
                return null;
            } else {
                PreparedStatement statement = Koneksi.getConnection().prepareStatement(sql);

                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    ObjectClass.memberMod.insertObj(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4));
                }

                statement.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Member_List;
    }

    public Member Search(String search) {
        Member mem = new Member();
        for (Member u : Member_List) {
            if (u.getNama().equals(search)) {
                mem= u;
            }
        }
        return mem;
    }
}
