package netpad.orb;

public class ProdutorNetPad extends org.omg.CosEventComm.PushSupplierPOA {
	/**
	 * Reference to the ORB
	 */
	private org.omg.CORBA.ORB orb;

	/**
	 * Reference to the consumer
	 */
	private org.omg.CosEventComm.PushConsumer consumer;

	/**
	 * Constructor
	 */
	public ProdutorNetPad(org.omg.CORBA.ORB orb,
			org.omg.CosEventComm.PushConsumer consumer) {
		this.orb = orb;
		this.consumer = consumer;
	}

	/**
	 * Disconnection from supplier
	 */
	public void disconnect_push_supplier() {
		consumer.disconnect_push_consumer();

		consumer = null;
	}

	public void query(String nickname) {
		this.pushMessage("QUERY|" + nickname);
	}

	public void answer(String nickname, int numeroTotalSecoes, int numeroSecao) {
		this.pushMessage("ANSWER|" + nickname + "|" + numeroTotalSecoes + "|"
				+ numeroSecao);
	}

	public void update(int numeroSecao, String nickname, String tituloSecao,
			String textoSecao) {
		this.pushMessage("UPDATE|" + numeroSecao + "|" + nickname + "|"
				+ tituloSecao + "|" + textoSecao);
	}

	public void leave(String nickname, int numeroSecao) {
		this.pushMessage("LEAVE|" + nickname + "|" + numeroSecao);
	}

	private void pushMessage(String msg) {
		org.omg.CORBA.Any any = orb.create_any();

		any.insert_string(msg);

		try {
			consumer.push(any);
		} catch (java.lang.Exception ex) {
			System.out.println("Erro no Produtor");
			ex.printStackTrace();
		}
	}
}
