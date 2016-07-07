package edu.asu.ows;

public class ServiceIdentification implements ICapabilities {
	public String title = "WFS SOAP Server";
	public String abstraction = "";
	public String keywords = "WFS|SOAP";
	public String serviceType = "WFS";
	public String serviceTypeVersion = "2.0.0";
	public String fees = "";
	public String accessConstraints = "";
	public String serviceSchemaUrl = "http://localhost/";
	public String serviceNameSpace = "myWfsSoapServer";
	public String serviceUrl = "";
	
	public String getCapabilities() {
		StringBuffer sb = new StringBuffer();
		sb.append("<ows:ServiceIdentification>");
		sb.append("<ows:Title>").append(this.getTitle()).append("</ows:Title>");
		sb.append("<ows:Abstract>").append(this.getAbstraction()).append("</ows:Abstract>");
		sb.append("<ows:Keywords>");
		for(String key : this.getKeywords().split("\\|")) {
			sb.append("<ows:Keyword>" + key + "</ows:Keyword>");
		}
		sb.append("</ows:Keywords>");
		sb.append("<ows:ServiceType>").append(this.getServiceType()).append("</ows:ServiceType>");
		sb.append("<ows:ServiceTypeVersion>").append(this.getServiceTypeVersion()).append("</ows:ServiceTypeVersion>");
		sb.append("<ows:Fees>").append(this.getFees()).append("</ows:Fees>");
		sb.append("<ows:AccessConstraints>").append(this.getAccessConstraints()).append("</ows:AccessConstraints>");
		sb.append("</ows:ServiceIdentification>");
		return sb.toString();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstraction() {
		return abstraction;
	}
	public void setAbstraction(String abstraction) {
		this.abstraction = abstraction;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceTypeVersion() {
		return serviceTypeVersion;
	}
	public void setServiceTypeVersion(String serviceTypeVersion) {
		this.serviceTypeVersion = serviceTypeVersion;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getAccessConstraints() {
		return accessConstraints;
	}
	public void setAccessConstraints(String accessConstraints) {
		this.accessConstraints = accessConstraints;
	}
	public String getServiceSchemaUrl() {
		return serviceSchemaUrl;
	}
	public void setServiceSchemaUrl(String serviceSchemaUrl) {
		this.serviceSchemaUrl = serviceSchemaUrl + (serviceSchemaUrl.endsWith("/") ? "" : "/");
	}
	public String getServiceNameSpace() {
		return serviceNameSpace;
	}
	public void setServiceNameSpace(String serviceNameSpace) {
		this.serviceNameSpace = serviceNameSpace;
	}
	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}	
}
