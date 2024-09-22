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
            char cc;
            do {
                System.out.println("\t Menu Thong tin dan cu");
                System.out.println("1. Them");
                System.out.println("2. Sua");
                System.out.println("3. Xoa");
                System.out.println("An phim khac: Thoat");
                System.out.print("Lua chon cua ban la: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice != 1 && choice != 2 && choice != 3) {
                    break;
                }
                if (choice == 1) {
                    // Thêm dữ liệu vào bảng ThongTinDanCu
                    String sql = "INSERT INTO ThongTinDanCu (ID, Name, Age, Address) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Nhập Tên: ");
                        String Name = scanner.nextLine();
                        System.out.print("Nhập Tuổi: ");
                        int Age = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Nhập Địa Chỉ: ");
                        String Address = scanner.nextLine();
                        pstmt.setInt(1, id); // Set ID
                        pstmt.setString(2, Name);
                        pstmt.setInt(3, Age);
                        pstmt.setString(4, Address);
                        pstmt.executeUpdate();
                    }
                }

                if (choice == 2) {
                    // Sửa dữ liệu trong bảng ThongTinCu
                    String sql = "UPDATE ThongTinDanCu SET Name = ?, Age = ?, Address = ? WHERE ID = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập ID cần sửa: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Nhập Name mới: ");
                        String Name = scanner.nextLine();
                        System.out.print("Nhập Tuổi: ");
                        int Age = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Nhập Địa Chỉ: ");
                        String Address = scanner.nextLine();
                        pstmt.setString(1, Name);
                        pstmt.setInt(2, Age);
                        pstmt.setString(3, Address);
                        pstmt.setInt(4, id);
                        pstmt.executeUpdate();
                    }
                }

                if (choice == 3) {
                    // Xóa dữ liệu trong bảng ThongTinCu
                    String sql = "DELETE FROM ThongTinDanCu WHERE ID = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập ID cần xóa: ");
                        pstmt.setInt(1, scanner.nextInt());
                        pstmt.executeUpdate();
                    }
                }

                System.out.print("Ban co muon tiep tuc chuong trinh lua chon (y/n): ");
                cc = scanner.next(".").charAt(0);
            } while (cc == 'y' || cc == 'Y');
            
            try (Statement stmt = con.createStatement()) {
                String sql = "SELECT * FROM ThongTinDanCu";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("ThongTinDanCu:");
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String Name = rs.getString("Name");
                    int Age = rs.getInt("Age");
                    String Address = rs.getString("Address");
                    System.out.println("ID: " + id + "| Name: " + Name + "| Age: " + Age + "| Address: " + Address);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
