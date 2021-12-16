/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Maria Elisa
 */
public class Account {
    private String emailAddress;
    private long id;
    private String password;
    
    private long idTemp;
    private String passwordTemp;
    
//----metodos de operacao
    public boolean verifyPassword(){
         return password.equals(passwordTemp);
    }
    
    public boolean validateLogin(){
        return (id==idTemp);
    }
    
    public boolean validateLogin(long ID, String password){
        idTemp = ID;
        passwordTemp=password;
        return (validateLogin() && verifyPassword());
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
