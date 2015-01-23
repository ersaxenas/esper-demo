package com.espcore.app.esper.subscriber;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.EventBean;

/**
 * Class contains statement and listener method for order event. 
 * @author SSAXENA
 */
@Component
public class OrderQunatityThresholdSubscriber implements EsperStatement{

	/*Logger for the class*/
	private static final Logger LOGGER = Logger.getLogger(OrderQunatityThresholdSubscriber.class);

	/**
	 * Function gets called by esper engine whenever an orders is pushed.
	 */
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
      EventBean event = newEvents[0];
      LOGGER.info("ALERT: Consecutive Orders for "+ event.get("stotal") + " qunatity received.");
	}
    /**
     * Function returns esper statement for order event. It captures if sum of last five orders is more then 5000.
     */
	public String getEsperStatement() {
		String selAllOrders = "select sum(quantity) as stotal from OrderEvent.win:length(5) having sum(quantity) > 7000 ";
		return selAllOrders;
	}
}
