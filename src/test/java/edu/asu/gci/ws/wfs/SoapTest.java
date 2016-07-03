package edu.asu.gci.ws.wfs;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import net.opengis.ows._1.AcceptVersionsType;
import net.opengis.wfs._2.GetCapabilitiesType;

public class SoapTest {
	@Test
	public void getCapabilities() throws ServiceExceptionReport {
		String serviceUrl = "http://localhost:9090/wfs/ows/ows";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ASUWFSSOAPPortType.class);
		factory.setAddress(serviceUrl);
		ASUWFSSOAPPortType ows = (ASUWFSSOAPPortType) factory.create();
		
		AcceptVersionsType version = new AcceptVersionsType();
		
		GetCapabilitiesType getCapabilitiesType = new GetCapabilitiesType();
		//getCapabilitiesType.setAcceptVersions();
		getCapabilitiesType.setService("WFS");
		net.opengis.wfs._2.WFSCapabilitiesType type = ows.getCapabilities(getCapabilitiesType);
		System.out.println(type);
	}

}
