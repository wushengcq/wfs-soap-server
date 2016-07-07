package edu.asu.ows;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Utils {
	
	public static String parameter(String name, Object[] values){
		StringBuffer sb = new StringBuffer();
		sb.append("<ows:Parameter name='" + name +  "'>");
		sb.append("<ows:AllowedValues>");
		for(Object value : values) {
			sb.append("<ows:Value>").append(value).append("</ows:Value>");
		}
		sb.append("</ows:AllowedValues>");
		sb.append("</ows:Parameter>");
		return sb.toString();
	}
	
	public static <T> T unmarshal(Class<T> clazz, String xml) {
		T t = JAXB.unmarshal(new StringReader(xml), clazz);
		return t;
	}
	
	public static <T> String marshal(Class<T> clazz, T obj) throws JAXBException {
		StringWriter out = new StringWriter();		
		JAXBContext context = JAXBContext.newInstance(clazz);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    m.marshal(obj, out);
	    return out.toString();
	}
}
