package edu.asu.ows.wfs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import edu.asu.ows.ServiceIdentification;
import edu.asu.ows.ServiceProvider;
import edu.asu.ows.Utils;

public abstract class OperationGeneral {
	private ServiceIdentification wfsServiceIdentification = null;
	private ServiceProvider serviceProvider = null;
	protected abstract String[] getAcceptFormats();
	protected abstract String[] getAcceptVersions();

	protected String buildCapabilities(String operationName) {
		StringBuffer sb = new StringBuffer();
		sb.append("<ows:Operation name='" + operationName + "'>");
		sb.append("<ows:DCP>");
		sb.append("<ows:HTTP>");
		sb.append("<ows:Get xlink:href='").append(this.getWfsServiceIdentification().getServiceUrl()).append("/").append("kvp").append("' xlink:type='simple'/>");
		sb.append("<ows:Post xlink:href='").append(this.getWfsServiceIdentification().getServiceUrl()).append("/").append("kvp").append("' xlink:type='simple'/>");
		sb.append("</ows:HTTP>");
		sb.append("</ows:DCP>");
		sb.append(Utils.parameter("AcceptVersions",	new String[] { this.getWfsServiceIdentification().getServiceTypeVersion() }));
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
	
//	protected String buildCapabilities(String operationName) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("<ows:Operation name='" + operationName + "'>");
//		sb.append("<ows:DCP>");
//		sb.append("<ows:HTTP>");
//		sb.append("<ows:Post xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
//				.append("post").append("'/>");
//		sb.append("<ows:SOAP1.1 xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
//				.append("soap/1.1").append("'/>");
//		sb.append("<ows:SOAP1.1-MTOM xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
//				.append("soap/1.1/mtom").append("'/>");
//		sb.append("<ows:SOAP1.2 xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
//				.append("soap/1.2").append("'/>");
//		sb.append("<ows:SOAP1.2-MTOM xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
//				.append("soap/1.2/mtom").append("'/>");
//		sb.append("<ows:SOAP1.2-SECURITY xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
//				.append("soap/1.2/sec").append("'/>");
//		sb.append("<ows:SOAP1.2-MTOM-SECURITY xlink:href='").append(this.getServiceIdentification().getServiceUrl()).append("/")
//			.append("soap/1.2/mtom/sec").append("'/>");
//		sb.append("</ows:HTTP>");
//		sb.append("</ows:DCP>");
//		sb.append(Utils.parameter("AcceptVersions",
//				new String[] { this.getServiceIdentification().getServiceTypeVersion() }));
//		sb.append(Utils.parameter("AcceptFormats", new String[] { "text/xml" }));
//		sb.append("</ows:Operation>");
//		return sb.toString();
//	}

	protected String getNameSpaceUrl() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getWfsServiceIdentification().getServiceSchemaUrl())
		  .append(this.getWfsServiceIdentification().getServiceNameSpace());
		return sb.toString();
	}
	
	protected String getNameSpaceAttributeTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("xmlns:");
		sb.append(this.getWfsServiceIdentification().getServiceNameSpace()).append("='");
		sb.append(this.getNameSpaceUrl()).append("' ");
		return sb.toString();
	}

	@Autowired
    @Qualifier("wfsServiceIdentification")
	public void setWfsServiceIdentification(ServiceIdentification serviceIdentification) {
		this.wfsServiceIdentification = serviceIdentification;
	}
	public ServiceIdentification getWfsServiceIdentification() {
		return wfsServiceIdentification;
	}

	@Autowired
	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}
}
