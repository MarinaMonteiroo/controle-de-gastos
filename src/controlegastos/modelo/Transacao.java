package controlegastos.modelo;

import java.time.LocalDate;

public class Transacao { 
    private static int proximoId = 1;  

    private int identificador;                     // cada transação tem o seu
    private String nome;
    private String descricao;
    protected double valor;
    private LocalDate data;
    private Categoria categoria;

    public Transacao(String nome, String descricao, double valor,
                     LocalDate data, Categoria categoria) {
        this.identificador = proximoId;
        proximoId++;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoria = categoria;
    }

    public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double efeitoNoSaldo() {
        return valor;
    }

    public String getTipo() {
        return "TRANSACAO";
    }

    public int getIdentificador1() { return identificador; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public LocalDate getData() { return data; }
    public Categoria getCategoria() { return categoria; }

	}


