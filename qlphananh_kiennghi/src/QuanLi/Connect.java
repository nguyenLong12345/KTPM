/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package QuanLi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author long2
 */
public class Connect {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String url = "jdbc:sqlserver://localhost:1433;databaseName=kiennghi_phananh;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "12345";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Ket noi mssql thanh cong");
            System.out.println(conn.getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
