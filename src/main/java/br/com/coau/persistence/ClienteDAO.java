/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.coau.persistence;

import java.util.List;

/**
 *
 * @author Warley
 */
public interface ClienteDAO {
     void adicionarClientes(Cliente c);

    List<Cliente> listarClientes();

    List<Cliente> pesquisarClientes(String filtro);

    void editarCliente(String nomecli, String fonecli, String facucli, String emailcli, long idcli);

    void excluirCliente(long idcli);

    boolean temEmprestimosAtivos(long clienteId);
     Cliente buscarClientePorId(long idcli);

}
