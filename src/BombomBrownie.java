
public class BombomBrownie extends Doces{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BombomBrownie(String sabor, String tamanho, int qtd) {
		super(sabor, tamanho, qtd);
		
	}

	@Override
	protected String alergicos() {
		return "Trigo, ovos, leite";
	}
	public String toString() {
		String bombomB = super.toString();
		return "Bombom de Brownie\n" + bombomB;
	}

}
