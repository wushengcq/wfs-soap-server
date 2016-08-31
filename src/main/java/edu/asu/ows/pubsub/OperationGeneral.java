package edu.asu.ows.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import edu.asu.ows.ServiceIdentification;
import edu.asu.ows.ServiceProvider;
import edu.asu.ows.Utils;

public abstract class OperationGeneral {
	private ServiceIdentification pubsubServiceIdentification = null;
	private ServiceProvider serviceProvider = null;
	protected abstract String[] getAcceptFormats();
	protected abstract String[] getAcceptVersions();

	protected String buildCapabilities(String operationName) {
		StringBuffer sb = new StringBuffer();
		sb.append("<ows:Operation name='" + operationName + "'>");
		sb.append("<ows:DCP>");
		sb.append("<ows:HTTP>");
		//sb.append("<ows:Get xlink:href='").append(this.getPubsubServiceIdentification().getServiceUrl()).append("/").append("kvp").append("' xlink:type='simple'/>");
		sb.append("<ows:Post xlink:href='").append(this.getPubsubServiceIdentification().getServiceUrl()).append("/").append("kvp").append("' xlink:type='simple'/>");
		sb.append("</ows:HTTP>");
		sb.append("</ows:DCP>");
		sb.append(Utils.parameter("AcceptVersions",	new String[] { this.getPubsubServiceIdentification().getServiceTypeVersion() }));
		sb.append(Utils.parameter("AcceptFormats", new String[] { "text/xml" }));
		sb.append("<ows:Constraint name='PostEncoding'>");
		sb.append("<ows:AllowedValues>");
		sb.append("<ows:Value>XML</ows:Value>");
		sb.append("<ows:Value>SOAP</ows:Value>");
		sb.append("</ows:AllowedValues>");
		sb.append("</ows:Constraint>");
		sb.append("</ows:Operation>");
		return sb.toString();
	}
	
	protected String getNameSpaceUrl() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getPubsubServiceIdentification().getServiceSchemaUrl())
		  .append(this.getPubsubServiceIdentification().getServiceNameSpace());
		return sb.toString();
	}
	
	protected String getNameSpaceAttributeTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("xmlns:");
		sb.append(this.getPubsubServiceIdentification().getServiceNameSpace()).append("='");
		sb.append(this.getNameSpaceUrl()).append("' ");
		return sb.toString();
	}

	@Autowired
    @Qualifier("pubsubServiceIdentification")
	public void setPubsubServiceIdentification(ServiceIdentification serviceIdentification) {
		this.pubsubServiceIdentification = serviceIdentification;
	}
	public ServiceIdentification getPubsubServiceIdentification() {
		return pubsubServiceIdentification;
	}

	@Autowired
	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}
}
