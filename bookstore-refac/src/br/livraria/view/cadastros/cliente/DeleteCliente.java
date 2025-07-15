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
import br.livraria.model.Pessoa;

public class DeleteCliente {

	private JFrame frame;
	private JLabel textNome;
	private JTextField textId;
	private JLabel textCpf;
	private JLabel textEndereco;
	private JLabel textCidade;
	private JLabel textEstado;
	private JLabel textTelefone;
	private JLabel textEmail;

	public DeleteCliente() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Deletar Funcionario");
		frame.setLocationRelativeTo(null);
		
		textNome = new JLabel("");
		textNome.setFont(new Font("Tahoma", Font.BOLD, 16));
		textNome.setBounds(30, 170, 250, 46);
		frame.getContentPane().add(textNome);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeletar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PessoaControladora.delete(textId.getText())) {
					new ViewCliente();
					frame.dispose();
				} else {
					textId.setText("");
				}
			}
		});
		btnDeletar.setForeground(Color.BLACK);
		btnDeletar.setBackground(Color.WHITE);
		btnDeletar.setBounds(650, 704, 150, 46);
		btnDeletar.setEnabled(false);
		frame.getContentPane().add(btnDeletar);
		
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
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(30, 100, 250, 46);
		frame.getContentPane().add(lblNome);
		
		textCpf = new JLabel("");
		textCpf.setFont(new Font("Tahoma", Font.BOLD, 16));
		textCpf.setBounds(360, 170, 250, 46);
		frame.getContentPane().add(textCpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCpf.setBounds(360, 100, 250, 46);
		frame.getContentPane().add(lblCpf);
		
		textId = new JTextField();
		textId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textId.setBounds(30, 11, 300, 46);
		frame.getContentPane().add(textId);
		textId.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Pessoa pessoa = PessoaControladora.search(textId.getText());
				if(pessoa != null) {
					textId.setText(pessoa.getCpf() + ""); 
					textNome.setText(pessoa.getNome());
					textCpf.setText(pessoa.getCpf());
					textEndereco.setText(pessoa.getEndereco());
					textCidade.setText(pessoa.getCidade());
					textEstado.setText(pessoa.getEstado());
					textTelefone.setText(pessoa.getTelefone());
					textEmail.setText(pessoa.getEmail());
					btnDeletar.setEnabled(true);
				} else {
					textId.setText(""); 
					textNome.setText("");
					textCpf.setText("");
					textEndereco.setText("");
					textCidade.setText("");
					textEstado.setText("");
					textTelefone.setText("");
					textEmail.setText("");
					btnDeletar.setEnabled(false);
				}
				
			}
		});
		btnPesquisar.setBounds(340, 11, 150, 46);
		frame.getContentPane().add(btnPesquisar);
		
		textEndereco = new JLabel("");
		textEndereco.setFont(new Font("Tahoma", Font.BOLD, 16));
		textEndereco.setBounds(30, 310, 250, 46);
		frame.getContentPane().add(textEndereco);
		
		textCidade = new JLabel("");
		textCidade.setFont(new Font("Tahoma", Font.BOLD, 16));
		textCidade.setBounds(360, 310, 250, 46);
		frame.getContentPane().add(textCidade);
		
		textEstado = new JLabel("");
		textEstado.setFont(new Font("Tahoma", Font.BOLD, 16));
		textEstado.setBounds(690, 310, 250, 46);
		frame.getContentPane().add(textEstado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstado.setBounds(690, 240, 250, 46);
		frame.getContentPane().add(lblEstado);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCidade.setBounds(360, 240, 250, 46);
		frame.getContentPane().add(lblCidade);
		
		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereco.setBounds(30, 240, 250, 46);
		frame.getContentPane().add(lblEndereco);
		
		textTelefone = new JLabel("");
		textTelefone.setFont(new Font("Tahoma", Font.BOLD, 16));
		textTelefone.setBounds(30, 450, 250, 46);
		frame.getContentPane().add(textTelefone);
		
		textEmail = new JLabel("");
		textEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		textEmail.setBounds(360, 450, 250, 46);
		frame.getContentPane().add(textEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(360, 380, 250, 46);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefone.setBounds(30, 380, 250, 46);
		frame.getContentPane().add(lblTelefone);
		
		frame.setVisible(true);
	}
}
