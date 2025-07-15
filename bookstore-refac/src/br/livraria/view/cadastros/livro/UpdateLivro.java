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

public class UpdateLivro {

	private JFrame frame;
	private JTextField textTitulo;
	private JTextField textAutor;
	private JTextField textGenero;
	private JTextField textCodEditora;
	private JTextField textPublicacao;
	private JTextField textEstoque;
	private JTextField textId;
	private JTextField textPreco;

	public UpdateLivro() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Editar Livro");
		frame.setLocationRelativeTo(null);
		
		textTitulo = new JTextField();
		textTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTitulo.setColumns(10);
		textTitulo.setBounds(30, 180, 400, 46);
		frame.getContentPane().add(textTitulo);
		
		textAutor = new JTextField();
		textAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAutor.setColumns(10);
		textAutor.setBounds(30, 320, 400, 46);
		frame.getContentPane().add(textAutor);
		
		textGenero = new JTextField();
		textGenero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textGenero.setColumns(10);
		textGenero.setBounds(500, 460, 400, 46);
		frame.getContentPane().add(textGenero);
		
		textCodEditora = new JTextField();
		textCodEditora.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCodEditora.setColumns(10);
		textCodEditora.setBounds(30, 600, 400, 46);
		frame.getContentPane().add(textCodEditora);
		
		textPublicacao = new JTextField();
		textPublicacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPublicacao.setColumns(10);
		textPublicacao.setBounds(500, 180, 400, 46);
		frame.getContentPane().add(textPublicacao);
		
		textEstoque = new JTextField();
		textEstoque.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEstoque.setColumns(10);
		textEstoque.setBounds(500, 320, 400, 46);
		frame.getContentPane().add(textEstoque);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(LivroControlador.update(textTitulo.getText(), textGenero.getText(), textPublicacao.getText(),
						textAutor.getText(), textEstoque.getText(), textPreco.getText(), textCodEditora.getText(),
						textId.getText())) {
					new ViewLivro();
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
				new ViewLivro();
				frame.dispose();
			}
		});
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBounds(803, 697, 150, 46);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblTitulo = new JLabel("Titulo*");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(30, 110, 400, 46);
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAutor.setBounds(30, 250, 400, 46);
		frame.getContentPane().add(lblAutor);
		
		JLabel lblGenero = new JLabel("Genero*");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGenero.setBounds(500, 390, 400, 46);
		frame.getContentPane().add(lblGenero);
		
		JLabel lblEstoque = new JLabel("Estoque*");
		lblEstoque.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEstoque.setBounds(500, 250, 400, 46);
		frame.getContentPane().add(lblEstoque);
		
		JLabel lblPublicacao = new JLabel("Publicacao");
		lblPublicacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPublicacao.setBounds(500, 110, 400, 46);
		frame.getContentPane().add(lblPublicacao);
		
		JLabel lblEditoraCod = new JLabel("ID Editora*");
		lblEditoraCod.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEditoraCod.setBounds(30, 530, 400, 46);
		frame.getContentPane().add(lblEditoraCod);
		
		textPreco = new JTextField();
		textPreco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPreco.setColumns(10);	
		textPreco.setBounds(30, 460, 400, 46);
		frame.getContentPane().add(textPreco);
		
		JLabel lblPreco = new JLabel("Preco");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPreco.setBounds(30, 390, 400, 46);
		frame.getContentPane().add(lblPreco);
		
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
					btnAtualizar.setEnabled(true);
				} else {
					textAutor.setText("");
					textCodEditora.setText("");
					textEstoque.setText("");
					textGenero.setText("");
					textId.setText(""); 
					textPublicacao.setText("");
					textTitulo.setText("");
					textPreco.setText("");
					btnAtualizar.setEnabled(false);
				}
				
			}
		});
		btnNewButton.setBounds(340, 40, 150, 46);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
}
