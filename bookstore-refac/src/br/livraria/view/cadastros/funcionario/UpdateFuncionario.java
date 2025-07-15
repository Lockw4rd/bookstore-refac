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
import br.livraria.controlador.menu.LoginControlador;
import br.livraria.model.Funcionario;
import javax.swing.JCheckBox;

public class UpdateFuncionario {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textCpf;
	private JTextField textDataContrato;
	private JTextField textId;
	private JTextField textEndereco;
	private JTextField textCidade;
	private JTextField textEstado;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTextField textSenha;
	private JCheckBox boxAdministrador;

	public UpdateFuncionario() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Editar Funcionarios");
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
		
		textDataContrato = new JTextField();
		textDataContrato.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textDataContrato.setColumns(10);
		textDataContrato.setBounds(690, 180, 250, 46);
		frame.getContentPane().add(textDataContrato);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean adm = LoginControlador.getFuncionarioLogado().isAdministrador();
				if(FuncionarioControlador.update(textCpf.getText(), textNome.getText(), textEndereco.getText(), 
						textCidade.getText(), textEstado.getText(), textTelefone.getText(), textEmail.getText(), 
						textSenha.getText(), textDataContrato.getText(), adm, textId.getText())) {
					new ViewFuncionario();
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
				new ViewFuncionario();
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
		
		JLabel lblDataContrato = new JLabel("Data Contrato");
		lblDataContrato.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDataContrato.setBounds(690, 110, 250, 46);
		frame.getContentPane().add(lblDataContrato);
		
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
				Funcionario funcionario = FuncionarioControlador.search(textId.getText());
				if(funcionario != null) {
					textId.setText(funcionario.getIdFuncionario() + "");
					textNome.setText(funcionario.getNome());
					textCpf.setText(funcionario.getCpf());
					textDataContrato.setText(funcionario.getDataContrato() + "");
					textEndereco.setText(funcionario.getEndereco());
					textEstado.setText(funcionario.getEstado());
					textCidade.setText(funcionario.getCidade());
					textTelefone.setText(funcionario.getTelefone());
					textEmail.setText(funcionario.getEmail());
					textSenha.setText(funcionario.getSenha());
					if(funcionario.isAdministrador())
						boxAdministrador.setSelected(true);
					else
						boxAdministrador.setSelected(false);
					btnAtualizar.setEnabled(true);
				} else {
					textId.setText("");
					textNome.setText("");
					textCpf.setText("");
					textDataContrato.setText("");
					textEndereco.setText("");
					textEstado.setText("");
					textCidade.setText("");
					textTelefone.setText("");
					textEmail.setText("");
					textSenha.setText("");
					boxAdministrador.setSelected(false);
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
		
		JLabel lblSenha = new JLabel("Senha*");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenha.setBounds(690, 390, 250, 46);
		frame.getContentPane().add(lblSenha);
		
		textSenha = new JTextField();
		textSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textSenha.setColumns(10);
		textSenha.setBounds(690, 460, 250, 46);
		frame.getContentPane().add(textSenha);
		
		boxAdministrador = new JCheckBox("Administrador");
		boxAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 16));
		boxAdministrador.setBounds(30, 530, 250, 46);
		frame.getContentPane().add(boxAdministrador);
		
		frame.setVisible(true);
	}
}
