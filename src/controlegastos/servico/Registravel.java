package controlegastos.servico;

import java.time.LocalDate;

import controlegastos.excecoes.ControleGastosException;
import controlegastos.modelo.Categoria;
import controlegastos.modelo.Transacao;

public interface Registravel {
    void inserir(Transacao t) throws ControleGastosException;

    double getSaldo();

    void atualizar(int identificador, Transacao nova) throws ControleGastosException;

    void remover(int identificador) throws ControleGastosException;
    
    void extrato();

    void extrato(Categoria categoria);

    void extrato(LocalDate inicio, LocalDate fim);
    
    void extrato(Categoria categoria, LocalDate inicio, LocalDate fim);
    
  
    
}
