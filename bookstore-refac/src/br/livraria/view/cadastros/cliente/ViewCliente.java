package br.livraria.view.cadastros.cliente;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.livraria.controlador.cadastros.PessoaControladora;
import br.livraria.controlador.menu.LoginControlador;
import br.livraria.view.menu.ViewMenu;

public class ViewCliente {

	private JFrame frame;
	private JTextField textField;
	private JTable table;

	public ViewCliente() {
		
		initialize();
		
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Gerenciar Clientes");
		frame.setLocationRelativeTo(null);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new NewCliente();
				frame.dispose();
			}
		});
		btnNovo.setBounds(10, 68, 150, 46);
		if(!LoginControlador.getFuncionarioLogado().isAdministrador())
			btnNovo.setEnabled(false); 
		frame.getContentPane().add(btnNovo);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(10, 11, 310, 46);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String busca = textField.getText();
				table.setModel(PessoaControladora.updateTable(busca));
			}
		});
		btnPesquisar.setBounds(330, 11, 150, 46);
		frame.getContentPane().add(btnPesquisar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 125, 964, 625);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(PessoaControladora.updateTable(""));
		scrollPane.setViewportView(table);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateCliente();
				frame.dispose();
			}
		});
		btnEditar.setBounds(170, 68, 150, 46);
		if(!LoginControlador.getFuncionarioLogado().isAdministrador())
			btnEditar.setEnabled(false); 
		frame.getContentPane().add(btnEditar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeletar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteCliente();
				frame.dispose();
			}
		});
		btnDeletar.setBounds(330, 68, 150, 46);
		if(!LoginControlador.getFuncionarioLogado().isAdministrador())
			btnDeletar.setEnabled(false); 
		frame.getContentPane().add(btnDeletar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewMenu();
				frame.dispose();
			}
		});
		btnVoltar.setBounds(490, 11, 150, 103);
		frame.getContentPane().add(btnVoltar);
		
		JLabel lblLivraria = new JLabel("Livraria");
		lblLivraria.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblLivraria.setHorizontalAlignment(SwingConstants.CENTER);
		lblLivraria.setBounds(650, 11, 324, 103);
		frame.getContentPane().add(lblLivraria);
		
		frame.setVisible(true);
	}
}