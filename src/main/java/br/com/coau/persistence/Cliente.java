/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.coau.persistence;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author Warley
 */
@Entity
@Table(name= "tbclientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idcli;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlugarLivro> emprestimos;
    
    private String nomecli;
    private String fonecli;
    private String facucli;
    private String emailcli;

    public long getIdcli() {
        return idcli;
    }

    public void setIdcli(long idcli) {
        this.idcli = idcli;
    }

    public String getNomecli() {
        return nomecli;
    }

    public void setNomecli(String nomecli) {
        this.nomecli = nomecli;
    }

    public String getFonecli() {
        return fonecli;
    }

    public void setFonecli(String fonecli) {
        this.fonecli = fonecli;
    }

    public String getFacucli() {
        return facucli;
    }

    public void setFacucli(String facucli) {
        this.facucli = facucli;
    }

    public String getEmailcli() {
        return emailcli;
    }

    public void setEmailcli(String emailcli) {
        this.emailcli = emailcli;
    }
    
    // Adicione um getter e um setter para o campo emprestimos
    public List<AlugarLivro> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<AlugarLivro> emprestimos) {
        this.emprestimos = emprestimos;
    }
}