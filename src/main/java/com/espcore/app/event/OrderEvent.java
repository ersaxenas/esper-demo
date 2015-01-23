package com.espcore.app.event;

/**
 * Class is a POJO. It represents order event.
 * @author SSAXENA
 *
 */
public class OrderEvent {
	private String security;
	private int quantity;
	private double price;
	private String direction;
	private String symbol;
	
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	@Override
	public String toString() {
		return "OrderEvent [security=" + security + ", quantity=" + quantity + ", price=" + price + ", direction=" + direction + ", symbol=" + symbol + "]";
	}
}
