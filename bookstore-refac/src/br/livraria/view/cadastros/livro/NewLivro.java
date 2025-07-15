package br.livraria.view.cadastros.livro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.livraria.controlador.cadastros.LivroControlador;

public class NewLivro {

	private JFrame frame;
	private JTextField textTitulo;
	private JTextField textAutor;
	private JTextField textGenero;
	private JTextField textCodEditora;
	private JTextField textPublicacao;
	private JTextField textEstoque;
	private JTextField textPreco;

	public NewLivro() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Novo Livro");
		frame.setLocationRelativeTo(null);
		
		textTitulo = new JTextField();
		textTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTitulo.setColumns(10);
		textTitulo.setBounds(30, 130, 400, 46);
		frame.getContentPane().add(textTitulo);
		
		textAutor = new JTextField();
		textAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAutor.setColumns(10);
		textAutor.setBounds(30, 270, 400, 46);
		frame.getContentPane().add(textAutor);
		
		textGenero = new JTextField();
		textGenero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textGenero.setColumns(10);
		textGenero.setBounds(30, 410, 400, 46);
		frame.getContentPane().add(textGenero);
		
		textCodEditora = new JTextField();
		textCodEditora.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCodEditora.setColumns(10);
		textCodEditora.setBounds(500, 130, 400, 46);
		frame.getContentPane().add(textCodEditora);
		
		textPublicacao = new JTextField();
		textPublicacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPublicacao.setColumns(10);
		textPublicacao.setBounds(500, 270, 400, 46);
		frame.getContentPane().add(textPublicacao);
		
		textEstoque = new JTextField();
		textEstoque.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEstoque.setColumns(10);
		textEstoque.setBounds(500, 410, 400, 46);
		frame.getContentPane().add(textEstoque);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LivroControlador.save(textTitulo.getText(), textGenero.getText(), textPublicacao.getText(),
						textAutor.getText(), textEstoque.getText(), textPreco.getText(), textCodEditora.getText())) {
					new ViewLivro();
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
				new ViewLivro();
				frame.dispose();
			}
		});
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBounds(824, 704, 150, 46);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblTitulo = new JLabel("Titulo*");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(30, 60, 400, 46);
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAutor.setBounds(30, 200, 400, 46);
		frame.getContentPane().add(lblAutor);
		
		JLabel lblGenero = new JLabel("Genero*");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenero.setBounds(30, 340, 400, 46);
		frame.getContentPane().add(lblGenero);
		
		JLabel lblEstoque = new JLabel("Estoque*");
		lblEstoque.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstoque.setBounds(500, 340, 400, 46);
		frame.getContentPane().add(lblEstoque);
		
		JLabel lblPublicacao = new JLabel("Publicacao");
		lblPublicacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPublicacao.setBounds(500, 200, 400, 46);
		frame.getContentPane().add(lblPublicacao);
		
		JLabel lblCodEditora = new JLabel("ID Editora*");
		lblCodEditora.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCodEditora.setBounds(500, 60, 400, 46);
		frame.getContentPane().add(lblCodEditora);
		
		JLabel lblPreco = new JLabel("Preco*");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPreco.setBounds(30, 480, 400, 46);
		frame.getContentPane().add(lblPreco);
		
		textPreco = new JTextField();
		textPreco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPreco.setColumns(10);
		textPreco.setBounds(30, 550, 400, 46);
		frame.getContentPane().add(textPreco);
		
		frame.setVisible(true);
	}
}
