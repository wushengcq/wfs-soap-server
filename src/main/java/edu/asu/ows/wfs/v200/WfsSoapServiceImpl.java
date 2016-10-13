
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package edu.asu.ows.wfs.v200;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3._2001.xmlschema.Schema;

import edu.asu.ows.Utils;
import edu.asu.ows.wfs.ServiceExceptionReport;
import edu.asu.ows.wfs.WfsPortType;
import net.opengis.wfs._2.DescribeFeatureTypeType;
import net.opengis.wfs._2.FeatureCollectionType;
import net.opengis.wfs._2.GetCapabilitiesType;
import net.opengis.wfs._2.GetFeatureType;
import net.opengis.wfs._2.WFSCapabilitiesType;

@javax.jws.WebService(serviceName = "WfsSoapService", portName = "WfsSoap12Service", targetNamespace = "http://wfs.ows.asu.edu", wsdlLocation = "file:ASU_WFS_SOAP.wsdl", endpointInterface = "edu.asu.ows.wfs.WfsPortType")
public class WfsSoapServiceImpl extends WfsServiceGeneral implements WfsPortType {
	private static final Logger logger = Logger.getLogger(WfsSoapServiceImpl.class);
	@Autowired protected GetCapabilities getCapabilities = null;
	@Autowired protected DescribeFeatureType describeFeatureType = null;
	@Autowired protected GetFeature getFeature = null;
	
	@Override
	public WFSCapabilitiesType getCapabilities(GetCapabilitiesType request) throws ServiceExceptionReport {
		try {
			String capabilities = this.getCapabilities.getWfsServerCapabilities(request);
			WFSCapabilitiesType result = Utils.unmarshal(WFSCapabilitiesType.class, capabilities);
			return result;
		} catch (Exception e) {
			throw this.throwServiceExceptionReport(e);
		}
	}

	@Override
	public Schema describeFeatureType(DescribeFeatureTypeType request) throws ServiceExceptionReport {
		try {
			String xml = this.describeFeatureType.describe(request.getTypeName());
			return Utils.unmarshal(Schema.class, xml);
		} catch (Exception e) {
			throw this.throwServiceExceptionReport(e);
		}
	}

	@Override
	public FeatureCollectionType getFeature(GetFeatureType request) throws ServiceExceptionReport {
		try {
			return this.getFeature.run(request);
		} catch (Exception e) {
			throw this.throwServiceExceptionReport(e);
		}
	}

}
