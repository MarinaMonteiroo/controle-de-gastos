package controlegastos.modelo;

import java.time.LocalDate;

public class Transacao { 
    private static int proximoId = 1;   // static: um só para todas

    private int id;                     // cada transação tem o seu
    private String nome;
    private String descricao;
    protected double valor;
    private LocalDate data;
    private Categoria categoria;

    public Transacao(String nome, String descricao, double valor,
                     LocalDate data, Categoria categoria) {
        this.id = proximoId;
        proximoId++;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }

    public double efeitoNoSaldo() {
        return valor;
    }

    public String getTipo() {
        return "TRANSACAO";
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public LocalDate getData() { return data; }
    public Categoria getCategoria() { return categoria; }

}
