package edu.asu.ows.wfs.v110;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.asu.datastore.FeatureSourceCollection;
import edu.asu.ows.IOperation;
import edu.asu.ows.wfs.Constant;
import edu.asu.ows.wfs.OperationGeneral;
import edu.asu.ows.wfs._1_1_0.ServiceExceptionReport;
import net.opengis.wfs._1.GetCapabilitiesType;

@Component("wfsGetCapabilities")
public class GetCapabilities extends OperationGeneral implements IOperation {
	@Autowired private FeatureSourceCollection featureSourceCollection = null;
	@Autowired private GetFeature getFeature = null;
	@Autowired private DescribeFeatureType describeFeatureType = null;
	
	public String getWfsServerCapabilities(GetCapabilitiesType request) throws ServiceExceptionReport {
		return this.v1_1_0_impl();
	}
	
	private String v1_1_0_impl(){
		StringBuffer sb = new StringBuffer();
		sb.append("<wfs:WFS_Capabilities "
				+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
				+ "xmlns='http://www.opengis.net/wfs' "
				+ "xmlns:wfs='http://www.opengis.net/wfs' "
				+ "xmlns:ows='http://www.opengis.net/ows' "
				+ "xmlns:gml='http://www.opengis.net/gml' "
				+ "xmlns:ogc='http://www.opengis.net/ogc' "
				+ "xmlns:xlink='http://www.w3.org/1999/xlink' "
				+ "xmlns:xs='http://www.w3.org/2001/XMLSchema' "
				+ "xmlns:" + this.getWfsServiceIdentification().getServiceNameSpace() + "='" 
				+ 			 this.getNameSpaceUrl() + "' "
				+ "xsi:schemaLocation='http://www.opengis.net/wfs http://polar.geodacenter.org:80/geoserver29/schemas/wfs/1.1.0/wfs.xsd' "
				+ "version='" + Constant.WFS_VERSION_1_1 + "' "
				+ ">");
		this.getWfsServiceIdentification().setServiceTypeVersion(Constant.WFS_VERSION_1_1);
		sb.append(this.getWfsServiceIdentification().getCapabilities());
		sb.append(this.getServiceProvider().getCapabilities());
		sb.append("<ows:OperationsMetadata>");
		sb.append(this.getCapabilities());
		sb.append(this.describeFeatureType.getCapabilities());
		sb.append(this.getFeature.getCapabilities());
		sb.append("</ows:OperationsMetadata>");		
		sb.append(this.featureSourceCollection.getCapabilities());	
		//sb.append(this.getFilterCapabilities());
		sb.append("</wfs:WFS_Capabilities>");
		return sb.toString();
	}

	private String getFilterCapabilities(){
		StringBuffer sb = new StringBuffer();
		sb.append("<fes:Filter_Capabilities>");
		sb.append(this.getIdCapabilities());
		sb.append(this.getScalarCapabilities());
		sb.append(this.getSpatialFilterCapabilities());
		sb.append("</fes:Filter_Capabilities>");	
		return sb.toString();
	}	
	
	private String getIdCapabilities(){
		StringBuffer sb = new StringBuffer();
		sb.append("<fes:Id_Capabilities>");
		sb.append("<fes:ResourceIdentifier name='fes:ResourceId' />");
		sb.append("</fes:Id_Capabilities>");
		return sb.toString();
	}

	private String getScalarCapabilities(){
		StringBuffer sb = new StringBuffer();
		sb.append("<fes:Scalar_Capabilities>");
		sb.append("<fes:LogicalOperators>");
		sb.append("</fes:LogicalOperators>");
		sb.append("<fes:ComparisonOperators>");
		sb.append("<fes:ComparisonOperator name='PropertyIsLessThan'/>");
		sb.append("<fes:ComparisonOperator name='PropertyIsGreaterThan'/>");
		sb.append("<fes:ComparisonOperator name='PropertyIsLessThanOrEqualTo'/>");
		sb.append("<fes:ComparisonOperator name='PropertyIsGreaterThanOrEqualTo'/>");
		sb.append("<fes:ComparisonOperator name='PropertyIsEqualTo'/>");
		sb.append("<fes:ComparisonOperator name='PropertyIsNotEqualTo'/>");
		sb.append("<fes:ComparisonOperator name='PropertyIsLike'/>");
		sb.append("<fes:ComparisonOperator name='PropertyIsBetween'/>");
		sb.append("<fes:ComparisonOperator name='PropertyIsNull'/>");
		sb.append("</fes:ComparisonOperators>");
		sb.append("</fes:Scalar_Capabilities>");
		return sb.toString();
	}
	
	private String getSpatialFilterCapabilities(){
		StringBuffer sb = new StringBuffer();
		sb.append("<fes:Spatial_Capabilities>");
		sb.append("<fes:GeometryOperands>");
		sb.append("<fes:GeometryOperand name='gml:Envelope'/>");
		sb.append("<fes:GeometryOperand name='gml:Point'/>");
		sb.append("<fes:GeometryOperand name='gml:MultiPoint'/>");
		sb.append("<fes:GeometryOperand name='gml:LineString'/>");
		sb.append("<fes:GeometryOperand name='gml:MultiLineString'/>");
		sb.append("<fes:GeometryOperand name='gml:Polygon'/>");
		sb.append("<fes:GeometryOperand name='gml:MultiPolygon'/>");
		sb.append("<fes:GeometryOperand name='gml:MultiGeometry'/>");
		sb.append("</fes:GeometryOperands>");
		sb.append("<fes:SpatialOperators>");
		sb.append("<fes:SpatialOperator name='Intersects'/>");
		sb.append("<fes:SpatialOperator name='Within'/>");
		sb.append("<fes:SpatialOperator name='BBOX'/>");
		sb.append("<fes:SpatialOperator name='Disjoint'/>");
		sb.append("<fes:SpatialOperator name='Contains'/>");
		sb.append("<fes:SpatialOperator name='Overlaps'/>");
		sb.append("<fes:SpatialOperator name='Touches'/>");
		sb.append("<fes:SpatialOperator name='Equals'/>");
		sb.append("</fes:SpatialOperators>");
		sb.append("</fes:Spatial_Capabilities>");		
		return sb.toString();
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
	public String getCapabilities() {
		return super.buildCapabilities(this.getName());
	}

	@Override
	protected String[] getAcceptVersions() {
		return new String[]{Constant.WFS_VERSION_2_0, Constant.WFS_VERSION_1_1};
	}
	
}
