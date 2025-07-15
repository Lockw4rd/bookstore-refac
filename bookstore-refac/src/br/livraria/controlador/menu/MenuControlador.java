package br.livraria.controlador.menu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.livraria.factory.TableFactory;

public class MenuControlador {
	
	private MenuControlador() {}
	
	public static void zerarSistema() {
		
		TableFactory.createTables();
		
		TableFactory.createAdministrador();
		
		TableFactory.createFuncionario();
		TableFactory.createEditora();
		TableFactory.createLivro();
		TableFactory.createCliente();
		
		JOptionPane.showMessageDialog(new JFrame(), "Todos os dados foram deletados!");
	}

}
