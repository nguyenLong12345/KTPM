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

        String url = "jdbc:sqlserver://localhost:1433;databaseName=kiennghi_phananh;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "12345";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            
            // Thêm dữ liệu vào bảng ThongTinDanCu
            String sql = "INSERT INTO ThongTinDanCu (Name, Age, Address) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {                            
                System.out.println("Nhập Tên:");
                String Name = scanner.nextLine();
                System.out.println("Nhập Tuổi:");
                int Age = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Nhập Địa Chỉ:");
                String Address = scanner.nextLine();
                pstmt.setString(1, Name);
                pstmt.setInt(2, Age);
                pstmt.setString(3, Address);
                pstmt.executeUpdate();
            }

            // Sửa dữ liệu trong bảng ThongTinCu
            sql = "UPDATE ThongTinDanCu SET Name = ?, Age = ?, Address = ? WHERE ID = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                System.out.println("Nhập ID cần sửa:");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Nhập Name mới:");
                String Name = scanner.nextLine();
                System.out.println("Nhập Tuổi:");
                int Age = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.println("Nhập Địa Chỉ:");
                String Address = scanner.nextLine();
                pstmt.setString(1, Name);
                pstmt.setInt(2, Age);
                pstmt.setString(3, Address);
                pstmt.setInt(4, id);
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
                    String Name = rs.getString("Name");
                    int Age = rs.getInt("Age");
                    String Address = rs.getString("Address");
                    System.out.println("ID: " + id + ", Name: " + Name + ", Age: " + Age + ", Address: " + Address);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
