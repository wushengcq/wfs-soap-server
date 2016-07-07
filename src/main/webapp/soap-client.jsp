<html>
<head>
	<style>		
	.example {float:left;width:30%;margin-right:20px}
	xmp {background-color:#eee;padding:10px}
	.logo {float:left;padding-left:2em;padding-top:1em}
	.row {display:block;clear:both;padding-left:2em;padding-top:0.5em;padding-bottom:0.5em}
	.catalog {float:left;display:inline;width:8.5em;font-weight:bold;}
	.option {float:left;display:inline;padding-right:2em;cursor:pointer}
	.binding {cursor:pointer}
	.operation {cursor:pointer}
	.funcbtn {font-size:16px;font-weight:bold;height:40px;width:80px;margin-top:80px;cursor:pointer}
	label {cursor:pointer}
	</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
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
		<input type='radio' class="binding" name="binding" id="soap12" value="soap12" checked="checked">
		<label for="soap12"> SOAP1.2</label>
	</div>
	<div class="option">
		<input type='radio' class="binding" name="binding" id="soap12mtom" value="soap12mtom">
		<label for="soap12mtom"> SOAP1.2 / MTOM</label>
	</div>	
	<div class="option">
		<input type='radio' class="binding" name="binding" id="soap11" value="soap11">
		<label for="soap11"> SOAP1.1</label>
	</div>
	<div class="option">
		<input type='radio' class="binding" name="binding" id="soap11mtom" value="soap11mtom">
		<label for="soap11mtom"> SOAP1.1 / MTOM</label>
	</div>	
	<div class="option">
		<input type='radio' class="binding" name="binding" id="post" value="post">
		<label for="post"> HTTP / POST</label>
	</div>
</div>

<div class="row">
	<div class="catalog">WFS Operation</div>
	<div class="option">
		<input type='radio' class="operation" name="operation" id="getCapabilities" value="getCapabilities" checked="checked">
		<label for="getCapabilities"> GetCapabilities</label>
	</div>
	<div class="option">
		<input type='radio' class="operation" name="operation" id="describeFeatureType" value="describeFeatureType" >
		<label for="describeFeatureType"> DescribeFeatureType</label>
	</div>
</div>
<br/><br/>
<div class="row">
	<div style="float:left;width:45%;">
		<textarea id="soapMessage" style="width:100%;height:400px;"></textarea>
	</div>
	<div style="float:left;margin-left:20px;">
		<input type="button" id="sendButton" value="SEND" class="funcbtn"></input><p/>
		<input type="button" id="clearButton" value="CLEAR" class="funcbtn"></input>
	</div>
	<div style="float:left;width:45%;margin-left:20px;">
		<textarea id="soapResponse" style="width:100%;height:400px;" readonly></textarea>
	</div>
</div>

<div style="display:block;clear:both">
<br>
<div style="text-align:center"><h1>Example Requests</h1></div>

<div class="operation">
<div><h2>* GetCapabilites</h2></div>
<div class="example">
<div><h4>SOAP 1.1</h4></div>
<xmp><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
  xmlns:xsd="http://www.w3.org/2001/XMLSchema"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <soap:Body>
    <wfs:GetCapabilities service="WFS"
        xmlns:wfs="http://www.opengis.net/wfs/2.0"
      	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      	xsi:schemaLocation="http://www.opengis.net/wfs/2.0
        http://schemas.opengis.net/wfs/2.0/wfs.xsd"/>
  </soap:Body>
</soap:Envelope>
</xmp>
</div>
<div class="example">
<div><h4>SOAP 1.2</h4></div>
<xmp><soap12:Envelope xmlns:soap12="http://www.w3.org/2003/05/soap-envelope"
  xmlns:xsd="http://www.w3.org/2001/XMLSchema"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <soap12:Body>
    <wfs:GetCapabilities service="WFS"
    xmlns:wfs="http://www.opengis.net/wfs/2.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.opengis.net/wfs/2.0
	http://schemas.opengis.net/wfs/2.0/wfs.xsd"/>
  </soap12:Body>
</soap12:Envelope>
</xmp>
</div>
<div class="example">
<div><h4>POST</h4></div>
<xmp><wfs:GetCapabilities service="WFS"
    xmlns:wfs="http://www.opengis.net/wfs/2.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.opengis.net/wfs/2.0
	http://schemas.opengis.net/wfs/2.0/wfs.xsd"/>
</xmp>
</div>
</div>

<div class="operation">
<div><h2>* DescribeFeatureType</h2></div>
<div class="example">
<div><h4>SOAP 1.1</h4></div>
<xmp><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
  xmlns:xsd="http://www.w3.org/2001/XMLSchema"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <soap:Body>
	<DescribeFeatureType service="WFS" 
	xmlns="http://www.opengis.net/wfs/2.0" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://www.opengis.net/wfs/2.0 
		http://schemas.opengis.net/wfs/2.0/wfs.xsd" >
	<TypeName>cb_2015_us_state_20m</TypeName>
	</DescribeFeatureType>
  </soap:Body>
</soap:Envelope>
</xmp>
</div>
<div class="example">
<div><h4>SOAP 1.2</h4></div>
<xmp><soap:Envelope xmlns:soap='http://www.w3.org/2003/05/soap-envelope'>
  <soap:Body>
	<DescribeFeatureType service="WFS" 
	xmlns="http://www.opengis.net/wfs/2.0" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://www.opengis.net/wfs/2.0 
		http://schemas.opengis.net/wfs/2.0/wfs.xsd" >
	<TypeName>cb_2015_us_state_20m</TypeName>
  </soap:Body>
</soap:Envelope>
</xmp>
</div>
<div class="example">
<div><h4>POST</h4></div>
<xmp><DescribeFeatureType service="WFS" 
	xmlns="http://www.opengis.net/wfs/2.0" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://www.opengis.net/wfs/2.0 
	http://schemas.opengis.net/wfs/2.0/wfs.xsd" >
	<TypeName>cb_2015_us_state_20m</TypeName>
</DescribeFeatureType>
</xmp>
</div>
</div>

</div>
<div style="height:200px;display:block;clear:both">&nbsp;</div>
<script src="./lib/jquery/jquery.min.js"></script>
<script src="./lib/util/vkbeautify.js"></script>
<script src="./lib/wfs-soap/wfs-soap.js"></script>
</body>
</html>