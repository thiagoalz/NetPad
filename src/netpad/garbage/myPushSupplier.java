/*
* Copyright (C) The Community OpenORB Project. All rights reserved.
*
* This software is published under the terms of The OpenORB Community Software
* License version 1.0, a copy of which has been included with this distribution
* in the LICENSE.txt file.
*/

package netpad.garbage;

/**
 * PushSupplier example implementation
 * 
 * @author  Jerome Daniel 
 * @author  Olivier Modica
 */
public class myPushSupplier
    extends org.omg.CosEventComm.PushSupplierPOA
    implements Runnable
{
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
    public myPushSupplier( org.omg.CORBA.ORB orb, org.omg.CosEventComm.PushConsumer consumer )
    {
        this.orb = orb;
        this.consumer = consumer;
    }

    /**
     * Disconnection from supplier
     */
    public void disconnect_push_supplier()
    {
        consumer.disconnect_push_consumer();

        consumer = null;
    }

    /**
     * Entry point
     */
    public void run()
    {
        org.omg.CORBA.Any any = orb.create_any();
        String s = null;

        while ( true )
        {
            System.out.print( "Message to send : " );

            java.io.InputStreamReader inread = new java.io.InputStreamReader( System.in );
            java.io.BufferedReader reader = new java.io.BufferedReader( inread );

            try
            {
                s = reader.readLine();
            }
            catch ( java.io.IOException ioex )
            {
                any.insert_string( "Error at PushSupplier" );
            }

            any.insert_string( s );

            try
            {
                consumer.push( any );
            }
            catch ( java.lang.Exception ex )
            {
                System.out.println( "End of PushSupplier" );
                ex.printStackTrace();
                return ;
            }
        }
    }

}
