package edu.asu.ows.secure;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;

import edu.asu.ows.Utils;
import edu.asu.ows.wfs.WfsPortType;
import net.opengis.wfs._2.GetCapabilitiesType;
import net.opengis.wfs._2.WFSCapabilitiesType;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class SecurityHeaderHelper {
	
	public static String create(String usernameStr, String passwordStr) throws NoSuchAlgorithmException, IOException {
		// From the spec: Password_Digest = Base64 ( SHA-1 ( nonce + created + password ) )
		// Make the nonce
		SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
		rand.setSeed(System.currentTimeMillis());
		byte[] nonceBytes = new byte[16];
		rand.nextBytes(nonceBytes);

		// Make the created date
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		String createdDate = df.format(Calendar.getInstance().getTime());
		byte[] createdDateBytes = createdDate.getBytes("UTF-8");

		Calendar expire = Calendar.getInstance();
		expire.add(Calendar.MINUTE, 10);
		String expireDate = df.format(expire.getTime());

		// Make the password
		byte[] passwordBytes = passwordStr.getBytes("UTF-8");

		// SHA-1 hash the bunch of it.
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(nonceBytes);
		baos.write(createdDateBytes);
		baos.write(passwordBytes);
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] digestedPassword = md.digest(baos.toByteArray());

		// Encode the password and nonce for sending
		String passwordB64 = (new BASE64Encoder()).encode(digestedPassword);
		String nonceB64 = (new BASE64Encoder()).encode(nonceBytes);

		StringBuffer sb = new StringBuffer();
		//sb.append("<soap:Envelope xmlns:soap='http://schemas.xmlsoap.org/soap/envelope/'>");
		//sb.append("<SOAP-ENV:Header xmlns:SOAP-ENV='http://schemas.xmlsoap.org/soap/envelope/'>");
		sb.append("<wsse:Security xmlns:wsse='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd' ");
		sb.append("xmlns:wsu='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd' soap:mustUnderstand='1'>");
		sb.append("<wsu:Timestamp wsu:Id='TS-" + UUID.randomUUID().toString() + "'>");
		sb.append("<wsu:Created>" + createdDate + "</wsu:Created>");
		sb.append("<wsu:Expires>" + expireDate + "</wsu:Expires>");
		sb.append("</wsu:Timestamp>");
		sb.append("<wsse:UsernameToken wsu:Id='UsernameToken-" + UUID.randomUUID().toString() + "'>");
		sb.append("<wsse:Username>" + usernameStr + "</wsse:Username>");
		sb.append("<wsse:Password Type='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest'>"
						+ passwordB64 + "</wsse:Password>");
		sb.append("<wsse:Nonce EncodingType='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary'>"
						+ nonceB64 + "</wsse:Nonce>");
		sb.append("<wsu:Created>" + createdDate + "</wsu:Created>");
		sb.append("</wsse:UsernameToken>");
		sb.append("</wsse:Security>");
		//sb.append("</SOAP-ENV:Header>");
		//sb.append("<soap:Body>");
		//sb.append(bodyXml);
		//sb.append("</soap:Body>");
		//sb.append("</soap:Envelope>");

		return sb.toString();
	}	
}
