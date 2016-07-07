package edu.asu.datastore;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.geotools.data.FeatureSource;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.beans.factory.annotation.Autowired;
import edu.asu.ows.ICapabilities;
import edu.asu.ows.ServiceIdentification;


public class FeatureSourceCollection implements ICapabilities {
	private Logger log = Logger.getLogger(FeatureSourceCollection.class);
	private ServiceIdentification serviceIdentification = null;
	private List<FeatureSourceFactory> featureSourceFactories = null;

	public List<FeatureSourceFactory> getFeatureSourceFactories() {
		return featureSourceFactories;
	}

	public void setFeatureSourceFactories(List<FeatureSourceFactory> featureSourceFactories) {
		this.featureSourceFactories = featureSourceFactories;
	}

	public FeatureSource<SimpleFeatureType, SimpleFeature> getFeatureSource(String name) throws IOException {
		for (FeatureSourceFactory sfs : this.getFeatureSourceFactories()) {
			if (sfs.getFeatureTypeName().equals(name)) {
				return sfs.getFeatureSource();
			}
		}
		return null;
	}

	@Override
	public String getCapabilities() {
		StringBuffer sb = new StringBuffer();
		sb.append("<FeatureTypeList>");
		for (FeatureSourceFactory fsf : this.getFeatureSourceFactories()) {
			try {
				String capabilities = fsf.getCapabilities();
				sb.append(capabilities);
			} catch (IOException e) {
				log.error(e);
			}
		}
		sb.append("</FeatureTypeList>");
		return sb.toString();
	}

	@Autowired
	public void setServiceIdentification(ServiceIdentification serviceIdentification) {
		this.serviceIdentification = serviceIdentification;
	}

	public ServiceIdentification getServiceIdentification() {
		return serviceIdentification;
	}

}
