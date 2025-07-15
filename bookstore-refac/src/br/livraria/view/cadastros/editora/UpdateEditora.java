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

public class UpdateEditora {

	private JFrame frame;
	private JTextField textNome;
	private JTextField textEndereco;
	private JTextField textCnpj;
	private JTextField textId;

	public UpdateEditora() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Editar Editora");
		frame.setLocationRelativeTo(null);
		
		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textNome.setColumns(10);
		textNome.setBounds(30, 180, 400, 46);
		frame.getContentPane().add(textNome);
		
		textEndereco = new JTextField();
		textEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEndereco.setColumns(10);
		textEndereco.setBounds(30, 320, 400, 46);
		frame.getContentPane().add(textEndereco);
		
		textCnpj = new JTextField();
		textCnpj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCnpj.setColumns(10);
		textCnpj.setBounds(30, 460, 400, 46);
		frame.getContentPane().add(textCnpj);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAtualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(EditoraControladora.update(textNome.getText(), textCnpj.getText(), textEndereco.getText(), textId.getText())) {
					new ViewEditora();
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
				new ViewEditora();
				frame.dispose();
			}
		});
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setBounds(803, 697, 150, 46);
		frame.getContentPane().add(btnCancelar);
		
		JLabel lblNome = new JLabel("Nome*");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNome.setBounds(30, 110, 400, 46);
		frame.getContentPane().add(lblNome);
		
		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEndereco.setBounds(30, 250, 400, 46);
		frame.getContentPane().add(lblEndereco);
		
		JLabel lblCnpj = new JLabel("CNPJ (14)*");
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCnpj.setBounds(30, 390, 400, 46);
		frame.getContentPane().add(lblCnpj);
		
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
				
				Editora editora = EditoraControladora.search(textId.getText());
				
				if(editora != null) {
					textEndereco.setText(editora.getEndereco());
					textId.setText(editora.getIdEditora() + ""); 
					textCnpj.setText(editora.getCnpj());
					textNome.setText(editora.getNome());
					btnAtualizar.setEnabled(true);
				} else {
					textEndereco.setText("");
					textId.setText(""); 
					textCnpj.setText("");
					textNome.setText("");
					btnAtualizar.setEnabled(false);
				}
				
			}
		});
		btnNewButton.setBounds(340, 40, 150, 46);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
}
