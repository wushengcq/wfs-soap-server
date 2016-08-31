
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package edu.asu.ows.wfs;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.w3._2001.xmlschema.Schema;

import edu.asu.ows.Utils;
import net.opengis.wfs._2.DescribeFeatureTypeType;
import net.opengis.wfs._2.FeatureCollectionType;
import net.opengis.wfs._2.GetCapabilitiesType;
import net.opengis.wfs._2.GetFeatureType;
import net.opengis.wfs._2.WFSCapabilitiesType;

@javax.jws.WebService(serviceName = "WfsSoapService", portName = "WfsSoap12Service", targetNamespace = "http://wfs.ows.asu.edu", wsdlLocation = "file:ASU_WFS_SOAP.wsdl", endpointInterface = "edu.asu.ows.wfs.WfsPortType")
public class WfsSoapServiceImpl extends WfsServiceGeneral implements WfsPortType {
	private static final Logger logger = Logger.getLogger(WfsSoapServiceImpl.class);
	
	@Override
	public WFSCapabilitiesType getCapabilities(GetCapabilitiesType request) throws ServiceExceptionReport {
//		List<String>  versions = request.getAcceptVersions().getVersion();
//		for (String version : versions) {
//			if(version.startsWith(Constant.WFS_VERSION_2_0)){
//				String capabilities = super.getCapabilities.getWfsServerCapabilities(request);
//				WFSCapabilitiesType result = Utils.unmarshal(WFSCapabilitiesType.class, capabilities);
//				return result;
//			}else if(version.startsWith(Constant.WFS_VERSION_1_1)) {
//				String capabilities = super.getCapabilities.getWfsServerCapabilities(request);
//				WFSCapabilitiesType result = Utils.unmarshal(WFSCapabilitiesType.class, capabilities);
//				return result;
//			}
//		}
//		throw new ServiceExceptionReport("Unsupport WFS version number.");
		
		String capabilities = super.getCapabilities.getWfsServerCapabilities(request);
		WFSCapabilitiesType result = Utils.unmarshal(WFSCapabilitiesType.class, capabilities);
		return result;
	}

	@Override
	public Schema describeFeatureType(DescribeFeatureTypeType request) throws ServiceExceptionReport {
		try {
			String xml = super.describeFeatureType.describe(request.getTypeName());
			return Utils.unmarshal(Schema.class, xml);
		} catch (Exception e) {
			throw this.throwServiceExceptionReport(e);
		}
	}

	@Override
	public FeatureCollectionType getFeature(GetFeatureType request) throws ServiceExceptionReport {
		try {
			return super.getFeature.run(request);
		} catch (Exception e) {
			throw this.throwServiceExceptionReport(e);
		}
	}

}
