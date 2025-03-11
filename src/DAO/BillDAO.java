/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.BillDTO;
import DTO.BillDetailsDTO;
import Util.connectDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BillDAO {
    public boolean themHoaDon(BillDTO billDTO){
        boolean kq= false;
        try{
            Connection conn = connectDB.getConnection();
            String sql = "insert into bill (userCreated,dayCreated,totalPay) values(?,?,?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, billDTO.getUser());
            pre.setString(2,billDTO.getDayCreated());
            pre.setString(3, billDTO.getTotalPay());
            kq = pre.executeUpdate() > 0;
            conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return kq;
    }
    public BillDTO layBillCuoi(){
        BillDTO bill= null;
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from bill order by idbill desc limit 1";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs= pre.executeQuery();
            if(rs.next()){
                bill=new BillDTO(rs.getInt("idbill"),rs.getInt("userCreated"),rs.getString("dayCreated"),rs.getString("totalPay"));
            }
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return bill;
    }
    public ArrayList<BillDTO> layDsHd(){
        ArrayList<BillDTO> listBill = new ArrayList<>();
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from bill";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs= pre.executeQuery();
            while(rs.next()){
                BillDTO bill=new BillDTO(rs.getInt("idbill"),rs.getInt("userCreated"),rs.getString("dayCreated"),rs.getString("totalPay"));
                listBill.add(bill);
            }
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return listBill;
    }
    public ArrayList<BillDetailsDTO> layCthd(int id){
        ArrayList<BillDetailsDTO> listBilld= new ArrayList<>();
        try {
            Connection conn = connectDB.getConnection();
            String sql = "select * from billdetails where idBill = ?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet rs= pre.executeQuery();
            while(rs.next()){
                BillDetailsDTO billd=new BillDetailsDTO(rs.getInt("idbilldetails"),rs.getInt("idBill"),rs.getInt("idProduct"),rs.getString("price"),rs.getInt("quantity"),rs.getString("totalPay"),rs.getInt("idGrnDetails"));
                listBilld.add(billd);
            }
        } catch (Exception e) {
            System.out.println("Message: " + e.getMessage());
        }
        return listBilld;
    }
}
