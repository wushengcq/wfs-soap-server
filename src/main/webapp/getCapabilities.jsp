<%@ page language="java" pageEncoding="utf-8" %>
<%@ page import="org.apache.jasper.runtime.*" %>
<%@ page contentType ="text/html; charset=utf-8" session="true" %>
<%
String soapRequest = ""
+ "<GetCapabilities service='WFS' "
+ "xmlns='http://www.opengis.net/wfs/2.0' "
+ "xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' "
+ "xsi:schemaLocation='http://www.opengis.net/wfs/2.0 "
+ "http://schemas.opengis.net/wfs/2.0/wfs.xsd'/>";

String binding = (String)request.getParameter("binding");
String operation = "getCapabilities";
%>
<%@ include file="operation-template.jsp"%>
