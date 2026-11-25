package controlegastos.servico;

import java.util.ArrayList;

import controlegastos.modelo.Transacao;


class Repositorio <T extends Transacao> { // (polimorfismo paramétrico)

    private ArrayList<T> itens;

    Repositorio() {
        this.itens = new ArrayList<>();
    }

    void adicionar(T item) {
        itens.add(item);
    }

    void remover(T item) {
        itens.remove(item);
    }

    
    T buscarPorIdentificador(int identificador) {
        for (T item : itens) {
            if (item.getIdentificador1() == identificador) {
                return item;
            }
        }
        return null;
    }

    ArrayList<T> listar() {
        return itens;
    }
}
