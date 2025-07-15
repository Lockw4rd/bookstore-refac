package br.livraria.view.operacional;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.livraria.controlador.operacional.VendaControlador;
import br.livraria.view.menu.ViewMenu;

public class ViewPagamento extends JFrame {

	private static final long serialVersionUID = -2193519191097211955L;
	
	private JPanel contentPane;
	private JTextField textValor;
	private JTextField textCpf;
	
	public ViewPagamento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Pagamento");
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFormaPagamento = new JLabel("Forma de Pagamento");
		lblFormaPagamento.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormaPagamento.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFormaPagamento.setBounds(10, 11, 364, 46);
		contentPane.add(lblFormaPagamento);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 125, 364, 39);
		contentPane.add(panel);
		
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton radioDinheiro = new JRadioButton("Dinheiro");
		radioDinheiro.setSelected(true);
		radioDinheiro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bg.add(radioDinheiro);
		panel.add(radioDinheiro);
		
		JRadioButton radioCredito = new JRadioButton("Credito");
		radioCredito.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bg.add(radioCredito);
		panel.add(radioCredito);
		
		JRadioButton radioDebito = new JRadioButton("Debito");
		radioDebito.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bg.add(radioDebito);
		panel.add(radioDebito);
		
		JLabel lblValor = new JLabel("Valor Pago:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValor.setBounds(10, 170, 364, 46);
		contentPane.add(lblValor);
		
		textValor = new JTextField();
		textValor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textValor.setBounds(10, 220, 364, 46);
		contentPane.add(textValor);
		textValor.setColumns(10);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String formaPagamento = "";
				
				if(radioCredito.isSelected()) {
					formaPagamento = "CREDITO";
				}
				
				if(radioDinheiro.isSelected()) {
					formaPagamento = "DINHEIRO";
				}
				
				if(radioDebito.isSelected()) {
					formaPagamento = "DEBITO";
				}
				
				if(VendaControlador.finalizarVenda(formaPagamento, textValor.getText(), textCpf.getText())) {
					new ViewMenu();
					dispose();
				}
			}
		});
		btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFinalizar.setBounds(33, 404, 150, 46);
		contentPane.add(btnFinalizar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VendaControlador.cancelarVenda();
				new ViewMenu();
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelar.setBounds(193, 404, 150, 46);
		contentPane.add(btnCancelar);
		
		JLabel lblFormaPagamento_1 = new JLabel(VendaControlador.getPedidoAberto().getPrecoTotal() + "");
		lblFormaPagamento_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFormaPagamento_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFormaPagamento_1.setBounds(10, 68, 254, 46);
		contentPane.add(lblFormaPagamento_1);
		
		JLabel lblMoeda = new JLabel("R$");
		lblMoeda.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoeda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMoeda.setBounds(274, 68, 100, 46);
		contentPane.add(lblMoeda);
		
		JLabel lblCliente = new JLabel("CPF Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCliente.setBounds(10, 270, 364, 46);
		contentPane.add(lblCliente);
		
		textCpf = new JTextField();
		textCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCpf.setColumns(10);
		textCpf.setBounds(10, 320, 364, 46);
		contentPane.add(textCpf);
		
		setVisible(true);
	}
}
