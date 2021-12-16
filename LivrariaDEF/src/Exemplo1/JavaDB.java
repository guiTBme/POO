/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exemplo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


/**
 *
 * @author felip
 */
public class JavaDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] argv) {
        Properties connConfig = new Properties();
        connConfig.setProperty("user", "root");
        connConfig.setProperty("password", "123");
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3308", connConfig)) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet _list = stmt.executeQuery("SELECT id, name FROM library.user")) {
                    while (_list.next()) {
                        System.out.println(String.format("%d %s",
                            _list.getInt("id"),
                            _list.getString("name")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
