<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType ="text/html; charset=utf-8" session="true" %>
<%@ include file="header.jsp" %>
<%@ include file="util.jsp" %>
<%
WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(application);
String appPath = request.getContextPath();
FeatureSourceCollection featureSourceCollection = springContext.getBean(FeatureSourceCollection.class);

String prot = request.isSecure() ? "https": "http";
String host = request.getServerName();
String path = request.getContextPath();
int port = request.getServerPort(); 

String binding = (String)request.getParameter("binding");
String operation = (String)request.getParameter("operation");
String dataset = (String)request.getParameter("dataset");
String userName = "user1";
String password = "123456";
%>
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
	<link rel="icon" href="images/asu-fork.png?v=4" type="image/png" />
</head>
<body>

<div style="text-align:center">
	<div class="logo"><img src="./images/asu.png" style="width:4em;"></div>
	<div class="title"><h1 style="padding-top:0.2em;padding-right:5em">WFS SOAP TEST</h1></div>
</div>

<div class="row">
	<div class=catalog>WSDL Catalog</div><a href="<%=prot%>://<%=host%>:<%=port%><%=path%>/ows" target="_blank"> 
		<%=prot%>://<%=host%>:<%=port%><%=path%>/ows
	</a>
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
	<div class="catalog">&nbsp;</div> 
	<div class="option">
		<input type='radio' class="binding" name="binding" id="soap11-sec" value="soap11-sec"
			<%=binding.equals("soap11-sec")? "checked='checked'" : "" %> >
		<label for="soap11-sec"> Secured SOAP1.1 (Plain password)</label>
	</div>		
	<div class="option">
		<input type='radio' class="binding" name="binding" id="soap12-sec" value="soap12-sec" 
			<%=binding.equals("soap12-sec")? "checked='checked'" : "" %> >
		<label for="soap12-sec"> Secured SOAP1.2 (Password digest)</label>
	</div>
	<div class="option">
		<input type='radio' class="binding" name="binding" id="soap12-encrypt" value="soap12-encrypt"
			<%=binding.equals("soap12-encrypt")? "checked='checked'" : "" %> >
		<label for="soap12-encrypt"> Secured SOAP1.2 (Encrypt)</label>
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
	<div class="option">
		<input type='radio' class="operation" name="operation" id="getFeature" value="getFeature" 
			<%=operation.equals("getFeature")? "checked='checked'" : "" %> >
		<label for="getFeature"> GetFeature</label>
	</div>
</div>
<div class="row">
	<div class="catalog">&nbsp;</div>
	<div class="option">
		<input type='radio' class="operation" name="operation" id="getFeature_BBox" value="getFeature_BBox" 
			<%=operation.equals("getFeature_BBox")? "checked='checked'" : "" %> >
		<label for="getFeature_BBox"> getFeature_BBox</label>
	</div>
	<div class="option">
		<input type='radio' class="operation" name="operation" id="getFeature_Intersect" value="getFeature_Intersect" 
			<%=operation.equals("getFeature_Intersect")? "checked='checked'" : "" %> >
		<label for="getFeature_Intersect"> getFeature_Intersect</label>
	</div>	
	<div class="option">
		<input type='radio' class="operation" name="operation" id="getFeature_Between" value="getFeature_Between" 
			<%=operation.equals("getFeature_Between")? "checked='checked'" : "" %> >
		<label for="getFeature_Between"> getFeature_Between</label>
	</div>	
	<div class="option">
		<input type='radio' class="operation" name="operation" id="getFeature_Equal" value="getFeature_Equal" 
			<%=operation.equals("getFeature_Equal")? "checked='checked'" : "" %> >
		<label for="getFeature_Equal"> getFeature_Equal</label>
	</div>		
</div>
<div class="row">
	<div class="catalog">Data sources</div>
	<%
	for (FeatureSourceFactory sfs : featureSourceCollection.getFeatureSourceFactories()) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class='option'>");
		sb.append("<input type='radio' class='dataset' name='dataset' ");
		sb.append("id='").append(sfs.getFeatureTypeName()).append("' value='").append(sfs.getFeatureTypeName()).append("' ");
		sb.append(dataset.equals(sfs.getFeatureTypeName())? "checked='checked'" : "");
		sb.append(">");
		sb.append("<label for='").append(sfs.getFeatureTypeName()).append("'> ").append(sfs.getFeatureTypeName()).append("</label>");
		sb.append("</div>");
		out.print(sb.toString());
	}	
	%>
</div>
<br/><br/>
<div class="row">
	<div style="float:left;width:45%;">
		<%
		StringBuffer sb = new StringBuffer();
		if( binding.startsWith("soap11") ){
			sb.append("<soap:Envelope xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/' ");
			//sb.append("xmlns:xsd='http://www.w3.org/2001/XMLSchema' ");
			//sb.append("xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>");
			sb.append(">");
			if (binding.endsWith("sec")){
				sb.append("<soap:Header>");
				/**/
				sb.append("<wsse:Security xmlns:wsse='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd' "
						+ "xmlns:wsu='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd' "
						+ " soap:mustUnderstand='1'>");
				sb.append("<wsse:UsernameToken wsu:Id='UsernameToken-a612a4ab-667a-4774-bc49-8c6c5833ebc1'>");
				sb.append("<wsse:Username>user1</wsse:Username>");
				sb.append("<wsse:Password Type='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText'>123456</wsse:Password>");
				sb.append("</wsse:UsernameToken>");
				sb.append("</wsse:Security>");				
				//sb.append(SecurityHeaderHelper.create(userName, password));
				sb.append("</soap:Header>");
			}else {
				sb.append("<soap:Head></soap:Head>");
			}
			sb.append("<soap:Body>");
			sb.append(soapRequest);
			sb.append("</soap:Body>");
			sb.append("</soap:Envelope>");
		} else if (binding.startsWith("soap12") && binding.indexOf("encrypt") < 0) {
			sb.append("<soap:Envelope xmlns:soap='http://www.w3.org/2003/05/soap-envelope' ");
			//sb.append("xmlns:xsd='http://www.w3.org/2001/XMLSchema' ");
			//sb.append("xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>");
			sb.append(">");
			if (binding.endsWith("sec")){
				sb.append("<soap:Header>");
				sb.append(SecurityHeaderHelper.create(userName, password));
				sb.append("</soap:Header>");
			} else {
				sb.append("<soap:Head></soap:Head>");
			}
			sb.append("<soap:Body>");
			sb.append(soapRequest);
			sb.append("</soap:Body>");
			sb.append("</soap:Envelope>");		
		} else if (binding.indexOf("encrypt") > 0) {
			EncryptHelper helper = new EncryptHelper();
			helper.encryptSoap("user1", soapRequest);
			sb.append(helper.getEncyptRequest());
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