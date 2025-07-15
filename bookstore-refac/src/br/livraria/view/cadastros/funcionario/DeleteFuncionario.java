package br.livraria.view.cadastros.funcionario;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.livraria.controlador.cadastros.FuncionarioControlador;
import br.livraria.model.Funcionario;

public class DeleteFuncionario {

	private JFrame frame;
	private JLabel textNome;
	private JLabel textDataContrato;
	private JTextField textId;
	private JLabel textCpf;
	private JLabel textEndereco;
	private JLabel textCidade;
	private JLabel textEstado;
	private JLabel textTelefone;
	private JLabel textEmail;
	private JLabel textSenha;
	private JLabel textCargo;

	public DeleteFuncionario() {
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
		
		textDataContrato = new JLabel("");
		textDataContrato.setFont(new Font("Tahoma", Font.BOLD, 16));
		textDataContrato.setBounds(690, 170, 250, 46);
		frame.getContentPane().add(textDataContrato);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeletar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(FuncionarioControlador.delete(textId.getText())) {
					new ViewFuncionario();
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
				new ViewFuncionario();
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
		
		JLabel lblDataContrato = new JLabel("Data Contrato");
		lblDataContrato.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDataContrato.setBounds(690, 100, 250, 46);
		frame.getContentPane().add(lblDataContrato);
		
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
				Funcionario funcionario = FuncionarioControlador.search(textId.getText());
				if(funcionario != null) {
					textDataContrato.setText(funcionario.getDataContrato() + "");
					textId.setText(funcionario.getIdFuncionario() + ""); 
					textNome.setText(funcionario.getNome());
					textCpf.setText(funcionario.getCpf());
					textEndereco.setText(funcionario.getEndereco());
					textCidade.setText(funcionario.getCidade());
					textEstado.setText(funcionario.getEstado());
					textTelefone.setText(funcionario.getTelefone());
					textEmail.setText(funcionario.getEmail());
					textSenha.setText(funcionario.getSenha());
					textCargo.setText(funcionario.printPost());
					btnDeletar.setEnabled(true);
				} else {
					textDataContrato.setText("");
					textId.setText(""); 
					textNome.setText("");
					textCpf.setText("");
					textEndereco.setText("");
					textCidade.setText("");
					textEstado.setText("");
					textTelefone.setText("");
					textEmail.setText("");
					textSenha.setText("");
					textCargo.setText("");
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
		
		textSenha = new JLabel("");
		textSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
		textSenha.setBounds(690, 450, 250, 46);
		frame.getContentPane().add(textSenha);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenha.setBounds(690, 380, 250, 46);
		frame.getContentPane().add(lblSenha);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(360, 380, 250, 46);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefone.setBounds(30, 380, 250, 46);
		frame.getContentPane().add(lblTelefone);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCargo.setBounds(30, 520, 250, 46);
		frame.getContentPane().add(lblCargo);
		
		textCargo = new JLabel("");
		textCargo.setFont(new Font("Tahoma", Font.BOLD, 16));
		textCargo.setBounds(30, 590, 250, 46);
		frame.getContentPane().add(textCargo);
		
		frame.setVisible(true);
	}
}
