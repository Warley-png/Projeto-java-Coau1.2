
package br.com.coau.telas;


import br.com.coau.persistence.JPAUtil;
import br.com.coau.persistence.Livros;
import br.com.coau.persistence.LivrosDAO;
import br.com.coau.persistence.LivrosIMPL;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Warley
 */
public class TelaCadastroLivros extends javax.swing.JInternalFrame {

    private TelaAlugarLivro telaAlugarLivro;
    private String tipoUsuario;

    
    public TelaCadastroLivros() {
        initComponents();
        listarTabela();
        atualizarTblLivros();

    }

    public TelaCadastroLivros(TelaAlugarLivro telaAlugarLivro) {
        this.telaAlugarLivro = telaAlugarLivro;
        initComponents();
        listarTabela();
        atualizarTblLivros();
    }

    private void atualizarTblLivros() {
        LivrosDAO livro = new LivrosIMPL(JPAUtil.getEntityManager());
        List<Livros> livros = livro.listarLivros();


        DefaultTableModel model = (DefaultTableModel) tblLivros.getModel();
        model.setRowCount(0);

        for (Livros l : livros) {
            model.addRow(new Object[]{
                l.getIdliv(),
                l.getNomeliv(),
                l.getAutorliv(),
                l.getAssuntoliv(),
                l.getEditoraliv(),
                l.getAno(),
                l.getVolumerev(),
                l.getPrateleiraliv(),
                l.getTipo()
            });
        }
    }

    private void listarTabela() {
        atualizarTblLivros();
    }

    private void pesquisarLivro() {
        String nome = txtPesquisarLivro.getText();
         LivrosDAO livro = new LivrosIMPL(JPAUtil.getEntityManager());
        List<Livros> livros = livro.pesquisarLivros(nome);
        DefaultTableModel tabela = (DefaultTableModel) tblLivros.getModel();
        tabela.setRowCount(0);
        if (livros != null && !livros.isEmpty()) {
            for (Livros l : livros) {
                tabela.addRow(new Object[]{
                    l.getIdliv(),
                    l.getNomeliv(),
                    l.getAutorliv(),
                    l.getAssuntoliv(),
                    l.getEditoraliv(),
                    l.getAno(),
                    l.getVolumerev(),
                    l.getPrateleiraliv(),
                    l.getTipo()
                });
            }
        } else {
            System.out.println("Nenhum Livro encontrado para este Título !");
        }

    }

    public void setar_campos() {
        int setar = tblLivros.getSelectedRow();
        txtTitulo.setText(tblLivros.getModel().getValueAt(setar, 1).toString());
        txtAutor.setText(tblLivros.getModel().getValueAt(setar, 2).toString());
        txtAssunto.setText(tblLivros.getModel().getValueAt(setar, 3).toString());
        txtEditora.setText(tblLivros.getModel().getValueAt(setar, 4).toString());
        txtAno.setText(tblLivros.getModel().getValueAt(setar, 5).toString());
        txtVolumerev.setText(tblLivros.getModel().getValueAt(setar, 6).toString());
        cbPrateleira.setSelectedItem(tblLivros.getModel().getValueAt(setar, 7).toString());
        cboTipo.setSelectedItem(tblLivros.getModel().getValueAt(setar, 8).toString());
        //desabilitar o botão adicionar
        btnAdicionar.setEnabled(false);

    }

