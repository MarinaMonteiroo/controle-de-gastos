package controlegastos.principal;

import java.time.LocalDate;

import controlegastos.excecoes.ControleGastosException;
import controlegastos.modelo.Categoria;
import controlegastos.modelo.Despesa;
import controlegastos.modelo.Receita;
import controlegastos.modelo.Titular;
import controlegastos.servico.Conta;
import controlegastos.servico.Registravel;

public class Main {

	public static void main(String[] args) {
          
		Titular ana = new Titular("Ana Almeida", "123.456.789-00");
		// Inclusão: variável do tipo interface (Registravel) apontando para um objeto Conta
		Registravel conta = new Conta(ana);

		demonstrarInsercoes(conta);
		demonstrarAtualizacaoERemocao(conta);
		demonstrarExcecoes(conta);
		demonstrarConsultasEConversoes(conta);
		conferirSaldoFinal(conta);
	}

	private static void titulo(String texto) {
		System.out.println();
		System.out.println("##### " + texto + " #####");
	}

	// transações válidas
	private static void demonstrarInsercoes(Registravel conta) {
		titulo("Inserções válidas");
		try {
			conta.inserir(new Receita("Martinelli Advogados", "Salário de outubro", 3500.00, LocalDate.now(),
					Categoria.SALARIOS));
			conta.inserir(new Despesa("NeoEnergia", "Conta de luz", 180.50, LocalDate.now(), Categoria.LUZ));
			conta.inserir(new Despesa("Oba Hortifruti e Mercado", "Compras do mês", 650.00, LocalDate.now(),
					Categoria.ALIMENTACAO));
			conta.inserir(new Receita("Carlos Pereira (cliente)", "Honorários", 800.00, LocalDate.now(),
					Categoria.HONORARIOS));
			conta.inserir(new Despesa("Operadora Vivo", "Internet e celular", 119.90, LocalDate.now(),
					Categoria.COMUNICACOES));
		} catch (ControleGastosException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		conta.extrato();
	}

	private static void demonstrarAtualizacaoERemocao(Registravel conta) {
		titulo("Atualização e remoção");
		try {
			conta.atualizar(3, new Despesa("Oba Hortifruti e Mercado", "Compras do mês", 700.00, LocalDate.now(),
					Categoria.ALIMENTACAO));
			System.out.println("Transação 3 atualizada. Saldo: " + conta.getSaldo());

			conta.remover(4);
			System.out.println("Transação 4 removida. Saldo: " + conta.getSaldo());
		} catch (ControleGastosException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		conta.extrato();
	}

	private static void demonstrarExcecoes(Registravel conta) {
		titulo("Situações de exceção");

		try {
			conta.inserir(new Despesa("BYD Company Limited", "Entrada do carro", 5000.00, LocalDate.now(),
					Categoria.TRANSPORTE));
		} catch (ControleGastosException e) {
			System.out.println("Despesa maior que o saldo: " + e.getMessage());
		}

		try {
			conta.inserir(new Despesa("Padaria Pão Dourado", "Pão", -50.00, LocalDate.now(), Categoria.ALIMENTACAO));
		} catch (ControleGastosException e) {
			System.out.println("Valor inválido: " + e.getMessage());
		}

		try {
			conta.inserir(new Despesa("", "Compra", 20.00, LocalDate.now(), Categoria.ALIMENTACAO));
		} catch (ControleGastosException e) {
			System.out.println("Nome vazio: " + e.getMessage());
		}

		try {
			conta.inserir(new Despesa("Drogasil", "Remédio", 40.00, LocalDate.now(), null));
		} catch (ControleGastosException e) {
			System.out.println("Sem categoria: " + e.getMessage());
		}

		try {
			conta.inserir(new Receita("NeoEnergia", "Pagamento", 100.00, LocalDate.now(), Categoria.LUZ));
		} catch (ControleGastosException e) {
			System.out.println("Categoria incompatível: " + e.getMessage());
		}

		try {
			conta.remover(1);
		} catch (ControleGastosException e) {
			System.out.println("Remoção recusada: " + e.getMessage());
		}

		try {
			conta.atualizar(2, new Despesa("NeoEnergia", "Conta de luz", 9000.00, LocalDate.now(), Categoria.LUZ));
		} catch (ControleGastosException e) {
			System.out.println("Atualização recusada: " + e.getMessage());
		}

		System.out.println("Saldo após as exceções: " + conta.getSaldo());
	}

	private static void demonstrarConsultasEConversoes(Registravel conta) {
		titulo("Extratos filtrados (sobrecarga)");
		conta.extrato(Categoria.ALIMENTACAO);
		conta.extrato(LocalDate.now(), LocalDate.now());
		conta.extrato(Categoria.ALIMENTACAO, LocalDate.now(), LocalDate.now());

		titulo("Coerção");

		// Coerção implícita: o int é convertido para double automaticamente
		int valorInteiro = 50;
		double valorDecimal = valorInteiro;
		System.out.println("int " + valorInteiro + " virou double " + valorDecimal);

		// Coerção explícita: cast de double para int (perde a parte decimal)
		double preco = 10.99;
		int parteInteira = (int) preco;
		System.out.println("double " + preco + " virou int " + parteInteira);
		// Coerção de referência: cast de Registravel para Conta, protegido por instanceof
		if (conta instanceof Conta) {
			Conta contaCompleta = (Conta) conta;
			System.out.println("Conta convertida. Saldo: " + contaCompleta.getSaldo());
		}
		// Sobrecarga de construtor: Titular tem um construtor só com o nome
		Titular semCpf = new Titular("Titular sem CPF");
		System.out.println(semCpf.getNome() + " / " + semCpf.getCpf());
	}

	private static void conferirSaldoFinal(Registravel conta) {
		titulo("Conferência do saldo final");
		double esperado = 2499.60;
		double obtido = conta.getSaldo();

		if (Math.abs(obtido - esperado) < 0.001) {
			System.out.printf("OK: saldo final R$ %.2f confere com o esperado R$ %.2f%n", obtido, esperado);
		} else {
			System.out.printf("Divergente: obtido R$ %.2f, esperado R$ %.2f%n", obtido, esperado);
		}
	}
}