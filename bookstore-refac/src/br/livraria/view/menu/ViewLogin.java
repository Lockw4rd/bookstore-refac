package br.livraria.view.menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.livraria.controlador.menu.LoginControlador;

public class ViewLogin extends JFrame {

	private static final long serialVersionUID = 112371093650858296L;
	
	private JPanel contentPane;
	private JTextField inputUser;
	private JLabel labelSenha;
	private JPasswordField inputSenha;

	public ViewLogin() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Login");
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelFazerLogin = new JLabel("Fazer Login");
		labelFazerLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labelFazerLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelFazerLogin.setBounds(10, 30, 364, 46);
		contentPane.add(labelFazerLogin);
		
		JLabel labelName = new JLabel("Usuario:");
		labelName.setHorizontalAlignment(SwingConstants.LEFT);
		labelName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelName.setBounds(10, 90, 364, 46);
		contentPane.add(labelName);
		
		inputUser = new JTextField();
		inputUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inputUser.setBounds(10, 160, 364, 46);
		contentPane.add(inputUser);
		inputUser.setColumns(10);
		
		labelSenha = new JLabel("Senha:");
		labelSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelSenha.setBounds(10, 230, 364, 46);
		contentPane.add(labelSenha);
		
		inputSenha = new JPasswordField();
		inputSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inputSenha.setToolTipText("");
		inputSenha.setBounds(10, 290, 364, 46);
		contentPane.add(inputSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(LoginControlador.fazerLogin(inputUser.getText(), new String(inputSenha.getPassword()).trim())) {
					new ViewMenu();
					dispose();
				} else {
					inputUser.setText("");
					inputSenha.setText("");
				}
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEntrar.setBounds(119, 383, 139, 46);
		contentPane.add(btnEntrar);
		
		setVisible(true);
	}
	
}