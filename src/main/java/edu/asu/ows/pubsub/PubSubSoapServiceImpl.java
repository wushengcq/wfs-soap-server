package edu.asu.ows.pubsub;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import edu.asu.ows.Utils;
import edu.asu.ows.publisher.PubsubPortType;
import edu.asu.ows.publisher.ServiceExceptionReport;
import net.opengis.pubsub._1.GetCapabilitiesType;
import net.opengis.pubsub._1.PublisherCapabilitiesType;
import net.opengis.pubsub._1.SubscribeResponseType;
import net.opengis.pubsub._1.SubscribeType;
import net.opengis.pubsub._1.UnsubscribeResponseType;
import net.opengis.pubsub._1.UnsubscribeType;

@javax.jws.WebService(
		serviceName = "$service.ServiceName", 
		targetNamespace = "$service.Namespace", 
		wsdlLocation = "file:asu-wfs-soap-publisher.wsdl", 
		endpointInterface = "edu.asu.ows.publisher.PubsubPortType")
public class PubSubSoapServiceImpl implements PubsubPortType {
	private static final Logger logger = Logger.getLogger(PubSubSoapServiceImpl.class);
	@Autowired protected GetCapabilities getCapabilities = null;
	@Autowired protected Subscribe subscribe = null;
	@Autowired protected Unsubscribe unsubscribe = null;
	
	@Override
	public PublisherCapabilitiesType getCapabilities(GetCapabilitiesType request) throws ServiceExceptionReport {
		String result = this.getCapabilities.getPubsubServerCapabilities(request);
		logger.debug(result);
		return Utils.unmarshal(PublisherCapabilitiesType.class, result);
	}

	@Override
	public SubscribeResponseType subscribe(SubscribeType request) throws ServiceExceptionReport {
		String result = this.subscribe.run(request);
		logger.debug(result);
		return Utils.unmarshal(SubscribeResponseType.class, result);
	}

	@Override
	public UnsubscribeResponseType unsubscribe(UnsubscribeType request) throws ServiceExceptionReport {
		String result = this.unsubscribe.run(request);
		logger.debug(result);
		return Utils.unmarshal(UnsubscribeResponseType.class, result);
	}
}