    public long getLivroSelecionadoId() {
        int selectedRow = tblLivros.getSelectedRow();
        if (selectedRow != -1) {
            return (Long) tblLivros.getModel().getValueAt(selectedRow, 0);
        }
        return -1;
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
        txtId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnConsultar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtAutor = new javax.swing.JTextField();
        txtAssunto = new javax.swing.JTextField();
        txtEditora = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLivros = new javax.swing.JTable();
        txtPesquisarLivro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbPrateleira = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAno = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtVolumerev = new javax.swing.JTextField();
        cboTipo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jLabel6.setText("ID");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/read.png"))); // NOI18N
        btnConsultar.setToolTipText("Consultar");
        btnConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultar.setPreferredSize(new java.awt.Dimension(80, 80));

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro e Edição de Livros");
        setMinimumSize(new java.awt.Dimension(100, 100));

        jLabel2.setText("Título");

        jLabel3.setText("Autor");

        jLabel4.setText("Assunto");

        jLabel5.setText("Editora");

        txtTitulo.setToolTipText("Nome do Livro");

        txtAutor.setToolTipText("Autor do Livro");

        txtAssunto.setToolTipText("Assunto do livro");

        txtEditora.setToolTipText("Editora do livro");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/create.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar Livro");
        btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAdicionar.setRequestFocusEnabled(false);
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/update.png"))); // NOI18N
        btnEditar.setToolTipText("Editar Livro");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/delete.png"))); // NOI18N
        btnDeletar.setToolTipText("Deletar Livro");
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        tblLivros = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;

            }

        };
        tblLivros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "TÍTULO", "AUTOR", "ASSUNTO", "EDITORA", "ANO", "VOLUME", "PRATELEIRA", "TIPO "
            }
        ));
        tblLivros.setFocusable(false);
        tblLivros.getTableHeader().setReorderingAllowed(false);
        tblLivros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLivrosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblLivros);

        txtPesquisarLivro.setToolTipText("Pesquisa pelo Título do Livro");
        txtPesquisarLivro.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPesquisarLivroCaretUpdate(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/pesquisa1.png"))); // NOI18N

        cbPrateleira.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1- Maíra", "2- Arthur ", "3- João", "4- Lúcia Maria", "5- Aristides", "6- Ruth  (Revistas) ", "7- Mateus (revistas)", "8-Pedro (revistas) ", "9- Bete (livros)", "10-Chico (livros) ", "11-Gabriela (livros)", "12-- Letícia(revistas )", "13- Luiz (livros ) ", "14- Miguel (revistas )", "15- Luiza (revistas)", "16-Mariana(livros)", "17- Nathália (revistas) ", "18- Filipe (livros )", "19- Sabrina (livros )", "20- Maria Fernanda (livros )", "21- João Pedro (livros )", "22- Martha (livros)", "23-- Marcos (livros )", "24- Raquel (livros )", "25- Tia Vitória (livros)", "26-Tio Tarcísio(livros)", "27-- Túlio(livros)", "28- Tia Ieda(livros)", "29- Tio Duda(livros)", "30- Vô Darcy(livros)", "31- Vô Alair (livros )", "32- Vó Rute(livros)", "33-- Glória (livros)", "34-- Léa", "35- Bernardo", "36-- Leta", "37- Montanha", "38-Ro (livros)  ", "39-- Silke (livros)", "40- Ana Baltazar (livros )", "41- Alícia Penna (livros )", "42- Viviane Zerlotini (livros)", "43-Eduardo Bittencourt (livros)", "44- Warley (livros)", "45- Bruno Giacomini (livros)", "46- Ana Loures", "47- Oscar Clovis", "48-César Gandola (livro )", "49- Aurélio (livros) ", "50- Diego Fonseca (livros)", "51- Nego (livros)", "52- Bruna Marinho (livros)", "53-- Bruna Medici (livros)", "54- Izabella Shatler (livros)", "55-- Laís Grossi (livros)", "56- Ciça Ventania (livros)", "57-- Carina Guedes (livros)", "58- Tais Clark (livros)", "59-  Thais Lopes(livros)", "60-Frei Gilvander", "61-Joviano Mayer(livros)", "62-Bella Goçalves(livros)", "63-Luiz Fernando(livro)", "64-Rafael Bittencourt(livro)", "65-Pedro Otoni", "66-Léo Pericles", "67-Poliana Souza(livro)", "68-Letícia Notini(livro)", "69-Iara Pezzzuti", "70-Astor Piazolla(livro)", "71-Beethoven(livro)", "72-Mozart(livro)", "73-Bach(livro)", " " }));

        jLabel8.setText("Prateleira");

        jLabel9.setText("Ano");

        jLabel10.setText("Volume");

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Livro ", "Revista ", " " }));

        jLabel11.setText("Tipo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(145, 145, 145)
                                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbPrateleira, 0, 224, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtAssunto)
                                            .addComponent(txtAno, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel11)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtVolumerev)
                                    .addComponent(txtEditora)
                                    .addComponent(cboTipo, 0, 229, Short.MAX_VALUE))))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(txtPesquisarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(356, 356, 356)
                                .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtVolumerev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPrateleira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        setBounds(0, 0, 650, 500);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        //Adicionando um livro
       Livros l = new Livros();
        try {
            l.setNomeliv(txtTitulo.getText());
            l.setAutorliv(txtAutor.getText());
            l.setAssuntoliv(txtAssunto.getText());
            l.setEditoraliv(txtEditora.getText());
            l.setAno(txtAno.getText());
            l.setVolumerev(txtVolumerev.getText());
            l.setPrateleiraliv(cbPrateleira.getSelectedItem().toString());
            l.setTipo(cboTipo.getSelectedItem().toString());
            l.setDisponivelliv(true);

            LivrosDAO livro = new LivrosIMPL(JPAUtil.getEntityManager());
            livro.cadastrarLivro(l);
            JOptionPane.showMessageDialog(this, "Livro Cadastrado com Sucesso!");
            atualizarTblLivros();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu uma falha:\n" + e.getMessage());
        }
        //Codigo abaixo para limpar a tela apos final cadastro
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAssunto.setText("");
        txtEditora.setText("");
        txtAno.setText("");
        txtVolumerev.setText("");
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        
         int setar = tblLivros.getSelectedRow();
        if (setar != -1) {
            long id = (Long) tblLivros.getModel().getValueAt(setar, 0);
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String assunto = txtAssunto.getText();
            String editora = txtEditora.getText();
            String ano = txtAno.getText();
            String volume = txtVolumerev.getText();
            String prateleiraliv = cbPrateleira.getSelectedItem().toString();
            String tipo = cboTipo.getSelectedItem().toString();

           LivrosDAO livro = new LivrosIMPL(JPAUtil.getEntityManager());
            livro.editarLivro(titulo, autor, assunto, editora, ano, volume, prateleiraliv, tipo, id);
            JOptionPane.showMessageDialog(this, "Dados do Livro atualizado com sucesso!");
            atualizarTblLivros();
            txtTitulo.setText("");
            txtAutor.setText("");
            txtAssunto.setText("");
            txtEditora.setText("");
            txtAno.setText("");
            txtVolumerev.setText("");
            btnAdicionar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Título para editar.");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtPesquisarLivroCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPesquisarLivroCaretUpdate
    
        pesquisarLivro();
    }//GEN-LAST:event_txtPesquisarLivroCaretUpdate

    private void tblLivrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLivrosMouseClicked
       
        setar_campos();
    }//GEN-LAST:event_tblLivrosMouseClicked

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        
       int setar = tblLivros.getSelectedRow(); // Obtém a linha selecionada na tabela
        if (setar != -1) {
            long id = (Long) tblLivros.getModel().getValueAt(setar, 0); // Obtém o ID do livro da tabela

            LivrosDAO livro = new LivrosIMPL(JPAUtil.getEntityManager());

            // Verifica se o livro tem empréstimos ativos
            if (livro.EmprestimosAtivos(id)) {
                JOptionPane.showMessageDialog(this, "Este Livro não pode ser excluído porque ainda possui empréstimos ativos.");

                txtTitulo.setText("");
                txtAutor.setText("");
                txtAssunto.setText("");
                txtEditora.setText("");
                txtAno.setText("");
                txtVolumerev.setText("");
                btnAdicionar.setEnabled(true);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este Livro?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    livro.excluirlivro(id);
                    JOptionPane.showMessageDialog(this, "Livro excluído com sucesso!");

                    txtTitulo.setText("");
                    txtAutor.setText("");
                    txtAssunto.setText("");
                    txtEditora.setText("");
                    txtAno.setText("");
                    txtVolumerev.setText("");
                    btnAdicionar.setEnabled(true);

                    atualizarTblLivros();
                } catch (Exception e) {

                    JOptionPane.showMessageDialog(this, "Erro ao excluir Livro: " + e.getMessage());

                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Livro para excluir.");
        }

    }//GEN-LAST:event_btnDeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JComboBox<String> cbPrateleira;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tblLivros;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtAssunto;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtEditora;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPesquisarLivro;
    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtVolumerev;
    // End of variables declaration//GEN-END:variables

}
