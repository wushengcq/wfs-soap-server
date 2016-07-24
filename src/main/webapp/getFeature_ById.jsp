<%@ page language="java" pageEncoding="utf-8" %>
<%@ page import="org.apache.jasper.runtime.*" %>
<%@ page contentType ="text/html; charset=utf-8" session="true" %>
<%
String soapRequest = ""
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
+ "<fes:BBOX>"
+ "<fes:ValueReference>the_geom</fes:ValueReference>"
+ "<gml:Envelope srsName='http://www.opengis.net/gml/srs/epsg.xml#4326'>"
+ "<gml:lowerCorner>-80.102613 45.212597</gml:lowerCorner>"
+ "<gml:upperCorner>-100.361859 49.512517</gml:upperCorner>"
+ "</gml:Envelope>"
+ "</fes:BBOX>"
+ "</fes:Filter>"
+ "</wfs:Query>"
+ "</wfs:GetFeature>";

%>
<%@ include file="operation-template.jsp"%>
