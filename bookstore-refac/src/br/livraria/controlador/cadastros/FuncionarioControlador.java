package br.livraria.controlador.cadastros;

import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.livraria.dao.FuncionarioDAO;
import br.livraria.dao.PessoaDAO;
import br.livraria.model.Funcionario;
import br.livraria.util.Convert;

import br.livraria.util.TratamentoErro;

public class FuncionarioControlador {

    private FuncionarioControlador() {
    }

    private static Object[][] get(String busca) {
        Object[][] tabela;

        Vector<Funcionario> funcionarios = FuncionarioDAO.getFuncionarios();

        if (!busca.isEmpty()) {
            funcionarios = filtrarFuncionariosPorBusca(funcionarios, busca);
        }

        tabela = new Object[funcionarios.size()][11];

        for (int i = 0; i < funcionarios.size(); i++) {
            Funcionario funcionario = funcionarios.get(i);
            tabela[i] = new Object[]{
                    funcionario.getIdFuncionario(), funcionario.getNome(), funcionario.getCpf(),
                    funcionario.getDataContrato(), funcionario.getEndereco(), funcionario.getCidade(),
                    funcionario.getEstado(), funcionario.getTelefone(), funcionario.getEmail(),
                    funcionario.getSenha(), funcionario.printPost()
            };
        }

        return tabela;
    }

    private static Vector<Funcionario> filtrarFuncionariosPorBusca(Vector<Funcionario> funcionarios, String busca) {
        Vector<Funcionario> funcionariosFiltrados = new Vector<>();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getNome().toLowerCase().contains(busca.toLowerCase())) {
                funcionariosFiltrados.add(funcionario);
            }
        }
        return funcionariosFiltrados;
    }

    public static DefaultTableModel updateTable(String busca) {
        return new DefaultTableModel(
                get(busca),
                new String[]{
                        "ID", "Nome", "CPF", "Contrato", "Endereco", "Cidade", "Estado", "Telefone",
                        "Email", "Senha", "Cargo"
                }
        );
    }

    public static boolean delete(String idFuncionario) {
        int id = parseId(idFuncionario);
        if (id == -1) {
            TratamentoErro.exibirMensagem("Insira um número válido");
            return false;
        }

        Funcionario funcionario = FuncionarioDAO.getFuncionarioById(id);
        if (funcionario != null) {
            FuncionarioDAO.deleteByID(id);
            PessoaDAO.deleteByCpf(funcionario.getCpf());
            return true;
        }

        TratamentoErro.exibirMensagem("Funcionário não consta na base de dados");
        return false;
    }

    public static Funcionario search(String idFuncionario) {
        int id = parseId(idFuncionario);
        if (id == -1) {
            TratamentoErro.exibirMensagem("Insira um número válido");
            return null;
        }

        Funcionario funcionario = FuncionarioDAO.getFuncionarioById(id);
        if (funcionario == null) {
            TratamentoErro.exibirMensagem("Funcionário não consta na base de dados");
            return null;
        }

        return funcionario;
    }

    public static boolean save(String campoCpf, String campoNome, String campoEndereco,
                               String campoCidade, String campoEstado, String campoTelefone,
                               String campoEmail, String campoSenha, String campoDataContrato,
                               boolean administrador) {

        
    	/*
		 * Converter as Strings para seus respectivos tipos primitivos
		 */
    	
    	Date dataContrato = Convert.parseDate(campoDataContrato);

        if (!validarCampos(campoCpf, campoNome, campoSenha)) {
            return false;
        }

        if (!validarCpf(campoCpf)) {
            return false;
        }
        
        /*
		 * Identificar validacao dos campos unicos
		 */
		

        if (PessoaDAO.getPessoaByCpf(campoCpf) != null) {
            TratamentoErro.exibirMensagem("CPF já consta na base de dados");
            return false;
        }

        if (!validarEstado(campoEstado)) {
            return false;
        }

        Funcionario funcionario = new Funcionario(campoCpf, campoNome, campoEndereco, campoCidade,
                campoEstado, campoTelefone, campoEmail, campoSenha, dataContrato, administrador);
        FuncionarioDAO.save(funcionario);

        return true;
    }

    public static boolean update(String campoCpf, String campoNome, String campoEndereco,
                                 String campoCidade, String campoEstado, String campoTelefone,
                                 String campoEmail, String campoSenha, String campoDataContrato,
                                 boolean administrador, String campoIdFuncionario) {

    	/*
		 * Converter as Strings para seus respectivos tipos primitivos
		 */
    	
    	Date dataContrato = Convert.parseDate(campoDataContrato);

        int idFuncionario = parseId(campoIdFuncionario);
        if (idFuncionario == -1) {
            TratamentoErro.exibirMensagem("Insira um número válido");
            return false;
        }

        if (!validarCampos(campoCpf, campoNome, campoSenha)) {
            return false;
        }

        if (!validarCpf(campoCpf)) {
            return false;
        }

        if (!validarEstado(campoEstado)) {
            return false;
        }

        Funcionario funcionario = new Funcionario(campoCpf, campoNome, campoEndereco, campoCidade,
                campoEstado, campoTelefone, campoEmail, campoSenha, dataContrato, administrador);
        funcionario.setIdFuncionario(idFuncionario);
        FuncionarioDAO.update(funcionario);

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

    private static boolean validarCampos(String cpf, String nome, String senha) {
        if (cpf.isEmpty()) {
            TratamentoErro.exibirMensagem("CPF não pode ser nulo");
            return false;
        }

        if (nome.isEmpty()) {
            TratamentoErro.exibirMensagem("Nome não pode ser nulo");
            return false;
        }

        if (senha.isEmpty()) {
            TratamentoErro.exibirMensagem("Senha não pode ser nula");
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
