package netpad.orb;

import java.util.StringTokenizer;

import netpad.controle.ControleDocumento;

public class ConsumidorNetPad extends org.omg.CosEventComm.PushConsumerPOA {
	private enum TipoMensagem {
		Query, Answer, Update, Leave
	}

	/**
	 * Reference to consommateur
	 */
	private org.omg.CosEventComm.PushSupplier supplier;
	
	private ControleDocumento controleDocumento;

	/**
	 * Constructor
	 */
	public ConsumidorNetPad(org.omg.CosEventComm.PushSupplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * Disconnection from consumer
	 */
	public void disconnect_push_consumer() {
		supplier.disconnect_push_supplier();

		supplier = null;
	}

	public void push(org.omg.CORBA.Any any) {
		String s = any.extract_string();
		System.out.println("Received : " + s);
		StringTokenizer tk = new StringTokenizer(s,"|");
		String[] elementos = new String[tk.countTokens()];
		int i = 0;
		while (tk.hasMoreElements()){	
			elementos[i++] = tk.nextToken();
		}
		TipoMensagem tipo = getTipoMensagem(elementos[0]);
		switch (tipo) {
		case Query:
			this.controleDocumento.trataQuery(elementos[1]);			
			break;
		case Answer:
			this.controleDocumento.trataAnswer(elementos[1],Integer.parseInt(elementos[2]),Integer.parseInt(elementos[3]));			
			break;
		case Update:
			String texto;
			try{
				texto = elementos[4];
			} catch (IndexOutOfBoundsException e) {
				texto = "";
			}
			this.controleDocumento.trataUpdate(Integer.parseInt(elementos[1]),elementos[2],elementos[3], texto);			
			break;
		case Leave: 
			this.controleDocumento.trataLeave(elementos[1],Integer.parseInt(elementos[2]));			
			break;
		}
	}

	private TipoMensagem getTipoMensagem(String tipo) {
		if (tipo.equals("QUERY")) {
			return TipoMensagem.Query;
		} else if (tipo.equals("ANSWER")) {
			return TipoMensagem.Answer;
		} else if (tipo.equals("UPDATE")) {
			return TipoMensagem.Update;
		} else if (tipo.equals("LEAVE")) {
			return TipoMensagem.Leave;
		}
		return null;
	}

	public ControleDocumento getControleDocumento() {
		return controleDocumento;
	}

	public void setControleDocumento(ControleDocumento controleDocumento) {
		this.controleDocumento = controleDocumento;
	}

}
