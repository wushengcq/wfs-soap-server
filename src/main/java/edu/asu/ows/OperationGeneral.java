package edu.asu.ows;

import org.springframework.beans.factory.annotation.Autowired;

public class OperationGeneral {
	private ServiceIdentification serviceIdentification = null;
	private ServiceProvider serviceProvider = null;

	protected String getCapabilities(String operationName) {
		StringBuffer sb = new StringBuffer();
		sb.append("<ows:Operation name='" + operationName + "'>");
		sb.append("<ows:DCP>");
		sb.append("<ows:HTTP>");
		sb.append("<ows:Post xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
				.append("post").append("'/>");
		sb.append("<ows:SOAP1.1 xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
				.append("soap/1.1").append("'/>");
		sb.append("<ows:SOAP1.1-MTOM xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
				.append("soap/1.1/mtom").append("'/>");
		sb.append("<ows:SOAP1.2 xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
				.append("soap/1.2").append("'/>");
		sb.append("<ows:SOAP1.2-MTOM xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
				.append("soap/1.2/mtom").append("'/>");
		sb.append("</ows:HTTP>");
		sb.append("</ows:DCP>");
		sb.append(Utils.parameter("AcceptVersions",
				new String[] { this.getServiceIdentification().getServiceTypeVersion() }));
		sb.append(Utils.parameter("AcceptFormats", new String[] { "text/xml" }));
		sb.append("</ows:Operation>");
		return sb.toString();
	}

	protected String getNameSpaceUrl() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getServiceIdentification().getServiceSchemaUrl())
		  .append(this.getServiceIdentification().getServiceNameSpace());
		return sb.toString();
	}
	
	protected String getNameSpaceAttributeTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("xmlns:");
		sb.append(this.getServiceIdentification().getServiceNameSpace()).append("='");
		sb.append(this.getNameSpaceUrl()).append("' ");
		return sb.toString();
	}

	@Autowired
	public void setServiceIdentification(ServiceIdentification serviceIdentification) {
		this.serviceIdentification = serviceIdentification;
	}

	public ServiceIdentification getServiceIdentification() {
		return serviceIdentification;
	}

	@Autowired
	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}
}
