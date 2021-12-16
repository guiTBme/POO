/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Models.Author;
import Models.Book;
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
public class DAOAuthor {
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
    public void incluir(Author author) throws SQLException {
        conectar();

        String query = "INSERT INTO library.author (name) "
                + "VALUES (?)";
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, author.getName());
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(Author author) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.auhtor "
                    + "SET name=? WHERE auID=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(2, author.getId());
            prep.setString(1, author.getName());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(Author author) {
        conectar();
        try {
            String query = "DELETE FROM library.author "
                    + "WHERE auID=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, author.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorNome(Author author) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.author "
                + "WHERE name = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, author.getName());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("auID");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        author.setId(idTmp);
        return idTmp;
    }

    public List listar(String params) {
        conectar();
        List<Author> listaAuthor = new ArrayList<>();
        String query = "SELECT * from library.author " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery();

            while (lista.next()) {
                Author author = new Author();
                author.setId(lista.getInt("auID"));
                author.setName(lista.getString("name"));
                listaAuthor.add(author);
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAuthor;
    }

}
