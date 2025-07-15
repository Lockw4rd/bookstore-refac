package br.livraria.view.operacional;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.livraria.controlador.operacional.DevolucaoControlador;
import br.livraria.view.menu.ViewMenu;

public class ViewDevolucao {
	
	private JFrame frame;
	private JTable table;
	private JTextField textCodigo;
	private JTextField textQtd;
	
	private JLabel lblValor;

	public ViewDevolucao() {
		
		initialize();
		
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Devolucao");
		frame.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 964, 598);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(DevolucaoControlador.updateTable());
		scrollPane.setViewportView(table);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(10, 46, 150, 30);
		frame.getContentPane().add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(10, 11, 150, 30);
		frame.getContentPane().add(lblCodigo);
		
		textQtd = new JTextField();
		textQtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textQtd.setBounds(198, 46, 150, 30);
		frame.getContentPane().add(textQtd);
		textQtd.setColumns(10);
		
		JLabel lblQtd = new JLabel("Quantidade");
		lblQtd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQtd.setBounds(198, 11, 150, 30);
		frame.getContentPane().add(lblQtd);
		
		JButton btnRemoveItem = new JButton("Remover Item");
		btnRemoveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(DevolucaoControlador.removerItem(textCodigo.getText(), textQtd.getText())) {
					table.setModel(DevolucaoControlador.updateTable());
					lblValor.setText("" + DevolucaoControlador.getPedidoDevolucao().getPrecoTotal());
				}
				
			}

		});
		btnRemoveItem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemoveItem.setBounds(358, 34, 200, 46);
		frame.getContentPane().add(btnRemoveItem);
		
		JLabel lblTotal = new JLabel("Credito:");
		lblTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotal.setBounds(10, 704, 90, 46);
		frame.getContentPane().add(lblTotal);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				DevolucaoControlador.cancelarDevolucao();
				
				new ViewMenu();
				frame.dispose();
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(824, 705, 150, 46);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DevolucaoControlador.finalizarDevolucao();
				new ViewMenu();
				frame.dispose();
			}
		});
		btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFinalizar.setBounds(664, 705, 150, 46);
		frame.getContentPane().add(btnFinalizar);
		
		lblValor = new JLabel("0.00");
		lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblValor.setBounds(110, 704, 80, 46);
		frame.getContentPane().add(lblValor);
		
		JLabel lblMoeda = new JLabel("R$");
		lblMoeda.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoeda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMoeda.setBounds(200, 704, 60, 46);
		frame.getContentPane().add(lblMoeda);
		
		frame.setVisible(true);
	}
	
}
