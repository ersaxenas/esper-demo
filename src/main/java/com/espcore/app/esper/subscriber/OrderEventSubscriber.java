package com.espcore.app.esper.subscriber;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.EventBean;

/**
 * Class contains statement and listener method for order event. 
 * @author SSAXENA
 */
@Component
public class OrderEventSubscriber implements EsperStatement{

	/*Logger for the class*/
	private static final Logger LOGGER = Logger.getLogger(OrderEventSubscriber.class);

	/**
	 * Function gets called by esper engine whenever an orders is pushed.
	 */
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
      EventBean event = newEvents[0];
      LOGGER.info("Order for security "+ event.get("security") + " received.");
	}
    /**
     * Function returns esper statement for order event.
     * Statement captures all the order events.
     */
	public String getEsperStatement() {
		String selAllOrders = "select * from OrderEvent";
		return selAllOrders;
	}
}
