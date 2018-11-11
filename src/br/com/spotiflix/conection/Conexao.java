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
    private String usuario = "postgres";//� o usu�rio do banco de dados
    private String senha = "Vingados7vezes";//� a senha do banco de dados
    
    public void conexao(){
        System.setProperty("jdbc.Driver", driver);//responsavel por setar a propriedade do driver de conecxao
        
        try {
            conecta = DriverManager.getConnection(caminho, usuario,senha );//efetua a a conecxao com o banco de dados
            //JOptionPane.showMessageDialog(null,"Conecx�o com o banco de dados foi efetuada com sucesso!");
        } catch (SQLException ex) {//exibe uma menssagem informando ao usuario que n�o teve conecxao 
            JOptionPane.showMessageDialog(null,"N�o foi poss�vel estabelecer uma conecx�o com o banco de dados!\n"+ex.getMessage());
            
        }
    }
    
    public void desconexao(){
        try {
            conecta.close();//desconecta com o banco de dados com o banco de dados
            //JOptionPane.showMessageDialog(null,"Banco de dados foi desconectado com sucesso!");
        } catch (SQLException ex) {//exibe uma menssagem informando ao usuario que n�o coseguiu encerrar a conecxao com o banco de dados
            JOptionPane.showMessageDialog(null,"N�o foi poss�vel desconectar com o banco de dados!\n"+ex.getMessage());
        }
    }
    
    public static void main(String args[]) {
    	Conexao c = new Conexao();
    	c.conexao();
    }

}
