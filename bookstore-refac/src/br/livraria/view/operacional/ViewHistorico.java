package br.livraria.view.operacional;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.livraria.controlador.operacional.HistoricoControlador;
import br.livraria.view.menu.ViewMenu;

public class ViewHistorico {

	private JFrame frame;
	private JTable table;
	
	public ViewHistorico() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Historico");
		frame.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 964, 682);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(HistoricoControlador.updateTable());
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewMenu();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(824, 704, 150, 46);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
}