var _current_protocol = "getCapabilities";

function startup() {
    initSendButton();
	initClearButton();
}

// ----------------------------------------------------
function initSendButton() {
    $("#sendButton").click(function(e){
    	var soapMessage = $("#soapMessage").val();
		var endpoint = "http://localhost:8080/services/ows/wfs/soap12";
		var protocol = $("#sendButton").attr("protocol");
		var header = {'Accept':'text/plain, */*; q=0.01'};
        sendSoap(endpoint, soapMessage, header);
    });
}

function sendSoap(endpoint, soapMessage, header) {
	console.log(endpoint);
	console.log(soapMessage);
	$.ajax({
		url: endpoint, 
		type: "POST",
		dataType: "text", 
		data: soapMessage,
		processData: false,  
		contentType: "application/soap+xml; charset=utf-8",
		//contentType: "application/xml; charset=utf-8",
		headers: header,
		success: OnSuccess, 
		error: OnError
	});
}

function OnSuccess(data, status){
	var xml_pp = data;
	xml_pp = vkbeautify.xml(data);
	$("#soapResponse").val(xml_pp);
}

function OnError(request, status, error){
	console.log('error');
}

function initClearButton() {
	$("#clearButton").click(function(e){
		$("#soapResponse").val("");
	});
}

$(document).ready(function() {
    startup();
});
