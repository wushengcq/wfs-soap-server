<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType ="text/html; charset=utf-8" session="true" %>
<%@ include file="header.jsp" %>
<%@ include file="util.jsp" %>
<html>
<head>
	<style>		
	.logo {float:left;padding-left:2em;padding-top:1em}
	.row {display:block;clear:both;padding-left:2em;padding-top:0.5em;padding-bottom:0.5em}
	.catalog {float:left;display:inline;width:8.5em;font-weight:bold;}
	.option {float:left;display:inline;padding-right:2em;cursor:pointer}
	.binding {cursor:pointer}
	.operation {cursor:pointer}
	.funcbtn {font-size:16px;font-weight:bold;height:40px;width:80px;margin-top:80px;cursor:pointer}
	label {cursor:pointer}
	</style>
	<link rel="icon" href="asu-fork.png?v=4" type="image/png" />
</head>
<body>

<div style="text-align:center">
	<div class="logo"><img src="./images/asu.png" style="width:4em;"></div>
	<div class="title"><h1 style="padding-top:0.2em;padding-right:5em">WFS SOAP TEST</h1></div>
</div>

<div class="row">
	<div class=catalog>WSDL Catalog</div><a href="http://localhost:8080/services/ows" target="_blank"> http://localhost:8080/services/ows</a>
</div>

<div class="row">
	<div class="catalog">Binding Types</div> 
	<div class="option">
		<input type='radio' class="binding" name="binding" id="soap12" value="soap12" 
			<%=binding.equals("soap12")? "checked='checked'" : "" %> >
		<label for="soap12"> SOAP1.2</label>
	</div>
	<div class="option">
		<input type='radio' class="binding" name="binding" id="soap12mtom" value="soap12mtom"
			<%=binding.equals("soap12mtom")? "checked='checked'" : "" %> >
		<label for="soap12mtom"> SOAP1.2 / MTOM</label>
	</div>	
	<div class="option">
		<input type='radio' class="binding" name="binding" id="soap11" value="soap11"
			<%=binding.equals("soap11")? "checked='checked'" : "" %> >
		<label for="soap11"> SOAP1.1</label>
	</div>
	<div class="option">
		<input type='radio' class="binding" name="binding" id="soap11mtom" value="soap11mtom"
			<%=binding.equals("soap11mtom")? "checked='checked'" : "" %> >
		<label for="soap11mtom"> SOAP1.1 / MTOM</label>
	</div>	
	<div class="option">
		<input type='radio' class="binding" name="binding" id="post" value="post"
			<%=binding.equals("post")? "checked='checked'" : "" %> >
		<label for="post"> HTTP / POST</label>
	</div>
</div>

<div class="row">
	<div class="catalog">WFS Operation</div>
	<div class="option">
		<input type='radio' class="operation" name="operation" id="getCapabilities" value="getCapabilities" 
			<%=operation.equals("getCapabilities")? "checked='checked'" : "" %> >
		<label for="getCapabilities"> GetCapabilities</label>
	</div>
	<div class="option">
		<input type='radio' class="operation" name="operation" id="describeFeatureType" value="describeFeatureType" 
			<%=operation.equals("describeFeatureType")? "checked='checked'" : "" %> >
		<label for="describeFeatureType"> DescribeFeatureType</label>
	</div>
</div>
<br/><br/>
<div class="row">
	<div style="float:left;width:45%;">
		<%
		StringBuffer sb = new StringBuffer();
		if( binding.startsWith("soap11") ){
			sb.append("<soap:Envelope xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/' ");
			sb.append("xmlns:xsd='http://www.w3.org/2001/XMLSchema' ");
			sb.append("xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>");
			sb.append("<soap:Head></soap:Head>");
			sb.append("<soap:Body>");
			sb.append(soapRequest);
			sb.append("</soap:Body>");
			sb.append("</soap:Envelope>");
		} else if (binding.startsWith("soap12")) {
			sb.append("<soap:Envelope xmlns:soap='http://www.w3.org/2003/05/soap-envelope' ");
			sb.append("xmlns:xsd='http://www.w3.org/2001/XMLSchema' ");
			sb.append("xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>");
			sb.append("<soap:Head></soap:Head>");
			sb.append("<soap:Body>");
			sb.append(soapRequest);
			sb.append("</soap:Body>");
			sb.append("</soap:Envelope>");		
		} else {		
			sb.append(soapRequest);
		}
		%>	
		<textarea id="soapMessage" style="width:100%;height:400px;"><%=sb.toString()%></textarea>
	</div>
	<div style="float:left;margin-left:20px;">
		<input type="button" id="sendButton" value="SEND" class="funcbtn"></input><p/>
		<input type="button" id="clearButton" value="CLEAR" class="funcbtn"></input>
	</div>
	<div style="float:left;width:45%;margin-left:20px;">
		<textarea id="soapResponse" style="width:100%;height:400px;" readonly></textarea>
	</div>
</div>
<div style="height:200px;display:block;clear:both">&nbsp;</div>
<script src="./lib/jquery/jquery.min.js"></script>
<script src="./lib/util/vkbeautify.js"></script>
<script src="./lib/wfs-soap/wfs-soap.js"></script>
</body>
</html>