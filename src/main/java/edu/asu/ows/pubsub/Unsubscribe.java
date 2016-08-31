package edu.asu.ows.pubsub;

import org.springframework.stereotype.Component;

import edu.asu.ows.IOperation;
import net.opengis.pubsub._1.UnsubscribeType;

@Component
public class Unsubscribe  extends OperationGeneral implements IOperation {
	
	public String run(UnsubscribeType request) {
		return null;
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
