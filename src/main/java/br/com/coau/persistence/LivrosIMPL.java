package br.com.coau.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LivrosIMPL implements LivrosDAO {

    private EntityManager em;

    public LivrosIMPL(EntityManager em) {
        this.em = em;
    }

    @Override
    public void cadastrarLivro(Livros livro) {

        try {
            em.getTransaction().begin();
            em.persist(livro);

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }

    }

    @Override
    public List<Livros> listarLivros() {
        List<Livros> livros = new ArrayList<>();
        try {
            Query consulta = em.createQuery("SELECT l FROM Livros l");
            livros = consulta.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livros;
    }

    @Override
    public List<Livros> pesquisarLivros(String filtro) {
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
        }

        return resultados;

    }

    @Override
    public void editarLivro(String nomeliv, String autorliv, String assuntoliv,
            String editoraliv, String ano, String volumerev,
            String prateleiraliv, String tipo, long idliv) {
        try {

            em.getTransaction().begin();

            String sql = "UPDATE Livros l SET l.nomeliv = :nomeliv, "
                    + "l.autorliv = :autorliv, l.assuntoliv = :assuntoliv, "
                    + "l.editoraliv = :editoraliv, l.ano = :ano,"
                    + "l.volumerev = :volumerev,l.prateleiraliv= :prateleiraliv,"
                    + " l.tipo = :tipo WHERE l.idliv = :idliv";
            Query consulta = em.createQuery(sql);
            consulta.setParameter("nomeliv", nomeliv);
            consulta.setParameter("autorliv", autorliv);
            consulta.setParameter("assuntoliv", assuntoliv);
            consulta.setParameter("editoraliv", editoraliv);
            consulta.setParameter("ano", ano);
            consulta.setParameter("volumerev", volumerev);
            consulta.setParameter("prateleiraliv", prateleiraliv);
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
        }

    }

    @Override
    public void excluirlivro(long idliv) {
        try {
            em.getTransaction().begin();

            // Excluindo os empréstimos associados ao livro
            String sql = "DELETE FROM AlugarLivro r WHERE r.livro.idliv = :idliv";
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
        }
    }

    @Override
    public boolean EmprestimosAtivos(long idliv) {
        try {
            String sql = "SELECT COUNT(r) FROM AlugarLivro r WHERE "
                    + "r.livro.idliv = :idliv AND r.status = true"; 
            Query query = em.createQuery(sql);
            query.setParameter("idliv", idliv);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (NoResultException e) {
            return false;
        } catch (NonUniqueResultException e) {

            e.printStackTrace();
            return true;
        }
    }

    @Override
    public Livros buscarLivroPorId(long idliv) {
        try {
            String sql = "SELECT l FROM Livros l WHERE l.idliv = :idliv";
            return em.createQuery(sql, Livros.class)
                    .setParameter("idliv", idliv)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {

            e.printStackTrace();

        }
        return null;
    }

    @Override
    public boolean alugarLivro(long clienteId, long livroId) {
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
        }
        return sucesso;
    }

    @Override
    public boolean devolverLivro(long livroId) {
        EntityTransaction transaction = em.getTransaction();
        boolean sucesso = false;

        try {
            transaction.begin();

            // Busca o aluguel ativo do livro
            AlugarLivro aluguel = em.createQuery("SELECT a FROM AlugarLivro a "
                    + "WHERE a.livro.idliv = :livroId AND "
                    + "a.status = true", AlugarLivro.class)
                    .setParameter("livroId", livroId)
                    .getResultList()
                    .stream()
                    .findFirst() // Pega o primeiro resultado, se existir
                    .orElse(null); // Retorna null se não houver resultados

            if (aluguel != null) {
                aluguel.devolverLivro();
                em.merge(aluguel);
                em.createQuery("UPDATE Livros l SET l.disponivelliv = true "
                        + "WHERE l.idliv = :livroId")
                        .setParameter("livroId", livroId)
                        .executeUpdate();
                sucesso = true;
            } else {
                // A linha de código abaixo e para lidar com o caso em que não há aluguel ativo
                System.out.println("Não há aluguel ativo para este livro.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return sucesso;

    }

    @Override
    public List<AlugarLivro> listarEmprestimos() {
        List<AlugarLivro> emprestimos = new ArrayList<>();
        try {
            Query consulta = em.createQuery("SELECT t FROM AlugarLivro t");
            emprestimos = consulta.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emprestimos;

    }

    @Override
    public List<AlugarLivro> pesquisarEmprestimo(String filtro) {
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
        }

        return resultados;

    }

    @Override
    public List<Livros> pesquisarlivros(String prateleiraliv) {
        List<Livros> livros = new ArrayList<>();

        try {
            String sql = "SELECT l FROM Livros l WHERE LOWER(l.prateleiraliv)"
                    + " LIKE LOWER(:prateleiraliv)";
            Query query = em.createQuery(sql);
            query.setParameter("prateleiraliv", "%" + prateleiraliv + "%");
            livros = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livros;
    }

//o Metodo abaixo verifica a existencia do nome de prateleira que esta sendo pesquisada
    @Override
    public boolean prateleiracad(String prateleiraliv) {
        boolean existe = false;

        try {
            String sql = "SELECT COUNT(l) FROM Livros l WHERE LOWER"
                    + "(l.prateleiraliv) LIKE LOWER(:prateleiraliv)";
            Query query = em.createQuery(sql);
            query.setParameter("prateleiraliv", "%" + prateleiraliv + "%");
            Long count = (Long) query.getSingleResult();
            existe = count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return existe;

    }

}
