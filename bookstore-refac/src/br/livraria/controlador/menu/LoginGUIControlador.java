package br.livraria.controlador.menu;

import br.livraria.dao.FuncionarioDAO;
import br.livraria.model.Funcionario;

import br.livraria.util.TratamentoErro;

// Controlador separado para interface gráfica (se necessário)
public class LoginGUIControlador {

    private static Funcionario funcionarioLogado;
    private final ServicoAutenticacao servicoAutenticacao;

    public LoginGUIControlador(ServicoAutenticacao servicoAutenticacao) {
        this.servicoAutenticacao = servicoAutenticacao;
    }

    public boolean fazerLogin(String idUsuario, String senha) {
        try {
            funcionarioLogado = servicoAutenticacao.autenticar(idUsuario, senha);
            return true;
        } catch (AutenticacaoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }
}