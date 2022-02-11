
public class Tortinhas extends Doces{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tortinhas(String sabor, String tamanho, int qtd) {
		super(sabor, tamanho, qtd);
		
	}

	@Override
	protected String alergicos() {
		
		return "Trigo, ovos, leite";
	}
	
	public String toString() {
		String tortinha = super.toString();
		return "Tortinha\n" + tortinha;
	}

}
