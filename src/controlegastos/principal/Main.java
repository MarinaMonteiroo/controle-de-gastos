package controlegastos.principal;

import controlegastos.excecoes.SaldoInsuficienteException;
import controlegastos.modelo.Titular;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Titular teste = new Titular("Fulana" , "123.456.789-01");
				System.out.println(teste.getNome()); 

				try { System.out.println("tentat");
		            throw new SaldoInsuficienteException("Saldo disponível: 100, valor pretendido: 150");
		        } catch (SaldoInsuficienteException e) {
		            System.out.println("Erro capturado: " + e.getMessage());
		        }
		        System.out.println(" continuou");

	}

}
