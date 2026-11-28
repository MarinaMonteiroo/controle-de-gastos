package controlegastos.modelo;

public class Titular {
	
	private String nome;
	private String cpf;

public Titular (String nome, String cpf) {
	this.nome = nome; 
	this.cpf =  cpf;

}
 public Titular(String nome) {
	 this.nome  = nome;
	 this.cpf = "Não informado";
	 }
 
	public String getNome() {
		return nome;
	}
   public String getCpf () {
	   return cpf;
   }
}
