package edu.asu.datastore;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.junit.Test;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

public class ShapeFileDatastore {
	@Test
	public void load() throws IOException {
		File file = new File("/opt/data/us/cb_2015_us_state_20m/cb_2015_us_state_20m.shp");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", file.toURI().toURL());

		org.geotools.data.shapefile.ShapefileDataStore dataStore = (org.geotools.data.shapefile.ShapefileDataStore)DataStoreFinder.getDataStore(map);
		String typeName = dataStore.getTypeNames()[0];	
		
		
		FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
		Filter filter = Filter.INCLUDE; // ECQL.toFilter("BBOX(THE_GEOM,10,20,30,40)")
		FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
		try (FeatureIterator<SimpleFeature> features = collection.features()) {
			while (features.hasNext()) {
				SimpleFeature feature = features.next();
				System.out.print(feature.getID());
				System.out.print(": ");
				System.out.println(feature.getDefaultGeometryProperty().getValue());
			}
		}
	}
	
	@Test
	public void testPath() throws MalformedURLException {
		File f = new File("/opt/data/us/cb_2015_us_state_20m/cb_2015_us_state_20m.shp");
		System.out.println(f.toURI().toURL());
	}
}
