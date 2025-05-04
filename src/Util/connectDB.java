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

            // Nạp driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Cấu hình URL kết nối
            String url = "jdbc:mysql://localhost:3306/qlch?"
                    + "useSSL=false&"
                    + "allowPublicKeyRetrieval=true&"
                    + "autoReconnect=true&"
                    + "serverTimezone=UTC";

            // Thông tin đăng nhập
            String username = "root"; // Username mặc định của XAMPP
            String password = "";     // Password mặc định của XAMPP (rỗng)

            // Kết nối đến database
            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Kết nối MySQL thành công!");

        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi: MySQL Driver không tìm thấy! " + e.getMessage());
            connection = null;
        } catch (SQLException sqlException) {
            System.err.println("Lỗi kết nối MySQL: " + sqlException.getMessage());
            connection = null; // Đảm bảo connection không trỏ vào một kết nối lỗi
        }
        return connection;
    }

    // Phương thức để đóng kết nối (tùy chọn, nếu cần)
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Đóng kết nối MySQL thành công!");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
        }
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
