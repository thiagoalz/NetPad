/*
 * Copyright (C) The Community OpenORB Project. All rights reserved.
 *
 * This software is published under the terms of The OpenORB Community Software
 * License version 1.0, a copy of which has been included with this distribution
 * in the LICENSE.txt file.
 */

package netpad.orb;

/**
 * PushClient example implementation
 * 
 * @author Jerome Daniel
 * @author Olivier Modica
 */
public class PushConsumer extends Thread {
	
	private ConsumidorNetPad consumidor;

	private String nomeCanal = "ExampleChannel";
	private org.omg.CosEventChannelAdmin.EventChannel canal = null;
	private org.omg.CORBA.ORB orb = null;
	
	public PushConsumer() {
		super("ExampleChannel");
	}

	public PushConsumer(String nomeCanal) {
		this.nomeCanal = nomeCanal;
		this.setName("PushConsumer - Doc: "+nomeCanal);
		this.start();
	}

	public void run() {
		String args[] = null;
		org.omg.CORBA.Object obj = null;

		System.out.println("Event Service example");
		System.out.println("Push model / client side");

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

		org.omg.CosEventChannelAdmin.ConsumerAdmin consumerAdmin = canal
				.for_consumers();
		org.omg.CosEventChannelAdmin.ProxyPushSupplier supplier = consumerAdmin
				.obtain_push_supplier();

		consumidor = new ConsumidorNetPad(supplier);
		System.out.println("Consumidor ativo");
		//orb.connect( consumidor );

		try {
			supplier.connect_push_consumer(consumidor._this(orb));
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

	public ConsumidorNetPad getConsumidor() {
		return consumidor;
	}

	public void fecharCanal() {
		canal.destroy();
	}
	
	public void interrupt(){
		super.interrupt();
		this.consumidor.disconnect_push_consumer();
		orb.shutdown(true);
	}
}
