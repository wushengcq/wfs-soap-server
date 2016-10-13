<%@ page language="java" pageEncoding="utf-8" %>
<%@ page import="org.apache.jasper.runtime.*" %>
<%@ page contentType ="text/html; charset=utf-8" session="true" %>
<%
String soapRequest = null;

if (request.getParameter("version").toLowerCase().equals("2.0.0")) {
	soapRequest = ""
	+ "<GetCapabilities service='WFS' "
	+ "xmlns='http://www.opengis.net/wfs/2.0' "
	+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
	+ "version='2.0.0' "
	+ "xsi:schemaLocation='http://www.opengis.net/wfs/2.0 "
	+ "http://schemas.opengis.net/wfs/2.0/wfs.xsd'/>";
} else if (request.getParameter("version").toLowerCase().equals("1.1.0")) {
	soapRequest = ""
	+ "<GetCapabilities service='WFS' "
	+ "xmlns='http://www.opengis.net/wfs' "
	+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
	+ "version='1.1.0' "
	+ "xsi:schemaLocation='http://www.opengis.net/wfs "
	+ "http://schemas.opengis.net/wfs/1.1.0/wfs.xsd'/>";
}

%>
<%@ include file="operation-template.jsp"%>
