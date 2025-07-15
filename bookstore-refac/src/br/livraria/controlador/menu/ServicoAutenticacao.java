package br.livraria.controlador.menu;

import br.livraria.dao.FuncionarioDAO;
import br.livraria.model.Funcionario;

import br.livraria.util.TratamentoErro;

// Serviço de autenticação (sem dependência gráfica)
public class ServicoAutenticacao {

    private final FuncionarioDAO funcionarioDAO;

    public ServicoAutenticacao(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    public Funcionario autenticar(String idUsuario, String senha) throws AutenticacaoException {
        int id;

        try {
            id = Integer.parseInt(idUsuario);
        } catch (NumberFormatException e) {
            throw new AutenticacaoException("ID do usuário precisa ser um número válido.");
        }

        Funcionario funcionario = funcionarioDAO.getFuncionarioById(id);

        if (funcionario == null) {
            throw new AutenticacaoException("ID do usuário não encontrado.");
        }

        if (!funcionario.getSenha().equals(senha)) {
            throw new AutenticacaoException("Senha inválida.");
        }

        return funcionario;
    }
}