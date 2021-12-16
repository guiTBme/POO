/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livrariadef;

import DAL.DAOAuthor;
import DAL.DAOBook;
import DAL.DAOOrder;
import DAL.DAOUser;
import Models.Author;
import Models.Book;
import Models.ConnectionDB;
import Models.Order;
import Models.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Maria Elisa
 */

public class LivrariaDEF {
    
    public static void main(String[] args) throws SQLException {
        
        int escolha;
        Scanner scan = new Scanner(System.in);
      
      
      do{
        System.out.print("Digite 1 para efetuar uma venda ou 2 para inserir um novo livro..>");
        escolha = scan.nextInt();
        
        switch (escolha){
            case 1:
                Book newBook = new Book();
                Order newOrder = new Order();
                User newUser = new User();
                DAOUser daoUser = new DAOUser();
                DAOBook daoBook = new DAOBook();
                DAOOrder daoOrder = new DAOOrder();
                
                //incluindo um cliente/user no sistema
                
                System.out.println("Nome do cliente..>");
                String nomeC = scan.next();
                newUser.setName(nomeC);
                daoUser.incluir(newUser);
                newOrder.setUser(newUser);
                System.out.println();
                
                //listando os livros disponíveis e efetuando o pedido
                
                /*List<Book> lista = new ArrayList<>();
                lista = daoBook.Soldbook("");
                System.out.println("ID   Título");
                for(Book b : lista){
                    System.out.print(b.getId());
                    System.out.print("   ");
                    System.out.print(b.getTitle());
                    System.out.println("");
                    
                }*/
                System.out.print("\nID  do livro desejado..>"); // precisa ir listando os métodos do book e criar um aqui
                int liID = scan.nextInt();
                newBook.setId(liID);
                System.out.println();
                //1List<Book> listaLivro = new ArrayList<>();
                String query = "WHERE boID = " + liID;
                newOrder.setBook(daoBook.Soldbook(query));
                newOrder.setUser(daoUser.consultarPorNome(newUser));
                daoOrder.insert(newOrder);
                
                //System.out.print(daoBook.consultarPorTitulo(newBook));
                //1newOrder.setBook(book););
                
                
                break;
            case 2:
                /*Author newAut = new Author();
                Book newBook = new Book();
                
                System.out.println("Título do livro..>");
                String tituL = scan.next();
                newBook.setTitle(tituL);
                
                System.out.println("Autor do livro..>");
                String autL = scan.next();
                newAut.setName(autL);
                
                System.out.println("Email do autor..>");
                String autMail = scan.next();
                newBook.setTitle(tituL);
                
                DAOBook daoBook = new DAOBook();
                daoBook.incluir(newBook);
                //List<Book> lista = new ArrayList<>();
                lista = daoBook.listar("");
                for(Book b : lista){
                    System.out.println(b.getAuthor());
                }
                break;*/
        }
      
    }while(escolha != 0);  
    
}
}


