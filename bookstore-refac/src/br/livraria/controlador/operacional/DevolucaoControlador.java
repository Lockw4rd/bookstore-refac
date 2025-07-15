
package br.livraria.controlador.operacional;

import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.livraria.controlador.menu.LoginControlador;
import br.livraria.dao.ItemDAO;
import br.livraria.dao.LivroDAO;
import br.livraria.dao.PedidoDAO;
import br.livraria.model.Item;
import br.livraria.model.Livro;
import br.livraria.model.Pedido;

import br.livraria.util.TratamentoErro;

public class DevolucaoControlador {

    private static Pedido pedidoDevolucao;
    private static Pedido pedidoVenda;

    private DevolucaoControlador() {}

    public static boolean abrirDevolucao() {

        String idPedido = JOptionPane.showInputDialog(new JFrame(), "Informe o ID do Pedido");
        
        /*
		 * Verificar se � um inteiro
		 */

        try {
            int id = Integer.parseInt(idPedido);

            if (idPedido.equals("")) {
                TratamentoErro.exibirMensagem( "ID Pedido nao pode ser nulo");
                return false;
            }
            
            /*
    		 * Verificar se existe
    		 */

            Pedido pedidoRecuperado = PedidoDAO.getPedidoById(id);
            if (pedidoRecuperado == null || pedidoRecuperado.getOperacao().equals("DEVOLUCAO")) {
                TratamentoErro.exibirMensagem( "Pedido nao consta na base de dados ou é uma Devolução");
                return false;
            }

            Pedido pedido = new Pedido(LoginControlador.getFuncionarioLogado(), new Date(), "DEVOLUCAO");
            PedidoDAO.open(pedido);

            pedidoDevolucao = PedidoDAO.getLastPedido();
            pedidoVenda = pedidoRecuperado;

            return true;

        } catch (NumberFormatException e) {
            TratamentoErro.exibirMensagem( "Insira um número válido");
            return false;
        }
    }

    public static Pedido getPedidoDevolucao() {
        return pedidoDevolucao;
    }

    public static Pedido getPedidoVenda() {
        return pedidoVenda;
    }

    public static boolean removerItem(String idLivro, String qtdDevolvida) {
    	
    	/*
		 * Converter as Strings para seus respectivos tipos primitivos
		 */

    	
        int id = 0;
        int qtd = 0;
        try {
            id = Integer.parseInt(idLivro);
            qtd = Integer.parseInt(qtdDevolvida);
        } catch (Exception e) {
            TratamentoErro.exibirMensagem( "Numeros invalidos");
            return false;
        }
        
        /*
		 * Verificar se � vazio
		 */
    	

        if (idLivro.equals("")) {
            TratamentoErro.exibirMensagem( "ID Livro nao pode ser nulo");
            return false;
        }

        if (qtdDevolvida.equals("")) {
            TratamentoErro.exibirMensagem( "Quantidade nao pode ser nulo");
            return false;
        }
        
		/*
		 * Identificar validacao dos campos unicos
		 */

		/*
		 * Identificar outras validacoes
		 */

        if (qtd <= 0) {
            TratamentoErro.exibirMensagem( "Quantidade precisa ser maior que 0");
            return false;
        }

        Livro livro = LivroDAO.getLivroById(id);
        if (livro == null) {
            TratamentoErro.exibirMensagem( "Livro nao consta na base de dados");
            return false;
        }
        
        /*
		 * Criar um Item
		 * Atualizar o Item antigo
		 */

        Item item = new Item(livro, pedidoDevolucao, qtd);
        ItemDAO.save(item);

        item = ItemDAO.getItemByIdLivro(id, pedidoVenda.getIdPedido());

        if (item == null) {
            TratamentoErro.exibirMensagem( "O ID informado nao consta no Pedido");
            return false;
        }

        if (qtd > item.getQtdVendida()) {
            TratamentoErro.exibirMensagem( "Quantidade a ser devolvida é maior que a quantidade vendida");
            return false;
        }

        item.removeQtdVendida(qtd);
        item.setPrecoTotal();
        ItemDAO.update(item);

        livro.addEstoque(qtd);
        LivroDAO.update(livro);

        pedidoVenda = PedidoDAO.getPedidoById(pedidoVenda.getIdPedido());
        pedidoDevolucao = PedidoDAO.getPedidoById(pedidoDevolucao.getIdPedido());

        return true;
    }

    private static Object[][] getItensTable(Vector<Item> itens) {
        Object[][] tabela = new Object[itens.size()][5];

        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            Livro livro = item.getLivro();

            tabela[i][0] = livro.getIdLivro();
            tabela[i][1] = livro.getTitulo();
            tabela[i][2] = livro.getPrecoUnit();
            tabela[i][3] = item.getQtdVendida();
            tabela[i][4] = item.getPrecoTotal();
        }

        return tabela;
    }

    public static DefaultTableModel updateTable() {
    	
    	
        Vector<Item> itens = ItemDAO.getItensByPedido(pedidoVenda.getIdPedido());
        return new DefaultTableModel(
            getItensTable(itens),
            new String[] {
                "ID", "Livro", "Preco", "Quantidade", "Subtotal"
            }
        );
    }

    public static void cancelarDevolucao() {
        Vector<Item> itensDevolucao = ItemDAO.getItensByPedido(pedidoDevolucao.getIdPedido());
        for (Item itemDevolucao : itensDevolucao) {
            Item itemVenda = ItemDAO.getItemByIdLivro(itemDevolucao.getLivro().getIdLivro(), pedidoVenda.getIdPedido());
            if (itemVenda != null) {
                itemVenda.addQtdVendida(itemDevolucao.getQtdVendida());
                ItemDAO.update(itemVenda);
            }

            ItemDAO.deleteByID(itemDevolucao.getIdItem());
        }

        PedidoDAO.deleteById(pedidoDevolucao.getIdPedido());
    }

    public static void finalizarDevolucao() {
        pedidoDevolucao.setCliente(pedidoVenda.getCliente());
        pedidoDevolucao.setFormaPagamento("DINHEIRO");

        PedidoDAO.close(pedidoDevolucao);
    }
}
