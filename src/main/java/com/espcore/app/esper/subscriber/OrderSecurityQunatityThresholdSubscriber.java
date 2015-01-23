package com.espcore.app.esper.subscriber;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.EventBean;

/**
 * Class contains statement and listener method for order event. 
 * @author SSAXENA
 */
@Component
public class OrderSecurityQunatityThresholdSubscriber implements EsperStatement{

	/*Logger for the class*/
	private static final Logger LOGGER = Logger.getLogger(OrderSecurityQunatityThresholdSubscriber.class);

	/**
	 * Function gets called by esper engine whenever an orders is pushed.
	 */
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
      EventBean event = newEvents[0];
      LOGGER.info("ALERT: Orders for "+ event.get("security") +" received for "+ event.get("stotal") + " qunatity received.");
	}
    /**
     * Function returns esper statement for order event. It captures if orders for a security exceed 1000 within 10 secs.
     */
	public String getEsperStatement() {
		String selAllOrders = "select security, sum(quantity) as stotal from OrderEvent.win:time(10 sec) group by security having sum(quantity) > 1000 ";
		return selAllOrders;
	}
}
