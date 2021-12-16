/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datadrivenpessoa;

/**
 *
 * @author Maria Elisa
 */
public class DataDrivenPessoa {

    public static void main(String[] args) {
        Pessoa p1=new Pessoa();
        
        p1.setNome("Joao");
        p1.setEndereco("Rua A");
        p1.setTelefone("33223322");
        p1.setIdade(20);
        
        Pessoa p2=new Pessoa();
        
        p2.setNome("Jose");
        p2.setEndereco("Rua B");
        p2.setTelefone("11221122");
        p2.setIdade(30);
        
        imprimirPessoa(p1);
        imprimirPessoa(p2);
        
        CadastroPessoa cadastro=new CadastroPessoa();
        
        cadastro.addPessoa(p1);
        cadastro.imprimirPessoas();
        
        cadastro.addPessoa(p2);
        cadastro.imprimirPessoas();
    }
    
    public static void imprimirPessoa(Pessoa p){
        System.out.println(p.getNome());
    }
    
}
