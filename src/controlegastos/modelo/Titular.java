package controlegastos.modelo;

public class Titular {
	
	private String nome;
	private String cpf;

public Titular (String nome, String cpf) {
	this.nome = nome; // atributo da classe , e o nome é o dado
	this.cpf =  cpf;

}
// metodos 
	public String getNome() {
		return nome;
	}
   public String getCpf () {
	   return cpf;
   }
}
