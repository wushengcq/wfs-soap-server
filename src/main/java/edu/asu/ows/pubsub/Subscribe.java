package edu.asu.ows.pubsub;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import edu.asu.ows.IOperation;
import edu.asu.ows.Utils;
import net.opengis.pubsub._1.SubscribeType;

@Component
public class Subscribe extends OperationGeneral implements IOperation {
	
	private Logger logger = Logger.getLogger(Subscribe.class);
	
	public String run(SubscribeType request) {
		logger.debug(request.getFilter());
		logger.debug(request.getDeliveryLocation());
		logger.debug(request.getDeliveryMethod());
		logger.debug(request.getPublicationIdentifier());
		logger.debug(request.getFilterLanguageId());
		try {
			logger.debug(Utils.marshalV2(SubscribeType.class, request, "http://docs.oasis-open.org/wsn/b-2", "Subscribe"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "<done>test</done>";
	}
	
	@Override
	public String getCapabilities() {
		return super.buildCapabilities(this.getName());
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
