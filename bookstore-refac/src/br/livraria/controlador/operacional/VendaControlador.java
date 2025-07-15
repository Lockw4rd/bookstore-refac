package br.livraria.controlador.operacional;

import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import br.livraria.controlador.menu.LoginControlador;
import br.livraria.dao.ItemDAO;
import br.livraria.dao.LivroDAO;
import br.livraria.dao.PedidoDAO;
import br.livraria.dao.PessoaDAO;
import br.livraria.model.Item;
import br.livraria.model.Livro;
import br.livraria.model.Pedido;
import br.livraria.model.Pessoa;

import br.livraria.util.TratamentoErro;

public class VendaControlador {

    private static Pedido pedidoAberto;

    private VendaControlador() {}

    public static void criarPedido() {
        Pedido novoPedido = new Pedido(LoginControlador.getFuncionarioLogado(), new Date(), "VENDA");
        PedidoDAO.open(novoPedido);
        pedidoAberto = PedidoDAO.getLastPedido();
    }

    public static Pedido getPedidoAberto() {
        return pedidoAberto;
    }

    private static Object[][] getItensTabela() {
        List<Item> itens = ItemDAO.getItensByPedido(pedidoAberto.getIdPedido());
        Object[][] tabela = new Object[itens.size()][5];

        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            tabela[i][0] = item.getLivro().getIdLivro();
            tabela[i][1] = item.getLivro().getTitulo();
            tabela[i][2] = item.getLivro().getPrecoUnit();
            tabela[i][3] = item.getQtdVendida();
            tabela[i][4] = item.getPrecoTotal();
        }

        return tabela;
    }

    public static DefaultTableModel updateTable() {
        return new DefaultTableModel(
            getItensTabela(),
            new String[] {
                "ID", "Livro", "Preco", "Quantidade", "Subtotal"
            }
        );
    }

    public static boolean addItem(String idLivro, String quantidade) {
    	
    	/*
		 * Converter as Strings para seus respectivos tipos primitivos
		 */
    	
        try {
            int id = Integer.parseInt(idLivro);
            int qtd = Integer.parseInt(quantidade);

			/*
			* Identificar preenchimento do campos nulos
			*/
            if (idLivro.isEmpty() || quantidade.isEmpty()) {
                TratamentoErro.exibirMensagem( "ID Livro e Quantidade são obrigatórios");
                return false;
            }

			/*
			* Identificar outras validacoes
			*/

            Livro livro = LivroDAO.getLivroById(id);
            if (livro == null) {
                TratamentoErro.exibirMensagem( "Livro não encontrado");
                return false;
            }

            if (qtd <= 0 || livro.getQtdEstoque() - qtd < 0) {
                TratamentoErro.exibirMensagem( "Quantidade inválida ou estoque insuficiente");
                return false;
            }


			/*
			* Verifica se ha naquele pedido um livro ja lancado
			*/
            Item item = ItemDAO.getItemByIdLivro(id, pedidoAberto.getIdPedido());
            if (item != null) {
                item.addQtdVendida(qtd);
                ItemDAO.update(item);
            } else {
                item = new Item(livro, pedidoAberto, qtd);
                ItemDAO.save(item);
            }
            
            /*
    		 * Atualizacao do estoque
    		 */

            livro.removeEstoque(qtd);
            LivroDAO.update(livro);
            pedidoAberto = PedidoDAO.getPedidoById(pedidoAberto.getIdPedido());

            return true;

        } catch (NumberFormatException e) {
            TratamentoErro.exibirMensagem( "Números inválidos");
            return false;
        }
    }

    public static void cancelarVenda() {
        List<Item> itens = ItemDAO.getItensByPedido(pedidoAberto.getIdPedido());
        for (Item item : itens) {
            Livro livro = item.getLivro();
            livro.addEstoque(item.getQtdVendida());
            LivroDAO.update(livro);
            ItemDAO.deleteByID(item.getIdItem());
        }

        PedidoDAO.deleteById(pedidoAberto.getIdPedido());
    }

    public static boolean finalizarVenda(String formaPagamento, String valorPago, String cpf) {
        try {
            int valor = Integer.parseInt(valorPago);

            if (valorPago.isEmpty() || cpf.isEmpty()) {
                TratamentoErro.exibirMensagem( "Valor Pago e CPF são obrigatórios");
                return false;
            }

            Pessoa pessoa = PessoaDAO.getPessoaByCpf(cpf);
            if (pessoa == null) {
                TratamentoErro.exibirMensagem( "CPF não encontrado");
                return false;
            }

            if (valor < pedidoAberto.getPrecoTotal()) {
                TratamentoErro.exibirMensagem( "Dinheiro insuficiente");
                return false;
            }

            double troco = valor - pedidoAberto.getPrecoTotal();
            String mensagem = (troco > 0) ?
                "Venda Finalizada com sucesso para " + pessoa.getNome() + "! Troco: " + troco :
                "Venda Finalizada com sucesso para " + pessoa.getNome();
            TratamentoErro.exibirMensagem( mensagem);

            pedidoAberto.setCliente(pessoa);
            pedidoAberto.setFormaPagamento(formaPagamento);

            PedidoDAO.close(pedidoAberto);
            return true;

        } catch (NumberFormatException e) {
            TratamentoErro.exibirMensagem( "Valor inválido");
            return false;
        }
    }
}
