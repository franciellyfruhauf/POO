import java.io.Serializable;

public abstract class Doces implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sabor;
	private String tamanho;
	private int qtd;
	
	
	public Doces(String sabor, String tamanho, int qtd) {
		this.sabor = sabor;
		this.tamanho = tamanho;
		this.qtd = qtd;
	}
	public String toString() {
		String retorno = "";
		retorno += "Sabor: " + this.sabor + "\n";
		retorno += "Tamanho: " + this.tamanho + "\n";
		retorno += "Quantidade: " + this.qtd + "\n";
		retorno += "Alérgicos contém: " + alergicos() + "\n";
		return retorno;
	}
	protected abstract String alergicos();
}
	
