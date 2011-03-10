/*
 * Copyright (C) The Community OpenORB Project. All rights reserved.
 *
 * This software is published under the terms of The OpenORB Community Software
 * License version 1.0, a copy of which has been included with this distribution
 * in the LICENSE.txt file.
 */

package netpad.orb;

/**
 * PushServer example implementation
 * 
 * @author Jerome Daniel
 * @author Olivier Modica
 */
public class PushSupplier extends Thread {
	private ProdutorNetPad produtor;
	private String nomeCanal = "ExampleChannel";
	private org.omg.CosEventChannelAdmin.EventChannel canal = null;
	private org.omg.CORBA.ORB orb;
	
	public PushSupplier() {
		super("ExampleChannel");
	}
	
	public PushSupplier(String nomeCanal) {
		this.nomeCanal = nomeCanal;
		this.setName("PushSupplier - Doc: "+nomeCanal);
		this.start();
	}

	public void run() {
		String args[] = null;
		org.omg.CORBA.Object obj = null;

		 orb = org.omg.CORBA.ORB.init(args, null);
		org.omg.PortableServer.POA rootPOA = null;

		try {
			obj = orb.resolve_initial_references("RootPOA");
		} catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
			ex.printStackTrace();
			System.exit(1);
		}

		rootPOA = org.omg.PortableServer.POAHelper.narrow(obj);

		try {
			rootPOA.the_POAManager().activate();
		} catch (java.lang.Exception ex) {
			System.out.println("Internal error...");
			ex.printStackTrace();
		}

		System.out.println("Event Service example");
		System.out.println("Push model / server side");

		try {
			obj = orb
					.string_to_object("corbaname:rir:#COS/EventService/EventChannelFactory");
		} catch (org.omg.CORBA.BAD_PARAM ex) {
			System.out.println("The EventService cannot be found.");
			System.out
					.println("Check if the Event and Naming servers are running.");
			System.exit(-1);
		}

		if (obj == null) {
			System.out.println("The EventService reference is incorrect.");
			System.out
					.println("Check if the Event and Naming servers are running.");
			System.exit(-1);
		}

		canal = criarCanal(obj, canal);

		org.omg.CosEventChannelAdmin.SupplierAdmin supplierAdmin = canal
				.for_suppliers();
		org.omg.CosEventChannelAdmin.ProxyPushConsumer consumer = supplierAdmin
				.obtain_push_consumer();

		produtor = new ProdutorNetPad(orb, consumer);
		System.out.println("Produtor ativo");
		// orb.connect( supplier );

		try {
			consumer.connect_push_supplier(produtor._this(orb));
		} catch (java.lang.Exception ex_connect) {
			System.out.println("Unable to connect to consumer");
			ex_connect.printStackTrace();
		}

		orb.run();
	}

	private org.omg.CosEventChannelAdmin.EventChannel criarCanal(org.omg.CORBA.Object obj, org.omg.CosEventChannelAdmin.EventChannel canal) {
		try {
			canal = org.openorb.event.EventChannelFactoryHelper.narrow(obj)
					.create_channel(nomeCanal);
		} catch (org.openorb.event.NameAlreadyUsed ex) {
			canal = abrirCanal(obj, canal);
		}
		return canal;
	}

	private org.omg.CosEventChannelAdmin.EventChannel abrirCanal(org.omg.CORBA.Object obj, org.omg.CosEventChannelAdmin.EventChannel canal) {
		try {
			canal = org.openorb.event.EventChannelFactoryHelper.narrow(obj)
					.join_channel(nomeCanal);
		} catch (java.lang.Throwable error) {
			error.printStackTrace();
			System.exit(-1);
		}
		return canal;
	}

	public ProdutorNetPad getProdutor() {
		return produtor;
	}

	public void fecharCanal() {
		canal.destroy();
	}
	
	public void interrupt(){
		super.interrupt();
		this.produtor.disconnect_push_supplier();
		orb.shutdown(true);
	}
}
