
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package edu.asu.ows.wfs.v110;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3._2001.xmlschema.Schema;

import edu.asu.ows.Utils;
import edu.asu.ows.wfs._1_1_0.ServiceExceptionReport;
import edu.asu.ows.wfs._1_1_0.WfsPortType;
import net.opengis.wfs._1.DescribeFeatureTypeType;
import net.opengis.wfs._1.FeatureCollectionType;
import net.opengis.wfs._1.GetFeatureType;
import net.opengis.wfs._1.WFSCapabilitiesType;


@javax.jws.WebService(
		serviceName = "Wfs110SoapService", 
		portName = "Wfs110Soap12Service", 
		targetNamespace = "http://wfs.ows.asu.edu/1.1.0", 
		wsdlLocation = "file:ASU_WFS_SOAP_1.1.0.wsdl", 
		endpointInterface = "edu.asu.ows.wfs._1_1_0.WfsPortType")
public class WfsSoapServiceImpl extends WfsServiceGeneral implements WfsPortType {
	private static final Logger logger = Logger.getLogger(WfsSoapServiceImpl.class);
	@Autowired protected GetCapabilities getCapabilities = null;
	@Autowired protected DescribeFeatureType describeFeatureType = null;
	@Autowired protected GetFeature getFeature = null;
	
	@Override
	public WFSCapabilitiesType getCapabilities(net.opengis.wfs._1.GetCapabilitiesType request)
			throws ServiceExceptionReport {
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
	public net.opengis.wfs._1.FeatureCollectionType getFeature(net.opengis.wfs._1.GetFeatureType request) throws ServiceExceptionReport {
		try {
			net.opengis.wfs._1.FeatureCollectionType fc = this.getFeature.run(request);
			return fc;
		} catch (Exception e) {
			throw this.throwServiceExceptionReport(e);
		}
	}
	

}
