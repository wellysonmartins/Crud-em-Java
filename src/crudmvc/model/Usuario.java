package crudmvc.model;

public class Usuario {
    private String user;
    private String password;
    private Integer id;
    
    public static final String INSERE = " INSERT INTO TB_USUARIO (USERNAME, PASSWORD) VALUES (?,?) ";
    public static final String LISTA_TODOS = " SELECT * FROM TB_USUARIO ";
    public static final String LISTA_USUARIO = " SELECT USERNAME FROM TB_USUARIO WHERE USERNAME = ? ";
    public static final String LISTA_ID_SENHA = " SELECT ID, PASSWORD FROM TB_USUARIO WHERE USERNAME = ? ";
    public static final String DELETA = " DELETE FROM TB_USUARIO WHERE USERNAME = ? ";
    public static final String EDITA = " UPDATE TB_USUARIO SET USERNAME = ?, PASSWORD = ? WHERE ID = ? ";
    
    
    public Usuario(String user, String password, Integer id) {
        this.user = user;
        this.password = password;
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }
    
    
    
}