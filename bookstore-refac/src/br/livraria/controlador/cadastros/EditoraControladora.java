package br.livraria.controlador.cadastros;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.livraria.dao.EditoraDAO;
import br.livraria.dao.LivroDAO;
import br.livraria.model.Editora;
import br.livraria.model.Livro;

import br.livraria.util.TratamentoErro;

public class EditoraControladora {

    private EditoraControladora() {
    }

    private static Object[][] get(String busca) {
        Vector<Editora> editoras = EditoraDAO.getEditoras();

        if (!busca.isEmpty()) {
            editoras = filtrarEditorasPorBusca(editoras, busca);
        }

        Object[][] tabela = new Object[editoras.size()][4];

        for (int i = 0; i < editoras.size(); i++) {
            Editora editora = editoras.get(i);
            tabela[i] = new Object[]{
                    editora.getIdEditora(), editora.getNome(), editora.getCnpj(), editora.getEndereco()
            };
        }

        return tabela;
    }

    private static Vector<Editora> filtrarEditorasPorBusca(Vector<Editora> editoras, String busca) {
        Vector<Editora> editorasFiltradas = new Vector<>();
        for (Editora editora : editoras) {
            if (editora.getNome().toLowerCase().contains(busca.toLowerCase())) {
                editorasFiltradas.add(editora);
            }
        }
        return editorasFiltradas;
    }

    public static DefaultTableModel updateTable(String busca) {
        return new DefaultTableModel(
                get(busca),
                new String[]{
                        "ID", "Editora", "CNPJ", "Endereco"
                }
        );
    }

    public static boolean delete(String idEditora) {
        int id = parseId(idEditora);
        if (id == -1) {
            TratamentoErro.exibirMensagem("Insira um número válido");
            return false;
        }

        Editora editora = EditoraDAO.getEditoraById(id);
        if (editora != null) {
            deletarLivrosDaEditora(editora);
            EditoraDAO.deleteByID(id);
            return true;
        }

        TratamentoErro.exibirMensagem("Editora não consta na base de dados");
        return false;
    }

    private static void deletarLivrosDaEditora(Editora editora) {
        Vector<Livro> livros = LivroDAO.getLivros();
        for (Livro livro : livros) {
            if (livro.getEditora().getIdEditora() == editora.getIdEditora()) {
                LivroDAO.deleteByID(livro.getIdLivro());
            }
        }
    }

    public static Editora search(String idEditora) {
        int id = parseId(idEditora);
        if (id == -1) {
            TratamentoErro.exibirMensagem("Insira um número válido");
            return null;
        }

        Editora editora = EditoraDAO.getEditoraById(id);
        if (editora == null) {
            TratamentoErro.exibirMensagem("Editora não consta na base de dados");
            return null;
        }

        return editora;
    }

    public static boolean save(String campoNome, String campoCNPJ, String campoEndereco) {
    	
    	/*
		 * Converter as Strings para seus respectivos tipos primitivos
		 */
		
		/*
		 * Identificar preenchimento do campos nulos
		 */
    	
        if (!validarCampos(campoNome, campoCNPJ)) {
            return false;
        }

        if (!validarCnpj(campoCNPJ)) {
            return false;
        }
        
        /*
		 * Identificar validacao dos campos unicos
		 */

        if (EditoraDAO.existeEditoraComCnpj(campoCNPJ)) {
            TratamentoErro.exibirMensagem("CNPJ já consta na base de dados");
            return false;
        }

        
        Editora editora = new Editora(campoNome, campoCNPJ, campoEndereco);
        EditoraDAO.save(editora);

        return true;
    }

    public static boolean update(String campoNome, String campoCNPJ, String campoEndereco, String campoIdEditora) {
        
    	/*
		 * Converter as Strings para seus respectivos tipos primitivos
		 */
    	
    	int idEditora = parseId(campoIdEditora);
        if (idEditora == -1) {
            TratamentoErro.exibirMensagem("Insira um número válido");
            return false;
        }

        if (!validarCampos(campoNome, campoCNPJ)) {
            return false;
        }

        if (!validarCnpj(campoCNPJ)) {
            return false;
        }

        if (EditoraDAO.existeEditoraComCnpj(campoCNPJ)) {
            TratamentoErro.exibirMensagem("CNPJ já consta na base de dados");
            return false;
        }

        Editora editora = new Editora(campoNome, campoCNPJ, campoEndereco);
        editora.setIdEditora(idEditora);
        EditoraDAO.update(editora);

        return true;
    }

    private static int parseId(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /*
	 * Identificar preenchimento do campos nulos
	 */

    private static boolean validarCampos(String nome, String cnpj) {
        if (nome.isEmpty()) {
            TratamentoErro.exibirMensagem("Nome não pode ser nulo");
            return false;
        }

        if (cnpj.isEmpty()) {
            TratamentoErro.exibirMensagem("CNPJ não pode ser nulo");
            return false;
        }

        return true;
    }

    /*
	 * Identificar outras validacoes
	 */
    
    private static boolean validarCnpj(String cnpj) {
        if (cnpj.length() != 14) {
            TratamentoErro.exibirMensagem("CNPJ precisa conter 14 caracteres");
            return false;
        }
        return true;
    }

}
