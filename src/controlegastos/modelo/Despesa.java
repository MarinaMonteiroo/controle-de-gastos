package controlegastos.modelo;

import java.time.LocalDate;

public class Despesa extends Transacao {
	  public Despesa(String nome, String descricao, double valor,
              LocalDate data, Categoria categoria) {
   super(nome, descricao, valor, data, categoria);
}

@Override
public double efeitoNoSaldo() {
   return -valor;     // despesa subtrai do saldo
}

@Override
public String getTipo() {
   return "DESPESA";
}
}
