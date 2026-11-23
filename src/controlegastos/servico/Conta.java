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

		//  saldo
		if (saldoFuturo < 0) {
			throw new SaldoInsuficienteException(
					"Saldo disponível: " + saldo + ", valor pretendido: " + t.getValor());
		}

		
		transacoes.add(t);
		saldo = saldoFuturo;
	}

	
	private void validar(Transacao t) throws ControleGastosException {

		//  nome e descrição
		if (t.getNome() == null || t.getNome().trim().isEmpty()
				|| t.getDescricao() == null || t.getDescricao().trim().isEmpty()) {
			throw new NomeInvalidoOuNuloException("Nome e descrição são obrigatórios.");
		}

		//  categoria
		Categoria c = t.getCategoria();
		if (c == null) {
			throw new CategoriaNaoDefinidaException("A transação não tem categoria.");
		}
		boolean transacaoEhReceita = t instanceof Receita;
		if (transacaoEhReceita != c.isReceita()) {
			throw new CategoriaNaoDefinidaException(
					"A categoria " + c.getNome() + " é incompatível com " + t.getTipo() + ".");
		}

		//  valor
		if (t.getValor() <= 0) {
			throw new ValorInvalidoException("O valor deve ser maior que zero.");
		}
	}
}