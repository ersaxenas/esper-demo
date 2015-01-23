package com.espcore.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.espcore.app.event.OrderEvent;
import com.espcore.app.event.PositionEvent;
import com.thoughtworks.xstream.XStream;

@Component("EventBuilder")
public class EventBuilder {
	/* xstream instance to serialize and de-serialize object from xml. */
	XStream xStream = null;
	/**
	 * Function initializes xstream. It sets alias for events.
	 */
	@PostConstruct
	private void initialize() {
		xStream = new XStream();
		xStream.alias("order", OrderEvent.class);
		xStream.alias("position", PositionEvent.class);
	}

	/**
	 * Function fetches list of events from xml file.
	 * 
	 * @return ArrayList<OrderEvent>
	 */
	public List<OrderEvent> getOrderEvents() {
		List<OrderEvent> orderLst = (List<OrderEvent>) xStream.fromXML(this.getClass().getResourceAsStream("/OrderEvents.xml"));
		System.out.println(orderLst.toString());
		return orderLst;
	}
	/**
	 * Function fetches list of position events from xml file.
	 * @return List<PositionEvent>
	 */
	public List<PositionEvent> getPositionEvents() {
		List<PositionEvent> positionLst = (List<PositionEvent>) xStream.fromXML(this.getClass().getResourceAsStream("/PositionEvent.xml"));
		System.out.println(positionLst.toString());
		return positionLst;
	}

	/**
	 * Function prepares list of account evnets.
	 * @return List<Map>
	 */
	public List<Map> getAccountEvent() {
		List<Map> accEventLst = new ArrayList<Map>();
		accEventLst.add(prepareEventMap("605", 129800.098, 84631531.87, 3216354.00, 5215554541313.00, "RG"));
		accEventLst.add(prepareEventMap("611", 855410.098, 89764165.87, 56453413.00, 1654684654521.00, "RG"));
		accEventLst.add(prepareEventMap("EU8", 521314.098, 7848746.87, 54221351.00, 94313216341.00, "LS"));
		accEventLst.add(prepareEventMap("RES", 56453.098, 7465436.87, 656644.00, 225686654316546.00, "RG"));
		accEventLst.add(prepareEventMap("006", 8945534.098, 23154864.87, 654656.00, 2156464613613.00, "LS"));
		accEventLst.add(prepareEventMap("NR8", 65454.098, 2315646.87, 2316464.00, 6513168574865.00, "RG"));
		accEventLst.add(prepareEventMap("BU4", 854541.098, 2315648.87, 561351313.00, 56463123131.00, "RG"));
		accEventLst.add(prepareEventMap("LRU", 864654.098, 6545432.87, 1465615135.00, 85656311334.00, "RG"));
		accEventLst.add(prepareEventMap("MU9", 564341.098, 54546321.87, 52165463.00, 651646416566.00, "LS"));
		accEventLst.add(prepareEventMap("SR2", 45242123.098, 56465435.87, 541654654.00, 564654641546341.00, "RG"));
		accEventLst.add(prepareEventMap("VNR", 6543641.098, 56453544.87, 5446546.00, 864656464.00, "RG"));
		accEventLst.add(prepareEventMap("FDR", 435121.098, 654613.87, 5655454654.00, 56456354413.00, "LS"));
		accEventLst.add(prepareEventMap("WRT", 6543532.098, 564646536.87, 5465641313.00, 239545644.00, "RG"));
		accEventLst.add(prepareEventMap("PMF", 564654.098, 65463413.87, 231646546.00, 561351313498.00, "LS"));
		accEventLst.add(prepareEventMap("UDF", 68465525.098, 6541321.87, 524564654.00, 68631923498.00, "RG"));
		accEventLst.add(prepareEventMap("MRU", 6545463.098, 54534354.87, 4513132.00, 2398354131498.00, "LS"));
		accEventLst.add(prepareEventMap("CDU", 2135455.098, 23413535.87, 56465465.00, 65434543498.00, "RG"));
		return accEventLst;
	}

	/**
	 * Function creates Map based on account information passed. It creates map representation of account event. This map object will be passed to esper engine. Map attributes are
	 * registered in CepEngineService.
	 * 
	 * @param accId
	 * @param netAssVal
	 * @param totalAssVal
	 * @param startOfDayCash
	 * @param parentNetAssVal
	 * @param accType
	 * @return Map.
	 */
	private Map prepareEventMap(final String accId, final Double netAssVal, final Double totalAssVal, final Double startOfDayCash, final Double parentNetAssVal,
			final String accType) {
		Map accMap = new HashMap();
		accMap.put(ApplicationConstants.ACCOUNT_ID, accId);
		accMap.put(ApplicationConstants.NET_ASSET_VALUE, netAssVal);
		accMap.put(ApplicationConstants.TOTAL_ASSET_VALUE, totalAssVal);
		accMap.put(ApplicationConstants.START_OF_THE_DAY_CASH, startOfDayCash);
		accMap.put(ApplicationConstants.PARENT_NET_ASSET_VALUE, parentNetAssVal);
		accMap.put(ApplicationConstants.ACCOUNT_TYPE, accType);
		return accMap;
	}

	public static void main(String args[]) {
		XStream xStream1 = new XStream();
		xStream1.alias("order", OrderEvent.class);
		xStream1.alias("position", PositionEvent.class);
		ArrayList<OrderEvent> orderLst = new ArrayList<OrderEvent>();
		OrderEvent oevent = new OrderEvent();
		oevent.setDirection("B");
		oevent.setPrice(120);
		oevent.setQuantity(234);
		oevent.setSecurity("CISCO");
		oevent.setSymbol("CISC");
		orderLst.add(oevent);
		oevent = new OrderEvent();
		oevent.setDirection("S");
		oevent.setPrice(393);
		oevent.setQuantity(87);
		oevent.setSecurity("ADOBE");
		oevent.setSymbol("ADOBE");
		orderLst.add(oevent);
		String xml = xStream1.toXML(orderLst);
		System.out.println(xml);
		ArrayList<PositionEvent> posLst = new ArrayList<PositionEvent>();
		PositionEvent posEvent = new PositionEvent();
		posEvent.setAccount("605");
		posEvent.setIssuer("IBM");
		posEvent.setSecurity("IBMLABS");
		posLst.add(posEvent);
		System.out.println(xStream1.toXML(posLst));
	}

}
