package br.livraria.controlador.cadastros;

import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.livraria.dao.EditoraDAO;
import br.livraria.dao.LivroDAO;
import br.livraria.model.Editora;
import br.livraria.model.Livro;
import br.livraria.util.Convert;
import br.livraria.util.TratamentoErro;

public class LivroControlador {

    private LivroControlador() {
    }

    private static Object[][] get(String busca) {
        Object[][] tabela;

        Vector<Livro> livros = LivroDAO.getLivros();

        if (!busca.isEmpty()) {
            livros = filtrarLivrosPorBusca(livros, busca);
        }

        tabela = new Object[livros.size()][8];

        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            tabela[i] = new Object[]{
                    livro.getIdLivro(), livro.getPrecoUnit(), livro.getTitulo(), livro.getGenero(),
                    livro.getAutor(), livro.getDataPublicada(), livro.getQtdEstoque(),
                    livro.getEditora().getNome()
            };
        }

        return tabela;
    }

    private static Vector<Livro> filtrarLivrosPorBusca(Vector<Livro> livros, String busca) {
        Vector<Livro> livrosFiltrados = new Vector<>();
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(busca.toLowerCase())) {
                livrosFiltrados.add(livro);
            }
        }
        return livrosFiltrados;
    }

    public static DefaultTableModel updateTable(String busca) {
        return new DefaultTableModel(
                get(busca),
                new String[]{
                        "ID", "Preco", "Titulo", "Genero", "Autor", "Publicacao", "Estoque", "Editora"
                }
        );
    }

    public static boolean delete(String idLivro) {
        int id;
        try {
            id = Integer.parseInt(idLivro);
        } catch (NumberFormatException e) {
            TratamentoErro.exibirMensagem("Insira um numero valido");
            return false;
        }

        Livro livro = LivroDAO.getLivroById(id);
        if (livro != null) {
            LivroDAO.deleteByID(id);
            return true;
        }

        TratamentoErro.exibirMensagem("Livro nao consta na base de dados");
        return false;
    }

    public static Livro search(String idLivro) {
        int id;
        try {
            id = Integer.parseInt(idLivro);
        } catch (NumberFormatException e) {
            TratamentoErro.exibirMensagem("Insira um numero valido");
            return null;
        }

        Livro livro = LivroDAO.getLivroById(id);
        if (livro == null) {
            TratamentoErro.exibirMensagem("Livro nao consta na base de dados");
            return null;
        }

        return livro;
    }

    public static boolean save(
            String campoTitulo, String campoGenero, String campoData,
            String campoAutor, String campoQtdEstoque, String campoPreco,
            String campoIdEditora) {

        if (!validarCampos(campoTitulo, campoGenero, campoData, campoQtdEstoque, campoPreco, campoIdEditora)) {
            return false;
        }
        
        /*
		 * Converter as Strings para seus respectivos tipos primitivos
		 */

        Date dataPublicada = Convert.parseDate(campoData);
        int qtdEstoque = Integer.parseInt(campoQtdEstoque);
        double preco = Double.parseDouble(campoPreco);
        int idEditora = Integer.parseInt(campoIdEditora);

        Editora editora = EditoraDAO.getEditoraById(idEditora);
        if (editora == null) {
            TratamentoErro.exibirMensagem("Editora nao consta na base de dados");
            return false;
        }

        Livro livro = new Livro(campoTitulo, campoGenero, dataPublicada, campoAutor, qtdEstoque, preco, editora);
        LivroDAO.save(livro);

        return true;
    }

    public static boolean update(String campoTitulo, String campoGenero, String campoData,
                                 String campoAutor, String campoQtdEstoque, String campoPreco,
                                 String campoIdEditora, String campoIdLivro) {

        if (!validarCampos(campoTitulo, campoGenero, campoData, campoQtdEstoque, campoPreco, campoIdEditora, campoIdLivro)) {
            return false;
        }
        
        /*
		 * Converter as Strings para seus respectivos tipos primitivos
		 */

        Date dataPublicada = Convert.parseDate(campoData);
        int qtdEstoque = Integer.parseInt(campoQtdEstoque);
        double preco = Double.parseDouble(campoPreco);
        int idEditora = Integer.parseInt(campoIdEditora);
        int idLivro = Integer.parseInt(campoIdLivro);

        Editora editora = EditoraDAO.getEditoraById(idEditora);
        if (editora == null) {
            TratamentoErro.exibirMensagem("Editora nao consta na base de dados");
            return false;
        }

        Livro livro = new Livro(campoTitulo, campoGenero, dataPublicada, campoAutor, qtdEstoque, preco, editora);
        livro.setIdLivro(idLivro);
        LivroDAO.update(livro);

        return true;
    }
    
    /*
	 * Identificar preenchimento do campos nulos
	 */

    private static boolean validarCampos(String titulo, String genero, String data, String qtdEstoque,
                                         String preco, String idEditora, String... outrosCampos) {
        if (titulo.isEmpty() || genero.isEmpty() || data.isEmpty() || qtdEstoque.isEmpty() || preco.isEmpty() || idEditora.isEmpty()) {
            TratamentoErro.exibirMensagem("Todos os campos devem ser preenchidos");
            return false;
        }

        for (String campo : outrosCampos) {
            if (campo.isEmpty()) {
                TratamentoErro.exibirMensagem("Todos os campos devem ser preenchidos");
                return false;
            }
        }

        /*
		 * Identificar outras validacoes
		 */
        
        int estoque = Integer.parseInt(qtdEstoque);
        double price = Double.parseDouble(preco);

        if (estoque < 0) {
            TratamentoErro.exibirMensagem("Quantidade Estoque precisa ser maior ou igual a 0");
            return false;
        }

        if (price <= 0) {
            TratamentoErro.exibirMensagem("Preco precisa ser maior que 0");
            return false;
        }

        return true;
    }
}
