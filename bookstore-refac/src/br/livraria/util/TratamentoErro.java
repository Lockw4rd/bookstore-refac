package br.livraria.util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TratamentoErro {

	private TratamentoErro() {}

    public static void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(new JFrame(), mensagem);
    }
	
}



