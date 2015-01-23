package com.espcore.app;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.espcore.app.esper.CepEngineService;
import com.espcore.app.event.OrderEvent;
import com.espcore.app.event.PositionEvent;
import com.espcore.app.util.EventBuilder;

/**
 * Class creates a spring container. It creates 3 threads for publishing order, account and position events.
 * Each thread publishes an event and then sleeps for 1 sec. Order and position events are of POJO events and account events are of MAP events.
 */
public class App 
{
    public static void main( String[] args )
    {  /*Starting spring container here */
       ClassPathXmlApplicationContext springAppContext = new ClassPathXmlApplicationContext(new String[] {"esper-app-config.xml"});
       final CepEngineService espService = (CepEngineService) springAppContext.getBean("cepEngineService");
       EventBuilder eb = (EventBuilder) springAppContext.getBean("EventBuilder");
       /*List of Order Event*/
       final List<OrderEvent> orderLst = eb.getOrderEvents();
       /*List of Account Event*/
       final List<Map> accountEventLst = eb.getAccountEvent();
       /*List of position events*/
       final List<PositionEvent> postionEventLst = eb.getPositionEvents();
       /*Thread will start pushing order events*/
       Thread orderThread = new Thread(){
    	   public void run() {
    		 for(OrderEvent event: orderLst) {
    			 espService.sendOrderEvent(event);
    			 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		 }
    	   }
       };
       /*Thread will start pushing account events*/
       Thread accountThread = new Thread(){
    	   public void run() {
    		 for(Map event: accountEventLst) {
    			 espService.sendAccountEvent(event);
    			 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		 }
    	   }
       };
       /*Thread will start pushing position events*/
       Thread positionThread = new Thread(){
    	   public void run() {
    		 for(PositionEvent event : postionEventLst) {
    			 espService.sendPositionEvent(event);
    			 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		 }
    	   }
       };
     orderThread.start();  
     accountThread.start();
     positionThread.start();
    }
}
