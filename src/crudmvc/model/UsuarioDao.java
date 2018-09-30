package crudmvc.model;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Wellyson Martins
 */
public class UsuarioDao
{
    private Connection conexao; 
    private PreparedStatement ps;
    private ResultSet rs;
    
    public UsuarioDao() 
    {
        conexao = Conexao.abreConexao();
    }
    
    //insere um registro de usuario no banco de dados...
    public String insere(Usuario obj) {
        String msg = "Usuário cadastrado com sucesso!";
        try
        {
            ps = conexao.prepareStatement(Usuario.INSERE);
            ps.setString(1, obj.getUser());
            ps.setString(2, obj.getPassword());
            ps.executeUpdate();
        }
        catch (Exception e) 
        {
            msg = "Erro ao cadastrar o usuário!";
        }
        
        return msg;
    }
    
    public int validaUsuario(String user){
        int existe = 0;
        try
        {
            ps = conexao.prepareStatement(Usuario.LISTA_USUARIO);
            ps.setString(1, user);
            rs = ps.executeQuery();
            if(rs.next()){
                existe = 1;
            }
        }
        catch (Exception e)
        {
            System.out.println("Erro ao validar o usuário!");
        }
        return existe;
    }

    public String exclui(String user) {
        String msg = "Usuário excluído com sucesso!";
        try
        {
            ps = conexao.prepareStatement(Usuario.DELETA);
            ps.setString(1, user);
            ps.executeUpdate();
        }
        catch (Exception e) 
        {
            msg = "Erro ao excluir o usuário!";
        }
        
        return msg;
    } 
    
    public String edita(Usuario obj) {
        String msg = "Usuário editado com sucesso!";
        try
        {
            ps = conexao.prepareStatement(Usuario.EDITA);
            ps.setString(1, obj.getUser());
            ps.setString(2, obj.getPassword());
            ps.setInt(3, obj.getId());
            ps.executeUpdate();
        }
        catch (Exception e) 
        {
            msg = "Erro ao editar o usuário!";
        }
        
        return msg;
    }
    
    public String listaSenha(String user) {
        String senha = null;
        try
        {
            ps = conexao.prepareStatement(Usuario.LISTA_ID_SENHA);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                senha = rs.getString("PASSWORD");
            }
            return senha;
        }
        catch (Exception e) 
        {
            System.out.println("Erro ao listar");
        }
        
        return null;
    }
    
    public Integer listaId(String user) {
        Integer id = null;
        try
        {
            ps = conexao.prepareStatement(Usuario.LISTA_ID_SENHA);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("ID");
            }
            return id;
        }
        catch (Exception e) 
        {
            System.out.println("Erro ao listar!");
        }
        
        return null;
    }
    
    public List<Usuario> lista() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try
        {
            ps = conexao.prepareStatement(Usuario.LISTA_TODOS);
            rs = ps.executeQuery();
            while (rs.next())
            {
                usuarios.add( new Usuario(rs.getString("USERNAME"), rs.getString("PASSWORD"), null) );
            }

            return usuarios;
        }
        catch(Exception e) 
        {
            System.out.println("Erro ao listar!");
        }
        
        return null;
    }
}


