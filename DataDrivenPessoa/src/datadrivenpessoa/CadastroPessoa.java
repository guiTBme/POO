/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadrivenpessoa;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maria Elisa
 */
public class CadastroPessoa {
    List<Pessoa> pessoas=new ArrayList<>();
    
    public void addPessoa(Pessoa p){
        pessoas.add(p);            //um ponteiro de objeto pessoa passa como parametro,
    }             //passando para o atributo pessoa
    
    public void imprimirPessoas(){
        for(int i=0; i<pessoas.size();i++){
            System.out.println(pessoas.get(i).getNome() +"\n");
        }
         //ele pega o atributo passado, e resgata a informação 
        
    }
}
