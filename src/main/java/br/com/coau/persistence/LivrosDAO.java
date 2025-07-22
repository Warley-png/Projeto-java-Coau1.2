package br.com.coau.persistence;

import java.util.List;

public interface LivrosDAO {

    void cadastrarLivro(Livros livro);

    List<Livros> listarLivros();

    List<Livros> pesquisarLivros(String filtro);

    void editarLivro(String nomeliv, String autorliv, String assuntoliv, String editoraliv,
            String anorev, String volumerev, String prateleira, String tipo, long idliv);

    void excluirlivro(long idliv);

    boolean EmprestimosAtivos(long idliv);

    Livros buscarLivroPorId(long idliv);

    boolean alugarLivro(long clienteId, long livroId);

    boolean devolverLivro(long livroId);

    List<AlugarLivro> listarEmprestimos();

    List<AlugarLivro> pesquisarEmprestimo(String filtro);

    List<Livros> pesquisarlivros(String prateleiraliv);

    boolean prateleiracad(String prateleiraliv);

}
