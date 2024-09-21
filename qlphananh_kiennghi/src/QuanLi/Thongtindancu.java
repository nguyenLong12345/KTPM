/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 *
 * @author long2
 */
public class Thongtindancu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:sqlserver://localhost:1433;databaseName=kienghi_phananh;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "12345";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            
            // Thêm dữ liệu vào bảng ThongTinDanCu
            String sql = "INSERT INTO ThongTinDanCu (ID, NoiDung, NgayCapNhat) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                System.out.println("Nhập ID:");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Nhập Nội Dung:");
                String noiDung = scanner.nextLine();
                System.out.println("Nhập Ngày Cập Nhật (YYYY-MM-DD):");
                String ngayCapNhat = scanner.nextLine();
                pstmt.setInt(1, id);
                pstmt.setString(2, noiDung);
                pstmt.setDate(3, java.sql.Date.valueOf(ngayCapNhat));
                pstmt.executeUpdate();
            }

            // Sửa dữ liệu trong bảng ThongTinCu
            sql = "UPDATE ThongTinDanCu SET NoiDung = ?, NgayCapNhat = ? WHERE ID = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                System.out.println("Nhập ID cần sửa:");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Nhập Nội Dung mới:");
                String noiDung = scanner.nextLine();
                System.out.println("Nhập Ngày Cập Nhật mới (YYYY-MM-DD):");
                String ngayCapNhat = scanner.nextLine();
                pstmt.setString(1, noiDung);
                pstmt.setDate(2, java.sql.Date.valueOf(ngayCapNhat));
                pstmt.setInt(3, id);
                pstmt.executeUpdate();
            }

            // Xóa dữ liệu trong bảng ThongTinCu
            sql = "DELETE FROM ThongTinDanCu WHERE ID = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                System.out.println("Nhập ID cần xóa:");
                pstmt.setInt(1, scanner.nextInt());
                pstmt.executeUpdate();
            }
            
            try (Statement stmt = con.createStatement()) {
                sql = "SELECT * FROM ThongTinDanCu";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("ThongTinDanCu:");
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String noiDung = rs.getString("NoiDung");
                    String ngayCapNhat = rs.getDate("NgayCapNhat").toString();
                    System.out.println("ID: " + id + ", NoiDung: " + noiDung + ", NgayCapNhat: " + ngayCapNhat);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
