package br.livraria.view.cadastros.editora;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.livraria.controlador.cadastros.EditoraControladora;

public class NewEditora {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textCnpj;
	private JTextField textEndereco;

	public NewEditora() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Nova Editora");
		frame.setLocationRelativeTo(null);
		
		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNome.setColumns(10);
		textNome.setBounds(30, 130, 400, 46);
		frame.getContentPane().add(textNome);
		
		textCnpj = new JTextField();
		textCnpj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCnpj.setColumns(10);
		textCnpj.setBounds(30, 270, 400, 46);
		frame.getContentPane().add(textCnpj);
		
		textEndereco = new JTextField();
		textEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEndereco.setColumns(10);
		textEndereco.setBounds(30, 410, 400, 46);
		frame.getContentPane().add(textEndereco);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(EditoraControladora.save(textNome.getText(), textCnpj.getText(), textEndereco.getText())) {
					new ViewEditora();
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
				new ViewEditora();
				frame.dispose();
			}
		});
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBounds(824, 704, 150, 46);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblNome = new JLabel("Nome*");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(30, 60, 400, 46);
		frame.getContentPane().add(lblNome);
		
		JLabel lblCnpj = new JLabel("CNPJ (14)*");
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCnpj.setBounds(30, 200, 400, 46);
		frame.getContentPane().add(lblCnpj);
		
		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereco.setBounds(30, 340, 400, 46);
		frame.getContentPane().add(lblEndereco);
		
		frame.setVisible(true);
	}
}
