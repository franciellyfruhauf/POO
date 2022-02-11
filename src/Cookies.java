
public class Cookies extends Doces {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Cookies(String sabor, String tamanho,  int qtd) {
		super(sabor, tamanho, qtd);
		
	}

	@Override
	protected String alergicos() {
		return "Trigo, ovos, leite";
	}
	public String toString() {
		String cookie = super.toString();
		return "Cookie\n" + cookie;
	}

}
