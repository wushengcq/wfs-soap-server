<html>
<head></head>
<body>
<div style="display:block">
<div style="float:left;width:45%;margin-right:20px">
<textarea id="soapMessage" style="width:100%;height:500px;">
<soap:Envelope xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>
	<soap:Header/>
	<soap:Body>
		<wfs:GetCapabilities service="WFS" 
			xmlns:wfs="http://www.opengis.net/wfs/2.0" 
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
			xsi:schemaLocation="http://www.opengis.net/wfs/2.0 
			http://schemas.opengis.net/wfs/2.0/wfs.xsd"/>
	</soap:Body>
</soap:Envelope>
</textarea>
</div>
<div style="float:left;margin-right:20px">
<input type="button" id="sendButton" value="Send"></input><p/>
<input type="button" id="clearButton" value="Clean"></input>
</div>
<div style="float:left;width:45%">
<textarea id="soapResponse" style="width:100%;height:500px;" readonly></textarea>
</div>
</div>

<script src="./lib/jquery/jquery.min.js"></script>
<script src="./lib/util/vkbeautify.js"></script>
<script src="./lib/wfs-soap/wfs-soap.js"></script>
</body>
</html>