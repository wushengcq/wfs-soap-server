package edu.asu.ows.wfs.v110;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;
import org.eclipse.emf.common.util.EList;
import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.geotools.feature.FeatureCollection;
import org.geotools.xml.Configuration;
import org.geotools.xml.Parser;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import edu.asu.datastore.FeatureSourceCollection;
import edu.asu.ows.IOperation;
import edu.asu.ows.Utils;
import edu.asu.ows.wfs.Constant;
import edu.asu.ows.wfs.GML21;
import edu.asu.ows.wfs.GML31;
import edu.asu.ows.wfs.OperationGeneral;
import net.opengis.wfs._1.FeatureCollectionType;
import net.opengis.wfs._1.GetFeatureType;

@Component
public class GetFeature extends OperationGeneral implements IOperation {
	private Logger logger = Logger.getLogger(GetFeature.class);
	private FeatureSourceCollection featureSourceCollection = null;

	public FeatureCollectionType run(GetFeatureType request) throws IOException, JAXBException, ParserConfigurationException, SAXException, DocumentException, DatatypeConfigurationException {
		String xml = Utils.marshalV2(GetFeatureType.class, request, Constant.NS_WFS110_URL, "GetFeature");
		logger.debug("version : " + request.getVersion());
		Query query = this.retrieveQuery(xml);
		FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = this.getFeatureSourceCollection().getFeatureSource(query.getTypeName());
		FeatureCollection<SimpleFeatureType, SimpleFeature> totalFeature = featureSource.getFeatures(query);
		int matched = totalFeature.size();
		logger.debug("matched collection size : " + matched);
		
		if (request.getMaxFeatures().intValue() > 0) {
			query.setMaxFeatures(request.getMaxFeatures().intValue());
			logger.debug("max features : " + query.getMaxFeatures());
		}		
		FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection = featureSource.getFeatures(query);
		logger.debug("feature collection size : " + featureCollection.size());

		String outputFormat = request.getOutputFormat();
		outputFormat = (outputFormat == null || outputFormat.isEmpty() ? "application/gml+xml; version=3.2" : outputFormat);
		outputFormat = outputFormat.toLowerCase().replaceAll(" ", "");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		if (outputFormat.contains("gml")) {
			if (outputFormat.contains("version=3") || outputFormat.contains("gml3")) {
				logger.debug("GML 3.1");
				GML31 gml31 = new GML31();
				gml31.setNamespace("testbed12", "http://gci.asu.edu/testbed12");
				gml31.encode(out, featureCollection, matched);				
			} else if (outputFormat.contains("version=2") || outputFormat.contains("gml2")) {
				logger.debug("GML 2.1");
				GML21 gml21 = new GML21();
				gml21.setNamespace("testbed12", "http://gci.asu.edu/testbed12");
				gml21.encode(out, featureCollection, matched);
			} else {
				throw new IOException("Failed to find response for output format " + outputFormat); 
			}
			
			String gml = new String(out.toByteArray(), "UTF-8");
			logger.debug(gml);
			out.close();			
			return Utils.unmarshal(net.opengis.wfs._1.FeatureCollectionType.class, gml); 
		} else {
			throw new IOException("Failed to find response for output format " + outputFormat);
		}		
	}
	
	@SuppressWarnings("unchecked")
	private Query retrieveQuery(String xml) throws ParserConfigurationException, SAXException, IOException, DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new ByteArrayInputStream(xml.getBytes()));
		Namespace wfsNamespace =  new Namespace("wfs", Constant.NS_WFS110_URL);
		Namespace filterNamespace =  new Namespace("ogc", Constant.NS_FILTER_OGC_URL);
		
		Element xmlQuery = doc.getRootElement().element(new QName("Query", wfsNamespace));
		String typeNames = xmlQuery.attributeValue("typeName");		
		logger.debug(typeNames);
				
		String[] properties = null;
		List<Element> xmlProperties = xmlQuery.elements(new QName("PropertyName", wfsNamespace));
		if (xmlProperties.size() > 0) {
			properties = new String[xmlProperties.size()];
			for (int i=0; i<xmlProperties.size(); i++) {
				properties[i] = xmlProperties.get(i).getText();
				logger.debug(properties[i]);
			}	
		}
		
		Filter filter = null;
		Element xmlFilter = xmlQuery.element(new QName("Filter", filterNamespace));		
		if (xmlFilter != null) {
			logger.debug(xmlFilter.asXML());
			Configuration configuration = new org.geotools.filter.v1_1.OGCConfiguration();
			//Configuration configuration = new org.geotools.filter.v2_0.FESConfiguration();
			Parser parser = new Parser( configuration );
			filter = (Filter) parser.parse(new ByteArrayInputStream(xmlFilter.asXML().getBytes()));
		}
		return new Query(typeNames, filter, properties);
	}
	
	@Override
	public String getCapabilities() {
		return super.buildCapabilities(this.getName());
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

	@Override
	protected String[] getAcceptFormats() {
		return new String[]{"application/gml+xml; version=3.2", "application/gml+xml; version=2.1"};
	}

	@Override
	protected String[] getAcceptVersions() {
		return new String[]{Constant.WFS_VERSION_2_0};
	}
	

}
