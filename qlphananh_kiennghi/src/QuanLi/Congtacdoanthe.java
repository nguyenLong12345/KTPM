/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QuanLi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author long2
 */
public class Congtacdoanthe {
    public static void main(String[] args) {   
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:sqlserver://localhost:1433;databaseName=kiennghi_phananh;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "12345";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            char cc;
            do {
                System.out.println("\t Menu Cong tac doan the");
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
                    // Thêm dữ liệu vào bảng CongTacDoanThe
                    String sql = "INSERT INTO CongTacDoanThe (NguoiToChuc, EventName, EventDate) VALUES (?, ?, ?)";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập Ten Nguoi to chuc: ");
                        String NguoiToChuc = scanner.nextLine();
                        System.out.print("Nhập Ten Su Kien: ");
                        String EventName = scanner.nextLine();
                        System.out.print("Nhập Ngay To chuc Su Kien: ");
                        String EventDate = scanner.nextLine();
                        
                        pstmt.setString(1, NguoiToChuc);                       
                        pstmt.setString(2, EventName);
                        pstmt.setString(3, EventDate);
                        pstmt.executeUpdate();
                    }
                }

                if (choice == 2) {
                    // Sửa dữ liệu trong bảng CongTacDoanThe
                    String sql = "UPDATE CongTacDoanThe SET NguoiToChuc = ?, EventName = ?, EventDate = ? WHERE NguoiToChuc = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập NguoiToChuc cần sửa: ");
                        String ten = scanner.nextLine();
                        System.out.print("Nhập NguoiToChuc mới: ");
                        String NguoiToChuc = scanner.nextLine();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Nhập ten su kien mới: ");
                        String EventName = scanner.nextLine();
                        System.out.print("Nhập ngay su kien mới: ");
                        String EventDate = scanner.nextLine();
                        
                        pstmt.setString(1, NguoiToChuc);
                        pstmt.setString(2, EventName);
                        pstmt.setString(2, EventDate);
                        pstmt.setString(3, ten);
                        pstmt.executeUpdate();
                    }
                }

                if (choice == 3) {
                    // Xóa dữ liệu trong bảng CongTacDoanThe
                    String sql = "DELETE FROM CongTacDoanThe WHERE NguoiToChuc = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập NguoiToChuc cần xóa: ");
                        pstmt.setString(1, scanner.nextLine());
                        pstmt.executeUpdate();
                    }
                }

                System.out.print("Ban co muon tiep tuc chuong trinh lua chon (y/n): ");
                cc = scanner.next(".").charAt(0);
            } while (cc == 'y' || cc == 'Y');
            
            try (Statement stmt = con.createStatement()) {
                String sql = "SELECT * FROM CongTacDoanThe";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("CongTacDoanThe:");
                while (rs.next()) {
                    String NguoiToChuc = rs.getString("NguoiToChuc");
                    String EventName = rs.getString("EventName");
                    String EventDate = rs.getString("EventDate");
                    System.out.println("NguoiToChuc: " + NguoiToChuc + "| EventName: " + EventName + ", EventDate: " + EventDate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
