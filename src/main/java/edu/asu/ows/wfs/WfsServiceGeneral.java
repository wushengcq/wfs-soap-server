package edu.asu.ows.wfs;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WfsServiceGeneral {
	private final static Logger logger = Logger.getLogger(WfsServiceGeneral.class);
	private GetCapabilities capabilitiesServiceProvider = null;
	private DescribeFeatureType describeFeatureType = null;
	
	protected ServiceExceptionReport throwServiceExceptionReport(Exception ex) {
		if (logger.isDebugEnabled() ) {
			ex.printStackTrace();
		}
		ServiceExceptionReport e = new ServiceExceptionReport();
		e.setStackTrace(ex.getStackTrace());
		return e;
	}
	
	@Autowired
	public void setDescribeFeatureType(DescribeFeatureType describeFeatureType) {
		this.describeFeatureType = describeFeatureType;
	}
	public DescribeFeatureType getDescribeFeatureType() {
		return describeFeatureType;
	}

	@Autowired
	public void setCapabilitiesServiceProvider(GetCapabilities getCapabilitiesServiceProvider) {
		this.capabilitiesServiceProvider = getCapabilitiesServiceProvider;
	}
	public GetCapabilities getCapabilitiesServiceProvider() {
		return capabilitiesServiceProvider;
	}
}
