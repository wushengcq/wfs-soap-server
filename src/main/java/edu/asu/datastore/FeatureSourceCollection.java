package edu.asu.datastore;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.geotools.data.FeatureSource;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import edu.asu.ows.ICapabilities;
import edu.asu.ows.ServiceIdentification;


public class FeatureSourceCollection implements ICapabilities {
	private Logger logger = Logger.getLogger(FeatureSourceCollection.class);
	private ServiceIdentification serviceIdentification = null;
	private List<FeatureSourceFactory> featureSourceFactories = null;
	private String searchPath = null;

	public List<FeatureSourceFactory> getFeatureSourceFactories() {
		if (this.featureSourceFactories == null) {
			synchronized (this) {
				if (this.featureSourceFactories == null) {
					try {
						this.featureSourceFactories = this.find();
					} catch (MalformedURLException e) {
						if (logger.isDebugEnabled()) {
							e.printStackTrace();
						}
					}
				}
			}
		}
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
	
	private List<FeatureSourceFactory> find() throws MalformedURLException {
		ArrayList<FeatureSourceFactory> result = new ArrayList<FeatureSourceFactory>();
		if (searchPath != null && (new File(this.getSearchPath())).exists()) {
			File[] files = (new File(this.getSearchPath())).listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".shp");
				}
			});
			for (File file : files) {
				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("url", file.toURI().toURL());
				FeatureSourceFactory fsf = new FeatureSourceFactory();
				int pos = file.getName().lastIndexOf(".");
				if (pos != -1) {
					fsf.setFeatureTypeName(file.getName().substring(0, pos));
					fsf.setKeywords("vector data,shapefile," + file.getName().substring(0, pos));
				} else {
					fsf.setFeatureTypeName(file.getName());
					fsf.setKeywords("vector data,shapefile," + file.getName());
				}
				fsf.setAbstraction("shapefile datasource " + file.getName());				
				fsf.setDataStoreParameters(parameters);
				fsf.setServiceIdentification(this.getServiceIdentification());
				result.add(fsf);
			}
		}
		return result;
	}

	@Override
	public String getCapabilities() {
		StringBuffer sb = new StringBuffer();
		sb.append("<FeatureTypeList>");
		for (FeatureSourceFactory fsf : this.getFeatureSourceFactories()) {
			String capabilities = fsf.getCapabilities();
			sb.append(capabilities);
		}
		sb.append("</FeatureTypeList>");
		return sb.toString();
	}

	@Autowired
	@Qualifier("wfsServiceIdentification")
	public void setServiceIdentification(ServiceIdentification serviceIdentification) {
		this.serviceIdentification = serviceIdentification;
	}
	public ServiceIdentification getServiceIdentification() {
		return serviceIdentification;
	}

	
	public void setSearchPath(String searchPath) {
		this.searchPath = searchPath;
	}
	public String getSearchPath() {
		return searchPath;
	}	

}
