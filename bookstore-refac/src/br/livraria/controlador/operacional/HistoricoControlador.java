package br.livraria.controlador.operacional;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.livraria.dao.PedidoDAO;
import br.livraria.model.Pedido;

public class HistoricoControlador {
	
	private static Object[][] get() {
		
		Object[][] tabela;
		
		Vector<Pedido> pedidos = PedidoDAO.getPedidos();
		
		tabela = new Object[pedidos.size()][6];
		
		for(int i = 0; i < pedidos.size(); i++) {
			
			tabela[i][0] = pedidos.get(i).getIdPedido();
			tabela[i][1] = pedidos.get(i).getFuncionario().getNome();
			tabela[i][2] = pedidos.get(i).getCliente().getNome();
			tabela[i][3] = pedidos.get(i).getPrecoTotal();
			tabela[i][4] = pedidos.get(i).getFormaPagamento();
			tabela[i][5] = pedidos.get(i).getOperacao();
			
		}
		
		return tabela;
		
	}
	
	public static DefaultTableModel updateTable() {
		
		return new DefaultTableModel(
			HistoricoControlador.get(),
			new String[] {
				"ID", "Funcionario", "Cliente", "Total", "Pagamento", "Operacao"
			}
		);
		
	}

}
