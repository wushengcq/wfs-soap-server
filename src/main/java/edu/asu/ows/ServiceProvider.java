package edu.asu.ows;

public class ServiceProvider implements ICapabilities {
	private String providerName = "";
	private String IndividualName = "";
	private String positionName = "";
	private String voice = "";
	private String facsimile = "";
	private String deliveryPoint = "";
	private String city = "";
	private String administrativeArea = "";
	private String postalCode = "";
	private String country = "";
	private String electronicMailAddress = "";
			
	public String getCapabilities(){
		StringBuffer sb = new StringBuffer();
		sb.append("<ows:ServiceProvider>");
		sb.append("<ows:ProviderName>").append(this.getProviderName()).append("</ows:ProviderName>");
		sb.append("<ows:ServiceContact>");
		sb.append("<ows:IndividualName>").append(this.getIndividualName()).append("</ows:IndividualName>");
		sb.append("<ows:PositionName>").append(this.getPositionName()).append("</ows:PositionName>");
		sb.append("<ows:ContactInfo>");
		sb.append("<ows:Phone>");
		sb.append("<ows:Voice>").append(this.getVoice()).append("</ows:Voice>");
		sb.append("<ows:Facsimile>").append(this.getFacsimile()).append("</ows:Facsimile>");
		sb.append("</ows:Phone>");
		sb.append("<ows:Address>");
		sb.append("<ows:DeliveryPoint>").append(this.getDeliveryPoint()).append("</ows:DeliveryPoint>");
		sb.append("<ows:City>").append(this.getCity()).append("</ows:City>");
		sb.append("<ows:AdministrativeArea>").append(this.getAdministrativeArea()).append("</ows:AdministrativeArea>");
		sb.append("<ows:PostalCode>").append(this.getPostalCode()).append("</ows:PostalCode>");
		sb.append("<ows:Country>").append(this.getCountry()).append("</ows:Country>");
		sb.append("<ows:ElectronicMailAddress>").append(this.getElectronicMailAddress()).append("</ows:ElectronicMailAddress>");
		sb.append("</ows:Address>");
		sb.append("</ows:ContactInfo>");
		sb.append("</ows:ServiceContact>");
		sb.append("</ows:ServiceProvider>");
		
		return sb.toString();
	}
	
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getIndividualName() {
		return IndividualName;
	}
	public void setIndividualName(String individualName) {
		IndividualName = individualName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public String getFacsimile() {
		return facsimile;
	}
	public void setFacsimile(String facsimile) {
		this.facsimile = facsimile;
	}
	public String getDeliveryPoint() {
		return deliveryPoint;
	}
	public void setDeliveryPoint(String deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAdministrativeArea() {
		return administrativeArea;
	}
	public void setAdministrativeArea(String administrativeArea) {
		this.administrativeArea = administrativeArea;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getElectronicMailAddress() {
		return electronicMailAddress;
	}
	public void setElectronicMailAddress(String electronicMailAddress) {
		this.electronicMailAddress = electronicMailAddress;
	}
	
}
