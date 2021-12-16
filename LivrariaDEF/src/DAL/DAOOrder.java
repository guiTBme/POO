/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Models.Book;
import Models.ConnectionDB;
import Models.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Elisa
 */
public class DAOOrder {
    ConnectionDB cdb = new ConnectionDB();
    public void insert(Order order) throws SQLException{
        
        cdb.conectar();

       //faz a insercao do id de usuário/cliente e ordem de compra
        String query0 = "INSERT INTO library.order_itens (fkusID) "
               + "VALUES (?)";
        PreparedStatement prep0 = cdb.getConnection().prepareStatement(
                           query0,Statement.RETURN_GENERATED_KEYS);    
        prep0.setInt(1, order.getUser().getId());
        prep0.execute();

            //persiste a ordem de venda no banco
            cdb.getConnection().commit();

        //pega o id da ordem de venda
        ResultSet idOrdemVenda = prep0.getGeneratedKeys();
        idOrdemVenda.next();
        int idOV = idOrdemVenda.getInt("oiID");

       //a segunda parte Ã© a insercao dos livros

       //varre a lista de livros e prepara a insercao em order
       String query1 = "INSERT INTO library.order (orID, fkboID, quantidade) "
               + "VALUES (?,?,?)";
       PreparedStatement prep1 = cdb.getConnection().prepareStatement(
                            query1,Statement.RETURN_GENERATED_KEYS);
       
       //atualiza a lista de produtos        
       String query2 = "UPDATE livraria.books" +
                        "SET quantidade = ? WHERE boID = ?";

       PreparedStatement prep2 = cdb.getConnection().prepareStatement(
                            query2);


       //consulta sql do produto
       String queryLivro= "Select * from livraria.books " +
                        "where boID = ?";

       PreparedStatement prepLivro = cdb.getConnection().prepareStatement(
                                queryLivro);

        for(Book l: order.getListaLivros()){

           // faz a consulta no banco pela quantidade do produto
           prepLivro.setInt(1, l.getId());
           ResultSet produtoDoBanco;
            produtoDoBanco = prepLivro.executeQuery();
           produtoDoBanco.next();
           int qtdeDeLivroNoBanco = produtoDoBanco.getInt("quantidade");

            //adiciona o produto vinculando no orderitens
            prep1.setInt(1, idOV);
            prep1.setInt(2, l.getId());
            prep1.setInt(3, l.getQnt());
            //prep1.addBatch();
            prep1.execute();


            //atualiza a tabela de produtos, decrementando o estoque.
            prep2.setInt(1, qtdeDeLivroNoBanco-l.getQnt());
            prep2.setInt(2, l.getId());
            prep2.execute();

        }

        cdb.getConnection().commit();
        cdb.close();   
    }
}
