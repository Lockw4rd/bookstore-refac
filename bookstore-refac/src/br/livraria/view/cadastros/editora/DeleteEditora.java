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
import br.livraria.model.Editora;

public class DeleteEditora {

	private JFrame frame;
	private JLabel textNome;
	private JLabel textEndereco;
	private JTextField textId;
	private JLabel textCnpj;

	public DeleteEditora() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Deletar Editora");
		frame.setLocationRelativeTo(null);
		
		textNome = new JLabel("");
		textNome.setFont(new Font("Tahoma", Font.BOLD, 16));
		textNome.setBounds(30, 170, 400, 46);
		frame.getContentPane().add(textNome);
		
		textEndereco = new JLabel("");
		textEndereco.setFont(new Font("Tahoma", Font.BOLD, 16));
		textEndereco.setBounds(30, 450, 400, 46);
		frame.getContentPane().add(textEndereco);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeletar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(EditoraControladora.delete(textId.getText())) {
					new ViewEditora();
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
				new ViewEditora();
				frame.dispose();
			}
		});
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBounds(824, 704, 150, 46);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(30, 100, 400, 46);
		frame.getContentPane().add(lblNome);
		
		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereco.setBounds(30, 380, 400, 46);
		frame.getContentPane().add(lblEndereco);
		
		textCnpj = new JLabel("");
		textCnpj.setFont(new Font("Tahoma", Font.BOLD, 16));
		textCnpj.setBounds(30, 310, 400, 46);
		frame.getContentPane().add(textCnpj);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCnpj.setBounds(30, 240, 400, 46);
		frame.getContentPane().add(lblCnpj);
		
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
				Editora editora = EditoraControladora.search(textId.getText());
				if(editora != null) {
					textEndereco.setText(editora.getEndereco());
					textId.setText(editora.getIdEditora() + ""); 
					textNome.setText(editora.getNome());
					textCnpj.setText(editora.getCnpj());
					btnDeletar.setEnabled(true);
				} else {
					textEndereco.setText("");
					textId.setText(""); 
					textNome.setText("");
					textCnpj.setText("");
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
