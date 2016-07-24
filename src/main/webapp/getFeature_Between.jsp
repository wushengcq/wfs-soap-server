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
+ "<fes:PropertyIsBetween>"
+ "<fes:ValueReference>version</fes:ValueReference>"
+ "<fes:LowerBoundary><fes:Literal>5</fes:Literal></fes:LowerBoundary>"
+ "<fes:UpperBoundary><fes:Literal>10</fes:Literal></fes:UpperBoundary>"
+ "</fes:PropertyIsBetween>"
+ "</fes:Filter>"
+ "</wfs:Query>"
+ "</wfs:GetFeature>";
%>

<%@ include file="operation-template.jsp"%>
