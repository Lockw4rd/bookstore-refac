package br.livraria.controlador.menu;

import br.livraria.dao.FuncionarioDAO;
import br.livraria.model.Funcionario;

import br.livraria.util.TratamentoErro;

// Exceção específica para autenticação
public class AutenticacaoException extends Exception {
    public AutenticacaoException(String mensagem) {
        super(mensagem);
    }
}