<%@ page language="java" pageEncoding="utf-8" %>
<%@ page import="org.apache.jasper.runtime.*" %>
<%@ page contentType ="text/html; charset=utf-8" session="true" %>
<%
String soapRequest = null;

if (request.getParameter("version").toLowerCase().equals("2.0.0")) {
	soapRequest = ""
		+ "<wfs:GetFeature "
		+ "service='WFS' "
		+ "version='2.0.0' "
		+ "xmlns:wfs='http://www.opengis.net/wfs/2.0' "
		+ "xmlns:fes='http://www.opengis.net/fes/2.0' "
		+ "xmlns:gml='http://www.opengis.net/gml/3.2' "
		+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
		+ "xsi:schemaLocation='http://schemas.opengis.net/wfs/2.0/wfs.xsd http://schemas.opengis.net/gml/3.2.1/gml.xsd http://schemas.opengis.net/filter/2.0/filterAll.xsd' "	
		+ "outputFormat='application/gml+xml; version=3.2' "
		+ "count='3' "
		+ ">"
		+ "<wfs:Query typeNames='" + (String)request.getParameter("dataset") + "'></wfs:Query>"
		+ "</wfs:GetFeature>";
} else if (request.getParameter("version").toLowerCase().equals("1.1.0")) {
	soapRequest = ""
		+ "<wfs:GetFeature "
		+ "service='WFS' "		
		+ "version='1.1.0' "	
		+ "outputFormat='application/gml+xml; version=3.1' "
		+ "maxFeatures='3' "		
		+ "xmlns:wfs='http://www.opengis.net/wfs' "
		+ "xmlns:ogc='http://www.opengis.net/ogc' "
		+ "xmlns:gml='http://www.opengis.net/gml' "
		+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
		+ "xsi:schemaLocation='http://schemas.opengis.net/wfs/1.1.0/wfs.xsd http://schemas.opengis.net/gml/3.1.1/base/gml.xsd http://schemas.opengis.net/filter/2.0/filterAll.xsd' "	
		+ ">"
		+ "<wfs:Query typeName='" + (String)request.getParameter("dataset") + "'></wfs:Query>"
		+ "</wfs:GetFeature>";
}

%>
<%@ include file="operation-template.jsp"%>
