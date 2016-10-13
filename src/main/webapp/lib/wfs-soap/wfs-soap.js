function startup() {
	initOptioinButton();
    initSendButton();
	initClearButton();
	formatSoapRequest();
}

// ----------------------------------------------------
function initSendButton() {
    $("#sendButton").click(function(e){
    	var path = "";
    	var version = "";
    	if ("1.1.0" == getVersion()) {
    		version = "1.1.0/"
    	}
    	
    	var binding = getBinding();
    	if (binding == "soap12") {
    		path = "soap/1.2";
    	} else if (binding == "soap12mtom") {
    		path = "soap/1.2/mtom";
    	} else if (binding == "soap11") {
    		path = "soap/1.1";
    	} else if (binding == "soap11mtom") {
    		path = "soap/1.1/mtom";
    	} else if (binding == "post") {
    		path = "kvp"
    	} else if (binding == "soap12-sec") {
    		path = "soap/1.2/sec"
    	} else if (binding == "soap11-sec") {
    		path = "soap/1.1/sec"
    	} else if (binding == "soap12-encrypt") {
    		path = "soap/1.2/encrypt"
    	}   	
    	var soapMessage = $("#soapMessage").val();
		var endpoint = "/services/ows/wfs/" + version + path;
		var protocol = $("#sendButton").attr("protocol");
		var header = {'Accept':'text/plain, */*; q=0.01'};
        sendSoap(endpoint, soapMessage, header);
    });
}

function sendSoap(endpoint, soapMessage, header) {
	var content_type = null;
	if(endpoint.includes("soap/1.2")) {
		content_type = "application/soap+xml; charset=utf-8";
	} else {
		content_type = "application/xml; charset=utf-8";
	}
	
	$.ajax({
		url: endpoint, 
		type: "POST",
		dataType: "text", 
		data: soapMessage,
		processData: false,  
		contentType: content_type,
		headers: header,
		success: OnSuccess, 
		error: OnError
	});
}

function OnSuccess(data, status){
	if (getBinding().includes("mtom")) {
		$("#soapResponse").val(data);
	} else {
		var xml_pp = vkbeautify.xml(data);
		$("#soapResponse").val(xml_pp);
	}
}

function OnError(request, status, error){
	var xml_pp = vkbeautify.xml(request.responseText);
	$("#soapResponse").val(xml_pp);
}

function initClearButton() {
	$("#clearButton").click(function(e){
		$("#soapResponse").val("");
	});
}

function initOptioinButton() {
	$("input[type=radio]").change(function(e){
		var url = getOperation() + ".jsp?"
			+ "version=" + getVersion()
			+ "&binding=" + getBinding() 
			+ "&operation=" + getOperation()
			+ "&dataset=" + getDataset(); 
		window.location.href = url;
	});
}

function getVersion() {
	return $("input[name='version']:checked").val();
}

function getBinding() {
	return $("input[name='binding']:checked").val();
}

function getOperation() {
	return $("input[name='operation']:checked").val();
}

function getDataset() {
	return $("input[name='dataset']:checked").val();
}

function formatSoapRequest() {
	var xml = $("#soapMessage").val();
	xml = vkbeautify.xml(xml);
	$("#soapMessage").val(xml);
}

$(document).ready(function() {
    startup();
});
