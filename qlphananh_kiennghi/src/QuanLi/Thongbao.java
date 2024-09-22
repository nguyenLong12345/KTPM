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
public class Thongbao {
    public static void main(String[] args) {   
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:sqlserver://localhost:1433;databaseName=kiennghi_phananh;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "12345";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            char cc;
            do {
                System.out.println("\t Menu Thong Bao");
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
                    // Thêm dữ liệu vào bảng ThongBao
                    String sql = "INSERT INTO ThongBao (NguoiThongBao, NoiDung) VALUES (?, ?)";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập Ten cua NguoiThongBao: ");
                        String NguoiThongBao = scanner.nextLine();  
                        System.out.print("Nhập Noi Dung: ");
                        String NoiDung = scanner.nextLine();
                        
                        pstmt.setString(1, NguoiThongBao);                       
                        pstmt.setString(2, NoiDung);
                        pstmt.executeUpdate();
                    }
                }

                if (choice == 2) {
                    // Sửa dữ liệu trong bảng ThongBao
                    String sql = "UPDATE ThongBao SET NguoiThongBao = ?, NoiDung = ? WHERE NguoiThongBao = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập ten NguoiThongBao cần sửa: ");
                        String Ten = scanner.nextLine();
                        System.out.print("Nhập ten mới: ");
                        String TenMoi = scanner.nextLine();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Nhập Noi Dung mới: ");
                        String NoiDung = scanner.nextLine();
                        
                        pstmt.setString(1, TenMoi);
                        pstmt.setString(2, NoiDung);
                        pstmt.setString(3, Ten);
                        pstmt.executeUpdate();
                    }
                }

                if (choice == 3) {
                    // Xóa dữ liệu trong bảng ThongBao
                    String sql = "DELETE FROM ThongBao WHERE NguoiThongBao = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập Ten NguoiThongBao cần xóa: ");
                        pstmt.setString(1, scanner.nextLine());
                        pstmt.executeUpdate();
                    }
                }

                System.out.print("Ban co muon tiep tuc chuong trinh lua chon (y/n): ");
                cc = scanner.next(".").charAt(0);
            } while (cc == 'y' || cc == 'Y');
            
            try (Statement stmt = con.createStatement()) {
                String sql = "SELECT * FROM ThongBao";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("ThongBao:");
                while (rs.next()) {
                    String NguoiThongBao = rs.getString("NguoiThongBao");
                    String NoiDung = rs.getString("NoiDung");
                    String NgayGui = rs.getDate("NgayGui").toString();
                    System.out.println("NguoiThongBao: " + NguoiThongBao + "| NoiDung: " + NoiDung + "| NgayGui: " + NgayGui);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
