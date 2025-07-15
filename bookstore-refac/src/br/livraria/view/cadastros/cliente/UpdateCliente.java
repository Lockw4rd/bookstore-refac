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

public class UpdateCliente {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textId;
	private JTextField textEndereco;
	private JTextField textCidade;
	private JTextField textEstado;
	private JTextField textTelefone;
	private JTextField textEmail;

	public UpdateCliente() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Editar Clientes");
		frame.setLocationRelativeTo(null);
		
		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNome.setColumns(10);
		textNome.setBounds(30, 180, 250, 46);
		frame.getContentPane().add(textNome);
		
		textCpf = new JTextField();
		textCpf.setEditable(false);
		textCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCpf.setColumns(10);
		textCpf.setBounds(360, 180, 250, 46);
		frame.getContentPane().add(textCpf);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PessoaControladora.update(textCpf.getText(), textNome.getText(), textEndereco.getText(), 
						textCidade.getText(), textEstado.getText(), textTelefone.getText(), textEmail.getText())) {
					new ViewCliente();
					frame.dispose();
				}
			}
		});
		btnAtualizar.setForeground(Color.BLACK);
		btnAtualizar.setBackground(Color.WHITE);
		btnAtualizar.setBounds(643, 697, 150, 46);
		btnAtualizar.setEnabled(false);
		frame.getContentPane().add(btnAtualizar);
		
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
		btnCancelar.setBounds(803, 697, 150, 46);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblNome = new JLabel("Nome*");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(30, 110, 250, 46);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF (11)*");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCpf.setBounds(360, 110, 250, 46);
		frame.getContentPane().add(lblCpf);
		
		textId = new JTextField();
		textId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textId.setBounds(30, 40, 300, 46);
		frame.getContentPane().add(textId);
		textId.setColumns(10);
		
		JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Pessoa pessoa = PessoaControladora.search(textId.getText());
				if(pessoa != null) {
					textNome.setText(pessoa.getNome());
					textCpf.setText(pessoa.getCpf());
					textEndereco.setText(pessoa.getEndereco());
					textEstado.setText(pessoa.getEstado());
					textCidade.setText(pessoa.getCidade());
					textTelefone.setText(pessoa.getTelefone());
					textEmail.setText(pessoa.getEmail());
					btnAtualizar.setEnabled(true);
				} else {
					textId.setText("");
					textNome.setText("");
					textCpf.setText("");
					textEndereco.setText("");
					textEstado.setText("");
					textCidade.setText("");
					textTelefone.setText("");
					textEmail.setText("");
					btnAtualizar.setEnabled(false);
				}
				
			}
		});
		btnNewButton.setBounds(340, 40, 150, 46);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereco.setBounds(30, 250, 250, 46);
		frame.getContentPane().add(lblEndereco);
		
		textEndereco = new JTextField();
		textEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEndereco.setColumns(10);
		textEndereco.setBounds(30, 320, 250, 46);
		frame.getContentPane().add(textEndereco);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCidade.setBounds(360, 250, 250, 46);
		frame.getContentPane().add(lblCidade);
		
		textCidade = new JTextField();
		textCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCidade.setColumns(10);
		textCidade.setBounds(360, 320, 250, 46);
		frame.getContentPane().add(textCidade);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstado.setBounds(690, 250, 250, 46);
		frame.getContentPane().add(lblEstado);
		
		textEstado = new JTextField();
		textEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEstado.setColumns(10);
		textEstado.setBounds(690, 320, 250, 46);
		frame.getContentPane().add(textEstado);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefone.setBounds(30, 390, 250, 46);
		frame.getContentPane().add(lblTelefone);
		
		textTelefone = new JTextField();
		textTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTelefone.setColumns(10);
		textTelefone.setBounds(30, 460, 250, 46);
		frame.getContentPane().add(textTelefone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(360, 390, 250, 46);
		frame.getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEmail.setColumns(10);
		textEmail.setBounds(360, 460, 250, 46);
		frame.getContentPane().add(textEmail);
		
		frame.setVisible(true);
	}
}
