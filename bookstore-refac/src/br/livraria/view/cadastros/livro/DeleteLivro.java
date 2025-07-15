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
import br.livraria.model.Livro;

public class DeleteLivro {

	private JFrame frame;
	private JLabel textTitulo;
	private JLabel textAutor;
	private JLabel textGenero;
	private JLabel textCodEditora;
	private JLabel textPublicacao;
	private JLabel textEstoque;
	private JTextField textId;
	private JLabel textPreco;

	public DeleteLivro() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Deletar Livro");
		frame.setLocationRelativeTo(null);
		
		textTitulo = new JLabel("");
		textTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		textTitulo.setBounds(30, 170, 400, 46);
		frame.getContentPane().add(textTitulo);
		
		textAutor = new JLabel("");
		textAutor.setFont(new Font("Tahoma", Font.BOLD, 16));
		textAutor.setBounds(500, 310, 400, 46);
		frame.getContentPane().add(textAutor);
		
		textGenero = new JLabel("");
		textGenero.setFont(new Font("Tahoma", Font.BOLD, 16));
		textGenero.setBounds(500, 170, 400, 46);
		frame.getContentPane().add(textGenero);
		
		textCodEditora = new JLabel("");
		textCodEditora.setFont(new Font("Tahoma", Font.BOLD, 16));
		textCodEditora.setBounds(30, 590, 400, 46);
		frame.getContentPane().add(textCodEditora);
		
		textPublicacao = new JLabel("");
		textPublicacao.setFont(new Font("Tahoma", Font.BOLD, 16));
		textPublicacao.setBounds(500, 450, 400, 46);
		frame.getContentPane().add(textPublicacao);
		
		textEstoque = new JLabel("");
		textEstoque.setFont(new Font("Tahoma", Font.BOLD, 16));
		textEstoque.setBounds(30, 450, 400, 46);
		frame.getContentPane().add(textEstoque);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeletar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(LivroControlador.delete(textId.getText())) {
					new ViewLivro();
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
				new ViewLivro();
				frame.dispose();
			}
		});
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBounds(824, 704, 150, 46);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(30, 100, 400, 46);
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAutor.setBounds(500, 240, 400, 46);
		frame.getContentPane().add(lblAutor);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenero.setBounds(500, 100, 400, 46);
		frame.getContentPane().add(lblGenero);
		
		JLabel lblEstoque = new JLabel("Estoque");
		lblEstoque.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstoque.setBounds(30, 380, 400, 46);
		frame.getContentPane().add(lblEstoque);
		
		JLabel lblPublicacao = new JLabel("Publicacao");
		lblPublicacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPublicacao.setBounds(500, 380, 400, 46);
		frame.getContentPane().add(lblPublicacao);
		
		JLabel lblEditoraCod = new JLabel("ID Editora");
		lblEditoraCod.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEditoraCod.setBounds(30, 520, 400, 46);
		frame.getContentPane().add(lblEditoraCod);
		
		textPreco = new JLabel("");
		textPreco.setFont(new Font("Tahoma", Font.BOLD, 16));
		textPreco.setBounds(30, 310, 400, 46);
		frame.getContentPane().add(textPreco);
		
		JLabel lblPreco = new JLabel("Preco");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPreco.setBounds(30, 240, 400, 46);
		frame.getContentPane().add(lblPreco);
		
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
				Livro livro = LivroControlador.search(textId.getText());
				if(livro != null) {
					textAutor.setText(livro.getAutor());
					textCodEditora.setText(livro.getEditora().getIdEditora() + "");
					textEstoque.setText(livro.getQtdEstoque() + "");
					textGenero.setText(livro.getGenero());
					textId.setText(livro.getIdLivro() + ""); 
					textPublicacao.setText(livro.getDataPublicada() + "");
					textTitulo.setText(livro.getTitulo());
					textPreco.setText(livro.getPrecoUnit() + "");
					btnDeletar.setEnabled(true);
				} else {
					textAutor.setText("");
					textCodEditora.setText("");
					textEstoque.setText("");
					textGenero.setText("");
					textId.setText(""); 
					textPublicacao.setText("");
					textTitulo.setText("");
					textPreco.setText("");
					textId.setText("");
					btnDeletar.setEnabled(false);
				}
				
			}
		});
		btnPesquisar.setBounds(340, 11, 150, 46);
		frame.getContentPane().add(btnPesquisar);
		
		frame.setVisible(true);
	}
}
