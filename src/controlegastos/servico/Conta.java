package controlegastos.servico;

import java.util.ArrayList;

import controlegastos.excecoes.CategoriaNaoDefinidaException;
import controlegastos.excecoes.ControleGastosException;
import controlegastos.excecoes.NomeInvalidoOuNuloException;
import controlegastos.excecoes.SaldoInsuficienteException;
import controlegastos.excecoes.ValorInvalidoException;
import controlegastos.modelo.Categoria;
import controlegastos.modelo.Receita;
import controlegastos.modelo.Titular;
import controlegastos.modelo.Transacao;

public class Conta implements Registravel {

	private Titular titular;
	private double saldo;
	private ArrayList<Transacao> transacoes;

	public Conta(Titular titular) {
		this.titular = titular;
		this.saldo = 0;
		this.transacoes = new ArrayList<>();
	}

	@Override
	public double getSaldo() {
		return saldo;
	}

	@Override
	public void inserir(Transacao t) throws ControleGastosException {
		validar(t); // confere nome, categoria e valor

		double saldoFuturo = saldo + t.efeitoNoSaldo();

		// saldo
		if (saldoFuturo < 0) {
			throw new SaldoInsuficienteException("Saldo disponível: " + saldo + ", valor pretendido: " + t.getValor());
		}

		transacoes.add(t);
		saldo = saldoFuturo;
	}

	private void validar(Transacao t) throws ControleGastosException {

		// nome e descrição
		if (t.getNome() == null || t.getNome().trim().isEmpty() || t.getDescricao() == null
				|| t.getDescricao().trim().isEmpty()) {
			throw new NomeInvalidoOuNuloException("Nome e descrição são obrigatórios.");
		}

		// categoria
		Categoria c = t.getCategoria();
		if (c == null) {
			throw new CategoriaNaoDefinidaException("A transação não tem categoria.");
		}
		boolean transacaoEhReceita = t instanceof Receita;
		if (transacaoEhReceita != c.isReceita()) {
			throw new CategoriaNaoDefinidaException(
					"A categoria " + c.getNome() + " é incompatível com " + t.getTipo() + ".");
		}

		// valor
		if (t.getValor() <= 0) {
			throw new ValorInvalidoException("O valor deve ser maior que zero.");
		}
	}

	private Transacao buscar(int identificador) throws ControleGastosException {
		for (Transacao t : transacoes) {
			if (t.getIdentificador1() == identificador) {
				return t;
			}
		}
		throw new ControleGastosException("Transação " + identificador + " não encontrada.");
	}

	@Override
	public void remover(int identificador) throws ControleGastosException {
		Transacao antiga = buscar(identificador);

		double saldoFuturo = saldo - antiga.efeitoNoSaldo();

		if (saldoFuturo < 0) {
			throw new SaldoInsuficienteException("A remoção deixaria o saldo negativo. Saldo disponível: " + saldo
					+ ", saldo após remover: " + saldoFuturo);
		}

		transacoes.remove(antiga);
		saldo = saldoFuturo;
	}

	public void atualizar(int identificador, Transacao nova) throws ControleGastosException {
		Transacao antiga = buscar(identificador);

		validar(nova);

		/// não trocar receita por despesa
		if ((antiga instanceof Receita) != (nova instanceof Receita)) {
			throw new ControleGastosException("Não é possível mudar o tipo da transação " + identificador + ".");
		}

		double saldoFuturo = saldo - antiga.efeitoNoSaldo() + nova.efeitoNoSaldo();

		if (saldoFuturo < 0) {
			throw new SaldoInsuficienteException("A atualização deixaria o saldo negativo. Saldo disponível: " + saldo
					+ ", saldo após atualizar: " + saldoFuturo);
		}

		antiga.setNome(nova.getNome());
		antiga.setDescricao(nova.getDescricao());
		antiga.setValor(nova.getValor());
		antiga.setData(nova.getData());
		antiga.setCategoria(nova.getCategoria());
		saldo = saldoFuturo;
	}
}