
package br.com.coau.persistence;


public interface UsuarioDAO {
    
    String validarUsuario(String login, String senha);
    Usuario consultarUsuario(long iduser);
    void  cadastrarUsuario(Usuario u);
    void editarUsuario(Usuario u);
    void removerUsuario(long iduser);
    
}
