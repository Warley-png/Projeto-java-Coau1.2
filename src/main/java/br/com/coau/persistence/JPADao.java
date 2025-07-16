/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.coau.persistence;

import br.com.coau.persistence.JPAUtil;
import br.com.coau.persistence.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.Query;

/**
 *
 * @author Warley
 */
public class JPADao {

   
    public String validarUsuario(String login, String senha) {
        EntityManager em = JPAUtil.getEntityManager();
        String perfil = null;
        try {
            String sql = "SELECT u.perfil FROM Usuario u WHERE u.login = :login AND u.senha = :senha";

            Query consulta = em.createQuery(sql);
            consulta.setParameter("login", login);
            consulta.setParameter("senha", senha);
            //obtem o tipo de usuario(String)converte o que foi digitado em string
            perfil = (String) consulta.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Usuário ou senha inválidos !");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return perfil;
    }

   public Usuario consultarUsuario(long iduser) {
        EntityManager em = JPAUtil.getEntityManager();
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
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return usuario;
    }


    public void cadastrarUsuario(Usuario u) {
         EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen() && em != null) {
                em.close();
            }
        }

       
    }

     public void editarusuario(String Usuario, String login, String senha, String perfil, long iduser) {
        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            String sql = "UPDATE Usuario u SET u.Usuario = :Usuario, u.login = :login, u.senha = :senha, u.perfil = :perfil WHERE u.iduser = :iduser";
            Query consulta = em.createQuery(sql);
            consulta.setParameter("Usuario", Usuario);
            consulta.setParameter("login", login);
            consulta.setParameter("senha", senha);
            consulta.setParameter("perfil", perfil);
            consulta.setParameter("iduser", iduser); 

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
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
   public void removerusuario(long iduser) {
        EntityManager em = JPAUtil.getEntityManager();

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
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }

    public void adicionarclientes(Cliente c) {
          EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen() && em != null) {
                em.close();
            }
        }

      

    }

   public List<Cliente> listarClientes() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Cliente> clientes = new ArrayList<>();
        try {
            Query consulta = em.createQuery("SELECT c FROM Cliente c");
            clientes = consulta.getResultList();
        } finally {
            if (em.isOpen() && em != null) {
                em.close();
            }
        }
        return clientes;
    }


    public List<Cliente> pesquisarCliente(String filtro) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Cliente> resultados = null;
        String sql = "SELECT c FROM Cliente c";
        try {
            if (filtro != null && !filtro.trim().isEmpty()) {
                sql += " WHERE c.nomecli LIKE :descricao";
            }
            Query consulta = em.createQuery(sql);
            if (filtro != null && !filtro.trim().isEmpty()) {
                consulta.setParameter("descricao", "%" + filtro.trim() + "%");
            }

            resultados = consulta.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return resultados;

    }

    public void editarCliente(String nomecli, String fonecli, String facucli, String emailcli, long idcli) {
        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            String sql = "UPDATE Cliente c SET c.nomecli = :nomecli, c.fonecli = :fonecli, c.facucli = :facucli, c.emailcli = :emailcli WHERE c.idcli = :idcli";
            Query consulta = em.createQuery(sql);
            consulta.setParameter("nomecli", nomecli);
            consulta.setParameter("fonecli", fonecli);
            consulta.setParameter("facucli", facucli);
            consulta.setParameter("emailcli", emailcli);
            consulta.setParameter("idcli", idcli);

            int editar = consulta.executeUpdate();

            em.getTransaction().commit();

            if (editar > 0) {
                System.out.println("Cliente atualizado com sucesso.");
            } else {
                System.out.println("Nenhum cliente encontrado .");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); 
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
     public void excluirCliente(long idcli) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            // Verificando se o cliente tem empréstimos ativos
            if (temEmprestimosAtivos(idcli)) {
                // Excluindo os empréstimos associados ao cliente
                String sql = "DELETE FROM AlugarLivro r WHERE r.idcli = :idcli";
                Query query = em.createQuery(sql);
                query.setParameter("idcli", idcli);
                query.executeUpdate();
            }

            Cliente cliente = em.find(Cliente.class, idcli);
            if (cliente != null) {
                em.remove(cliente);
                em.getTransaction().commit();
                System.out.println("Cliente removido com sucesso.");
            } else {
                System.out.println("Nenhum cliente encontrado.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            throw e; 
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }


//A linha de código abaixo e para verificar de quando for excluir cliente , o mesmo tem empréstimo ativo
    public boolean temEmprestimosAtivos(long clienteId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String sql = "SELECT COUNT(r) FROM AlugarLivro r WHERE r.cliente.idcli = :clienteId AND r.status = true";
            Query query = em.createQuery(sql);
            query.setParameter("clienteId", clienteId);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public void CadastarLivros(Livros l) {
          EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen() && em != null) {
                em.close();
            }
        }
    }
       

     public List<Livros> listarLivros() {
        EntityManager em = JPAUtil.getEntityManager();
        List<Livros> livros = new ArrayList<>();
        try {
            Query consulta = em.createQuery("SELECT l FROM Livros l");
            livros = consulta.getResultList();
        } finally {
            if (em.isOpen() && em != null) {
                em.close();
            }
        }
        return livros;
    }

    public List<Livros> pesquisarlivro(String filtro) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Livros> resultados = null;
        String sql = "SELECT l FROM Livros l";
        try {
            if (filtro != null && !filtro.trim().isEmpty()) {
                sql += " WHERE l.nomeliv LIKE :descricao";
            }
            Query consulta = em.createQuery(sql);
            if (filtro != null && !filtro.trim().isEmpty()) {
                consulta.setParameter("descricao", "%" + filtro.trim() + "%");
            }

            resultados = consulta.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return resultados;

    }
  public void editarLivro(String nomeliv, String autorliv, String assuntoliv, String editoraliv,
                            String anorev, String volumerev, String prateleira, String tipo, long idliv) {
        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            String sql = "UPDATE Livros l SET l.nomeliv = :nomeliv, l.autorliv = :autorliv, l.assuntoliv = :assuntoliv, l.editoraliv = :editoraliv, l.anorev = :anorev,l.volumerev = :volumerev,l.prateleira= :prateleira, l.tipo = :tipo WHERE l.idliv = :idliv";
            Query consulta = em.createQuery(sql);
            consulta.setParameter("nomeliv", nomeliv);
            consulta.setParameter("autorliv", autorliv);
            consulta.setParameter("assuntoliv", assuntoliv);
            consulta.setParameter("editoraliv", editoraliv);
            consulta.setParameter("anorev", anorev);
            consulta.setParameter("volumerev", volumerev);
            consulta.setParameter("prateleira", prateleira);
            consulta.setParameter("tipo", tipo);
            consulta.setParameter("idliv", idliv);

            int editar = consulta.executeUpdate();

            em.getTransaction().commit();

            if (editar > 0) {
                System.out.println("Livro atualizado com sucesso.");
            } else {
                System.out.println("Nenhum Livro encontrado .");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Reverte a transação em caso de erro
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    public void excluirlivro(long idliv) {
    EntityManager em = JPAUtil.getEntityManager();
    try {
        em.getTransaction().begin();

        // Excluindo os empréstimos associados ao livro
        String sql = "DELETE FROM AlugarLivro r WHERE r.livro.idliv = :idliv"; // Certifique-se de que o mapeamento está correto
        Query query = em.createQuery(sql);
        query.setParameter("idliv", idliv);
        query.executeUpdate();

        // Buscando o livro
        Livros livro = em.find(Livros.class, idliv);
        if (livro != null) {
            em.remove(livro);
            em.getTransaction().commit();
            System.out.println("Livro removido com sucesso.");
        } else {
            System.out.println("Nenhum livro encontrado.");
        }
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        e.printStackTrace();
        throw new RuntimeException("Erro ao excluir livro: " + e.getMessage());
    } finally {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}



    

    public boolean EmprestimosAtivos(long idliv) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String sql = "SELECT COUNT(r) FROM AlugarLivro r WHERE r.livro.idliv = :idliv AND r.status = true"; // Supondo que 'status' indica se o empréstimo está ativo
            Query query = em.createQuery(sql);
            query.setParameter("idliv", idliv);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (NoResultException e) {
            return false;
        } catch (NonUniqueResultException e) {

            e.printStackTrace();
            return true;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public Livros buscarLivroPorId(long idliv) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String sql = "SELECT l FROM Livros l WHERE l.idliv = :idliv";
            return em.createQuery(sql, Livros.class)
                    .setParameter("idliv", idliv)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {

            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public boolean alugarLivro(long clienteId, long livroId) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        boolean sucesso = false;

        try {
            transaction.begin();

            Livros livro = em.find(Livros.class, livroId);
            // Buscar o cliente pelo ID
            Cliente cliente = em.find(Cliente.class, clienteId);

            if (livro != null && cliente != null && livro.isDisponivelliv()) {
                livro.setDisponivelliv(false);

                AlugarLivro aluguel = new AlugarLivro();
                aluguel.setCliente(cliente);
                aluguel.setLivro(livro);
                aluguel.setDatasaida(new Date()); // Marca a data de saída com a data atual
                aluguel.setStatus(true);

                em.persist(aluguel);
                sucesso = true;
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return sucesso;
    }

    public boolean devolverLivro(long livroId) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        boolean sucesso = false;

        try {
            transaction.begin();

            // Busca o aluguel ativo do livro
            AlugarLivro aluguel = em.createQuery("SELECT a FROM AlugarLivro a WHERE a.livro.idliv = :livroId AND a.status = true", AlugarLivro.class)
                    .setParameter("livroId", livroId)
                    .getResultList() // Use getResultList() em vez de getSingleResult()
                    .stream()
                    .findFirst() // Pega o primeiro resultado, se existir
                    .orElse(null); // Retorna null se não houver resultados

            if (aluguel != null) {
                aluguel.devolverLivro();
                em.merge(aluguel);
                em.createQuery("UPDATE Livros l SET l.disponivelliv = true WHERE l.idliv = :livroId")
                        .setParameter("livroId", livroId)
                        .executeUpdate();
                sucesso = true;
            } else {
                // Aqui você pode lidar com o caso em que não há aluguel ativo
                System.out.println("Não há aluguel ativo para este livro.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return sucesso;
    }

    public Cliente buscarClientePorId(long idcli) {

        EntityManager em = JPAUtil.getEntityManager();
        Cliente cliente = null;

        try {

            cliente = em.find(Cliente.class, idcli);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return cliente;
    }

    public List<AlugarLivro> listarEmprestimos() {
        EntityManager em = JPAUtil.getEntityManager();
        List<AlugarLivro> emprestimos = new ArrayList<>();
        try {
            Query consulta = em.createQuery("SELECT t FROM AlugarLivro t");
            emprestimos = consulta.getResultList();
        } finally {
            if (em.isOpen() && em != null) {
                em.close();
            }
        }
        return emprestimos;
    }

    public List<AlugarLivro> pesquisarEmprestimo(String filtro) {
        EntityManager em = JPAUtil.getEntityManager();
        List<AlugarLivro> resultados = null;
        String sql = "SELECT a FROM AlugarLivro a JOIN a.livro l";
        try {
            // Filtrando pelo título do livro
            if (filtro != null && !filtro.trim().isEmpty()) {
                sql += " WHERE l.nomeliv LIKE :descricao";
            }
            Query consulta = em.createQuery(sql);
            if (filtro != null && !filtro.trim().isEmpty()) {
                consulta.setParameter("descricao", "%" + filtro.trim() + "%");
            }

            resultados = consulta.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return resultados;
    }

    public List<Livros> pesquisarlivros(String prateleiraliv) {
        EntityManager em = JPAUtil.getEntityManager();
        List<Livros> livros = new ArrayList<>();

        try {
            String sql = "SELECT l FROM Livros l WHERE LOWER(l.prateleiraliv) LIKE LOWER(:prateleiraliv)";
            Query query = em.createQuery(sql);
            query.setParameter("prateleiraliv", "%" + prateleiraliv + "%");
            livros = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return livros;
    }

    //o Metodo abaixo verifica a existencia do nome de prateleira que esta sendo pesquisada
    public boolean prateleiracad(String prateleiraliv) {
        EntityManager em = JPAUtil.getEntityManager();
        boolean existe = false;

        try {
            String sql = "SELECT COUNT(l) FROM Livros l WHERE LOWER(l.prateleiraliv) LIKE LOWER(:prateleiraliv)";
            Query query = em.createQuery(sql);
            query.setParameter("prateleiraliv", "%" + prateleiraliv + "%");
            Long count = (Long) query.getSingleResult();
            existe = count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return existe;
    }
}
