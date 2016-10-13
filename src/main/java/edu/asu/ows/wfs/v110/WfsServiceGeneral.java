package edu.asu.ows.wfs.v110;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.ows.wfs._1_1_0.ServiceExceptionReport;
import edu.asu.ows.wfs.v200.DescribeFeatureType;
import edu.asu.ows.wfs.v200.GetCapabilities;
import edu.asu.ows.wfs.v200.GetFeature;

@Service
public class WfsServiceGeneral {
	private final static Logger logger = Logger.getLogger(WfsServiceGeneral.class);
	protected ServiceExceptionReport throwServiceExceptionReport(Exception ex) {
		if (logger.isDebugEnabled() ) {
			ex.printStackTrace();
		}
		ServiceExceptionReport e = new ServiceExceptionReport(ex.getMessage());
		e.setStackTrace(ex.getStackTrace());
		return e;
	}
}
