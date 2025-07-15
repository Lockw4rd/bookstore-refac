package br.livraria.controlador.cadastros;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.livraria.dao.PessoaDAO;
import br.livraria.model.Pessoa;

import br.livraria.util.TratamentoErro;

public class PessoaControladora {

    private PessoaControladora() {
    }

    private static Object[][] get(String busca) {
        Object[][] tabela;

        Vector<Pessoa> pessoas = PessoaDAO.getPessoas();

        if (!busca.isEmpty()) {
            pessoas = filtrarPessoasPorBusca(pessoas, busca);
        }

        tabela = new Object[pessoas.size()][7];

        for (int i = 0; i < pessoas.size(); i++) {
            Pessoa pessoa = pessoas.get(i);
            tabela[i] = new Object[]{
                    pessoa.getCpf(), pessoa.getNome(), pessoa.getEndereco(),
                    pessoa.getCidade(), pessoa.getEstado(), pessoa.getTelefone(), pessoa.getEmail()
            };
        }

        return tabela;
    }

    private static Vector<Pessoa> filtrarPessoasPorBusca(Vector<Pessoa> pessoas, String busca) {
        Vector<Pessoa> pessoasFiltradas = new Vector<>();
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().toLowerCase().contains(busca.toLowerCase())) {
                pessoasFiltradas.add(pessoa);
            }
        }
        return pessoasFiltradas;
    }

    public static DefaultTableModel updateTable(String busca) {
        return new DefaultTableModel(
                get(busca),
                new String[]{
                        "CPF", "Nome", "Endereco", "Cidade", "Estado", "Telefone", "Email"
                }
        );
    }

    public static boolean delete(String cpfPessoa) {
        if (!validarCpf(cpfPessoa)) {
            return false;
        }

        Pessoa pessoa = PessoaDAO.getPessoaByCpf(cpfPessoa);
        if (pessoa != null) {
            PessoaDAO.deleteByCpf(cpfPessoa);
            return true;
        }

        TratamentoErro.exibirMensagem("Cliente nao consta na base de dados");
        return false;
    }

    public static Pessoa search(String cpfPessoa) {
        if (!validarCpf(cpfPessoa)) {
            return null;
        }

        Pessoa pessoa = PessoaDAO.getPessoaByCpf(cpfPessoa);
        if (pessoa == null) {
            TratamentoErro.exibirMensagem("Cliente nao consta na base de dados");
            return null;
        }

        return pessoa;
    }

    public static boolean save(String campoCpf, String campoNome, String campoEndereco,
                               String campoCidade, String campoEstado, String campoTelefone, String campoEmail) {

    	/*
		 * Converter as Strings para seus respectivos tipos primitivos
		 */
    	
        if (!validarCampos(campoCpf, campoNome)) {
            return false;
        }

        if (!validarCpf(campoCpf)) {
            return false;
        }
        
        /*
		 * Identificar validacao dos campos unicos
		 */

        if (PessoaDAO.getPessoaByCpf(campoCpf) != null) {
            TratamentoErro.exibirMensagem("CPF ja consta na base de dados");
            return false;
        }

        if (!validarEstado(campoEstado)) {
            return false;
        }

        Pessoa pessoa = new Pessoa(campoCpf, campoNome, campoEndereco, campoCidade, campoEstado, campoTelefone, campoEmail);
        PessoaDAO.save(pessoa);

        return true;
    }

    public static boolean update(String campoCpf, String campoNome, String campoEndereco,
                                 String campoCidade, String campoEstado, String campoTelefone, String campoEmail) {
    	
    	/*
		 * Converter as Strings para seus respectivos tipos primitivos
		 */

        if (!validarCampos(campoCpf, campoNome)) {
            return false;
        }

        if (!validarCpf(campoCpf)) {
            return false;
        }

        /*
		 * Identificar validacao dos campos unicos
		 */
        
        if (PessoaDAO.getPessoaByCpf(campoCpf) == null) {
            TratamentoErro.exibirMensagem("CPF nao consta na base de dados");
            return false;
        }

        if (!validarEstado(campoEstado)) {
            return false;
        }

        Pessoa pessoa = new Pessoa(campoCpf, campoNome, campoEndereco, campoCidade, campoEstado, campoTelefone, campoEmail);
        PessoaDAO.update(pessoa);

        return true;
    }
    
    /*
	 * Identificar preenchimento do campos nulos
	 */

    private static boolean validarCampos(String cpf, String nome) {
        if (cpf.isEmpty()) {
            TratamentoErro.exibirMensagem("CPF nao pode ser nulo");
            return false;
        }

        if (nome.isEmpty()) {
            TratamentoErro.exibirMensagem("Nome nao pode ser nulo");
            return false;
        }

        return true;
    }
    
    /*
	 * Identificar outras validacoes
	 */

    private static boolean validarCpf(String cpf) {
        if (cpf.length() != 11) {
            TratamentoErro.exibirMensagem("CPF precisa conter 11 caracteres");
            return false;
        }
        return true;
    }

    private static boolean validarEstado(String estado) {
        if (!estado.isEmpty() && estado.length() != 2) {
            TratamentoErro.exibirMensagem("Estado precisa conter 2 caracteres");
            return false;
        }
        return true;
    }

}
