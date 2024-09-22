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
public class Phananhkiennghi {
    public static void main(String[] args) {   
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:sqlserver://localhost:1433;databaseName=kiennghi_phananh;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "12345";
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            char cc;
            do {
                System.out.println("\t Menu Phan anh kien nghi");
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
                    // Thêm dữ liệu vào bảng PhanAnhKienNghi
                    String sql = "INSERT INTO PhanAnhKienNghi (IDNguoiPhanAnh, NoiDung) VALUES (?, ?)";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập ID cua NguoiPhanAnh: ");
                        int IDNguoiPhanAnh = scanner.nextInt();    
                        scanner.nextLine(); // Consume newline
                        System.out.print("Nhập Noi Dung: ");
                        String NoiDung = scanner.nextLine();
                        
                        pstmt.setInt(1, IDNguoiPhanAnh);                       
                        pstmt.setString(2, NoiDung);
                        pstmt.executeUpdate();
                    }
                }

                if (choice == 2) {
                    // Sửa dữ liệu trong bảng PhanAnhKienNghi
                    String sql = "UPDATE PhanAnhKienNghi SET IDNguoiPhanAnh = ?, NoiDung = ? WHERE IDNguoiPhanAnh = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập ID NguoiPhanAnh cần sửa: ");
                        int id = scanner.nextInt();
                        System.out.print("Nhập ID NguoiPhanAnh mới: ");
                        int IDNguoiPhanAnh = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Nhập NoiDung mới: ");
                        String NoiDung = scanner.nextLine();
                        
                        pstmt.setInt(1, IDNguoiPhanAnh);
                        pstmt.setString(2, NoiDung);
                        pstmt.setInt(3, id);
                        pstmt.executeUpdate();
                    }
                }

                if (choice == 3) {
                    // Xóa dữ liệu trong bảng PhanAnhKienNghi
                    String sql = "DELETE FROM PhanAnhKienNghi WHERE IDNguoiPhanAnh = ?";
                    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                        System.out.print("Nhập ID NguoiPhanAnh cần xóa: ");
                        pstmt.setInt(1, scanner.nextInt());
                        pstmt.executeUpdate();
                    }
                }

                System.out.print("Ban co muon tiep tuc chuong trinh lua chon (y/n): ");
                cc = scanner.next(".").charAt(0);
            } while (cc == 'y' || cc == 'Y');
            
            try (Statement stmt = con.createStatement()) {
                String sql = "SELECT * FROM PhanAnhKienNghi";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("PhanAnhKienNghi:");
                while (rs.next()) {
                    int IDNguoiPhanAnh = rs.getInt("IDNguoiPhanAnh");
                    String NoiDung = rs.getString("NoiDung");
                    String NgayPhanAnh = rs.getDate("NgayPhanAnh").toString();
                    System.out.println("IDNguoiPhanAnh: " + IDNguoiPhanAnh + "| NoiDung: " + NoiDung + "| NgayPhanAnh: " + NgayPhanAnh);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
