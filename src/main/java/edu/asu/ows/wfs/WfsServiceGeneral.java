package edu.asu.ows.wfs;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WfsServiceGeneral {
	private final static Logger logger = Logger.getLogger(WfsServiceGeneral.class);
//	@Autowired protected GetCapabilities2_0 getCapabilities2_0 = null;
//	@Autowired protected GetCapabilities1_1 getCapabilities1_1 = null;
	@Autowired protected GetCapabilities getCapabilities = null;
	@Autowired protected DescribeFeatureType describeFeatureType = null;
	@Autowired protected GetFeature getFeature = null;
	
	protected ServiceExceptionReport throwServiceExceptionReport(Exception ex) {
		if (logger.isDebugEnabled() ) {
			ex.printStackTrace();
		}
		ServiceExceptionReport e = new ServiceExceptionReport(ex.getMessage());
		e.setStackTrace(ex.getStackTrace());
		return e;
	}
	
//	@Autowired
//	public void setDescribeFeatureType(DescribeFeatureType describeFeatureType) {
//		this.describeFeatureType = describeFeatureType;
//	}
//	public DescribeFeatureType getDescribeFeatureType() {
//		return describeFeatureType;
//	}
//
//	@Autowired
//	public void setCapabilitiesServiceProvider(GetCapabilities getCapabilitiesServiceProvider) {
//		this.capabilitiesServiceProvider = getCapabilitiesServiceProvider;
//	}
//	public GetCapabilities getCapabilitiesServiceProvider() {
//		return capabilitiesServiceProvider;
//	}
//
//	@Autowired
//	public void setGetFeature(GetFeature getFeature) {
//		this.getFeature = getFeature;
//	}	
//	public GetFeature getGetFeature() {
//		return getFeature;
//	}
}
