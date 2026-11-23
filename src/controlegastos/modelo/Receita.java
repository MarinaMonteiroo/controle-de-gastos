package controlegastos.modelo;

import java.time.LocalDate;

public class Receita extends Transacao {

	 public Receita(String nome, String descricao, double valor,
             LocalDate data, Categoria categoria) {
  super(nome, descricao, valor, data, categoria);
}

@Override
public double efeitoNoSaldo() {
  return valor;      // receita soma ao saldo
}

@Override
public String getTipo() {
  return "RECEITA";
}
}
