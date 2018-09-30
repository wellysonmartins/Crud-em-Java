package crudmvc.controller;

import crudmvc.model.Usuario;
import crudmvc.model.UsuarioDao;
import crudmvc.view.ViewMenu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Wellyson
 */
public class UsuarioControl implements ActionListener{
    
    ViewMenu menu;
    private boolean editando = false; // VARIÁVEL PARA FAZER A VALIDAÇÃO DO BOTÃO SALVAR
    Integer id = null; // VARIÁVEL PARA RECEBER ID DE USUÁRIO PARA EDITAR
    String user1 = null; // VARIÁVEL PARA RECEBER USERNAME DO USUÁRIO PARA VERIFICAÇÃO
    
    public UsuarioControl(ViewMenu menu) {
        
        this.menu = menu;        
        addListener();
        listarUsuarios();
    }
    
    //MÉTODO COM OS GET DOS BOTÕES
    private void addListener() {
        menu.getBtnNovo().addActionListener(this);
        menu.getBtnSalvar().addActionListener(this);
        menu.getBtnExcluir().addActionListener(this);
        menu.getBtnEditar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //ACTION DO BOTÃO - NOVO
        if(e.getSource() == menu.getBtnNovo()) {
            novoUsuario();
        }
        
        //ACTION DO BOTÃO - SALVAR
        if(e.getSource() == menu.getBtnSalvar()) {
            if(editando == false) {
                cadastrarUsuario();  
            } else {
                atualizarUsuario();  
            }
        }
        
        //ACTION DO BOTÃO - EXCLUIR
        if(e.getSource() == menu.getBtnExcluir()) {
            excluirUsuario();
        }
        
        //ACTION DO BOTÃO - EDITAR
        if(e.getSource() == menu.getBtnEditar()) {
            editarUsuario();
        }
    }
    
    //MÉTODO PARA LIMPAR CAMPOS E ADICIONAR CURSOR NO USERNAME
    private void novoUsuario() {
        limparMensagem();
        menu.getTxtUsuario().setText("");
        menu.getTxtSenha().setText("");
        menu.getTxtUsuario().requestFocus();
        editando = false;
    }
    
    //MÉTODO PARA CADASTRAR NOVO USUÁRIO
    private void cadastrarUsuario(){
        limparMensagem();
        String user = menu.getTxtUsuario().getText();//RECEBE USERNAME
        String password = menu.getTxtSenha().getText();//RECEBE PASSWORD
        Usuario usuario = new Usuario(user, password, null);//CRIA O OBJETO USUARIO COM OS DADOS RECEBIDOS
        
        //SE O CAMPO USUÁRIO OU SENHA ESTIVER VAZIO, RETORNA MENSAGEM DE ERRO
        if(!validarCadastro(usuario)) {
            menu.getLblMensagem().setText("Preencha todos os campos corretamente!");
            menu.getLblMensagem().setForeground(vermelho());
            return;
        }
        UsuarioDao cadastrar = new UsuarioDao();
        //SE NÃO EXIXSTIR O USUÁRIO INFORMADO JÁ CADASTRADO, EFETUA O NOVO CADASTRO
        if(cadastrar.validaUsuario(user) == 0) { 
            String msg = cadastrar.insere(usuario);
            listarUsuarios();
            novoUsuario();
            menu.getLblMensagem().setText(msg);
            menu.getLblMensagem().setForeground(verdeEscuro());
            
        //SENÃO, RETORNA MENSAGEM DE ERRO
        } else {
            menu.getLblMensagem().setText("Usuário informado já existe!");
            menu.getLblMensagem().setForeground(vermelho());
        }
    }
    
    //MÉTODO PARA EXCLUIR USUÁRIO
    private void excluirUsuario() {
        limparMensagem();
        
        //SE TIVER UM USUÁRIO SELECIONADO, FAZ A SOLICITAÇÃO DE EXCLUSÃO
        try {
            String user = menu.getTxtLista().getSelectedValue().toString();
            //ABRE PAINEL DE CONFIRMAÇÃO DE EXCLUSÃO
            int escolha = JOptionPane.showConfirmDialog(menu, "Deseja realmente excluir o usuário?");
            
            //SE O USUÁRIO CONFIRMAR EXCLUSÃO, CHAMA O MÉTODO EXCLUI
            if(JOptionPane.YES_OPTION == escolha) {
                String msg = new UsuarioDao().exclui(user);
                listarUsuarios();
                novoUsuario();
                menu.getLblMensagem().setText(msg);
                menu.getLblMensagem().setForeground(verdeEscuro());
            }
            
        //SE NÃO TIVER SELECIONADO UM USUÁRIO PARA EXCLUIR, RETORNA MENSAGEM DE ERRO    
        } catch (Exception e) {
            menu.getLblMensagem().setText("Selecione um usuário para excluir!");
            menu.getLblMensagem().setForeground(vermelho());
        }
    }
    
