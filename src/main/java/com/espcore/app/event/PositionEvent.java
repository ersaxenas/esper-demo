package com.espcore.app.event;
/**
 * Class is POJO. It represents Position Events.
 * @author SSAXENA
 *
 */
public class PositionEvent {
    private String security;
    private String issuer;
    private String account;
    
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
    
   
}
