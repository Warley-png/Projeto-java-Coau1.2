package br.com.coau.telas;

import br.com.coau.persistence.AlugarLivro;
import br.com.coau.persistence.Cliente;
import br.com.coau.persistence.ClienteDAO;
import br.com.coau.persistence.ClienteIMPL;
import br.com.coau.persistence.JPAUtil;
import br.com.coau.persistence.Livros;
import br.com.coau.persistence.LivrosDAO;
import br.com.coau.persistence.LivrosIMPL;
import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Warley
 */
public class TelaListadeLivroEmprestados extends javax.swing.JInternalFrame {

    public TelaListadeLivroEmprestados() {
        initComponents();
        listarLivrosEmprestados();
        atualizarListaEmprestados();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaEmprestimo = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Tela Lista de Livros Emprestados");

        tblListaEmprestimo = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;

            }

        };
        tblListaEmprestimo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        tblListaEmprestimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Título", "Data Empréstimo", "Data Devolução", "Nome Cliente"
            }
        ));
        tblListaEmprestimo.setFocusable(false);
        tblListaEmprestimo.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblListaEmprestimo);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/pesquisa1.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Livros Emprestados");

        txtPesquisa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPesquisa.setToolTipText("Pesquisa pelo Título do livro");
        txtPesquisa.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPesquisaCaretUpdate(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/C- azul .png"))); // NOI18N

        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Pesquisa pelo Título do Livro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel4)
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(38, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        setBounds(0, 0, 650, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPesquisaCaretUpdate

        pesquisarEmprestimo();
    }//GEN-LAST:event_txtPesquisaCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListaEmprestimo;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables

    private void listarLivrosEmprestados() {
        EntityManager em = JPAUtil.getEntityManager();
        LivrosDAO livroDAO = new LivrosIMPL(em);
        ClienteDAO clienteDAO = new ClienteIMPL(em);
        List<AlugarLivro> emprestimos = livroDAO.listarEmprestimos();
        DefaultTableModel model = (DefaultTableModel) tblListaEmprestimo.getModel();
        model.setRowCount(0);

        try {
            // Verifica se a lista de empréstimos não é nula
            if (emprestimos != null && !emprestimos.isEmpty()) {
                for (AlugarLivro emprestimo : emprestimos) {
                    Livros livro = emprestimo.getLivro();
                    Cliente cliente = clienteDAO.buscarClientePorId(emprestimo.getCliente().getIdcli());

                    if (livro != null && cliente != null) {
                        model.addRow(new Object[]{
                            emprestimo.getIdret(),
                            livro.getNomeliv(),
                            emprestimo.getDatasaida(),
                            emprestimo.getDataretorno(),
                            cliente.getNomecli()
                        });
                    } else {
                        System.out.println("Livro ou cliente não encontrado para o empréstimo ID: " + emprestimo.getIdret());
                    }
                }
            } else {
                System.out.println("Nenhum empréstimo encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprime a pilha de erros para depuração
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    private void pesquisarEmprestimo() {
        String titulo = txtPesquisa.getText();
        EntityManager em = JPAUtil.getEntityManager();
        LivrosDAO livroDAO = new LivrosIMPL(em);
        List<AlugarLivro> emprestimos = livroDAO.pesquisarEmprestimo(titulo);
        DefaultTableModel tabela = (DefaultTableModel) tblListaEmprestimo.getModel();
        tabela.setRowCount(0); // Limpa a tabela antes de adicionar novos dados

        try {
            if (emprestimos != null && !emprestimos.isEmpty()) {
                for (AlugarLivro a : emprestimos) {
                    // Obtém o cliente diretamente do objeto AlugarLivro
                    Cliente cliente = a.getCliente();
                    String nomeCliente = (cliente != null) ? cliente.getNomecli() : "Cliente não encontrado";

                    // Verifica se o livro não é nulo antes de acessar suas propriedades
                    Livros livro = a.getLivro();
                    String nomeLivro = (livro != null) ? livro.getNomeliv() : "Livro não encontrado";

                    tabela.addRow(new Object[]{
                        a.getIdret(),
                        nomeLivro,
                        a.getDatasaida(),
                        a.getDataretorno(),
                        nomeCliente
                    });
                }
            } else {
                System.out.println("Nenhum Empréstimo encontrado para este Livro !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void atualizarListaEmprestados() {
        listarLivrosEmprestados();
    }
}
