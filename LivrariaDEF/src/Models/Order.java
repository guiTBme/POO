/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maria Elisa
 */
public class Order {
    private Book book;
    private User user;
    private int id;
    private List<Book> listaLivros = new ArrayList<>();

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getListaLivros() {
        return listaLivros;
    }

    public void setListaLivros(List<Book> listaLivros) {
        this.listaLivros = listaLivros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
