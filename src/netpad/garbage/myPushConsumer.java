/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/
package netpad.garbage;

/**
 * PullConsumer example implementation
 * 
 * @author  Jerome Daniel 
 * @author  Olivier Modica
 */
public class myPushConsumer
    extends org.omg.CosEventComm.PushConsumerPOA
{
    /**
     * Reference to consommateur
     */
    private org.omg.CosEventComm.PushSupplier supplier;

    /**
     * Constructor
     */
    public myPushConsumer( org.omg.CosEventComm.PushSupplier supplier )
    {
        this.supplier = supplier;
    }

    /**
     * Disconnection from consumer
     */
    public void disconnect_push_consumer()
    {
        supplier.disconnect_push_supplier();

        supplier = null;
    }

    public void push( org.omg.CORBA.Any any )
    {
        String s = any.extract_string();

        System.out.println( "Received : " + s );
    }
}
