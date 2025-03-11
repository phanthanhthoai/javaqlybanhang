/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class connectDB {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            // Kiểm tra nếu connection tồn tại và còn mở thì trả về
            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            // Nạp driver (chỉ cần thực hiện một lần trong suốt vòng đời ứng dụng)
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/javaswing?"
                    + "useSSL=false&"
                    + "allowPublicKeyRetrieval=true&"
                    + "autoReconnect=true&"
                    + "serverTimezone=UTC";

            connection = DriverManager.getConnection(url, "root", "123456");

            System.out.println("Kết nối MySQL thành công!");

        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi: MySQL Driver không tìm thấy! " + e.getMessage());
        } catch (SQLException sqlException) {
            System.err.println("Lỗi kết nối MySQL: " + sqlException.getMessage());
            connection = null;  // Đảm bảo connection không trỏ vào một kết nối lỗi
        }
        return connection;
    }
//    public static Connection getConnection() {
//        if (connection != null) {
//            return connection;
//        }
//
//        try {
//            String url = "jdbc:mysql://localhost:3306/javaswing?"
//                    + "useSSL=false&" // Tắt SSL
//                    + "allowPublicKeyRetrieval=true&"// Bỏ qua lỗi xác thực public key
//                    + "autoReconnect=true&" // Tự động kết nối lại nếu mất kết nối
//                    + "serverTimezone=UTC";          // Xác định múi giờ
//
//            connection = DriverManager.getConnection(url, "root", "123456");
//        } catch (SQLException sqlException) {
//            System.out.println("Error when connect database: " + sqlException.getMessage());
//        }
//        return connection;
//    }
}
