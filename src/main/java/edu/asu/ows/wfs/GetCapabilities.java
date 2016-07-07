package edu.asu.ows.wfs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.asu.datastore.FeatureSourceCollection;
import edu.asu.ows.IOperation;
import edu.asu.ows.OperationGeneral;

@Component
public class GetCapabilities extends OperationGeneral implements IOperation {
	private FeatureSourceCollection featureSourceCollection = null;
	private DescribeFeatureType describeFeatureType = null;
	
	public String getWfsServerCapabilities() {
		StringBuffer sb = new StringBuffer();
		sb.append("<wfs:WFS_Capabilities "
				+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
				+ "xmlns='http://www.opengis.net/wfs/2.0' "
				+ "xmlns:wfs='http://www.opengis.net/wfs/2.0' "
				+ "xmlns:ows='http://www.opengis.net/ows/1.1' "
				+ "xmlns:gml='http://www.opengis.net/gml/3.2' "
				+ "xmlns:fes='http://www.opengis.net/fes/2.0' "
				+ "xmlns:xlink='http://www.w3.org/1999/xlink' "
				+ "xmlns:xs='http://www.w3.org/2001/XMLSchema' "
				+ "xmlns:" + this.getServiceIdentification().getServiceNameSpace() + "='" 
				+ 			 this.getNameSpaceUrl() + "' "
				+ "xsi:schemaLocation='http://schemas.opengis.net/wfs/2.0/wfs.xsd http://www.opengis.net/wfs/2.0' "
				+ "version='2.0.0' "
				+ ">");
		sb.append(this.getServiceIdentification().getCapabilities());
		sb.append(this.getServiceProvider().getCapabilities());
		sb.append("<ows:OperationsMetadata>");
		sb.append(this.getCapabilities());
		sb.append(this.getDescribeFeatureType().getCapabilities());
		sb.append("</ows:OperationsMetadata>");		
		sb.append(this.getFeatureSourceCollection().getCapabilities());	
		sb.append("<fes:Filter_Capabilities>");		
		sb.append("</fes:Filter_Capabilities>");
		sb.append("</wfs:WFS_Capabilities>");
		return sb.toString();
	}
	
	@Override
	public String getCapabilities() {
		return this.getCapabilities(this.getName());
	}	
	
	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Autowired
	public void setFeatureSourceCollection(FeatureSourceCollection featureSourceCollection) {
		this.featureSourceCollection = featureSourceCollection;
	}	
	public FeatureSourceCollection getFeatureSourceCollection() {
		return featureSourceCollection;
	}
	
	@Autowired
	public void setDescribeFeatureType(DescribeFeatureType describeFeatureType) {
		this.describeFeatureType = describeFeatureType;
	}	
	public DescribeFeatureType getDescribeFeatureType() {
		return describeFeatureType;
	}

	
}
