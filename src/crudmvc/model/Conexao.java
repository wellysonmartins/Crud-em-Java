package crudmvc.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String URL = "jdbc:derby://localhost:1527/sample";
    private static final String USR = "app";
    private static final String PWD = "app";
    
    
    public static Connection abreConexao() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USR, PWD);
        } catch (Exception e) {
            System.out.println("Erro ao conectar database");
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        Connection conn = Conexao.abreConexao();
        if (conn != null)
            System.out.println("OK conectado.");
        
    }
}
