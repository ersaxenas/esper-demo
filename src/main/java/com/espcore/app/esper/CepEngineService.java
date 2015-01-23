package com.espcore.app.esper;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.espcore.app.esper.subscriber.EsperStatement;
import com.espcore.app.event.OrderEvent;
import com.espcore.app.event.PositionEvent;
import com.espcore.app.util.ApplicationConstants;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

/**
 * Class initializes Esper engine instances. It creates and keeps handle to CEP engine. It also provides helper functions to publish events to engine. While initializing it
 * iterates over colletion of <TYPE> to register statements and listeners.
 * 
 * @author SSAXENA
 * 
 */
public class CepEngineService {

	private static final Logger LOGGER = Logger.getLogger(CepEngineService.class);
	private final boolean isInfo = LOGGER.isInfoEnabled();
	private final boolean isDebug = LOGGER.isDebugEnabled();
	/* Esper service reference */
	EPServiceProvider epService = null;
	/*
	 * Package where all the esper POJO events would reside. This is being injected via spring xml configuration file.
	 */
	private String eventPackage;
	
    /*All statements and listeners*/
	@Autowired
	private Collection<EsperStatement> esperStatementsColl;

	/**
	 * Function initializes esper engine.
	 */
	@PostConstruct
	private void initialize() {
		if(isDebug) {
			LOGGER.debug("Initializing ESPER engine.");
		}
		Configuration config = new Configuration();
		//config.addEventTypeAutoName(eventPackage);
		config.configure("esper_config.xml");
		this.epService = EPServiceProviderManager.getDefaultProvider(config);
		/*Register map events*/
		registerMapEvent();
		/*Register statements and subscribers*/
		if(esperStatementsColl!= null && !esperStatementsColl.isEmpty()) {
			for(EsperStatement estmt : esperStatementsColl) {
				EPStatement statement = epService.getEPAdministrator().createEPL(estmt.getEsperStatement());
				statement.addListener(estmt);
			}
		}
	}
	
	/**
	 * Function registers a map event type of Account Event. It registers name and attributes of the event.
	 */
	private void registerMapEvent() {
		/*Registering Account event*/
		Map<String, Object> eventDef = new HashMap<String, Object>();
		eventDef.put(ApplicationConstants.ACCOUNT_ID, String.class);
		eventDef.put(ApplicationConstants.NET_ASSET_VALUE, Number.class);
		eventDef.put(ApplicationConstants.TOTAL_ASSET_VALUE, Number.class);
		eventDef.put(ApplicationConstants.START_OF_THE_DAY_CASH, Number.class);
		eventDef.put(ApplicationConstants.PARENT_NET_ASSET_VALUE, Number.class);
		eventDef.put(ApplicationConstants.ACCOUNT_TYPE, String.class);
		epService.getEPAdministrator().getConfiguration().addEventType(ApplicationConstants.ACCOUNT_EVENT, eventDef);
	}
	
	/**
	 * Function submits order event to esper engine.
	 * @param event
	 */
	public void sendOrderEvent(final OrderEvent event) {
		epService.getEPRuntime().sendEvent(event);
	}
	/**
	 * Function submits account event to esper engine.
	 * @param accEvent
	 */
	public void sendAccountEvent(final Map accEvent) {
		epService.getEPRuntime().sendEvent(accEvent,ApplicationConstants.ACCOUNT_EVENT);
	}
	/**
	 * Function submits position events to esper engine.
	 * @param event
	 */
	public void sendPositionEvent(final PositionEvent event) {
		epService.getEPRuntime().sendEvent(event);
	}
	

	public String getEventPackage() {
		return eventPackage;
	}

	public void setEventPackage(String eventPackage) {
		this.eventPackage = eventPackage;
	}

}
