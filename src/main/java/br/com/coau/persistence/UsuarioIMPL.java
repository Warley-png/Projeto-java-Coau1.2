package br.com.coau.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class UsuarioIMPL implements UsuarioDAO {

    private EntityManager em;

    public UsuarioIMPL(EntityManager em) {
        this.em = em;
    }

    @Override
    public String validarUsuario(String login, String senha) {
        String perfil = null;
        try {
            String sql = "SELECT u.perfil FROM Usuario u WHERE u.login = :login AND u.senha = :senha";
            Query consulta = em.createQuery(sql);
            consulta.setParameter("login", login);
            consulta.setParameter("senha", senha);
            perfil = (String) consulta.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Usuário ou senha inválidos!");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return perfil;

    }

    @Override
    public void cadastrarUsuario(Usuario u) {

        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

        }

    }

    @Override
    public Usuario consultarUsuario(long iduser) {

        Usuario usuario = null;
        try {
            String sql = "SELECT u FROM Usuario u WHERE u.iduser = :iduser";
            Query consulta = em.createQuery(sql);
            consulta.setParameter("iduser", iduser);
            usuario = (Usuario) consulta.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Usuário não encontrado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public void editarUsuario(Usuario u) {

        try {

            em.getTransaction().begin();

            String sql = "UPDATE Usuario u SET u.Usuario = :Usuario, u.login = :login,"
                    + " u.senha = :senha, u.perfil = :perfil WHERE u.iduser = :iduser";
            Query consulta = em.createQuery(sql);
            consulta.setParameter("Usuario", u.getUsuario());
            consulta.setParameter("login", u.getLogin());
            consulta.setParameter("senha", u.getSenha());
            consulta.setParameter("perfil", u.getPerfil());
            consulta.setParameter("iduser", u.getIduser());

            int editar = consulta.executeUpdate();

            em.getTransaction().commit();

            if (editar > 0) {
                System.out.println("Usuário atualizado com sucesso.");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID fornecido.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removerUsuario(long iduser) {

        try {

            em.getTransaction().begin();

            String sql = "DELETE FROM Usuario u WHERE u.iduser = :iduser";
            Query consulta = em.createQuery(sql);
            consulta.setParameter("iduser", iduser);

            int excluir = consulta.executeUpdate();

            // Confirma a transação
            em.getTransaction().commit();

            if (excluir > 0) {
                System.out.println("Usuário removido com sucesso.");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID fornecido.");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
