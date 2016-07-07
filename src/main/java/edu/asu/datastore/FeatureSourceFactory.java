package edu.asu.datastore;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.beans.factory.annotation.Autowired;

import edu.asu.ows.ICapabilities;
import edu.asu.ows.ServiceIdentification;

public class FeatureSourceFactory implements ICapabilities {
	private Map<String, Object> dataStoreParameters = null;
	private String featureTypeName = null;
	//private String featureTitle = null;
	private String abstraction = "";
	private String keywords = "";
	private ServiceIdentification serviceIdentification = null;
	private Logger log = Logger.getLogger(FeatureSourceFactory.class);

	public FeatureSource<SimpleFeatureType, SimpleFeature> getFeatureSource() throws IOException {
		DataStore dataStore = DataStoreFinder.getDataStore(this.getDataStoreParameters());
		return dataStore.getFeatureSource(this.getFeatureTypeName());
	}

	@Override
	public String getCapabilities() throws IOException {
		try {
			FeatureSource<SimpleFeatureType, SimpleFeature> fs = this.getFeatureSource();
			ReferencedEnvelope wgs84Box = fs.getBounds().transform(DefaultGeographicCRS.WGS84, true);
			StringBuffer sb = new StringBuffer();
			String nameSpace = "xmlns:" + this.getServiceIdentification().getServiceNameSpace() + "='"
					+ this.getServiceIdentification().getServiceSchemaUrl()
					+ this.getServiceIdentification().getServiceNameSpace() + "'";
			sb.append("<FeatureType " + nameSpace + ">");
			sb.append("<Name>").append(this.getServiceIdentification().getServiceNameSpace()).append(":")
					.append(this.getFeatureTypeName()).append("</Name>");
			sb.append("<Title>").append(this.getFeatureTypeName()).append("</Title>");
			sb.append("<Abstract>").append(this.getAbstraction()).append("</Abstract>");
			sb.append("<ows:Keywords>");
			for (String key : this.getKeywords().split("\\|")) {
				sb.append("<ows:Keyword>").append(key).append("</ows:Keyword>");
			}
			sb.append("</ows:Keywords>");
			sb.append("<DefaultCRS>")
					.append(CRS.toSRS(fs.getSchema().getGeometryDescriptor().getCoordinateReferenceSystem()))
					.append("</DefaultCRS>");
			sb.append("<ows:WGS84BoundingBox>");
			sb.append("<ows:LowerCorner>").append(wgs84Box.getMinX() + " " + wgs84Box.getMinY())
					.append("</ows:LowerCorner>");
			sb.append("<ows:UpperCorner>").append(wgs84Box.getMaxX() + " " + wgs84Box.getMaxY())
					.append("</ows:UpperCorner>");
			sb.append("</ows:WGS84BoundingBox>");
			sb.append("</FeatureType>");
			log.debug(sb.toString());
			return sb.toString();
		} catch (Exception ex) {
			throw new IOException(ex);
		}
	}

	public Map<String, Object> getDataStoreParameters() {
		return dataStoreParameters;
	}

	public void setDataStoreParameters(Map<String, Object> dataStoreParameters) {
		this.dataStoreParameters = dataStoreParameters;
	}

	public String getFeatureTypeName() {
		return featureTypeName;
	}

	public void setFeatureTypeName(String featureTypeName) {
		this.featureTypeName = featureTypeName;
	}

//	public String getFeatureTitle() {
//		return featureTitle;
//	}
//
//	public void setFeatureTitle(String featureTitle) {
//		this.featureTitle = featureTitle;
//	}

	public String getAbstraction() {
		return abstraction;
	}

	public void setAbstraction(String abstraction) {
		this.abstraction = abstraction;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Autowired
	public void setServiceIdentification(ServiceIdentification serviceIdentification) {
		this.serviceIdentification = serviceIdentification;
	}

	public ServiceIdentification getServiceIdentification() {
		return serviceIdentification;
	}

}
