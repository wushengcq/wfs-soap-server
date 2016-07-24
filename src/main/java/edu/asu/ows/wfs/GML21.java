package edu.asu.ows.wfs;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.log4j.Logger;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.gml2.GMLWriter;

public class GML21 extends GMLAbstract {
	private Logger logger = Logger.getLogger(GML21.class);

	public void encode(OutputStream out, FeatureCollection<SimpleFeatureType, SimpleFeature> fc, int total)
			throws DatatypeConfigurationException, IOException {
		this.toGML(out, (SimpleFeatureCollection) fc, total);
	}

	private void toGML(OutputStream out, FeatureCollection<SimpleFeatureType, SimpleFeature> fc, int total)
			throws DatatypeConfigurationException, IOException {
		ReferencedEnvelope box = fc.getBounds();
		StringBuffer sb = new StringBuffer();
		sb.append("<wfs:FeatureCollection ");
		sb.append("xmlns:xs='http://www.w3.org/2001/XMLSchema' ");
		sb.append("xmlns:wfs='http://www.opengis.net/wfs/2.0' ");
		sb.append("xmlns:gml='http://schemas.opengis.net/gml/2.1.2' ");
		sb.append("xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' ");
		sb.append("xmlns:" + this.namespace + "='" + this.namespaceUrl + "' ");
		sb.append("xsi:schemaLocation='http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd http://schemas.opengis.net/gml/2.1.2/gml.xsd' ");
		sb.append("numberMatched='" + total + "' ");
		sb.append("numberReturned='" + fc.size() + "' ");
		sb.append("timeStamp='" + (new Timestamp(System.currentTimeMillis())).toString() + "' >");
		sb.append("<gml:boundedBy>");
		sb.append("<gml:Box srsName='" + box.getCoordinateReferenceSystem().getName() + "'>");
		sb.append("<gml:coordinates>");
		sb.append(box.getMinX() + "," + box.getMinY() + " " + box.getMaxX() + "," + box.getMaxY());
		sb.append("</gml:coordinates>");
		sb.append("</gml:Box>");
		sb.append("</gml:boundedBy>");
				
		String typeName = fc.getSchema().getTypeName();
		List<AttributeDescriptor> attrs = fc.getSchema().getAttributeDescriptors();
		for (FeatureIterator<SimpleFeature> it = fc.features(); it.hasNext();) {
			SimpleFeature sf = it.next();
			sb.append("<wfs:member>");
			sb.append("<" + this.namespace + ":" + typeName + " fid='" + sf.getID() + "'>");
			for (AttributeDescriptor attr : attrs) {
				Object val = sf.getAttribute(attr.getName());
				sb.append("<" + this.namespace + ":" + attr.getLocalName() + ">");
				if (val instanceof Geometry) {
					sb.append((new GMLWriter()).write((Geometry)val));					
				} else {
					sb.append(val == null ? "" : val.toString());
				}
				sb.append("</" + this.namespace + ":" + attr.getLocalName() + ">");
			}
			sb.append("</" + this.namespace + ":" + typeName + ">");
			sb.append("</wfs:member>");
		}
		sb.append("</wfs:FeatureCollection>");
		out.write(sb.toString().getBytes());
	}
	
}
