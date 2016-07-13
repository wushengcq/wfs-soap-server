<%@ page language="java" pageEncoding="utf-8" %>
<%@ page import="org.apache.jasper.runtime.*" %>
<%@ include file="header.jsp" %>
<%
FeatureSourceCollection fsc = WebApplicationContextUtils.getWebApplicationContext(application).getBean(FeatureSourceCollection.class);
String nextJSP = "/getCapabilities.jsp?binding=soap12&operation=getCapabilities&dataset=" 
				+ fsc.getFeatureSourceFactories().get(0).getFeatureTypeName();
RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
dispatcher.forward(request,response);
%>

