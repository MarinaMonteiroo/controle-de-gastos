package controlegastos.modelo;

public class Categoria {
	// todas as categorias de receita e despesa estão aqui
	 
	//receita
	public static final Categoria SALARIOS = new Categoria("Salários", true);
    public static final Categoria HONORARIOS = new Categoria("Honorários", true);
    public static final Categoria RENDIMENTOS = new Categoria("Rendimentos", true);
    public static final Categoria OUTRAS_RECEITAS = new Categoria("Outras receitas", true);

    
     /// despesa
    public static final Categoria MORADIA = new Categoria("Moradia", false);
    public static final Categoria LUZ = new Categoria("Luz", false);
    public static final Categoria AGUA = new Categoria("Água", false);
    public static final Categoria COMUNICACOES = new Categoria("Comunicações", false);
    public static final Categoria ALIMENTACAO = new Categoria("Alimentação", false);
    public static final Categoria TRANSPORTE = new Categoria("Transporte", false);
    public static final Categoria SAUDE = new Categoria("Saúde", false);
    public static final Categoria EDUCACAO = new Categoria("Educação", false);
    public static final Categoria LAZER = new Categoria("Lazer", false);
    public static final Categoria OUTRAS_DESPESAS = new Categoria("Outras despesas", false);

    private String nome;
    private boolean receita;   // true significa que é categoria de receita

    public Categoria(String nome, boolean receita) {
        this.nome = nome;
        this.receita = receita;
    }

    public String getNome() {
        return nome;
    }

    public boolean isReceita() {
        return receita;
    }
}
