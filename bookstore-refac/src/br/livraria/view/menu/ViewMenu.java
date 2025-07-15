package br.livraria.view.menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.livraria.controlador.menu.LoginControlador;
import br.livraria.controlador.menu.MenuControlador;
import br.livraria.controlador.operacional.DevolucaoControlador;
import br.livraria.view.cadastros.cliente.ViewCliente;
import br.livraria.view.cadastros.editora.ViewEditora;
import br.livraria.view.cadastros.funcionario.ViewFuncionario;
import br.livraria.view.cadastros.livro.ViewLivro;
import br.livraria.view.operacional.ViewDevolucao;
import br.livraria.view.operacional.ViewHistorico;
import br.livraria.view.operacional.ViewVenda;

public class ViewMenu extends JFrame {

	private static final long serialVersionUID = -7783528672230826524L;
	
	private JPanel contentPane;
	
	public ViewMenu() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Menu Principal");
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNome = new JLabel(LoginControlador.getFuncionarioLogado().getNome());
		labelNome.setHorizontalAlignment(SwingConstants.LEFT);
		labelNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		labelNome.setBounds(490, 23, 484, 46);
		contentPane.add(labelNome);
		
		JLabel lblNewLabel = new JLabel("Cadastros");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(30, 101, 206, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sistema");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(750, 101, 206, 46);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Operacional");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(379, 101, 206, 46);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblCargo = new JLabel(LoginControlador.getFuncionarioLogado().printPost() + " : ");
		lblCargo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCargo.setBounds(10, 23, 470, 46);
		contentPane.add(lblCargo);
		
		JButton btnEditora = new JButton("Editoras");
		btnEditora.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewEditora();
				dispose();
			}
		});
		btnEditora.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditora.setBounds(30, 160, 206, 46);
		contentPane.add(btnEditora);
		
		JButton btnFuncionario = new JButton("Funcionarios");
		btnFuncionario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewFuncionario();
				dispose();
			}
		});
		btnFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFuncionario.setBounds(30, 230, 206, 46);
		contentPane.add(btnFuncionario);
		
		JButton btnLivro = new JButton("Livros");
		btnLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewLivro();
				dispose();
			}
		});
		btnLivro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLivro.setBounds(30, 300, 206, 46);
		contentPane.add(btnLivro);
		
		JButton btnCliente = new JButton("Clientes");
		btnCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewCliente();
				dispose();
			}
		});
		btnCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCliente.setBounds(30, 370, 206, 46);
		contentPane.add(btnCliente);
		
		JButton btnHistorico = new JButton("Ver Historico");
		btnHistorico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewHistorico();
				dispose();
			}
		});
		btnHistorico.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHistorico.setBounds(379, 160, 206, 46);
		contentPane.add(btnHistorico);
		
		JButton btnVender = new JButton("Vender");
		btnVender.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewVenda();
				dispose();
			}
		});
		btnVender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVender.setBounds(379, 230, 206, 46);
		contentPane.add(btnVender);
		
		JButton btnZerar = new JButton("Zerar Sistema");
		btnZerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuControlador.zerarSistema();
				new ViewLogin();
				dispose();
			}
		});
		btnZerar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnZerar.setBounds(750, 160, 206, 46);
		contentPane.add(btnZerar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewLogin();
				dispose();
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSair.setBounds(750, 230, 206, 46);
		contentPane.add(btnSair);
		
		JButton btnDevolucao = new JButton("Devolucao");
		btnDevolucao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DevolucaoControlador.abrirDevolucao()) {
					new ViewDevolucao();
					dispose();
				}
				
			}
		});
		btnDevolucao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDevolucao.setBounds(379, 300, 206, 46);
		contentPane.add(btnDevolucao);
		
		setVisible(true);
	}
}
