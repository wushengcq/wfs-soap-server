<%@ page language="java" pageEncoding="utf-8" %>
<%@ page import="org.apache.jasper.runtime.*" %>
<%@ page contentType ="text/html; charset=utf-8" session="true" %>
<%
String soapRequest = null;

if (request.getParameter("version").toLowerCase().equals("2.0.0")) {
	soapRequest = ""
		+ "<DescribeFeatureType service='WFS' "
		+ "version='2.0.0' "
		+ "xmlns='http://www.opengis.net/wfs/2.0' "
		+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
		+ "xsi:schemaLocation='http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd' >"
		+ "<TypeName>" + (String)request.getParameter("dataset") + "</TypeName>"
		+ "</DescribeFeatureType>";
} else if (request.getParameter("version").toLowerCase().equals("1.1.0")) {
	soapRequest = ""
		+ "<DescribeFeatureType service='WFS' "
		+ "version='1.1.0' "
		+ "xmlns='http://www.opengis.net/wfs' "
		+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
		+ "xsi:schemaLocation='http://www.opengis.net/wfs/2.0 http://schemas.opengis.net/wfs/2.0/wfs.xsd' >"
		+ "<TypeName>" + (String)request.getParameter("dataset") + "</TypeName>"
		+ "</DescribeFeatureType>";
}

%>
<%@ include file="operation-template.jsp"%>
