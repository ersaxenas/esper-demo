package com.espcore.app.esper.subscriber;

import com.espertech.esper.client.UpdateListener;

/**
 * Interface defines contract for esper statement and listener.
 * If declares function to get the statement. It extends from UpdateListener.
 * It wraps listener and statement.
 * @author SSAXENA
 *
 */
public interface EsperStatement extends UpdateListener {

	/**
	 * Function returns esper statements which can be registered using EPService provider.
	 * @return
	 */
	public String getEsperStatement();
}
