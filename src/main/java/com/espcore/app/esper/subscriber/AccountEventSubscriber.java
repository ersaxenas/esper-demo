package com.espcore.app.esper.subscriber;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.espcore.app.util.ApplicationConstants;
import com.espertech.esper.client.EventBean;

/**
 * Class contains statement and listener method for Account event. 
 * @author SSAXENA
 */
@Component
public class AccountEventSubscriber implements EsperStatement{

	/*Logger for the class*/
	private static final Logger LOGGER = Logger.getLogger(AccountEventSubscriber.class);

	/**
	 * Function gets called by esper engine whenever an account event is pushed.
	 */
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
      EventBean event = newEvents[0];
      LOGGER.info("Account Event for account  "+ event.get(ApplicationConstants.ACCOUNT_ID) + " received.");
	}
    /**
     * Function returns esper statement for order event.
     * Statement captures all the order events.
     */
	public String getEsperStatement() {
		String selAllOrders = "select * from AccountEvent";
		return selAllOrders;
	}
}
