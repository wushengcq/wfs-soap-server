package edu.asu.ows.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.asu.ows.IOperation;
import edu.asu.ows.Utils;
import edu.asu.ows.publisher.ServiceExceptionReport;
import net.opengis.pubsub._1.GetCapabilitiesType;

@Component("pubsubGetCapabilities")
public class GetCapabilities extends OperationGeneral implements IOperation {
	@Autowired private Subscribe subscribe = null;
	@Autowired private Unsubscribe unsubscribe = null;
	
	public String getPubsubServerCapabilities(GetCapabilitiesType request) throws ServiceExceptionReport {
		return this.v2_0_0_impl();
	}
	
	private String v2_0_0_impl(){
		StringBuffer sb = new StringBuffer();
		sb.append("<pubsub:PublisherCapabilities ");
		sb.append("xmlns:" + this.getPubsubServiceIdentification().getServiceNameSpace() + "='" + this.getNameSpaceUrl() + "' ");
		sb.append("xmlns:pubsub='http://www.opengis.net/pubsub/1.0' ");
		sb.append("xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' ");
		sb.append("xmlns:wsa='http://www.w3.org/2005/08/addressing' ");
		sb.append("xmlns:ows='http://www.opengis.net/ows/1.1' ");		
		sb.append("xmlns:fes='http://www.opengis.net/fes/2.0' ");
		sb.append("xmlns:gml='http://www.opengis.net/gml/3.2' ");
		sb.append("xmlns:xlink='http://www.w3.org/1999/xlink' ");
		sb.append("xsi:schemaLocation='http://www.opengis.net/pubsub/1.0 http://schemas.opengis.net/pubsub/1.0.0/pubsubAll.xsd' ");
		sb.append("version='1.0.0'>");
		sb.append(this.getPubsubServiceIdentification().getCapabilities());
		sb.append(this.getServiceProvider().getCapabilities());
		sb.append("<ows:OperationsMetadata>");
		sb.append(this.getCapabilities());
		sb.append(this.subscribe.getCapabilities());
		sb.append(this.unsubscribe.getCapabilities());
		sb.append("</ows:OperationsMetadata>");		
		sb.append("</pubsub:PublisherCapabilities>");
		return sb.toString();
	}
			
	@Override
	public String getCapabilities() {
		StringBuffer sb = new StringBuffer();
		sb.append("<ows:Operation name='" + this.getName() + "'>");
		sb.append("<ows:DCP>");
		sb.append("<ows:HTTP>");
		sb.append("<ows:Post xlink:href='").append(this.getPubsubServiceIdentification().getServiceUrl()).append("/").append("kvp").append("' xlink:type='simple'/>");
		sb.append("</ows:HTTP>");
		sb.append("</ows:DCP>");
		sb.append(Utils.parameter("AcceptVersions",	this.getAcceptVersions()));
		sb.append(Utils.parameter("AcceptFormats", this.getAcceptFormats()));
		sb.append(Utils.parameter("Sections", new String[]{"ServiceIdentification","ServiceProvider","OperationsMetadata","Contents","FilterCapabilities","DeliveryCapabilities"}));
		sb.append("<ows:Constraint name='PostEncoding'>");
		sb.append("<ows:AllowedValues>");
		sb.append("<ows:Value>XML</ows:Value>");
		sb.append("<ows:Value>SOAP</ows:Value>");
		sb.append("</ows:AllowedValues>");
		sb.append("</ows:Constraint>");
		sb.append("</ows:Operation>");
		return sb.toString();
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	protected String[] getAcceptFormats() {
		return new String[]{"text/xml"};
	}

	@Override
	protected String[] getAcceptVersions() {
		return new String[]{"1.0.0"};
	}
}
