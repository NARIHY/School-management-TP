/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universitymanagement;

import java.sql.*;

/**
 *
 * @author RANDRIANARISOA
 */
public class DataBase {

    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/university", "root", "");
            return connect;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }
}
