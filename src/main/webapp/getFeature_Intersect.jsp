<%@ page language="java" pageEncoding="utf-8" %>
<%@ page import="org.apache.jasper.runtime.*" %>
<%@ page contentType ="text/html; charset=utf-8" session="true" %>
<%
String soapRequest = "";

if (request.getParameter("version").toLowerCase().equals("2.0.0")) {
	soapRequest = ""
			+ "<wfs:GetFeature "
			+ "xmlns:wfs='http://www.opengis.net/wfs/2.0' "
			+ "xmlns:fes='http://www.opengis.net/fes/2.0' "
			+ "xmlns:gml='http://www.opengis.net/gml/3.2' "
			+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
			+ "xsi:schemaLocation='http://schemas.opengis.net/wfs/2.0/wfs.xsd http://schemas.opengis.net/gml/3.2.1/gml.xsd http://schemas.opengis.net/filter/2.0/filterAll.xsd' "	
			+ "service='WFS' "
			+ "version='2.0' "
			+ "outputFormat='application/gml+xml; version=3.2' "
			+ "count='3' "
			+ ">"
			+ "<wfs:Query typeNames='" + (String)request.getParameter("dataset") + "'>"
			+ "<fes:Filter>"
			+ "<fes:Intersects>"
			+ "<fes:ValueReference>the_geom</fes:ValueReference>"
			+ "<gml:Polygon srsName='http://www.opengis.net/def/crs/epsg/0/4326'>"
			+ "<gml:exterior><gml:LinearRing>"
			+ "<gml:posList dimension='2'>-122.44294 37.7411 -122.44294 37.7983 -122.40020 37.7983 -122.40020 37.7411 -122.44294 37.7411</gml:posList>"
			+ "</gml:LinearRing></gml:exterior></gml:Polygon>"
			+ "</fes:Intersects>"
			+ "</fes:Filter>"
			+ "</wfs:Query>"
			+ "</wfs:GetFeature>";	
} else if (request.getParameter("version").toLowerCase().equals("1.1.0")) {
	soapRequest = ""
		+ "<wfs:GetFeature "
		+ "xmlns:wfs='http://www.opengis.net/wfs' "
		+ "xmlns:ogc='http://www.opengis.net/ogc' "
		+ "xmlns:gml='http://www.opengis.net/gml' "
		+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
		+ "xsi:schemaLocation='http://schemas.opengis.net/wfs/1.1.0/wfs.xsd http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://schemas.opengis.net/filter/1.1.0/filter.xsd' "	
		+ "service='WFS' "
		+ "version='1.1.0' "
		+ "outputFormat='application/gml+xml; version=3.1' "
		+ "count='3' "
		+ ">"
		+ "<wfs:Query typeNames='" + (String)request.getParameter("dataset") + "'>"
		+ "<ogc:Filter>"
		+ "<ogc:Intersects>"
		+ "<ogc:PropertyName>the_geom</ogc:PropertyName>"
		+ "<gml:Polygon srsName='http://www.opengis.net/def/crs/epsg/0/4326'>"
		+ "<gml:exterior><gml:LinearRing>"
		+ "<gml:posList dimension='2'>-122.44294 37.7411 -122.44294 37.7983 -122.40020 37.7983 -122.40020 37.7411 -122.44294 37.7411</gml:posList>"
		+ "</gml:LinearRing></gml:exterior></gml:Polygon>"
		+ "</ogc:Intersects>"
		+ "</ogc:Filter>"
		+ "</wfs:Query>"
		+ "</wfs:GetFeature>";	
}
%>

<%@ include file="operation-template.jsp"%>