    //MÉTODO PARA PEENCHER OS CAMPOS COM O USUÁRIO SELECIONADO PARA EDITAR
    private void editarUsuario() {
        limparMensagem();
        
        try {
            String user = menu.getTxtLista().getSelectedValue().toString();//PEGA O USERNAME
            String senha = new UsuarioDao().listaSenha(user);//PEGA A SENHA NO BANCO DO USERNAME SELECIONADO
            id = new UsuarioDao().listaId(user);//PEGA O ID NO BANCO DO USERNAME SELECIONADO
            
            menu.getTxtUsuario().setText(user);//PREENCHE O CAMPO USUÁRIO COM O VALOR SELECIONADO
            menu.getTxtSenha().setText(senha);//PREENCHE O CAMPO SENHA COM O VALOR RECEBIDO DA CONSULTA NO BANCO
            
            user1 = menu.getTxtUsuario().getText();//GUARDA VALOR DO USUÁRIO SELECIONADO PARA VERIFICAÇÃO POSTERIOR
            
            editando = true;//VARIÁVEL PARA VERIFICAÇÃO DO BOTÃO SALVAR
        
        //SE NÃO TIVER SELECIONADO UM USUÁRIO PARA EXCLUIR, RETORNA MENSAGEM DE ERRO        
        } catch (Exception e) {            
            menu.getLblMensagem().setText("Selecione um usuário para editar!");
            menu.getLblMensagem().setForeground(vermelho());
        }
    }
    
    //MÉTODO PARA ATUALIZAR USUARIO EDITADO
    private void atualizarUsuario() {
        limparMensagem();
        try {
            String user = menu.getTxtUsuario().getText();//PEGA O USERNAME EDITADO
            String password = menu.getTxtSenha().getText();//PEGA A SENHA EDITADA
            Usuario usuario = new Usuario(user, password, id);
            UsuarioDao atualizar = new UsuarioDao();
            
            //VERIFICA SE TODOS OS CAMPOS FORAM PREENCHIDOS
            if(!validarCadastro(usuario)) {
                menu.getLblMensagem().setText("Preencha todos os campos corretamente!");
                menu.getLblMensagem().setForeground(vermelho());
            } 
            
            //VERIFICAR SE USUARIO INFORMADO É O MESMO PARA EDITAR E EM CASO DE MODIFICAÇÃO SE EXISTE OUTRO USUÁRIO NO BANCO
            else if ((user1.equals(user)) || (!user1.equals(user) && atualizar.validaUsuario(user) == 0)) {
                String msg = atualizar.edita(usuario);
                listarUsuarios();
                novoUsuario();
                menu.getLblMensagem().setText(msg);
                menu.getLblMensagem().setForeground(verdeEscuro());
                editando = false;
                
            //SE USUÁRIO ALTERADO QUE NÃO SEJA O SOLICITADO FOR IGUAL A OUTRO JÁ EXISTENTE NO BANCO, RETORNA MANSAGEM DE ERRO
            } else {
                menu.getLblMensagem().setText("Usuário informado já existe!");
                menu.getLblMensagem().setForeground(vermelho());
            }
        } catch (Exception e) {
            
        }
    }
    
    //MÉTODO PARA LISTAR TODOS OS USÁRIOS NO PAINEL LIST
    private void listarUsuarios() {
        List<Usuario> usuarios = new UsuarioDao().lista();
        
        DefaultListModel linha = new DefaultListModel();
        
        for(int i = 0; i < usuarios.size(); i++) {
            linha.add(i, usuarios.get(i).getUser());
        }
        menu.getTxtLista().setModel(linha);  
    }
    
    //MÉTODO PARA VERIFICAR SE TODOS OS CAMPOS ESTÃO PREENCHIDOS
    private boolean validarCadastro(Usuario user) {
        return !(user.getUser().equals("") || user.getPassword().equals(""));
    }
    
    //MÉTODO PARA LIMPAR LABEL DE MENSAGEM DE ERRO
    private void limparMensagem() {
        menu.getLblMensagem().setText("");
    }
    
    //MÉTODO PARA COLOCAR FONTE VERDE NA MENSAGEM DE VALIDAÇÃO
    private Color verdeEscuro() {
        Color verdeEscuro = new Color(35,142,35);
        return verdeEscuro;
    }
    
    //MÉTODO PARA COLOCAR FONTE VERMELHA NA MENSAGEM DE VALIDAÇÃO
    private Color vermelho() {
        Color vermelho = new Color(255,0,0);
        return vermelho;
    }
}
