package br.livraria.view.cadastros.cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.livraria.controlador.cadastros.PessoaControladora;

public class NewCliente {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textEndereco;
	private JTextField textCidade;
	private JTextField textEstado;
	private JTextField textTelefone;
	private JTextField textEmail;

	public NewCliente() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Novo Funcionario");
		frame.setLocationRelativeTo(null);
		
		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNome.setColumns(10);
		textNome.setBounds(30, 130, 250, 46);
		frame.getContentPane().add(textNome);
		
		textCpf = new JTextField();
		textCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCpf.setColumns(10);
		textCpf.setBounds(360, 130, 250, 46);
		frame.getContentPane().add(textCpf);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(PessoaControladora.save(textCpf.getText(), textNome.getText(), textEndereco.getText(), 
						textCidade.getText(), textEstado.getText(), textTelefone.getText(), 
						textEmail.getText())) {
					new ViewCliente();
					frame.dispose();
				}
			}
		});
		btnSalvar.setForeground(Color.BLACK);
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setBounds(664, 704, 150, 46);
		frame.getContentPane().add(btnSalvar);
		
		JButton btnCancelar = new JButton("Voltar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ViewCliente();
				frame.dispose();
			}
		});
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBounds(824, 704, 150, 46);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblNome = new JLabel("Nome*");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(30, 60, 250, 46);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCp = new JLabel("CPF (11)*");
		lblCp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCp.setBounds(360, 60, 250, 46);
		frame.getContentPane().add(lblCp);
		
		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereco.setBounds(30, 200, 250, 46);
		frame.getContentPane().add(lblEndereco);
		
		textEndereco = new JTextField();
		textEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEndereco.setColumns(10);
		textEndereco.setBounds(30, 270, 250, 46);
		frame.getContentPane().add(textEndereco);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCidade.setBounds(360, 200, 250, 46);
		frame.getContentPane().add(lblCidade);
		
		textCidade = new JTextField();
		textCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCidade.setColumns(10);
		textCidade.setBounds(360, 270, 250, 46);
		frame.getContentPane().add(textCidade);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstado.setBounds(690, 200, 250, 46);
		frame.getContentPane().add(lblEstado);
		
		textEstado = new JTextField();
		textEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEstado.setColumns(10);
		textEstado.setBounds(690, 270, 250, 46);
		frame.getContentPane().add(textEstado);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefone.setBounds(30, 340, 250, 46);
		frame.getContentPane().add(lblTelefone);
		
		textTelefone = new JTextField();
		textTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTelefone.setColumns(10);
		textTelefone.setBounds(30, 410, 250, 46);
		frame.getContentPane().add(textTelefone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(360, 340, 250, 46);
		frame.getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setColumns(10);
		textEmail.setBounds(360, 410, 250, 46);
		frame.getContentPane().add(textEmail);
		
		frame.setVisible(true);
	}
}
