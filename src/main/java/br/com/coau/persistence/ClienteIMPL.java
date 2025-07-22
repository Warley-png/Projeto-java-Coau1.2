
package br.com.coau.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class ClienteIMPL implements ClienteDAO {
    private EntityManager em;

    public ClienteIMPL(EntityManager em) {
        this.em = em;
    }
    
 @Override
    public void adicionarClientes(Cliente c) {
       
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } 

        
    }

    @Override
    public List<Cliente> listarClientes() {

        List<Cliente> clientes = new ArrayList<>();
        try {
            Query consulta = em.createQuery("SELECT c FROM Cliente c");
            clientes = consulta.getResultList();
        } catch(Exception e){
            e.printStackTrace();
        }
        return clientes;
        
    }


        
    

    @Override
    public List<Cliente> pesquisarClientes(String filtro) {

      
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
        } 

        return resultados;

    
    }

    @Override
    public void editarCliente(String nomecli, String fonecli, String facucli, String emailcli, long idcli) {
      
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
        } 

    }

    @Override
    public void excluirCliente(long idcli) {
      
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
        }

    }

    @Override
    public boolean temEmprestimosAtivos(long clienteId) {
        
        try {
            String sql = "SELECT COUNT(r) FROM AlugarLivro r WHERE r.cliente.idcli = :clienteId AND r.status = true";
            Query query = em.createQuery(sql);
            query.setParameter("clienteId", clienteId);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }

    @Override
    public Cliente buscarClientePorId(long idcli) {
        
       
        Cliente cliente = null;

        try {

            cliente = em.find(Cliente.class, idcli);
        } catch (Exception e) {
            e.printStackTrace();
        } 

        return cliente;
    }

}
    
    


