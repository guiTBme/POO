/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

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
 * @author felip
 */
public class DAOBook {

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
    public void incluir(Book book) throws SQLException {
        conectar();

        String query = "INSERT INTO library.books (title, author, publisher, quantidade) "
                + "VALUES (?,?,?,?)";
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, book.getTitle());
        prep.setInt(2, book.getAuthor());
        prep.setInt(3, book.getPublisher());
        prep.setInt(4, book.getQnt());
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(Book book) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.books "
                    + "SET title=?, fkauID=?, fkpuID=?, quantidade=? WHERE boID=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(5, book.getId());
            prep.setString(1, book.getTitle());
            prep.setInt(2, book.getAuthor());
            prep.setInt(3, book.getPublisher());
            prep.setInt(4, book.getQnt());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(Book book) {
        conectar();
        try {
            String query = "DELETE FROM library.books "
                    + "WHERE boID=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, book.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorTitulo(Book book) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.books "
                + "WHERE title = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, book.getTitle());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("boID");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        book.setId(idTmp);
        return idTmp;
    }

    public Book Soldbook(String params) {
        conectar();
        List<Book> listaLivros = new ArrayList<>();
        String query = "SELECT * from library.books " + params;
        Book Soldbook = null;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery();

            while (lista.next()) {
                Book book = new Book();
                book.setId(lista.getInt("boID"));
                book.setTitle(lista.getString("title"));
                book.setAuthor(lista.getInt("fkauID"));
                book.setPublisher(lista.getInt("fkpuID"));
                listaLivros.add(book);
                Soldbook = book;
                
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Soldbook;
    }

}
