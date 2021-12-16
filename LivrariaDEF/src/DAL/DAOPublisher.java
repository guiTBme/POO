/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Models.Book;
import Models.Publisher;
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
public class DAOPublisher {
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
    public void incluir(Publisher publisher) throws SQLException {
        conectar();

        String query = "INSERT INTO library.books (name, endereco) "
                + "VALUES (?,?)";
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, publisher.getName());
        prep.setString(2, publisher.getEndereco());
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(Publisher publisher) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.publisher "
                    + "SET name=?, endereco=? WHERE puID=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(3, publisher.getId());
            prep.setString(1, publisher.getName());
            prep.setString(2, publisher.getEndereco());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(Publisher publisher) {
        conectar();
        try {
            String query = "DELETE FROM library.publisher "
                    + "WHERE puID=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, publisher.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorNome(Publisher publisher) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.publisher "
                + "WHERE name = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, publisher.getName());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("puID");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        publisher.setId(idTmp);
        return idTmp;
    }

    public List listar(String params) {
        conectar();
        List<Publisher> listaPublisher = new ArrayList<>();
        String query = "SELECT * from library.publisher " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery();

            while (lista.next()) {
                Publisher publisher = new Publisher();
                publisher.setId(lista.getInt("puID"));
                publisher.setName(lista.getString("name"));
                publisher.setEndereco(lista.getString("endereco"));
                listaPublisher.add(publisher);
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPublisher;
    }

}
