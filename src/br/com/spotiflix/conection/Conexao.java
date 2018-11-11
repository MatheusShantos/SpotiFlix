package br.com.spotiflix.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexao {
	
	public Connection conecta;
    public Statement stmt;//realisa a pesquisa no banco de dados
    public ResultSet rs;//armazena o resultado buscado no banco de dados
    private String driver = "org.postgresql.Driver";//identifica o serviso usado do banco de dados
    private String caminho = "jdbc:postgresql://localhost:5432/spotiflix";//responsavel por setar o local onde os dados se encontram
    private String usuario = "postgres";//é o usuário do banco de dados
    private String senha = "Vingados7vezes";//é a senha do banco de dados
    
    public void conexao(){
        System.setProperty("jdbc.Driver", driver);//responsavel por setar a propriedade do driver de conecxao
        
        try {
            conecta = DriverManager.getConnection(caminho, usuario,senha );//efetua a a conecxao com o banco de dados
            //JOptionPane.showMessageDialog(null,"Conecxão com o banco de dados foi efetuada com sucesso!");
        } catch (SQLException ex) {//exibe uma menssagem informando ao usuario que não teve conecxao 
            JOptionPane.showMessageDialog(null,"Não foi possível estabelecer uma conecxão com o banco de dados!\n"+ex.getMessage());
            
        }
    }
    
    public void desconexao(){
        try {
            conecta.close();//desconecta com o banco de dados com o banco de dados
            //JOptionPane.showMessageDialog(null,"Banco de dados foi desconectado com sucesso!");
        } catch (SQLException ex) {//exibe uma menssagem informando ao usuario que não coseguiu encerrar a conecxao com o banco de dados
            JOptionPane.showMessageDialog(null,"Não foi possível desconectar com o banco de dados!\n"+ex.getMessage());
        }
    }
    
    public static void main(String args[]) {
    	Conexao c = new Conexao();
    	c.conexao();
    }

}
