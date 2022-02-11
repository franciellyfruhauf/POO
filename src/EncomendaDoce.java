import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class EncomendaDoce {
	private ArrayList<Doces> doces;

	public EncomendaDoce() {
		this.doces = new ArrayList<Doces>();

	}

	public String[] leValores(String[] dadosIn) {
		String[] dadosOut = new String[dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++) {
			dadosOut[i] = JOptionPane.showInputDialog("Entre com " + dadosIn[i] + ": ");

		}
		return dadosOut;
	}

	public Tortinhas leTortinhas() {
		String[] valores = new String[3];
		String[] nomeVal = { "Sabor:\n- Chocolate\n- Maracujá\n- Limão", "Tamanho:\n- Grande\n- Médio\n- Pequeno", "Quantidade" };
		valores = leValores(nomeVal);

		int qtd = this.retornaInteiro(valores[2]);

		Tortinhas tortinha = new Tortinhas(valores[0], valores[1], qtd);
		return tortinha;
	}

	public BombomBrownie leBombomBrownie() {
		String[] valores = new String[3];
		String[] nomeVal = { "Sabor:\n- Chocolate Preto\n- Chocolate Branco", "Tamanho:\n- Grande\n- Médio\n- Pequeno", "Quantidade" };
		valores = leValores(nomeVal);

		int qtd = this.retornaInteiro(valores[2]);

		BombomBrownie bombom = new BombomBrownie(valores[0], valores[1], qtd);
		return bombom;
	}

	public Cookies leCookies() {
		String[] valores = new String[3];
		String[] nomeVal = { "Sabor:\n- Tradicional\n- Cacau\n- Dark", "Tamanho:\n- Grande\n- Médio\n- Pequeno", "Quantidade" };
		valores = leValores(nomeVal);

		int qtd = this.retornaInteiro(valores[2]);

		Cookies cookie = new Cookies(valores[0], valores[1], qtd);
		return cookie;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public int retornaInteiro(String entrada) {
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor Incorreto! \n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}
	
	public void salvaEncomenda(ArrayList<Doces> doces) {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("c:\\temp\\encomendaDoces.dados"));
			for (int i = 0; i < doces.size(); i++)
				outputStream.writeObject(doces.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Impossivel criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Doces> recuperaEncomenda(){
		ArrayList<Doces> encomendaTemp = new ArrayList<Doces>();
		
		ObjectInputStream inputStream = null;
		try {
			inputStream = new ObjectInputStream (new FileInputStream("c:\\temp\\encomendaDoces.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if(obj instanceof Doces) {
					encomendaTemp.add((Doces)obj);
				}
			}
		}catch (EOFException ex) {
			System.out.println("Fim de arquivo.");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo com encomendas não existe!");
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				} catch (final IOException ex) {
					ex.printStackTrace();
				}
				return encomendaTemp;
			}
		}
	
	public void menuEncomenda() {
		String menu = "";
		String entrada;
		int opc1, opc2;
		do {
			menu = "Controle Encomendas\n" +
					"Opções: \n" + 
					"1. Escolher Tipo de Doce\n" +
	                "2. Exibir Carrinho Encomendas\n" +
	                "3. Limpar Carrinho Encomendas\n" +
	                "4. Gravar Carrinho Encomendas\n" +
	                "5. Recuperar Carrinho Encomendas\n" +
	                "6. Sair";
			entrada = JOptionPane.showInputDialog(menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);
			
			switch(opc1) {
			case 1:
				menu = "Escolher Tipo de Doce\n"+
						"Opções: \n" +
						"1. Tortinhas\n" +
						"2. Bombom de Brownie\n"+
						"3. Cookies \n";
				
				entrada = JOptionPane.showInputDialog(menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);
				
				switch (opc2) {
				case 1: doces.add((Doces)leTortinhas());
				break;
				case 2: doces.add((Doces)leBombomBrownie());
				break;
				case 3: doces.add((Doces)leCookies());
				break;
				default:
				JOptionPane.showMessageDialog(null, "Entrada não válida!");
				
				}
				
				break;
			case 2:
				if (doces.size() == 0) {
					JOptionPane.showMessageDialog(null, "Escolha algum doce ...");
					break;
				}
				String dados = "";
				for (int i = 0; i< doces.size(); i++) {
					dados += doces.get(i).toString() + "------------------------------\n";
				}
				JOptionPane.showMessageDialog(null, dados);
				break;
			case 3:
				if (doces.size() == 0) {
					JOptionPane.showMessageDialog(null, "Escolha algum doce ...");
					break;
				}
				doces.clear();
				JOptionPane.showMessageDialog(null, "Carrinho limpo com sucesso!");
				break;
			case 4:
				if (doces.size() == 0) {
					JOptionPane.showMessageDialog(null, "Escolha algum doce ...");
					break;
				}
				salvaEncomenda(doces);
				JOptionPane.showMessageDialog(null, "Carrinho salvo com sucesso!");
				break;
			case 5:
				doces = recuperaEncomenda();
				if (doces.size() == 0) {
					JOptionPane.showMessageDialog(null, "Sem carrinho para apresentar");
					break;
				}
				JOptionPane.showMessageDialog(null, "Carrinho recuperado com sucesso!");
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "Fim do aplicativo Encomenda de Doces");
				break;
				
			}
		}while (opc1 != 6);
	}


	public static void main(String[] args) {
		EncomendaDoce encomenda = new EncomendaDoce ();
		encomenda.menuEncomenda();

	}

}