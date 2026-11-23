package controlegastos.servico;

import controlegastos.excecoes.ControleGastosException;
import controlegastos.modelo.Transacao;

public interface Registravel {
    void inserir(Transacao t) throws ControleGastosException;

    double getSaldo();

}
