package edu.asu.ows.wfs;

import java.io.IOException;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.geotools.data.FeatureSource;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.asu.datastore.FeatureSourceCollection;
import edu.asu.ows.IOperation;
import edu.asu.ows.OperationGeneral;

@Component
public class DescribeFeatureType extends OperationGeneral implements IOperation {
	private Logger logger = Logger.getLogger(DescribeFeatureType.class);
	private FeatureSourceCollection featureSourceCollection = null;
	
	public String describe(List<QName> featureNames) throws NullPointerException, IOException, TransformerException {		
		StringBuffer sb = new StringBuffer();
		sb.append("<xsd:schema ");
		sb.append(this.getNameSpaceAttributeTag());
		sb.append("targetNamespace=").append("'").append(this.getNameSpaceUrl()).append("' ");
		sb.append("xmlns:gml='http://www.opengis.net/gml/3.2' ");
		sb.append("xmlns:xsd='http://www.w3.org/2001/XMLSchema' ");
		sb.append("elementFormDefault='qualified'> ");
		sb.append("<xsd:import ");
		sb.append("namespace='http://www.opengis.net/gml/3.2' ");
		sb.append("schemaLocation='http://schemas.opengis.net/gml/3.2.1/gml.xsd' />");
		for(QName name : featureNames) {
			sb.append(this.describe(name.getLocalPart()));
		}
		sb.append("</xsd:schema>");
		return sb.toString();
	}
	
	private String describe(String featureName) throws IOException, TransformerException {
		FeatureSource<SimpleFeatureType, SimpleFeature> fs = this.getFeatureSourceCollection().getFeatureSource(featureName);
		if (fs == null) {
			logger.warn("Can't find FeatureSource named '" + featureName + "'");
			throw new NullPointerException("Can't find FeatureSource named '" + featureName + "' in the registed feature sources.");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<xsd:complexType name='" + featureName + "Type'>");
		sb.append("<xsd:complexContent>");
		sb.append("<xsd:extension base='gml:AbstractFeatureType'>");
		sb.append("<xsd:sequence>");
		sb.append(this.describeAttribute(fs));
		sb.append("</xsd:sequence>");
		sb.append("</xsd:extension>");
		sb.append("</xsd:complexContent>");
		sb.append("</xsd:complexType>");
		sb.append("<xsd:element name='" + featureName + "' ");
		sb.append("substitutionGroup='gml:AbstractFeature' ");
		sb.append("type='" + this.getServiceIdentification().getServiceNameSpace() + ":" + featureName + "Type'/>");
		return sb.toString();
	}

	private String describeAttribute(FeatureSource<SimpleFeatureType, SimpleFeature> fs) throws IOException, TransformerException {
		StringBuffer sb = new StringBuffer();		
		SimpleFeatureType type = fs.getSchema();
		for(int i=0; i<type.getAttributeCount(); i++) {
			AttributeDescriptor ad = type.getDescriptor(i);
			sb.append("<xsd:element ");
			sb.append("maxOccurs='").append(ad.getMaxOccurs()).append("' ");
			sb.append("minOccurs='").append(ad.getMinOccurs()).append("' ");
			sb.append("nillable='").append(ad.isNillable()).append("' ");
			sb.append("type='").append(this.map2Gml(ad.getType().getBinding())).append("' ");
			sb.append("name='").append(ad.getName()).append("' />");
		}		
		return sb.toString();
	}

	private String map2Gml(Class<?> clazz) {
		String xsd = "xsd:";	
		switch(clazz.getName()){
		case "java.lang.String":	return xsd+"string";
		case "java.lang.Byte":		return xsd+"byte";
		case "java.lang.Short":		return xsd+"short";
		case "java.lang.Integer":	return xsd+"int";
		case "java.lang.Long":		return xsd+"long";
		case "java.lang.BigInteger":return xsd+"integer";
		case "java.lang.BigDecimal":return xsd+"decimal";
		case "java.util.Date":		return xsd+"dateTime";
		case "java.sql.Time":		return xsd+"date";
		case "java.sql.Timestamp":	return xsd+"dateTime";
		case "java.lang.Boolean":	return xsd+"boolean";
		case "com.vividsolutions.jts.geom.Point":	return "gml:PointPropertyType";	
		case "com.vividsolutions.jts.geom.LineString" : return "gml:LineStringPropertyType";
		case "com.vividsolutions.jts.geom.Polygon" : return "gml:PolygonPropertyType";
		case "com.vividsolutions.jts.geom.MultiPoint" : return "gml:MultiPointPropertyType";
		case "com.vividsolutions.jts.geom.MultiLineString" : return "gml:MutliLineStringPropertyType";
		case "com.vividsolutions.jts.geom.MultiPolygon" : return "gml:MultiPolygonPropertyType";
		case "org.geotools.data.Feature" : return "gml:AbstractFeatureType";
		default:
			throw new java.lang.UnknownError("Unknown feature attribute type " + clazz.getName());
		}		
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

}
