/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Models.Author;
import Models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Maria Elisa
 */
public class DAOUser {
    private Connection conection = null;

    public void conectar() {
        Properties prop = new Properties();
        prop.setProperty("user", "root");
        prop.setProperty("password", "qwe123");
        try {
            conection = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.0.1:3308", prop);
            conection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //incluir
    public void incluir(User user) throws SQLException {
        conectar();

        String query = "INSERT INTO library.user (name) "
                + "VALUES (?)";
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, user.getName());
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(User user) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.user "
                    + "SET name=? WHERE usID=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(2, user.getId());
            prep.setString(1, user.getName());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(User user) {
        conectar();
        try {
            String query = "DELETE FROM library.user "
                    + "WHERE usID=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, user.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public User consultarPorNome(User user) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.user "
                + "WHERE name = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, user.getName());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("usID");
                user.setId(idTmp);
                user.setName(list.getString("name"));
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user.setId(idTmp);
        return user;
    }

    public List listar(String params) {
        conectar();
        List<User> listaUser = new ArrayList<>();
        String query = "SELECT * from library.user " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery();

            while (lista.next()) {
                User user = new User();
                user.setId(lista.getInt("usID"));
                user.setName(lista.getString("name"));
                listaUser.add(user);
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUser;
    }
}
