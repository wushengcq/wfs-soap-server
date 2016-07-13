package edu.asu.ows.wfs;

import java.io.IOException;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.asu.datastore.FeatureSourceCollection;
import edu.asu.ows.IOperation;
import edu.asu.ows.OperationGeneral;
import net.opengis.wfs._2.FeatureCollectionType;
import net.opengis.wfs._2.GetFeatureType;

@Component
public class GetFeature extends OperationGeneral implements IOperation {
	private Logger logger = Logger.getLogger(GetFeature.class);
	private FeatureSourceCollection featureSourceCollection = null;

	public FeatureCollectionType run(GetFeatureType request) throws IOException {
		request.getAbstractQueryExpression();
		FeatureSource<SimpleFeatureType, SimpleFeature> fs = featureSourceCollection.getFeatureSource("");
		fs.getFeatures(new Query());
		return null;
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
